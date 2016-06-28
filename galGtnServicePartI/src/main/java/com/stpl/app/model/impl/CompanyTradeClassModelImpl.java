package com.stpl.app.model.impl;

import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.model.CompanyTradeClassModel;

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
 * The base model implementation for the CompanyTradeClass service. Represents a row in the &quot;COMPANY_TRADE_CLASS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.CompanyTradeClassModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CompanyTradeClassImpl}.
 * </p>
 *
 * @author
 * @see CompanyTradeClassImpl
 * @see com.stpl.app.model.CompanyTradeClass
 * @see com.stpl.app.model.CompanyTradeClassModel
 * @generated
 */
public class CompanyTradeClassModelImpl extends BaseModelImpl<CompanyTradeClass>
    implements CompanyTradeClassModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a company trade class model instance should use the {@link com.stpl.app.model.CompanyTradeClass} interface instead.
     */
    public static final String TABLE_NAME = "COMPANY_TRADE_CLASS";
    public static final Object[][] TABLE_COLUMNS = {
            { "PRIOR_TRADE_CLASS", Types.INTEGER },
            { "COMPANY_TRADE_CLASS_SID", Types.INTEGER },
            { "LAST_UPDATED_DATE", Types.TIMESTAMP },
            { "PRIOR_TRADE_CLASS_START_DATE", Types.TIMESTAMP },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "TRADE_CLASS_END_DATE", Types.TIMESTAMP },
            { "TRADE_CLASS_START_DATE", Types.TIMESTAMP },
            { "RECORD_LOCK_STATUS", Types.BOOLEAN },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "SOURCE", Types.VARCHAR },
            { "CREATED_BY", Types.INTEGER },
            { "BATCH_ID", Types.VARCHAR },
            { "COMPANY_TRADE_CLASS", Types.INTEGER },
            { "MODIFIED_BY", Types.INTEGER },
            { "INBOUND_STATUS", Types.VARCHAR },
            { "COMPANY_MASTER_SID", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table COMPANY_TRADE_CLASS (PRIOR_TRADE_CLASS INTEGER,COMPANY_TRADE_CLASS_SID INTEGER not null primary key IDENTITY,LAST_UPDATED_DATE DATE null,PRIOR_TRADE_CLASS_START_DATE DATE null,MODIFIED_DATE DATE null,TRADE_CLASS_END_DATE DATE null,TRADE_CLASS_START_DATE DATE null,RECORD_LOCK_STATUS BOOLEAN,CREATED_DATE DATE null,SOURCE VARCHAR(75) null,CREATED_BY INTEGER,BATCH_ID VARCHAR(75) null,COMPANY_TRADE_CLASS INTEGER,MODIFIED_BY INTEGER,INBOUND_STATUS VARCHAR(75) null,COMPANY_MASTER_SID INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table COMPANY_TRADE_CLASS";
    public static final String ORDER_BY_JPQL = " ORDER BY companyTradeClass.companyTradeClassSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY COMPANY_TRADE_CLASS.COMPANY_TRADE_CLASS_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.CompanyTradeClass"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.CompanyTradeClass"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.CompanyTradeClass"));
    private static ClassLoader _classLoader = CompanyTradeClass.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            CompanyTradeClass.class
        };
    private int _priorTradeClass;
    private int _companyTradeClassSid;
    private Date _lastUpdatedDate;
    private Date _priorTradeClassStartDate;
    private Date _modifiedDate;
    private Date _tradeClassEndDate;
    private Date _tradeClassStartDate;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private String _batchId;
    private int _companyTradeClass;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _companyMasterSid;
    private CompanyTradeClass _escapedModel;

    public CompanyTradeClassModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _companyTradeClassSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyTradeClassSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyTradeClassSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyTradeClass.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyTradeClass.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("priorTradeClass", getPriorTradeClass());
        attributes.put("companyTradeClassSid", getCompanyTradeClassSid());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("batchId", getBatchId());
        attributes.put("companyTradeClass", getCompanyTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer priorTradeClass = (Integer) attributes.get("priorTradeClass");

        if (priorTradeClass != null) {
            setPriorTradeClass(priorTradeClass);
        }

        Integer companyTradeClassSid = (Integer) attributes.get(
                "companyTradeClassSid");

        if (companyTradeClassSid != null) {
            setCompanyTradeClassSid(companyTradeClassSid);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date priorTradeClassStartDate = (Date) attributes.get(
                "priorTradeClassStartDate");

        if (priorTradeClassStartDate != null) {
            setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        Date tradeClassStartDate = (Date) attributes.get("tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer companyTradeClass = (Integer) attributes.get(
                "companyTradeClass");

        if (companyTradeClass != null) {
            setCompanyTradeClass(companyTradeClass);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    @Override
    public int getPriorTradeClass() {
        return _priorTradeClass;
    }

    @Override
    public void setPriorTradeClass(int priorTradeClass) {
        _priorTradeClass = priorTradeClass;
    }

    @Override
    public int getCompanyTradeClassSid() {
        return _companyTradeClassSid;
    }

    @Override
    public void setCompanyTradeClassSid(int companyTradeClassSid) {
        _companyTradeClassSid = companyTradeClassSid;
    }

    @Override
    public Date getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public Date getPriorTradeClassStartDate() {
        return _priorTradeClassStartDate;
    }

    @Override
    public void setPriorTradeClassStartDate(Date priorTradeClassStartDate) {
        _priorTradeClassStartDate = priorTradeClassStartDate;
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
    public Date getTradeClassEndDate() {
        return _tradeClassEndDate;
    }

    @Override
    public void setTradeClassEndDate(Date tradeClassEndDate) {
        _tradeClassEndDate = tradeClassEndDate;
    }

    @Override
    public Date getTradeClassStartDate() {
        return _tradeClassStartDate;
    }

    @Override
    public void setTradeClassStartDate(Date tradeClassStartDate) {
        _tradeClassStartDate = tradeClassStartDate;
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
    public int getCompanyTradeClass() {
        return _companyTradeClass;
    }

    @Override
    public void setCompanyTradeClass(int companyTradeClass) {
        _companyTradeClass = companyTradeClass;
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
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    @Override
    public CompanyTradeClass toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (CompanyTradeClass) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        CompanyTradeClassImpl companyTradeClassImpl = new CompanyTradeClassImpl();

        companyTradeClassImpl.setPriorTradeClass(getPriorTradeClass());
        companyTradeClassImpl.setCompanyTradeClassSid(getCompanyTradeClassSid());
        companyTradeClassImpl.setLastUpdatedDate(getLastUpdatedDate());
        companyTradeClassImpl.setPriorTradeClassStartDate(getPriorTradeClassStartDate());
        companyTradeClassImpl.setModifiedDate(getModifiedDate());
        companyTradeClassImpl.setTradeClassEndDate(getTradeClassEndDate());
        companyTradeClassImpl.setTradeClassStartDate(getTradeClassStartDate());
        companyTradeClassImpl.setRecordLockStatus(getRecordLockStatus());
        companyTradeClassImpl.setCreatedDate(getCreatedDate());
        companyTradeClassImpl.setSource(getSource());
        companyTradeClassImpl.setCreatedBy(getCreatedBy());
        companyTradeClassImpl.setBatchId(getBatchId());
        companyTradeClassImpl.setCompanyTradeClass(getCompanyTradeClass());
        companyTradeClassImpl.setModifiedBy(getModifiedBy());
        companyTradeClassImpl.setInboundStatus(getInboundStatus());
        companyTradeClassImpl.setCompanyMasterSid(getCompanyMasterSid());

        companyTradeClassImpl.resetOriginalValues();

        return companyTradeClassImpl;
    }

    @Override
    public int compareTo(CompanyTradeClass companyTradeClass) {
        int primaryKey = companyTradeClass.getPrimaryKey();

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

        if (!(obj instanceof CompanyTradeClass)) {
            return false;
        }

        CompanyTradeClass companyTradeClass = (CompanyTradeClass) obj;

        int primaryKey = companyTradeClass.getPrimaryKey();

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
    public CacheModel<CompanyTradeClass> toCacheModel() {
        CompanyTradeClassCacheModel companyTradeClassCacheModel = new CompanyTradeClassCacheModel();

        companyTradeClassCacheModel.priorTradeClass = getPriorTradeClass();

        companyTradeClassCacheModel.companyTradeClassSid = getCompanyTradeClassSid();

        Date lastUpdatedDate = getLastUpdatedDate();

        if (lastUpdatedDate != null) {
            companyTradeClassCacheModel.lastUpdatedDate = lastUpdatedDate.getTime();
        } else {
            companyTradeClassCacheModel.lastUpdatedDate = Long.MIN_VALUE;
        }

        Date priorTradeClassStartDate = getPriorTradeClassStartDate();

        if (priorTradeClassStartDate != null) {
            companyTradeClassCacheModel.priorTradeClassStartDate = priorTradeClassStartDate.getTime();
        } else {
            companyTradeClassCacheModel.priorTradeClassStartDate = Long.MIN_VALUE;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            companyTradeClassCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            companyTradeClassCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        Date tradeClassEndDate = getTradeClassEndDate();

        if (tradeClassEndDate != null) {
            companyTradeClassCacheModel.tradeClassEndDate = tradeClassEndDate.getTime();
        } else {
            companyTradeClassCacheModel.tradeClassEndDate = Long.MIN_VALUE;
        }

        Date tradeClassStartDate = getTradeClassStartDate();

        if (tradeClassStartDate != null) {
            companyTradeClassCacheModel.tradeClassStartDate = tradeClassStartDate.getTime();
        } else {
            companyTradeClassCacheModel.tradeClassStartDate = Long.MIN_VALUE;
        }

        companyTradeClassCacheModel.recordLockStatus = getRecordLockStatus();

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            companyTradeClassCacheModel.createdDate = createdDate.getTime();
        } else {
            companyTradeClassCacheModel.createdDate = Long.MIN_VALUE;
        }

        companyTradeClassCacheModel.source = getSource();

        String source = companyTradeClassCacheModel.source;

        if ((source != null) && (source.length() == 0)) {
            companyTradeClassCacheModel.source = null;
        }

        companyTradeClassCacheModel.createdBy = getCreatedBy();

        companyTradeClassCacheModel.batchId = getBatchId();

        String batchId = companyTradeClassCacheModel.batchId;

        if ((batchId != null) && (batchId.length() == 0)) {
            companyTradeClassCacheModel.batchId = null;
        }

        companyTradeClassCacheModel.companyTradeClass = getCompanyTradeClass();

        companyTradeClassCacheModel.modifiedBy = getModifiedBy();

        companyTradeClassCacheModel.inboundStatus = getInboundStatus();

        String inboundStatus = companyTradeClassCacheModel.inboundStatus;

        if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
            companyTradeClassCacheModel.inboundStatus = null;
        }

        companyTradeClassCacheModel.companyMasterSid = getCompanyMasterSid();

        return companyTradeClassCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(33);

        sb.append("{priorTradeClass=");
        sb.append(getPriorTradeClass());
        sb.append(", companyTradeClassSid=");
        sb.append(getCompanyTradeClassSid());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", priorTradeClassStartDate=");
        sb.append(getPriorTradeClassStartDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", tradeClassEndDate=");
        sb.append(getTradeClassEndDate());
        sb.append(", tradeClassStartDate=");
        sb.append(getTradeClassStartDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", companyTradeClass=");
        sb.append(getCompanyTradeClass());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(52);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CompanyTradeClass");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>priorTradeClass</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyTradeClassSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyTradeClassSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorTradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassEndDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
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
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyTradeClass</column-name><column-value><![CDATA[");
        sb.append(getCompanyTradeClass());
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
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
