package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemHierarchyMasterLocalServiceUtil;

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


public class ItemHierarchyMasterClp extends BaseModelImpl<ItemHierarchyMaster>
    implements ItemHierarchyMaster {
    private String _level1Alias;
    private String _level11Alias;
    private Date _createdDate;
    private String _level8Alias;
    private String _level14Alias;
    private String _level5Alias;
    private int _createdBy;
    private String _level10Alias;
    private int _itemHierarchyMasterSid;
    private String _level17Alias;
    private String _level9Alias;
    private String _level0Alias;
    private Date _modifiedDate;
    private String _level13Alias;
    private String _source;
    private String _level6Alias;
    private String _gtnBrand;
    private int _modifiedBy;
    private String _level3Alias;
    private String _level16Alias;
    private String _batchId;
    private String _level19Alias;
    private String _level20;
    private String _level2Alias;
    private String _level20Alias;
    private String _gtnTherapeuticClass;
    private String _level7Alias;
    private String _level0;
    private String _level1;
    private String _level2;
    private String _level3;
    private String _level12Alias;
    private String _level8;
    private String _level11;
    private String _level4Alias;
    private String _level9;
    private String _level12;
    private String _level13;
    private String _level14;
    private boolean _recordLockStatus;
    private String _level0Tag;
    private String _level4;
    private String _level5;
    private String _level6;
    private String _level15Alias;
    private String _level7;
    private String _level10;
    private String _level19;
    private String _level15;
    private String _level16;
    private String _gtnCompany;
    private String _level17;
    private String _level18Alias;
    private String _level18;
    private String _inboundStatus;
    private BaseModel<?> _itemHierarchyMasterRemoteModel;

    public ItemHierarchyMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemHierarchyMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ItemHierarchyMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemHierarchyMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemHierarchyMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemHierarchyMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("level1Alias", getLevel1Alias());
        attributes.put("level11Alias", getLevel11Alias());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("level8Alias", getLevel8Alias());
        attributes.put("level14Alias", getLevel14Alias());
        attributes.put("level5Alias", getLevel5Alias());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("level10Alias", getLevel10Alias());
        attributes.put("itemHierarchyMasterSid", getItemHierarchyMasterSid());
        attributes.put("level17Alias", getLevel17Alias());
        attributes.put("level9Alias", getLevel9Alias());
        attributes.put("level0Alias", getLevel0Alias());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("level13Alias", getLevel13Alias());
        attributes.put("source", getSource());
        attributes.put("level6Alias", getLevel6Alias());
        attributes.put("gtnBrand", getGtnBrand());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("level3Alias", getLevel3Alias());
        attributes.put("level16Alias", getLevel16Alias());
        attributes.put("batchId", getBatchId());
        attributes.put("level19Alias", getLevel19Alias());
        attributes.put("level20", getLevel20());
        attributes.put("level2Alias", getLevel2Alias());
        attributes.put("level20Alias", getLevel20Alias());
        attributes.put("gtnTherapeuticClass", getGtnTherapeuticClass());
        attributes.put("level7Alias", getLevel7Alias());
        attributes.put("level0", getLevel0());
        attributes.put("level1", getLevel1());
        attributes.put("level2", getLevel2());
        attributes.put("level3", getLevel3());
        attributes.put("level12Alias", getLevel12Alias());
        attributes.put("level8", getLevel8());
        attributes.put("level11", getLevel11());
        attributes.put("level4Alias", getLevel4Alias());
        attributes.put("level9", getLevel9());
        attributes.put("level12", getLevel12());
        attributes.put("level13", getLevel13());
        attributes.put("level14", getLevel14());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("level0Tag", getLevel0Tag());
        attributes.put("level4", getLevel4());
        attributes.put("level5", getLevel5());
        attributes.put("level6", getLevel6());
        attributes.put("level15Alias", getLevel15Alias());
        attributes.put("level7", getLevel7());
        attributes.put("level10", getLevel10());
        attributes.put("level19", getLevel19());
        attributes.put("level15", getLevel15());
        attributes.put("level16", getLevel16());
        attributes.put("gtnCompany", getGtnCompany());
        attributes.put("level17", getLevel17());
        attributes.put("level18Alias", getLevel18Alias());
        attributes.put("level18", getLevel18());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String level1Alias = (String) attributes.get("level1Alias");

        if (level1Alias != null) {
            setLevel1Alias(level1Alias);
        }

        String level11Alias = (String) attributes.get("level11Alias");

        if (level11Alias != null) {
            setLevel11Alias(level11Alias);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String level8Alias = (String) attributes.get("level8Alias");

        if (level8Alias != null) {
            setLevel8Alias(level8Alias);
        }

        String level14Alias = (String) attributes.get("level14Alias");

        if (level14Alias != null) {
            setLevel14Alias(level14Alias);
        }

        String level5Alias = (String) attributes.get("level5Alias");

        if (level5Alias != null) {
            setLevel5Alias(level5Alias);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String level10Alias = (String) attributes.get("level10Alias");

        if (level10Alias != null) {
            setLevel10Alias(level10Alias);
        }

        Integer itemHierarchyMasterSid = (Integer) attributes.get(
                "itemHierarchyMasterSid");

        if (itemHierarchyMasterSid != null) {
            setItemHierarchyMasterSid(itemHierarchyMasterSid);
        }

        String level17Alias = (String) attributes.get("level17Alias");

        if (level17Alias != null) {
            setLevel17Alias(level17Alias);
        }

        String level9Alias = (String) attributes.get("level9Alias");

        if (level9Alias != null) {
            setLevel9Alias(level9Alias);
        }

        String level0Alias = (String) attributes.get("level0Alias");

        if (level0Alias != null) {
            setLevel0Alias(level0Alias);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String level13Alias = (String) attributes.get("level13Alias");

        if (level13Alias != null) {
            setLevel13Alias(level13Alias);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String level6Alias = (String) attributes.get("level6Alias");

        if (level6Alias != null) {
            setLevel6Alias(level6Alias);
        }

        String gtnBrand = (String) attributes.get("gtnBrand");

        if (gtnBrand != null) {
            setGtnBrand(gtnBrand);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String level3Alias = (String) attributes.get("level3Alias");

        if (level3Alias != null) {
            setLevel3Alias(level3Alias);
        }

        String level16Alias = (String) attributes.get("level16Alias");

        if (level16Alias != null) {
            setLevel16Alias(level16Alias);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String level19Alias = (String) attributes.get("level19Alias");

        if (level19Alias != null) {
            setLevel19Alias(level19Alias);
        }

        String level20 = (String) attributes.get("level20");

        if (level20 != null) {
            setLevel20(level20);
        }

        String level2Alias = (String) attributes.get("level2Alias");

        if (level2Alias != null) {
            setLevel2Alias(level2Alias);
        }

        String level20Alias = (String) attributes.get("level20Alias");

        if (level20Alias != null) {
            setLevel20Alias(level20Alias);
        }

        String gtnTherapeuticClass = (String) attributes.get(
                "gtnTherapeuticClass");

        if (gtnTherapeuticClass != null) {
            setGtnTherapeuticClass(gtnTherapeuticClass);
        }

        String level7Alias = (String) attributes.get("level7Alias");

        if (level7Alias != null) {
            setLevel7Alias(level7Alias);
        }

        String level0 = (String) attributes.get("level0");

        if (level0 != null) {
            setLevel0(level0);
        }

        String level1 = (String) attributes.get("level1");

        if (level1 != null) {
            setLevel1(level1);
        }

        String level2 = (String) attributes.get("level2");

        if (level2 != null) {
            setLevel2(level2);
        }

        String level3 = (String) attributes.get("level3");

        if (level3 != null) {
            setLevel3(level3);
        }

        String level12Alias = (String) attributes.get("level12Alias");

        if (level12Alias != null) {
            setLevel12Alias(level12Alias);
        }

        String level8 = (String) attributes.get("level8");

        if (level8 != null) {
            setLevel8(level8);
        }

        String level11 = (String) attributes.get("level11");

        if (level11 != null) {
            setLevel11(level11);
        }

        String level4Alias = (String) attributes.get("level4Alias");

        if (level4Alias != null) {
            setLevel4Alias(level4Alias);
        }

        String level9 = (String) attributes.get("level9");

        if (level9 != null) {
            setLevel9(level9);
        }

        String level12 = (String) attributes.get("level12");

        if (level12 != null) {
            setLevel12(level12);
        }

        String level13 = (String) attributes.get("level13");

        if (level13 != null) {
            setLevel13(level13);
        }

        String level14 = (String) attributes.get("level14");

        if (level14 != null) {
            setLevel14(level14);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String level0Tag = (String) attributes.get("level0Tag");

        if (level0Tag != null) {
            setLevel0Tag(level0Tag);
        }

        String level4 = (String) attributes.get("level4");

        if (level4 != null) {
            setLevel4(level4);
        }

        String level5 = (String) attributes.get("level5");

        if (level5 != null) {
            setLevel5(level5);
        }

        String level6 = (String) attributes.get("level6");

        if (level6 != null) {
            setLevel6(level6);
        }

        String level15Alias = (String) attributes.get("level15Alias");

        if (level15Alias != null) {
            setLevel15Alias(level15Alias);
        }

        String level7 = (String) attributes.get("level7");

        if (level7 != null) {
            setLevel7(level7);
        }

        String level10 = (String) attributes.get("level10");

        if (level10 != null) {
            setLevel10(level10);
        }

        String level19 = (String) attributes.get("level19");

        if (level19 != null) {
            setLevel19(level19);
        }

        String level15 = (String) attributes.get("level15");

        if (level15 != null) {
            setLevel15(level15);
        }

        String level16 = (String) attributes.get("level16");

        if (level16 != null) {
            setLevel16(level16);
        }

        String gtnCompany = (String) attributes.get("gtnCompany");

        if (gtnCompany != null) {
            setGtnCompany(gtnCompany);
        }

        String level17 = (String) attributes.get("level17");

        if (level17 != null) {
            setLevel17(level17);
        }

        String level18Alias = (String) attributes.get("level18Alias");

        if (level18Alias != null) {
            setLevel18Alias(level18Alias);
        }

        String level18 = (String) attributes.get("level18");

        if (level18 != null) {
            setLevel18(level18);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public String getLevel1Alias() {
        return _level1Alias;
    }

    @Override
    public void setLevel1Alias(String level1Alias) {
        _level1Alias = level1Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel1Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level1Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel11Alias() {
        return _level11Alias;
    }

    @Override
    public void setLevel11Alias(String level11Alias) {
        _level11Alias = level11Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel11Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level11Alias);
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

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemHierarchyMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel8Alias() {
        return _level8Alias;
    }

    @Override
    public void setLevel8Alias(String level8Alias) {
        _level8Alias = level8Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel8Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level8Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel14Alias() {
        return _level14Alias;
    }

    @Override
    public void setLevel14Alias(String level14Alias) {
        _level14Alias = level14Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel14Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level14Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel5Alias() {
        return _level5Alias;
    }

    @Override
    public void setLevel5Alias(String level5Alias) {
        _level5Alias = level5Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel5Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level5Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemHierarchyMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel10Alias() {
        return _level10Alias;
    }

    @Override
    public void setLevel10Alias(String level10Alias) {
        _level10Alias = level10Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel10Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level10Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemHierarchyMasterSid() {
        return _itemHierarchyMasterSid;
    }

    @Override
    public void setItemHierarchyMasterSid(int itemHierarchyMasterSid) {
        _itemHierarchyMasterSid = itemHierarchyMasterSid;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemHierarchyMasterSid",
                        int.class);

                method.invoke(_itemHierarchyMasterRemoteModel,
                    itemHierarchyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel17Alias() {
        return _level17Alias;
    }

    @Override
    public void setLevel17Alias(String level17Alias) {
        _level17Alias = level17Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel17Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level17Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel9Alias() {
        return _level9Alias;
    }

    @Override
    public void setLevel9Alias(String level9Alias) {
        _level9Alias = level9Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel9Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level9Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel0Alias() {
        return _level0Alias;
    }

    @Override
    public void setLevel0Alias(String level0Alias) {
        _level0Alias = level0Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel0Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level0Alias);
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

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemHierarchyMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel13Alias() {
        return _level13Alias;
    }

    @Override
    public void setLevel13Alias(String level13Alias) {
        _level13Alias = level13Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel13Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level13Alias);
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

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel6Alias() {
        return _level6Alias;
    }

    @Override
    public void setLevel6Alias(String level6Alias) {
        _level6Alias = level6Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel6Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level6Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGtnBrand() {
        return _gtnBrand;
    }

    @Override
    public void setGtnBrand(String gtnBrand) {
        _gtnBrand = gtnBrand;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGtnBrand", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, gtnBrand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemHierarchyMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel3Alias() {
        return _level3Alias;
    }

    @Override
    public void setLevel3Alias(String level3Alias) {
        _level3Alias = level3Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel3Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level3Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel16Alias() {
        return _level16Alias;
    }

    @Override
    public void setLevel16Alias(String level16Alias) {
        _level16Alias = level16Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel16Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level16Alias);
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

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel19Alias() {
        return _level19Alias;
    }

    @Override
    public void setLevel19Alias(String level19Alias) {
        _level19Alias = level19Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel19Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level19Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel20() {
        return _level20;
    }

    @Override
    public void setLevel20(String level20) {
        _level20 = level20;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel20", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level20);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel2Alias() {
        return _level2Alias;
    }

    @Override
    public void setLevel2Alias(String level2Alias) {
        _level2Alias = level2Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel2Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level2Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel20Alias() {
        return _level20Alias;
    }

    @Override
    public void setLevel20Alias(String level20Alias) {
        _level20Alias = level20Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel20Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level20Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGtnTherapeuticClass() {
        return _gtnTherapeuticClass;
    }

    @Override
    public void setGtnTherapeuticClass(String gtnTherapeuticClass) {
        _gtnTherapeuticClass = gtnTherapeuticClass;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGtnTherapeuticClass",
                        String.class);

                method.invoke(_itemHierarchyMasterRemoteModel,
                    gtnTherapeuticClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel7Alias() {
        return _level7Alias;
    }

    @Override
    public void setLevel7Alias(String level7Alias) {
        _level7Alias = level7Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel7Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level7Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel0() {
        return _level0;
    }

    @Override
    public void setLevel0(String level0) {
        _level0 = level0;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel0", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level0);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel1() {
        return _level1;
    }

    @Override
    public void setLevel1(String level1) {
        _level1 = level1;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel1", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel2() {
        return _level2;
    }

    @Override
    public void setLevel2(String level2) {
        _level2 = level2;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel2", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel3() {
        return _level3;
    }

    @Override
    public void setLevel3(String level3) {
        _level3 = level3;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel3", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel12Alias() {
        return _level12Alias;
    }

    @Override
    public void setLevel12Alias(String level12Alias) {
        _level12Alias = level12Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel12Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level12Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel8() {
        return _level8;
    }

    @Override
    public void setLevel8(String level8) {
        _level8 = level8;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel8", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level8);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel11() {
        return _level11;
    }

    @Override
    public void setLevel11(String level11) {
        _level11 = level11;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel11", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level11);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel4Alias() {
        return _level4Alias;
    }

    @Override
    public void setLevel4Alias(String level4Alias) {
        _level4Alias = level4Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel4Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level4Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel9() {
        return _level9;
    }

    @Override
    public void setLevel9(String level9) {
        _level9 = level9;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel9", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level9);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel12() {
        return _level12;
    }

    @Override
    public void setLevel12(String level12) {
        _level12 = level12;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel12", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level12);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel13() {
        return _level13;
    }

    @Override
    public void setLevel13(String level13) {
        _level13 = level13;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel13", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level13);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel14() {
        return _level14;
    }

    @Override
    public void setLevel14(String level14) {
        _level14 = level14;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel14", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level14);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
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

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_itemHierarchyMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel0Tag() {
        return _level0Tag;
    }

    @Override
    public void setLevel0Tag(String level0Tag) {
        _level0Tag = level0Tag;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel0Tag", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level0Tag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel4() {
        return _level4;
    }

    @Override
    public void setLevel4(String level4) {
        _level4 = level4;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel4", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel5() {
        return _level5;
    }

    @Override
    public void setLevel5(String level5) {
        _level5 = level5;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel5", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel6() {
        return _level6;
    }

    @Override
    public void setLevel6(String level6) {
        _level6 = level6;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel6", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel15Alias() {
        return _level15Alias;
    }

    @Override
    public void setLevel15Alias(String level15Alias) {
        _level15Alias = level15Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel15Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level15Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel7() {
        return _level7;
    }

    @Override
    public void setLevel7(String level7) {
        _level7 = level7;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel7", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level7);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel10() {
        return _level10;
    }

    @Override
    public void setLevel10(String level10) {
        _level10 = level10;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel10", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level10);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel19() {
        return _level19;
    }

    @Override
    public void setLevel19(String level19) {
        _level19 = level19;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel19", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level19);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel15() {
        return _level15;
    }

    @Override
    public void setLevel15(String level15) {
        _level15 = level15;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel15", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level15);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel16() {
        return _level16;
    }

    @Override
    public void setLevel16(String level16) {
        _level16 = level16;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel16", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level16);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGtnCompany() {
        return _gtnCompany;
    }

    @Override
    public void setGtnCompany(String gtnCompany) {
        _gtnCompany = gtnCompany;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGtnCompany", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, gtnCompany);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel17() {
        return _level17;
    }

    @Override
    public void setLevel17(String level17) {
        _level17 = level17;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel17", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level17);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel18Alias() {
        return _level18Alias;
    }

    @Override
    public void setLevel18Alias(String level18Alias) {
        _level18Alias = level18Alias;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel18Alias", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level18Alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevel18() {
        return _level18;
    }

    @Override
    public void setLevel18(String level18) {
        _level18 = level18;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLevel18", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, level18);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_itemHierarchyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_itemHierarchyMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemHierarchyMasterRemoteModel() {
        return _itemHierarchyMasterRemoteModel;
    }

    public void setItemHierarchyMasterRemoteModel(
        BaseModel<?> itemHierarchyMasterRemoteModel) {
        _itemHierarchyMasterRemoteModel = itemHierarchyMasterRemoteModel;
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

        Class<?> remoteModelClass = _itemHierarchyMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemHierarchyMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemHierarchyMasterLocalServiceUtil.addItemHierarchyMaster(this);
        } else {
            ItemHierarchyMasterLocalServiceUtil.updateItemHierarchyMaster(this);
        }
    }

    @Override
    public ItemHierarchyMaster toEscapedModel() {
        return (ItemHierarchyMaster) ProxyUtil.newProxyInstance(ItemHierarchyMaster.class.getClassLoader(),
            new Class[] { ItemHierarchyMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemHierarchyMasterClp clone = new ItemHierarchyMasterClp();

        clone.setLevel1Alias(getLevel1Alias());
        clone.setLevel11Alias(getLevel11Alias());
        clone.setCreatedDate(getCreatedDate());
        clone.setLevel8Alias(getLevel8Alias());
        clone.setLevel14Alias(getLevel14Alias());
        clone.setLevel5Alias(getLevel5Alias());
        clone.setCreatedBy(getCreatedBy());
        clone.setLevel10Alias(getLevel10Alias());
        clone.setItemHierarchyMasterSid(getItemHierarchyMasterSid());
        clone.setLevel17Alias(getLevel17Alias());
        clone.setLevel9Alias(getLevel9Alias());
        clone.setLevel0Alias(getLevel0Alias());
        clone.setModifiedDate(getModifiedDate());
        clone.setLevel13Alias(getLevel13Alias());
        clone.setSource(getSource());
        clone.setLevel6Alias(getLevel6Alias());
        clone.setGtnBrand(getGtnBrand());
        clone.setModifiedBy(getModifiedBy());
        clone.setLevel3Alias(getLevel3Alias());
        clone.setLevel16Alias(getLevel16Alias());
        clone.setBatchId(getBatchId());
        clone.setLevel19Alias(getLevel19Alias());
        clone.setLevel20(getLevel20());
        clone.setLevel2Alias(getLevel2Alias());
        clone.setLevel20Alias(getLevel20Alias());
        clone.setGtnTherapeuticClass(getGtnTherapeuticClass());
        clone.setLevel7Alias(getLevel7Alias());
        clone.setLevel0(getLevel0());
        clone.setLevel1(getLevel1());
        clone.setLevel2(getLevel2());
        clone.setLevel3(getLevel3());
        clone.setLevel12Alias(getLevel12Alias());
        clone.setLevel8(getLevel8());
        clone.setLevel11(getLevel11());
        clone.setLevel4Alias(getLevel4Alias());
        clone.setLevel9(getLevel9());
        clone.setLevel12(getLevel12());
        clone.setLevel13(getLevel13());
        clone.setLevel14(getLevel14());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setLevel0Tag(getLevel0Tag());
        clone.setLevel4(getLevel4());
        clone.setLevel5(getLevel5());
        clone.setLevel6(getLevel6());
        clone.setLevel15Alias(getLevel15Alias());
        clone.setLevel7(getLevel7());
        clone.setLevel10(getLevel10());
        clone.setLevel19(getLevel19());
        clone.setLevel15(getLevel15());
        clone.setLevel16(getLevel16());
        clone.setGtnCompany(getGtnCompany());
        clone.setLevel17(getLevel17());
        clone.setLevel18Alias(getLevel18Alias());
        clone.setLevel18(getLevel18());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ItemHierarchyMaster itemHierarchyMaster) {
        int primaryKey = itemHierarchyMaster.getPrimaryKey();

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

        if (!(obj instanceof ItemHierarchyMasterClp)) {
            return false;
        }

        ItemHierarchyMasterClp itemHierarchyMaster = (ItemHierarchyMasterClp) obj;

        int primaryKey = itemHierarchyMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(111);

        sb.append("{level1Alias=");
        sb.append(getLevel1Alias());
        sb.append(", level11Alias=");
        sb.append(getLevel11Alias());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", level8Alias=");
        sb.append(getLevel8Alias());
        sb.append(", level14Alias=");
        sb.append(getLevel14Alias());
        sb.append(", level5Alias=");
        sb.append(getLevel5Alias());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", level10Alias=");
        sb.append(getLevel10Alias());
        sb.append(", itemHierarchyMasterSid=");
        sb.append(getItemHierarchyMasterSid());
        sb.append(", level17Alias=");
        sb.append(getLevel17Alias());
        sb.append(", level9Alias=");
        sb.append(getLevel9Alias());
        sb.append(", level0Alias=");
        sb.append(getLevel0Alias());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", level13Alias=");
        sb.append(getLevel13Alias());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", level6Alias=");
        sb.append(getLevel6Alias());
        sb.append(", gtnBrand=");
        sb.append(getGtnBrand());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", level3Alias=");
        sb.append(getLevel3Alias());
        sb.append(", level16Alias=");
        sb.append(getLevel16Alias());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", level19Alias=");
        sb.append(getLevel19Alias());
        sb.append(", level20=");
        sb.append(getLevel20());
        sb.append(", level2Alias=");
        sb.append(getLevel2Alias());
        sb.append(", level20Alias=");
        sb.append(getLevel20Alias());
        sb.append(", gtnTherapeuticClass=");
        sb.append(getGtnTherapeuticClass());
        sb.append(", level7Alias=");
        sb.append(getLevel7Alias());
        sb.append(", level0=");
        sb.append(getLevel0());
        sb.append(", level1=");
        sb.append(getLevel1());
        sb.append(", level2=");
        sb.append(getLevel2());
        sb.append(", level3=");
        sb.append(getLevel3());
        sb.append(", level12Alias=");
        sb.append(getLevel12Alias());
        sb.append(", level8=");
        sb.append(getLevel8());
        sb.append(", level11=");
        sb.append(getLevel11());
        sb.append(", level4Alias=");
        sb.append(getLevel4Alias());
        sb.append(", level9=");
        sb.append(getLevel9());
        sb.append(", level12=");
        sb.append(getLevel12());
        sb.append(", level13=");
        sb.append(getLevel13());
        sb.append(", level14=");
        sb.append(getLevel14());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", level0Tag=");
        sb.append(getLevel0Tag());
        sb.append(", level4=");
        sb.append(getLevel4());
        sb.append(", level5=");
        sb.append(getLevel5());
        sb.append(", level6=");
        sb.append(getLevel6());
        sb.append(", level15Alias=");
        sb.append(getLevel15Alias());
        sb.append(", level7=");
        sb.append(getLevel7());
        sb.append(", level10=");
        sb.append(getLevel10());
        sb.append(", level19=");
        sb.append(getLevel19());
        sb.append(", level15=");
        sb.append(getLevel15());
        sb.append(", level16=");
        sb.append(getLevel16());
        sb.append(", gtnCompany=");
        sb.append(getGtnCompany());
        sb.append(", level17=");
        sb.append(getLevel17());
        sb.append(", level18Alias=");
        sb.append(getLevel18Alias());
        sb.append(", level18=");
        sb.append(getLevel18());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(169);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemHierarchyMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>level1Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel1Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level11Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel11Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level8Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel8Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level14Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel14Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level5Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel5Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level10Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel10Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemHierarchyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemHierarchyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level17Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel17Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level9Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel9Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level0Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel0Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level13Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel13Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level6Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel6Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gtnBrand</column-name><column-value><![CDATA[");
        sb.append(getGtnBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level3Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel3Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level16Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel16Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level19Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel19Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level20</column-name><column-value><![CDATA[");
        sb.append(getLevel20());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level2Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel2Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level20Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel20Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gtnTherapeuticClass</column-name><column-value><![CDATA[");
        sb.append(getGtnTherapeuticClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level7Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel7Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level0</column-name><column-value><![CDATA[");
        sb.append(getLevel0());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level1</column-name><column-value><![CDATA[");
        sb.append(getLevel1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level2</column-name><column-value><![CDATA[");
        sb.append(getLevel2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level3</column-name><column-value><![CDATA[");
        sb.append(getLevel3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level12Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel12Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level8</column-name><column-value><![CDATA[");
        sb.append(getLevel8());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level11</column-name><column-value><![CDATA[");
        sb.append(getLevel11());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level4Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel4Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level9</column-name><column-value><![CDATA[");
        sb.append(getLevel9());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level12</column-name><column-value><![CDATA[");
        sb.append(getLevel12());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level13</column-name><column-value><![CDATA[");
        sb.append(getLevel13());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level14</column-name><column-value><![CDATA[");
        sb.append(getLevel14());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level0Tag</column-name><column-value><![CDATA[");
        sb.append(getLevel0Tag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level4</column-name><column-value><![CDATA[");
        sb.append(getLevel4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level5</column-name><column-value><![CDATA[");
        sb.append(getLevel5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level6</column-name><column-value><![CDATA[");
        sb.append(getLevel6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level15Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel15Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level7</column-name><column-value><![CDATA[");
        sb.append(getLevel7());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level10</column-name><column-value><![CDATA[");
        sb.append(getLevel10());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level19</column-name><column-value><![CDATA[");
        sb.append(getLevel19());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level15</column-name><column-value><![CDATA[");
        sb.append(getLevel15());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level16</column-name><column-value><![CDATA[");
        sb.append(getLevel16());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gtnCompany</column-name><column-value><![CDATA[");
        sb.append(getGtnCompany());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level17</column-name><column-value><![CDATA[");
        sb.append(getLevel17());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level18Alias</column-name><column-value><![CDATA[");
        sb.append(getLevel18Alias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>level18</column-name><column-value><![CDATA[");
        sb.append(getLevel18());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
