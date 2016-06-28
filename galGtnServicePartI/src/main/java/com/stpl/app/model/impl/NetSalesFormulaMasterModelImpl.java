package com.stpl.app.model.impl;

import com.stpl.app.model.NetSalesFormulaMaster;
import com.stpl.app.model.NetSalesFormulaMasterModel;

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
 * The base model implementation for the NetSalesFormulaMaster service. Represents a row in the &quot;NET_SALES_FORMULA_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.NetSalesFormulaMasterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NetSalesFormulaMasterImpl}.
 * </p>
 *
 * @author
 * @see NetSalesFormulaMasterImpl
 * @see com.stpl.app.model.NetSalesFormulaMaster
 * @see com.stpl.app.model.NetSalesFormulaMasterModel
 * @generated
 */
public class NetSalesFormulaMasterModelImpl extends BaseModelImpl<NetSalesFormulaMaster>
    implements NetSalesFormulaMasterModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a net sales formula master model instance should use the {@link com.stpl.app.model.NetSalesFormulaMaster} interface instead.
     */
    public static final String TABLE_NAME = "NET_SALES_FORMULA_MASTER";
    public static final Object[][] TABLE_COLUMNS = {
            { "CREATED_BY", Types.INTEGER },
            { "NET_SALES_FORMULA_MASTER_SID", Types.INTEGER },
            { "MODIFIED_BY", Types.INTEGER },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "NET_SALES_FORMULA_NAME", Types.VARCHAR },
            { "NET_SALES_FORMULA_TYPE", Types.INTEGER },
            { "NET_SALES_FORMULA_CATEGORY", Types.INTEGER },
            { "CONTRACT_SELECTION", Types.VARCHAR },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "RECORD_LOCK_STATUS", Types.BOOLEAN },
            { "SOURCE", Types.VARCHAR },
            { "NET_SALES_FORMULA_ID", Types.VARCHAR },
            { "NET_SALES_FORMULA_NO", Types.VARCHAR },
            { "INBOUND_STATUS", Types.VARCHAR },
            { "CDR_MODEL_SID", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table NET_SALES_FORMULA_MASTER (CREATED_BY INTEGER,NET_SALES_FORMULA_MASTER_SID INTEGER not null primary key IDENTITY,MODIFIED_BY INTEGER,CREATED_DATE DATE null,NET_SALES_FORMULA_NAME VARCHAR(75) null,NET_SALES_FORMULA_TYPE INTEGER,NET_SALES_FORMULA_CATEGORY INTEGER,CONTRACT_SELECTION VARCHAR(75) null,MODIFIED_DATE DATE null,RECORD_LOCK_STATUS BOOLEAN,SOURCE VARCHAR(75) null,NET_SALES_FORMULA_ID VARCHAR(75) null,NET_SALES_FORMULA_NO VARCHAR(75) null,INBOUND_STATUS VARCHAR(75) null,CDR_MODEL_SID VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table NET_SALES_FORMULA_MASTER";
    public static final String ORDER_BY_JPQL = " ORDER BY netSalesFormulaMaster.netSalesFormulaMasterSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY NET_SALES_FORMULA_MASTER.NET_SALES_FORMULA_MASTER_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.NetSalesFormulaMaster"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.NetSalesFormulaMaster"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.NetSalesFormulaMaster"));
    private static ClassLoader _classLoader = NetSalesFormulaMaster.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            NetSalesFormulaMaster.class
        };
    private int _createdBy;
    private int _netSalesFormulaMasterSid;
    private int _modifiedBy;
    private Date _createdDate;
    private String _netSalesFormulaName;
    private int _netSalesFormulaType;
    private int _netSalesFormulaCategory;
    private String _contractSelection;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private String _netSalesFormulaId;
    private String _netSalesFormulaNo;
    private String _inboundStatus;
    private String _cdrModelSid;
    private NetSalesFormulaMaster _escapedModel;

    public NetSalesFormulaMasterModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setNetSalesFormulaMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return NetSalesFormulaMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NetSalesFormulaMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("netSalesFormulaName", getNetSalesFormulaName());
        attributes.put("netSalesFormulaType", getNetSalesFormulaType());
        attributes.put("netSalesFormulaCategory", getNetSalesFormulaCategory());
        attributes.put("contractSelection", getContractSelection());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("netSalesFormulaId", getNetSalesFormulaId());
        attributes.put("netSalesFormulaNo", getNetSalesFormulaNo());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("cdrModelSid", getCdrModelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String netSalesFormulaName = (String) attributes.get(
                "netSalesFormulaName");

        if (netSalesFormulaName != null) {
            setNetSalesFormulaName(netSalesFormulaName);
        }

        Integer netSalesFormulaType = (Integer) attributes.get(
                "netSalesFormulaType");

        if (netSalesFormulaType != null) {
            setNetSalesFormulaType(netSalesFormulaType);
        }

        Integer netSalesFormulaCategory = (Integer) attributes.get(
                "netSalesFormulaCategory");

        if (netSalesFormulaCategory != null) {
            setNetSalesFormulaCategory(netSalesFormulaCategory);
        }

        String contractSelection = (String) attributes.get("contractSelection");

        if (contractSelection != null) {
            setContractSelection(contractSelection);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String netSalesFormulaId = (String) attributes.get("netSalesFormulaId");

        if (netSalesFormulaId != null) {
            setNetSalesFormulaId(netSalesFormulaId);
        }

        String netSalesFormulaNo = (String) attributes.get("netSalesFormulaNo");

        if (netSalesFormulaNo != null) {
            setNetSalesFormulaNo(netSalesFormulaNo);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String cdrModelSid = (String) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }
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
    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;
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
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    @Override
    public String getNetSalesFormulaName() {
        if (_netSalesFormulaName == null) {
            return StringPool.BLANK;
        } else {
            return _netSalesFormulaName;
        }
    }

    @Override
    public void setNetSalesFormulaName(String netSalesFormulaName) {
        _netSalesFormulaName = netSalesFormulaName;
    }

    @Override
    public int getNetSalesFormulaType() {
        return _netSalesFormulaType;
    }

    @Override
    public void setNetSalesFormulaType(int netSalesFormulaType) {
        _netSalesFormulaType = netSalesFormulaType;
    }

    @Override
    public int getNetSalesFormulaCategory() {
        return _netSalesFormulaCategory;
    }

    @Override
    public void setNetSalesFormulaCategory(int netSalesFormulaCategory) {
        _netSalesFormulaCategory = netSalesFormulaCategory;
    }

    @Override
    public String getContractSelection() {
        if (_contractSelection == null) {
            return StringPool.BLANK;
        } else {
            return _contractSelection;
        }
    }

    @Override
    public void setContractSelection(String contractSelection) {
        _contractSelection = contractSelection;
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
    public String getNetSalesFormulaId() {
        if (_netSalesFormulaId == null) {
            return StringPool.BLANK;
        } else {
            return _netSalesFormulaId;
        }
    }

    @Override
    public void setNetSalesFormulaId(String netSalesFormulaId) {
        _netSalesFormulaId = netSalesFormulaId;
    }

    @Override
    public String getNetSalesFormulaNo() {
        if (_netSalesFormulaNo == null) {
            return StringPool.BLANK;
        } else {
            return _netSalesFormulaNo;
        }
    }

    @Override
    public void setNetSalesFormulaNo(String netSalesFormulaNo) {
        _netSalesFormulaNo = netSalesFormulaNo;
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
    public String getCdrModelSid() {
        return _cdrModelSid;
    }

    @Override
    public void setCdrModelSid(String cdrModelSid) {
        _cdrModelSid = cdrModelSid;
    }

    @Override
    public NetSalesFormulaMaster toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (NetSalesFormulaMaster) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        NetSalesFormulaMasterImpl netSalesFormulaMasterImpl = new NetSalesFormulaMasterImpl();

        netSalesFormulaMasterImpl.setCreatedBy(getCreatedBy());
        netSalesFormulaMasterImpl.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        netSalesFormulaMasterImpl.setModifiedBy(getModifiedBy());
        netSalesFormulaMasterImpl.setCreatedDate(getCreatedDate());
        netSalesFormulaMasterImpl.setNetSalesFormulaName(getNetSalesFormulaName());
        netSalesFormulaMasterImpl.setNetSalesFormulaType(getNetSalesFormulaType());
        netSalesFormulaMasterImpl.setNetSalesFormulaCategory(getNetSalesFormulaCategory());
        netSalesFormulaMasterImpl.setContractSelection(getContractSelection());
        netSalesFormulaMasterImpl.setModifiedDate(getModifiedDate());
        netSalesFormulaMasterImpl.setRecordLockStatus(getRecordLockStatus());
        netSalesFormulaMasterImpl.setSource(getSource());
        netSalesFormulaMasterImpl.setNetSalesFormulaId(getNetSalesFormulaId());
        netSalesFormulaMasterImpl.setNetSalesFormulaNo(getNetSalesFormulaNo());
        netSalesFormulaMasterImpl.setInboundStatus(getInboundStatus());
        netSalesFormulaMasterImpl.setCdrModelSid(getCdrModelSid());

        netSalesFormulaMasterImpl.resetOriginalValues();

        return netSalesFormulaMasterImpl;
    }

    @Override
    public int compareTo(NetSalesFormulaMaster netSalesFormulaMaster) {
        int primaryKey = netSalesFormulaMaster.getPrimaryKey();

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

        if (!(obj instanceof NetSalesFormulaMaster)) {
            return false;
        }

        NetSalesFormulaMaster netSalesFormulaMaster = (NetSalesFormulaMaster) obj;

        int primaryKey = netSalesFormulaMaster.getPrimaryKey();

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
    public CacheModel<NetSalesFormulaMaster> toCacheModel() {
        NetSalesFormulaMasterCacheModel netSalesFormulaMasterCacheModel = new NetSalesFormulaMasterCacheModel();

        netSalesFormulaMasterCacheModel.createdBy = getCreatedBy();

        netSalesFormulaMasterCacheModel.netSalesFormulaMasterSid = getNetSalesFormulaMasterSid();

        netSalesFormulaMasterCacheModel.modifiedBy = getModifiedBy();

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            netSalesFormulaMasterCacheModel.createdDate = createdDate.getTime();
        } else {
            netSalesFormulaMasterCacheModel.createdDate = Long.MIN_VALUE;
        }

        netSalesFormulaMasterCacheModel.netSalesFormulaName = getNetSalesFormulaName();

        String netSalesFormulaName = netSalesFormulaMasterCacheModel.netSalesFormulaName;

        if ((netSalesFormulaName != null) &&
                (netSalesFormulaName.length() == 0)) {
            netSalesFormulaMasterCacheModel.netSalesFormulaName = null;
        }

        netSalesFormulaMasterCacheModel.netSalesFormulaType = getNetSalesFormulaType();

        netSalesFormulaMasterCacheModel.netSalesFormulaCategory = getNetSalesFormulaCategory();

        netSalesFormulaMasterCacheModel.contractSelection = getContractSelection();

        String contractSelection = netSalesFormulaMasterCacheModel.contractSelection;

        if ((contractSelection != null) && (contractSelection.length() == 0)) {
            netSalesFormulaMasterCacheModel.contractSelection = null;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            netSalesFormulaMasterCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            netSalesFormulaMasterCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        netSalesFormulaMasterCacheModel.recordLockStatus = getRecordLockStatus();

        netSalesFormulaMasterCacheModel.source = getSource();

        String source = netSalesFormulaMasterCacheModel.source;

        if ((source != null) && (source.length() == 0)) {
            netSalesFormulaMasterCacheModel.source = null;
        }

        netSalesFormulaMasterCacheModel.netSalesFormulaId = getNetSalesFormulaId();

        String netSalesFormulaId = netSalesFormulaMasterCacheModel.netSalesFormulaId;

        if ((netSalesFormulaId != null) && (netSalesFormulaId.length() == 0)) {
            netSalesFormulaMasterCacheModel.netSalesFormulaId = null;
        }

        netSalesFormulaMasterCacheModel.netSalesFormulaNo = getNetSalesFormulaNo();

        String netSalesFormulaNo = netSalesFormulaMasterCacheModel.netSalesFormulaNo;

        if ((netSalesFormulaNo != null) && (netSalesFormulaNo.length() == 0)) {
            netSalesFormulaMasterCacheModel.netSalesFormulaNo = null;
        }

        netSalesFormulaMasterCacheModel.inboundStatus = getInboundStatus();

        String inboundStatus = netSalesFormulaMasterCacheModel.inboundStatus;

        if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
            netSalesFormulaMasterCacheModel.inboundStatus = null;
        }

        netSalesFormulaMasterCacheModel.cdrModelSid = getCdrModelSid();

        String cdrModelSid = netSalesFormulaMasterCacheModel.cdrModelSid;

        if ((cdrModelSid != null) && (cdrModelSid.length() == 0)) {
            netSalesFormulaMasterCacheModel.cdrModelSid = null;
        }

        return netSalesFormulaMasterCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", netSalesFormulaName=");
        sb.append(getNetSalesFormulaName());
        sb.append(", netSalesFormulaType=");
        sb.append(getNetSalesFormulaType());
        sb.append(", netSalesFormulaCategory=");
        sb.append(getNetSalesFormulaCategory());
        sb.append(", contractSelection=");
        sb.append(getContractSelection());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", netSalesFormulaId=");
        sb.append(getNetSalesFormulaId());
        sb.append(", netSalesFormulaNo=");
        sb.append(getNetSalesFormulaNo());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NetSalesFormulaMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaName</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaType</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaCategory</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractSelection</column-name><column-value><![CDATA[");
        sb.append(getContractSelection());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
        sb.append(getCdrModelSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
