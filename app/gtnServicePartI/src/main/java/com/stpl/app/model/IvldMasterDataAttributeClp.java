package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldMasterDataAttributeLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class IvldMasterDataAttributeClp extends BaseModelImpl<IvldMasterDataAttribute>
    implements IvldMasterDataAttribute {
    private String _column19;
    private String _column18;
    private String _masterAttribute;
    private String _masterType;
    private String _dataAttributeIntfid;
    private Date _modifiedDate;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _addChgDelIndicator;
    private String _masterId;
    private String _column10;
    private String _column11;
    private String _errorCode;
    private String _column12;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _column13;
    private String _column14;
    private String _reprocessedFlag;
    private String _column15;
    private String _column16;
    private String _column17;
    private String _column4;
    private String _column3;
    private String _column2;
    private String _column1;
    private String _column8;
    private String _reasonForFailure;
    private String _column7;
    private String _column6;
    private String _column5;
    private String _column20;
    private String _batchId;
    private String _errorField;
    private String _column9;
    private int _ivldMasterDataAtbteSid;
    private boolean _checkRecord;
    private BaseModel<?> _ivldMasterDataAttributeRemoteModel;

    public IvldMasterDataAttributeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldMasterDataAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return IvldMasterDataAttribute.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldMasterDataAtbteSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldMasterDataAtbteSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldMasterDataAtbteSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("column19", getColumn19());
        attributes.put("column18", getColumn18());
        attributes.put("masterAttribute", getMasterAttribute());
        attributes.put("masterType", getMasterType());
        attributes.put("dataAttributeIntfid", getDataAttributeIntfid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("masterId", getMasterId());
        attributes.put("column10", getColumn10());
        attributes.put("column11", getColumn11());
        attributes.put("errorCode", getErrorCode());
        attributes.put("column12", getColumn12());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("column13", getColumn13());
        attributes.put("column14", getColumn14());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("column15", getColumn15());
        attributes.put("column16", getColumn16());
        attributes.put("column17", getColumn17());
        attributes.put("column4", getColumn4());
        attributes.put("column3", getColumn3());
        attributes.put("column2", getColumn2());
        attributes.put("column1", getColumn1());
        attributes.put("column8", getColumn8());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("column7", getColumn7());
        attributes.put("column6", getColumn6());
        attributes.put("column5", getColumn5());
        attributes.put("column20", getColumn20());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("column9", getColumn9());
        attributes.put("ivldMasterDataAtbteSid", getIvldMasterDataAtbteSid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String column19 = (String) attributes.get("column19");

        if (column19 != null) {
            setColumn19(column19);
        }

        String column18 = (String) attributes.get("column18");

        if (column18 != null) {
            setColumn18(column18);
        }

        String masterAttribute = (String) attributes.get("masterAttribute");

        if (masterAttribute != null) {
            setMasterAttribute(masterAttribute);
        }

        String masterType = (String) attributes.get("masterType");

        if (masterType != null) {
            setMasterType(masterType);
        }

        String dataAttributeIntfid = (String) attributes.get(
                "dataAttributeIntfid");

        if (dataAttributeIntfid != null) {
            setDataAttributeIntfid(dataAttributeIntfid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String masterId = (String) attributes.get("masterId");

        if (masterId != null) {
            setMasterId(masterId);
        }

        String column10 = (String) attributes.get("column10");

        if (column10 != null) {
            setColumn10(column10);
        }

        String column11 = (String) attributes.get("column11");

        if (column11 != null) {
            setColumn11(column11);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String column12 = (String) attributes.get("column12");

        if (column12 != null) {
            setColumn12(column12);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String column13 = (String) attributes.get("column13");

        if (column13 != null) {
            setColumn13(column13);
        }

        String column14 = (String) attributes.get("column14");

        if (column14 != null) {
            setColumn14(column14);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String column15 = (String) attributes.get("column15");

        if (column15 != null) {
            setColumn15(column15);
        }

        String column16 = (String) attributes.get("column16");

        if (column16 != null) {
            setColumn16(column16);
        }

        String column17 = (String) attributes.get("column17");

        if (column17 != null) {
            setColumn17(column17);
        }

        String column4 = (String) attributes.get("column4");

        if (column4 != null) {
            setColumn4(column4);
        }

        String column3 = (String) attributes.get("column3");

        if (column3 != null) {
            setColumn3(column3);
        }

        String column2 = (String) attributes.get("column2");

        if (column2 != null) {
            setColumn2(column2);
        }

        String column1 = (String) attributes.get("column1");

        if (column1 != null) {
            setColumn1(column1);
        }

        String column8 = (String) attributes.get("column8");

        if (column8 != null) {
            setColumn8(column8);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String column7 = (String) attributes.get("column7");

        if (column7 != null) {
            setColumn7(column7);
        }

        String column6 = (String) attributes.get("column6");

        if (column6 != null) {
            setColumn6(column6);
        }

        String column5 = (String) attributes.get("column5");

        if (column5 != null) {
            setColumn5(column5);
        }

        String column20 = (String) attributes.get("column20");

        if (column20 != null) {
            setColumn20(column20);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String column9 = (String) attributes.get("column9");

        if (column9 != null) {
            setColumn9(column9);
        }

        Integer ivldMasterDataAtbteSid = (Integer) attributes.get(
                "ivldMasterDataAtbteSid");

        if (ivldMasterDataAtbteSid != null) {
            setIvldMasterDataAtbteSid(ivldMasterDataAtbteSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getColumn19() {
        return _column19;
    }

    @Override
    public void setColumn19(String column19) {
        _column19 = column19;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn19", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column19);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn18() {
        return _column18;
    }

    @Override
    public void setColumn18(String column18) {
        _column18 = column18;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn18", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column18);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMasterAttribute() {
        return _masterAttribute;
    }

    @Override
    public void setMasterAttribute(String masterAttribute) {
        _masterAttribute = masterAttribute;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setMasterAttribute",
                        String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel,
                    masterAttribute);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMasterType() {
        return _masterType;
    }

    @Override
    public void setMasterType(String masterType) {
        _masterType = masterType;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setMasterType", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, masterType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDataAttributeIntfid() {
        return _dataAttributeIntfid;
    }

    @Override
    public void setDataAttributeIntfid(String dataAttributeIntfid) {
        _dataAttributeIntfid = dataAttributeIntfid;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setDataAttributeIntfid",
                        String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel,
                    dataAttributeIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel,
                    addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMasterId() {
        return _masterId;
    }

    @Override
    public void setMasterId(String masterId) {
        _masterId = masterId;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setMasterId", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, masterId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn10() {
        return _column10;
    }

    @Override
    public void setColumn10(String column10) {
        _column10 = column10;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn10", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column10);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn11() {
        return _column11;
    }

    @Override
    public void setColumn11(String column11) {
        _column11 = column11;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn11", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column11);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn12() {
        return _column12;
    }

    @Override
    public void setColumn12(String column12) {
        _column12 = column12;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn12", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column12);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel,
                    intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn13() {
        return _column13;
    }

    @Override
    public void setColumn13(String column13) {
        _column13 = column13;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn13", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column13);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn14() {
        return _column14;
    }

    @Override
    public void setColumn14(String column14) {
        _column14 = column14;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn14", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column14);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel,
                    reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn15() {
        return _column15;
    }

    @Override
    public void setColumn15(String column15) {
        _column15 = column15;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn15", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column15);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn16() {
        return _column16;
    }

    @Override
    public void setColumn16(String column16) {
        _column16 = column16;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn16", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column16);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn17() {
        return _column17;
    }

    @Override
    public void setColumn17(String column17) {
        _column17 = column17;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn17", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column17);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn4() {
        return _column4;
    }

    @Override
    public void setColumn4(String column4) {
        _column4 = column4;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn4", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn3() {
        return _column3;
    }

    @Override
    public void setColumn3(String column3) {
        _column3 = column3;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn3", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn2() {
        return _column2;
    }

    @Override
    public void setColumn2(String column2) {
        _column2 = column2;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn2", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn1() {
        return _column1;
    }

    @Override
    public void setColumn1(String column1) {
        _column1 = column1;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn1", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn8() {
        return _column8;
    }

    @Override
    public void setColumn8(String column8) {
        _column8 = column8;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn8", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column8);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel,
                    reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn7() {
        return _column7;
    }

    @Override
    public void setColumn7(String column7) {
        _column7 = column7;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn7", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column7);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn6() {
        return _column6;
    }

    @Override
    public void setColumn6(String column6) {
        _column6 = column6;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn6", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn5() {
        return _column5;
    }

    @Override
    public void setColumn5(String column5) {
        _column5 = column5;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn5", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn20() {
        return _column20;
    }

    @Override
    public void setColumn20(String column20) {
        _column20 = column20;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn20", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column20);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchId() {
        return _batchId;
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumn9() {
        return _column9;
    }

    @Override
    public void setColumn9(String column9) {
        _column9 = column9;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setColumn9", String.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, column9);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldMasterDataAtbteSid() {
        return _ivldMasterDataAtbteSid;
    }

    @Override
    public void setIvldMasterDataAtbteSid(int ivldMasterDataAtbteSid) {
        _ivldMasterDataAtbteSid = ivldMasterDataAtbteSid;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldMasterDataAtbteSid",
                        int.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel,
                    ivldMasterDataAtbteSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_ivldMasterDataAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldMasterDataAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldMasterDataAttributeRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldMasterDataAttributeRemoteModel() {
        return _ivldMasterDataAttributeRemoteModel;
    }

    public void setIvldMasterDataAttributeRemoteModel(
        BaseModel<?> ivldMasterDataAttributeRemoteModel) {
        _ivldMasterDataAttributeRemoteModel = ivldMasterDataAttributeRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _ivldMasterDataAttributeRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_ivldMasterDataAttributeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldMasterDataAttributeLocalServiceUtil.addIvldMasterDataAttribute(this);
        } else {
            IvldMasterDataAttributeLocalServiceUtil.updateIvldMasterDataAttribute(this);
        }
    }

    @Override
    public IvldMasterDataAttribute toEscapedModel() {
        return (IvldMasterDataAttribute) ProxyUtil.newProxyInstance(IvldMasterDataAttribute.class.getClassLoader(),
            new Class[] { IvldMasterDataAttribute.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldMasterDataAttributeClp clone = new IvldMasterDataAttributeClp();

        clone.setColumn19(getColumn19());
        clone.setColumn18(getColumn18());
        clone.setMasterAttribute(getMasterAttribute());
        clone.setMasterType(getMasterType());
        clone.setDataAttributeIntfid(getDataAttributeIntfid());
        clone.setModifiedDate(getModifiedDate());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setMasterId(getMasterId());
        clone.setColumn10(getColumn10());
        clone.setColumn11(getColumn11());
        clone.setErrorCode(getErrorCode());
        clone.setColumn12(getColumn12());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setColumn13(getColumn13());
        clone.setColumn14(getColumn14());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setColumn15(getColumn15());
        clone.setColumn16(getColumn16());
        clone.setColumn17(getColumn17());
        clone.setColumn4(getColumn4());
        clone.setColumn3(getColumn3());
        clone.setColumn2(getColumn2());
        clone.setColumn1(getColumn1());
        clone.setColumn8(getColumn8());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setColumn7(getColumn7());
        clone.setColumn6(getColumn6());
        clone.setColumn5(getColumn5());
        clone.setColumn20(getColumn20());
        clone.setBatchId(getBatchId());
        clone.setErrorField(getErrorField());
        clone.setColumn9(getColumn9());
        clone.setIvldMasterDataAtbteSid(getIvldMasterDataAtbteSid());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldMasterDataAttribute ivldMasterDataAttribute) {
        int primaryKey = ivldMasterDataAttribute.getPrimaryKey();

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

        if (!(obj instanceof IvldMasterDataAttributeClp)) {
            return false;
        }

        IvldMasterDataAttributeClp ivldMasterDataAttribute = (IvldMasterDataAttributeClp) obj;

        int primaryKey = ivldMasterDataAttribute.getPrimaryKey();

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
    public String toString() {
        StringBundler sb = new StringBundler(77);

        sb.append("{column19=");
        sb.append(getColumn19());
        sb.append(", column18=");
        sb.append(getColumn18());
        sb.append(", masterAttribute=");
        sb.append(getMasterAttribute());
        sb.append(", masterType=");
        sb.append(getMasterType());
        sb.append(", dataAttributeIntfid=");
        sb.append(getDataAttributeIntfid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", masterId=");
        sb.append(getMasterId());
        sb.append(", column10=");
        sb.append(getColumn10());
        sb.append(", column11=");
        sb.append(getColumn11());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", column12=");
        sb.append(getColumn12());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", column13=");
        sb.append(getColumn13());
        sb.append(", column14=");
        sb.append(getColumn14());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", column15=");
        sb.append(getColumn15());
        sb.append(", column16=");
        sb.append(getColumn16());
        sb.append(", column17=");
        sb.append(getColumn17());
        sb.append(", column4=");
        sb.append(getColumn4());
        sb.append(", column3=");
        sb.append(getColumn3());
        sb.append(", column2=");
        sb.append(getColumn2());
        sb.append(", column1=");
        sb.append(getColumn1());
        sb.append(", column8=");
        sb.append(getColumn8());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", column7=");
        sb.append(getColumn7());
        sb.append(", column6=");
        sb.append(getColumn6());
        sb.append(", column5=");
        sb.append(getColumn5());
        sb.append(", column20=");
        sb.append(getColumn20());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", column9=");
        sb.append(getColumn9());
        sb.append(", ivldMasterDataAtbteSid=");
        sb.append(getIvldMasterDataAtbteSid());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(118);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldMasterDataAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>column19</column-name><column-value><![CDATA[");
        sb.append(getColumn19());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column18</column-name><column-value><![CDATA[");
        sb.append(getColumn18());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>masterAttribute</column-name><column-value><![CDATA[");
        sb.append(getMasterAttribute());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>masterType</column-name><column-value><![CDATA[");
        sb.append(getMasterType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dataAttributeIntfid</column-name><column-value><![CDATA[");
        sb.append(getDataAttributeIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>masterId</column-name><column-value><![CDATA[");
        sb.append(getMasterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column10</column-name><column-value><![CDATA[");
        sb.append(getColumn10());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column11</column-name><column-value><![CDATA[");
        sb.append(getColumn11());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column12</column-name><column-value><![CDATA[");
        sb.append(getColumn12());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column13</column-name><column-value><![CDATA[");
        sb.append(getColumn13());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column14</column-name><column-value><![CDATA[");
        sb.append(getColumn14());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column15</column-name><column-value><![CDATA[");
        sb.append(getColumn15());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column16</column-name><column-value><![CDATA[");
        sb.append(getColumn16());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column17</column-name><column-value><![CDATA[");
        sb.append(getColumn17());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column4</column-name><column-value><![CDATA[");
        sb.append(getColumn4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column3</column-name><column-value><![CDATA[");
        sb.append(getColumn3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column2</column-name><column-value><![CDATA[");
        sb.append(getColumn2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column1</column-name><column-value><![CDATA[");
        sb.append(getColumn1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column8</column-name><column-value><![CDATA[");
        sb.append(getColumn8());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column7</column-name><column-value><![CDATA[");
        sb.append(getColumn7());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column6</column-name><column-value><![CDATA[");
        sb.append(getColumn6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column5</column-name><column-value><![CDATA[");
        sb.append(getColumn5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column20</column-name><column-value><![CDATA[");
        sb.append(getColumn20());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>column9</column-name><column-value><![CDATA[");
        sb.append(getColumn9());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldMasterDataAtbteSid</column-name><column-value><![CDATA[");
        sb.append(getIvldMasterDataAtbteSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
