package com.stpl.app.model.impl;

import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.model.DeductionCalendarMasterModel;

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
 * The base model implementation for the DeductionCalendarMaster service. Represents a row in the &quot;DEDUCTION_CALENDAR_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.DeductionCalendarMasterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DeductionCalendarMasterImpl}.
 * </p>
 *
 * @author
 * @see DeductionCalendarMasterImpl
 * @see com.stpl.app.model.DeductionCalendarMaster
 * @see com.stpl.app.model.DeductionCalendarMasterModel
 * @generated
 */
public class DeductionCalendarMasterModelImpl extends BaseModelImpl<DeductionCalendarMaster>
    implements DeductionCalendarMasterModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a deduction calendar master model instance should use the {@link com.stpl.app.model.DeductionCalendarMaster} interface instead.
     */
    public static final String TABLE_NAME = "DEDUCTION_CALENDAR_MASTER";
    public static final Object[][] TABLE_COLUMNS = {
            { "CREATED_BY", Types.INTEGER },
            { "DEDUCTION_CALENDAR_MASTER_SID", Types.INTEGER },
            { "DEDUCTION_CALENDAR_NO", Types.VARCHAR },
            { "MODIFIED_BY", Types.INTEGER },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "CATEGORY", Types.INTEGER },
            { "ADDITIONAL_NOTES", Types.VARCHAR },
            { "USER_ID", Types.INTEGER },
            { "DEDUCTION_CALENDAR_NAME", Types.VARCHAR },
            { "DEDUCTION_CALENDAR_DESC", Types.VARCHAR },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "INBOUND_STATUS", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table DEDUCTION_CALENDAR_MASTER (CREATED_BY INTEGER,DEDUCTION_CALENDAR_MASTER_SID INTEGER not null primary key IDENTITY,DEDUCTION_CALENDAR_NO VARCHAR(75) null,MODIFIED_BY INTEGER,CREATED_DATE DATE null,CATEGORY INTEGER,ADDITIONAL_NOTES VARCHAR(75) null,USER_ID INTEGER,DEDUCTION_CALENDAR_NAME VARCHAR(75) null,DEDUCTION_CALENDAR_DESC VARCHAR(75) null,MODIFIED_DATE DATE null,INBOUND_STATUS VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table DEDUCTION_CALENDAR_MASTER";
    public static final String ORDER_BY_JPQL = " ORDER BY deductionCalendarMaster.deductionCalendarMasterSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY DEDUCTION_CALENDAR_MASTER.DEDUCTION_CALENDAR_MASTER_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.DeductionCalendarMaster"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.DeductionCalendarMaster"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.DeductionCalendarMaster"));
    private static ClassLoader _classLoader = DeductionCalendarMaster.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            DeductionCalendarMaster.class
        };
    private int _createdBy;
    private int _deductionCalendarMasterSid;
    private String _deductionCalendarNo;
    private int _modifiedBy;
    private Date _createdDate;
    private int _category;
    private String _additionalNotes;
    private int _userId;
    private String _deductionCalendarName;
    private String _deductionCalendarDesc;
    private Date _modifiedDate;
    private String _inboundStatus;
    private DeductionCalendarMaster _escapedModel;

    public DeductionCalendarMasterModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setDeductionCalendarMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionCalendarMaster.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionCalendarMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("deductionCalendarNo", getDeductionCalendarNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("category", getCategory());
        attributes.put("additionalNotes", getAdditionalNotes());
        attributes.put("userId", getUserId());
        attributes.put("deductionCalendarName", getDeductionCalendarName());
        attributes.put("deductionCalendarDesc", getDeductionCalendarDesc());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        String deductionCalendarNo = (String) attributes.get(
                "deductionCalendarNo");

        if (deductionCalendarNo != null) {
            setDeductionCalendarNo(deductionCalendarNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer category = (Integer) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        String additionalNotes = (String) attributes.get("additionalNotes");

        if (additionalNotes != null) {
            setAdditionalNotes(additionalNotes);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String deductionCalendarName = (String) attributes.get(
                "deductionCalendarName");

        if (deductionCalendarName != null) {
            setDeductionCalendarName(deductionCalendarName);
        }

        String deductionCalendarDesc = (String) attributes.get(
                "deductionCalendarDesc");

        if (deductionCalendarDesc != null) {
            setDeductionCalendarDesc(deductionCalendarDesc);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
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
    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;
    }

    @Override
    public String getDeductionCalendarNo() {
        if (_deductionCalendarNo == null) {
            return StringPool.BLANK;
        } else {
            return _deductionCalendarNo;
        }
    }

    @Override
    public void setDeductionCalendarNo(String deductionCalendarNo) {
        _deductionCalendarNo = deductionCalendarNo;
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
    public int getCategory() {
        return _category;
    }

    @Override
    public void setCategory(int category) {
        _category = category;
    }

    @Override
    public String getAdditionalNotes() {
        if (_additionalNotes == null) {
            return StringPool.BLANK;
        } else {
            return _additionalNotes;
        }
    }

    @Override
    public void setAdditionalNotes(String additionalNotes) {
        _additionalNotes = additionalNotes;
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
    public String getDeductionCalendarName() {
        if (_deductionCalendarName == null) {
            return StringPool.BLANK;
        } else {
            return _deductionCalendarName;
        }
    }

    @Override
    public void setDeductionCalendarName(String deductionCalendarName) {
        _deductionCalendarName = deductionCalendarName;
    }

    @Override
    public String getDeductionCalendarDesc() {
        if (_deductionCalendarDesc == null) {
            return StringPool.BLANK;
        } else {
            return _deductionCalendarDesc;
        }
    }

    @Override
    public void setDeductionCalendarDesc(String deductionCalendarDesc) {
        _deductionCalendarDesc = deductionCalendarDesc;
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
    public DeductionCalendarMaster toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (DeductionCalendarMaster) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        DeductionCalendarMasterImpl deductionCalendarMasterImpl = new DeductionCalendarMasterImpl();

        deductionCalendarMasterImpl.setCreatedBy(getCreatedBy());
        deductionCalendarMasterImpl.setDeductionCalendarMasterSid(getDeductionCalendarMasterSid());
        deductionCalendarMasterImpl.setDeductionCalendarNo(getDeductionCalendarNo());
        deductionCalendarMasterImpl.setModifiedBy(getModifiedBy());
        deductionCalendarMasterImpl.setCreatedDate(getCreatedDate());
        deductionCalendarMasterImpl.setCategory(getCategory());
        deductionCalendarMasterImpl.setAdditionalNotes(getAdditionalNotes());
        deductionCalendarMasterImpl.setUserId(getUserId());
        deductionCalendarMasterImpl.setDeductionCalendarName(getDeductionCalendarName());
        deductionCalendarMasterImpl.setDeductionCalendarDesc(getDeductionCalendarDesc());
        deductionCalendarMasterImpl.setModifiedDate(getModifiedDate());
        deductionCalendarMasterImpl.setInboundStatus(getInboundStatus());

        deductionCalendarMasterImpl.resetOriginalValues();

        return deductionCalendarMasterImpl;
    }

    @Override
    public int compareTo(DeductionCalendarMaster deductionCalendarMaster) {
        int primaryKey = deductionCalendarMaster.getPrimaryKey();

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

        if (!(obj instanceof DeductionCalendarMaster)) {
            return false;
        }

        DeductionCalendarMaster deductionCalendarMaster = (DeductionCalendarMaster) obj;

        int primaryKey = deductionCalendarMaster.getPrimaryKey();

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
    public CacheModel<DeductionCalendarMaster> toCacheModel() {
        DeductionCalendarMasterCacheModel deductionCalendarMasterCacheModel = new DeductionCalendarMasterCacheModel();

        deductionCalendarMasterCacheModel.createdBy = getCreatedBy();

        deductionCalendarMasterCacheModel.deductionCalendarMasterSid = getDeductionCalendarMasterSid();

        deductionCalendarMasterCacheModel.deductionCalendarNo = getDeductionCalendarNo();

        String deductionCalendarNo = deductionCalendarMasterCacheModel.deductionCalendarNo;

        if ((deductionCalendarNo != null) &&
                (deductionCalendarNo.length() == 0)) {
            deductionCalendarMasterCacheModel.deductionCalendarNo = null;
        }

        deductionCalendarMasterCacheModel.modifiedBy = getModifiedBy();

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            deductionCalendarMasterCacheModel.createdDate = createdDate.getTime();
        } else {
            deductionCalendarMasterCacheModel.createdDate = Long.MIN_VALUE;
        }

        deductionCalendarMasterCacheModel.category = getCategory();

        deductionCalendarMasterCacheModel.additionalNotes = getAdditionalNotes();

        String additionalNotes = deductionCalendarMasterCacheModel.additionalNotes;

        if ((additionalNotes != null) && (additionalNotes.length() == 0)) {
            deductionCalendarMasterCacheModel.additionalNotes = null;
        }

        deductionCalendarMasterCacheModel.userId = getUserId();

        deductionCalendarMasterCacheModel.deductionCalendarName = getDeductionCalendarName();

        String deductionCalendarName = deductionCalendarMasterCacheModel.deductionCalendarName;

        if ((deductionCalendarName != null) &&
                (deductionCalendarName.length() == 0)) {
            deductionCalendarMasterCacheModel.deductionCalendarName = null;
        }

        deductionCalendarMasterCacheModel.deductionCalendarDesc = getDeductionCalendarDesc();

        String deductionCalendarDesc = deductionCalendarMasterCacheModel.deductionCalendarDesc;

        if ((deductionCalendarDesc != null) &&
                (deductionCalendarDesc.length() == 0)) {
            deductionCalendarMasterCacheModel.deductionCalendarDesc = null;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            deductionCalendarMasterCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            deductionCalendarMasterCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        deductionCalendarMasterCacheModel.inboundStatus = getInboundStatus();

        String inboundStatus = deductionCalendarMasterCacheModel.inboundStatus;

        if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
            deductionCalendarMasterCacheModel.inboundStatus = null;
        }

        return deductionCalendarMasterCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", deductionCalendarMasterSid=");
        sb.append(getDeductionCalendarMasterSid());
        sb.append(", deductionCalendarNo=");
        sb.append(getDeductionCalendarNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", category=");
        sb.append(getCategory());
        sb.append(", additionalNotes=");
        sb.append(getAdditionalNotes());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", deductionCalendarName=");
        sb.append(getDeductionCalendarName());
        sb.append(", deductionCalendarDesc=");
        sb.append(getDeductionCalendarDesc());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.DeductionCalendarMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarNo</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarNo());
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
            "<column><column-name>category</column-name><column-value><![CDATA[");
        sb.append(getCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>additionalNotes</column-name><column-value><![CDATA[");
        sb.append(getAdditionalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarName</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarDesc</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
