/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ItemHierarchyMaster}.
 * </p>
 *
 * @author
 * @see ItemHierarchyMaster
 * @generated
 */
@ProviderType
public class ItemHierarchyMasterWrapper implements ItemHierarchyMaster,
	ModelWrapper<ItemHierarchyMaster> {
	public ItemHierarchyMasterWrapper(ItemHierarchyMaster itemHierarchyMaster) {
		_itemHierarchyMaster = itemHierarchyMaster;
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
		String level1Alias = (String)attributes.get("level1Alias");

		if (level1Alias != null) {
			setLevel1Alias(level1Alias);
		}

		String level11Alias = (String)attributes.get("level11Alias");

		if (level11Alias != null) {
			setLevel11Alias(level11Alias);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String level8Alias = (String)attributes.get("level8Alias");

		if (level8Alias != null) {
			setLevel8Alias(level8Alias);
		}

		String level14Alias = (String)attributes.get("level14Alias");

		if (level14Alias != null) {
			setLevel14Alias(level14Alias);
		}

		String level5Alias = (String)attributes.get("level5Alias");

		if (level5Alias != null) {
			setLevel5Alias(level5Alias);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String level10Alias = (String)attributes.get("level10Alias");

		if (level10Alias != null) {
			setLevel10Alias(level10Alias);
		}

		Integer itemHierarchyMasterSid = (Integer)attributes.get(
				"itemHierarchyMasterSid");

		if (itemHierarchyMasterSid != null) {
			setItemHierarchyMasterSid(itemHierarchyMasterSid);
		}

		String level17Alias = (String)attributes.get("level17Alias");

		if (level17Alias != null) {
			setLevel17Alias(level17Alias);
		}

		String level9Alias = (String)attributes.get("level9Alias");

		if (level9Alias != null) {
			setLevel9Alias(level9Alias);
		}

		String level0Alias = (String)attributes.get("level0Alias");

		if (level0Alias != null) {
			setLevel0Alias(level0Alias);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String level13Alias = (String)attributes.get("level13Alias");

		if (level13Alias != null) {
			setLevel13Alias(level13Alias);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String level6Alias = (String)attributes.get("level6Alias");

		if (level6Alias != null) {
			setLevel6Alias(level6Alias);
		}

		String gtnBrand = (String)attributes.get("gtnBrand");

		if (gtnBrand != null) {
			setGtnBrand(gtnBrand);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String level3Alias = (String)attributes.get("level3Alias");

		if (level3Alias != null) {
			setLevel3Alias(level3Alias);
		}

		String level16Alias = (String)attributes.get("level16Alias");

		if (level16Alias != null) {
			setLevel16Alias(level16Alias);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String level19Alias = (String)attributes.get("level19Alias");

		if (level19Alias != null) {
			setLevel19Alias(level19Alias);
		}

		String level20 = (String)attributes.get("level20");

		if (level20 != null) {
			setLevel20(level20);
		}

		String level2Alias = (String)attributes.get("level2Alias");

		if (level2Alias != null) {
			setLevel2Alias(level2Alias);
		}

		String level20Alias = (String)attributes.get("level20Alias");

		if (level20Alias != null) {
			setLevel20Alias(level20Alias);
		}

		String gtnTherapeuticClass = (String)attributes.get(
				"gtnTherapeuticClass");

		if (gtnTherapeuticClass != null) {
			setGtnTherapeuticClass(gtnTherapeuticClass);
		}

		String level7Alias = (String)attributes.get("level7Alias");

		if (level7Alias != null) {
			setLevel7Alias(level7Alias);
		}

		String level0 = (String)attributes.get("level0");

		if (level0 != null) {
			setLevel0(level0);
		}

		String level1 = (String)attributes.get("level1");

		if (level1 != null) {
			setLevel1(level1);
		}

		String level2 = (String)attributes.get("level2");

		if (level2 != null) {
			setLevel2(level2);
		}

		String level3 = (String)attributes.get("level3");

		if (level3 != null) {
			setLevel3(level3);
		}

		String level12Alias = (String)attributes.get("level12Alias");

		if (level12Alias != null) {
			setLevel12Alias(level12Alias);
		}

		String level8 = (String)attributes.get("level8");

		if (level8 != null) {
			setLevel8(level8);
		}

		String level11 = (String)attributes.get("level11");

		if (level11 != null) {
			setLevel11(level11);
		}

		String level4Alias = (String)attributes.get("level4Alias");

		if (level4Alias != null) {
			setLevel4Alias(level4Alias);
		}

		String level9 = (String)attributes.get("level9");

		if (level9 != null) {
			setLevel9(level9);
		}

		String level12 = (String)attributes.get("level12");

		if (level12 != null) {
			setLevel12(level12);
		}

		String level13 = (String)attributes.get("level13");

		if (level13 != null) {
			setLevel13(level13);
		}

		String level14 = (String)attributes.get("level14");

		if (level14 != null) {
			setLevel14(level14);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String level0Tag = (String)attributes.get("level0Tag");

		if (level0Tag != null) {
			setLevel0Tag(level0Tag);
		}

		String level4 = (String)attributes.get("level4");

		if (level4 != null) {
			setLevel4(level4);
		}

		String level5 = (String)attributes.get("level5");

		if (level5 != null) {
			setLevel5(level5);
		}

		String level6 = (String)attributes.get("level6");

		if (level6 != null) {
			setLevel6(level6);
		}

		String level15Alias = (String)attributes.get("level15Alias");

		if (level15Alias != null) {
			setLevel15Alias(level15Alias);
		}

		String level7 = (String)attributes.get("level7");

		if (level7 != null) {
			setLevel7(level7);
		}

		String level10 = (String)attributes.get("level10");

		if (level10 != null) {
			setLevel10(level10);
		}

		String level19 = (String)attributes.get("level19");

		if (level19 != null) {
			setLevel19(level19);
		}

		String level15 = (String)attributes.get("level15");

		if (level15 != null) {
			setLevel15(level15);
		}

		String level16 = (String)attributes.get("level16");

		if (level16 != null) {
			setLevel16(level16);
		}

		String gtnCompany = (String)attributes.get("gtnCompany");

		if (gtnCompany != null) {
			setGtnCompany(gtnCompany);
		}

		String level17 = (String)attributes.get("level17");

		if (level17 != null) {
			setLevel17(level17);
		}

		String level18Alias = (String)attributes.get("level18Alias");

		if (level18Alias != null) {
			setLevel18Alias(level18Alias);
		}

		String level18 = (String)attributes.get("level18");

		if (level18 != null) {
			setLevel18(level18);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ItemHierarchyMasterWrapper((ItemHierarchyMaster)_itemHierarchyMaster.clone());
	}

	@Override
	public int compareTo(ItemHierarchyMaster itemHierarchyMaster) {
		return _itemHierarchyMaster.compareTo(itemHierarchyMaster);
	}

	/**
	* Returns the batch ID of this item hierarchy master.
	*
	* @return the batch ID of this item hierarchy master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _itemHierarchyMaster.getBatchId();
	}

	/**
	* Returns the created by of this item hierarchy master.
	*
	* @return the created by of this item hierarchy master
	*/
	@Override
	public int getCreatedBy() {
		return _itemHierarchyMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this item hierarchy master.
	*
	* @return the created date of this item hierarchy master
	*/
	@Override
	public Date getCreatedDate() {
		return _itemHierarchyMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _itemHierarchyMaster.getExpandoBridge();
	}

	/**
	* Returns the gtn brand of this item hierarchy master.
	*
	* @return the gtn brand of this item hierarchy master
	*/
	@Override
	public java.lang.String getGtnBrand() {
		return _itemHierarchyMaster.getGtnBrand();
	}

	/**
	* Returns the gtn company of this item hierarchy master.
	*
	* @return the gtn company of this item hierarchy master
	*/
	@Override
	public java.lang.String getGtnCompany() {
		return _itemHierarchyMaster.getGtnCompany();
	}

	/**
	* Returns the gtn therapeutic class of this item hierarchy master.
	*
	* @return the gtn therapeutic class of this item hierarchy master
	*/
	@Override
	public java.lang.String getGtnTherapeuticClass() {
		return _itemHierarchyMaster.getGtnTherapeuticClass();
	}

	/**
	* Returns the inbound status of this item hierarchy master.
	*
	* @return the inbound status of this item hierarchy master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _itemHierarchyMaster.getInboundStatus();
	}

	/**
	* Returns the item hierarchy master sid of this item hierarchy master.
	*
	* @return the item hierarchy master sid of this item hierarchy master
	*/
	@Override
	public int getItemHierarchyMasterSid() {
		return _itemHierarchyMaster.getItemHierarchyMasterSid();
	}

	/**
	* Returns the level0 of this item hierarchy master.
	*
	* @return the level0 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel0() {
		return _itemHierarchyMaster.getLevel0();
	}

	/**
	* Returns the level0 alias of this item hierarchy master.
	*
	* @return the level0 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel0Alias() {
		return _itemHierarchyMaster.getLevel0Alias();
	}

	/**
	* Returns the level0 tag of this item hierarchy master.
	*
	* @return the level0 tag of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel0Tag() {
		return _itemHierarchyMaster.getLevel0Tag();
	}

	/**
	* Returns the level1 of this item hierarchy master.
	*
	* @return the level1 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel1() {
		return _itemHierarchyMaster.getLevel1();
	}

	/**
	* Returns the level10 of this item hierarchy master.
	*
	* @return the level10 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel10() {
		return _itemHierarchyMaster.getLevel10();
	}

	/**
	* Returns the level10 alias of this item hierarchy master.
	*
	* @return the level10 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel10Alias() {
		return _itemHierarchyMaster.getLevel10Alias();
	}

	/**
	* Returns the level11 of this item hierarchy master.
	*
	* @return the level11 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel11() {
		return _itemHierarchyMaster.getLevel11();
	}

	/**
	* Returns the level11 alias of this item hierarchy master.
	*
	* @return the level11 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel11Alias() {
		return _itemHierarchyMaster.getLevel11Alias();
	}

	/**
	* Returns the level12 of this item hierarchy master.
	*
	* @return the level12 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel12() {
		return _itemHierarchyMaster.getLevel12();
	}

	/**
	* Returns the level12 alias of this item hierarchy master.
	*
	* @return the level12 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel12Alias() {
		return _itemHierarchyMaster.getLevel12Alias();
	}

	/**
	* Returns the level13 of this item hierarchy master.
	*
	* @return the level13 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel13() {
		return _itemHierarchyMaster.getLevel13();
	}

	/**
	* Returns the level13 alias of this item hierarchy master.
	*
	* @return the level13 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel13Alias() {
		return _itemHierarchyMaster.getLevel13Alias();
	}

	/**
	* Returns the level14 of this item hierarchy master.
	*
	* @return the level14 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel14() {
		return _itemHierarchyMaster.getLevel14();
	}

	/**
	* Returns the level14 alias of this item hierarchy master.
	*
	* @return the level14 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel14Alias() {
		return _itemHierarchyMaster.getLevel14Alias();
	}

	/**
	* Returns the level15 of this item hierarchy master.
	*
	* @return the level15 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel15() {
		return _itemHierarchyMaster.getLevel15();
	}

	/**
	* Returns the level15 alias of this item hierarchy master.
	*
	* @return the level15 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel15Alias() {
		return _itemHierarchyMaster.getLevel15Alias();
	}

	/**
	* Returns the level16 of this item hierarchy master.
	*
	* @return the level16 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel16() {
		return _itemHierarchyMaster.getLevel16();
	}

	/**
	* Returns the level16 alias of this item hierarchy master.
	*
	* @return the level16 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel16Alias() {
		return _itemHierarchyMaster.getLevel16Alias();
	}

	/**
	* Returns the level17 of this item hierarchy master.
	*
	* @return the level17 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel17() {
		return _itemHierarchyMaster.getLevel17();
	}

	/**
	* Returns the level17 alias of this item hierarchy master.
	*
	* @return the level17 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel17Alias() {
		return _itemHierarchyMaster.getLevel17Alias();
	}

	/**
	* Returns the level18 of this item hierarchy master.
	*
	* @return the level18 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel18() {
		return _itemHierarchyMaster.getLevel18();
	}

	/**
	* Returns the level18 alias of this item hierarchy master.
	*
	* @return the level18 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel18Alias() {
		return _itemHierarchyMaster.getLevel18Alias();
	}

	/**
	* Returns the level19 of this item hierarchy master.
	*
	* @return the level19 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel19() {
		return _itemHierarchyMaster.getLevel19();
	}

	/**
	* Returns the level19 alias of this item hierarchy master.
	*
	* @return the level19 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel19Alias() {
		return _itemHierarchyMaster.getLevel19Alias();
	}

	/**
	* Returns the level1 alias of this item hierarchy master.
	*
	* @return the level1 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel1Alias() {
		return _itemHierarchyMaster.getLevel1Alias();
	}

	/**
	* Returns the level2 of this item hierarchy master.
	*
	* @return the level2 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel2() {
		return _itemHierarchyMaster.getLevel2();
	}

	/**
	* Returns the level20 of this item hierarchy master.
	*
	* @return the level20 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel20() {
		return _itemHierarchyMaster.getLevel20();
	}

	/**
	* Returns the level20 alias of this item hierarchy master.
	*
	* @return the level20 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel20Alias() {
		return _itemHierarchyMaster.getLevel20Alias();
	}

	/**
	* Returns the level2 alias of this item hierarchy master.
	*
	* @return the level2 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel2Alias() {
		return _itemHierarchyMaster.getLevel2Alias();
	}

	/**
	* Returns the level3 of this item hierarchy master.
	*
	* @return the level3 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel3() {
		return _itemHierarchyMaster.getLevel3();
	}

	/**
	* Returns the level3 alias of this item hierarchy master.
	*
	* @return the level3 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel3Alias() {
		return _itemHierarchyMaster.getLevel3Alias();
	}

	/**
	* Returns the level4 of this item hierarchy master.
	*
	* @return the level4 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel4() {
		return _itemHierarchyMaster.getLevel4();
	}

	/**
	* Returns the level4 alias of this item hierarchy master.
	*
	* @return the level4 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel4Alias() {
		return _itemHierarchyMaster.getLevel4Alias();
	}

	/**
	* Returns the level5 of this item hierarchy master.
	*
	* @return the level5 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel5() {
		return _itemHierarchyMaster.getLevel5();
	}

	/**
	* Returns the level5 alias of this item hierarchy master.
	*
	* @return the level5 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel5Alias() {
		return _itemHierarchyMaster.getLevel5Alias();
	}

	/**
	* Returns the level6 of this item hierarchy master.
	*
	* @return the level6 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel6() {
		return _itemHierarchyMaster.getLevel6();
	}

	/**
	* Returns the level6 alias of this item hierarchy master.
	*
	* @return the level6 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel6Alias() {
		return _itemHierarchyMaster.getLevel6Alias();
	}

	/**
	* Returns the level7 of this item hierarchy master.
	*
	* @return the level7 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel7() {
		return _itemHierarchyMaster.getLevel7();
	}

	/**
	* Returns the level7 alias of this item hierarchy master.
	*
	* @return the level7 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel7Alias() {
		return _itemHierarchyMaster.getLevel7Alias();
	}

	/**
	* Returns the level8 of this item hierarchy master.
	*
	* @return the level8 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel8() {
		return _itemHierarchyMaster.getLevel8();
	}

	/**
	* Returns the level8 alias of this item hierarchy master.
	*
	* @return the level8 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel8Alias() {
		return _itemHierarchyMaster.getLevel8Alias();
	}

	/**
	* Returns the level9 of this item hierarchy master.
	*
	* @return the level9 of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel9() {
		return _itemHierarchyMaster.getLevel9();
	}

	/**
	* Returns the level9 alias of this item hierarchy master.
	*
	* @return the level9 alias of this item hierarchy master
	*/
	@Override
	public java.lang.String getLevel9Alias() {
		return _itemHierarchyMaster.getLevel9Alias();
	}

	/**
	* Returns the modified by of this item hierarchy master.
	*
	* @return the modified by of this item hierarchy master
	*/
	@Override
	public int getModifiedBy() {
		return _itemHierarchyMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this item hierarchy master.
	*
	* @return the modified date of this item hierarchy master
	*/
	@Override
	public Date getModifiedDate() {
		return _itemHierarchyMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this item hierarchy master.
	*
	* @return the primary key of this item hierarchy master
	*/
	@Override
	public int getPrimaryKey() {
		return _itemHierarchyMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemHierarchyMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this item hierarchy master.
	*
	* @return the record lock status of this item hierarchy master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _itemHierarchyMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this item hierarchy master.
	*
	* @return the source of this item hierarchy master
	*/
	@Override
	public java.lang.String getSource() {
		return _itemHierarchyMaster.getSource();
	}

	@Override
	public int hashCode() {
		return _itemHierarchyMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _itemHierarchyMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _itemHierarchyMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _itemHierarchyMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this item hierarchy master is record lock status.
	*
	* @return <code>true</code> if this item hierarchy master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _itemHierarchyMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_itemHierarchyMaster.persist();
	}

	/**
	* Sets the batch ID of this item hierarchy master.
	*
	* @param batchId the batch ID of this item hierarchy master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_itemHierarchyMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_itemHierarchyMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this item hierarchy master.
	*
	* @param createdBy the created by of this item hierarchy master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_itemHierarchyMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this item hierarchy master.
	*
	* @param createdDate the created date of this item hierarchy master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_itemHierarchyMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_itemHierarchyMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_itemHierarchyMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_itemHierarchyMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gtn brand of this item hierarchy master.
	*
	* @param gtnBrand the gtn brand of this item hierarchy master
	*/
	@Override
	public void setGtnBrand(java.lang.String gtnBrand) {
		_itemHierarchyMaster.setGtnBrand(gtnBrand);
	}

	/**
	* Sets the gtn company of this item hierarchy master.
	*
	* @param gtnCompany the gtn company of this item hierarchy master
	*/
	@Override
	public void setGtnCompany(java.lang.String gtnCompany) {
		_itemHierarchyMaster.setGtnCompany(gtnCompany);
	}

	/**
	* Sets the gtn therapeutic class of this item hierarchy master.
	*
	* @param gtnTherapeuticClass the gtn therapeutic class of this item hierarchy master
	*/
	@Override
	public void setGtnTherapeuticClass(java.lang.String gtnTherapeuticClass) {
		_itemHierarchyMaster.setGtnTherapeuticClass(gtnTherapeuticClass);
	}

	/**
	* Sets the inbound status of this item hierarchy master.
	*
	* @param inboundStatus the inbound status of this item hierarchy master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_itemHierarchyMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item hierarchy master sid of this item hierarchy master.
	*
	* @param itemHierarchyMasterSid the item hierarchy master sid of this item hierarchy master
	*/
	@Override
	public void setItemHierarchyMasterSid(int itemHierarchyMasterSid) {
		_itemHierarchyMaster.setItemHierarchyMasterSid(itemHierarchyMasterSid);
	}

	/**
	* Sets the level0 of this item hierarchy master.
	*
	* @param level0 the level0 of this item hierarchy master
	*/
	@Override
	public void setLevel0(java.lang.String level0) {
		_itemHierarchyMaster.setLevel0(level0);
	}

	/**
	* Sets the level0 alias of this item hierarchy master.
	*
	* @param level0Alias the level0 alias of this item hierarchy master
	*/
	@Override
	public void setLevel0Alias(java.lang.String level0Alias) {
		_itemHierarchyMaster.setLevel0Alias(level0Alias);
	}

	/**
	* Sets the level0 tag of this item hierarchy master.
	*
	* @param level0Tag the level0 tag of this item hierarchy master
	*/
	@Override
	public void setLevel0Tag(java.lang.String level0Tag) {
		_itemHierarchyMaster.setLevel0Tag(level0Tag);
	}

	/**
	* Sets the level1 of this item hierarchy master.
	*
	* @param level1 the level1 of this item hierarchy master
	*/
	@Override
	public void setLevel1(java.lang.String level1) {
		_itemHierarchyMaster.setLevel1(level1);
	}

	/**
	* Sets the level10 of this item hierarchy master.
	*
	* @param level10 the level10 of this item hierarchy master
	*/
	@Override
	public void setLevel10(java.lang.String level10) {
		_itemHierarchyMaster.setLevel10(level10);
	}

	/**
	* Sets the level10 alias of this item hierarchy master.
	*
	* @param level10Alias the level10 alias of this item hierarchy master
	*/
	@Override
	public void setLevel10Alias(java.lang.String level10Alias) {
		_itemHierarchyMaster.setLevel10Alias(level10Alias);
	}

	/**
	* Sets the level11 of this item hierarchy master.
	*
	* @param level11 the level11 of this item hierarchy master
	*/
	@Override
	public void setLevel11(java.lang.String level11) {
		_itemHierarchyMaster.setLevel11(level11);
	}

	/**
	* Sets the level11 alias of this item hierarchy master.
	*
	* @param level11Alias the level11 alias of this item hierarchy master
	*/
	@Override
	public void setLevel11Alias(java.lang.String level11Alias) {
		_itemHierarchyMaster.setLevel11Alias(level11Alias);
	}

	/**
	* Sets the level12 of this item hierarchy master.
	*
	* @param level12 the level12 of this item hierarchy master
	*/
	@Override
	public void setLevel12(java.lang.String level12) {
		_itemHierarchyMaster.setLevel12(level12);
	}

	/**
	* Sets the level12 alias of this item hierarchy master.
	*
	* @param level12Alias the level12 alias of this item hierarchy master
	*/
	@Override
	public void setLevel12Alias(java.lang.String level12Alias) {
		_itemHierarchyMaster.setLevel12Alias(level12Alias);
	}

	/**
	* Sets the level13 of this item hierarchy master.
	*
	* @param level13 the level13 of this item hierarchy master
	*/
	@Override
	public void setLevel13(java.lang.String level13) {
		_itemHierarchyMaster.setLevel13(level13);
	}

	/**
	* Sets the level13 alias of this item hierarchy master.
	*
	* @param level13Alias the level13 alias of this item hierarchy master
	*/
	@Override
	public void setLevel13Alias(java.lang.String level13Alias) {
		_itemHierarchyMaster.setLevel13Alias(level13Alias);
	}

	/**
	* Sets the level14 of this item hierarchy master.
	*
	* @param level14 the level14 of this item hierarchy master
	*/
	@Override
	public void setLevel14(java.lang.String level14) {
		_itemHierarchyMaster.setLevel14(level14);
	}

	/**
	* Sets the level14 alias of this item hierarchy master.
	*
	* @param level14Alias the level14 alias of this item hierarchy master
	*/
	@Override
	public void setLevel14Alias(java.lang.String level14Alias) {
		_itemHierarchyMaster.setLevel14Alias(level14Alias);
	}

	/**
	* Sets the level15 of this item hierarchy master.
	*
	* @param level15 the level15 of this item hierarchy master
	*/
	@Override
	public void setLevel15(java.lang.String level15) {
		_itemHierarchyMaster.setLevel15(level15);
	}

	/**
	* Sets the level15 alias of this item hierarchy master.
	*
	* @param level15Alias the level15 alias of this item hierarchy master
	*/
	@Override
	public void setLevel15Alias(java.lang.String level15Alias) {
		_itemHierarchyMaster.setLevel15Alias(level15Alias);
	}

	/**
	* Sets the level16 of this item hierarchy master.
	*
	* @param level16 the level16 of this item hierarchy master
	*/
	@Override
	public void setLevel16(java.lang.String level16) {
		_itemHierarchyMaster.setLevel16(level16);
	}

	/**
	* Sets the level16 alias of this item hierarchy master.
	*
	* @param level16Alias the level16 alias of this item hierarchy master
	*/
	@Override
	public void setLevel16Alias(java.lang.String level16Alias) {
		_itemHierarchyMaster.setLevel16Alias(level16Alias);
	}

	/**
	* Sets the level17 of this item hierarchy master.
	*
	* @param level17 the level17 of this item hierarchy master
	*/
	@Override
	public void setLevel17(java.lang.String level17) {
		_itemHierarchyMaster.setLevel17(level17);
	}

	/**
	* Sets the level17 alias of this item hierarchy master.
	*
	* @param level17Alias the level17 alias of this item hierarchy master
	*/
	@Override
	public void setLevel17Alias(java.lang.String level17Alias) {
		_itemHierarchyMaster.setLevel17Alias(level17Alias);
	}

	/**
	* Sets the level18 of this item hierarchy master.
	*
	* @param level18 the level18 of this item hierarchy master
	*/
	@Override
	public void setLevel18(java.lang.String level18) {
		_itemHierarchyMaster.setLevel18(level18);
	}

	/**
	* Sets the level18 alias of this item hierarchy master.
	*
	* @param level18Alias the level18 alias of this item hierarchy master
	*/
	@Override
	public void setLevel18Alias(java.lang.String level18Alias) {
		_itemHierarchyMaster.setLevel18Alias(level18Alias);
	}

	/**
	* Sets the level19 of this item hierarchy master.
	*
	* @param level19 the level19 of this item hierarchy master
	*/
	@Override
	public void setLevel19(java.lang.String level19) {
		_itemHierarchyMaster.setLevel19(level19);
	}

	/**
	* Sets the level19 alias of this item hierarchy master.
	*
	* @param level19Alias the level19 alias of this item hierarchy master
	*/
	@Override
	public void setLevel19Alias(java.lang.String level19Alias) {
		_itemHierarchyMaster.setLevel19Alias(level19Alias);
	}

	/**
	* Sets the level1 alias of this item hierarchy master.
	*
	* @param level1Alias the level1 alias of this item hierarchy master
	*/
	@Override
	public void setLevel1Alias(java.lang.String level1Alias) {
		_itemHierarchyMaster.setLevel1Alias(level1Alias);
	}

	/**
	* Sets the level2 of this item hierarchy master.
	*
	* @param level2 the level2 of this item hierarchy master
	*/
	@Override
	public void setLevel2(java.lang.String level2) {
		_itemHierarchyMaster.setLevel2(level2);
	}

	/**
	* Sets the level20 of this item hierarchy master.
	*
	* @param level20 the level20 of this item hierarchy master
	*/
	@Override
	public void setLevel20(java.lang.String level20) {
		_itemHierarchyMaster.setLevel20(level20);
	}

	/**
	* Sets the level20 alias of this item hierarchy master.
	*
	* @param level20Alias the level20 alias of this item hierarchy master
	*/
	@Override
	public void setLevel20Alias(java.lang.String level20Alias) {
		_itemHierarchyMaster.setLevel20Alias(level20Alias);
	}

	/**
	* Sets the level2 alias of this item hierarchy master.
	*
	* @param level2Alias the level2 alias of this item hierarchy master
	*/
	@Override
	public void setLevel2Alias(java.lang.String level2Alias) {
		_itemHierarchyMaster.setLevel2Alias(level2Alias);
	}

	/**
	* Sets the level3 of this item hierarchy master.
	*
	* @param level3 the level3 of this item hierarchy master
	*/
	@Override
	public void setLevel3(java.lang.String level3) {
		_itemHierarchyMaster.setLevel3(level3);
	}

	/**
	* Sets the level3 alias of this item hierarchy master.
	*
	* @param level3Alias the level3 alias of this item hierarchy master
	*/
	@Override
	public void setLevel3Alias(java.lang.String level3Alias) {
		_itemHierarchyMaster.setLevel3Alias(level3Alias);
	}

	/**
	* Sets the level4 of this item hierarchy master.
	*
	* @param level4 the level4 of this item hierarchy master
	*/
	@Override
	public void setLevel4(java.lang.String level4) {
		_itemHierarchyMaster.setLevel4(level4);
	}

	/**
	* Sets the level4 alias of this item hierarchy master.
	*
	* @param level4Alias the level4 alias of this item hierarchy master
	*/
	@Override
	public void setLevel4Alias(java.lang.String level4Alias) {
		_itemHierarchyMaster.setLevel4Alias(level4Alias);
	}

	/**
	* Sets the level5 of this item hierarchy master.
	*
	* @param level5 the level5 of this item hierarchy master
	*/
	@Override
	public void setLevel5(java.lang.String level5) {
		_itemHierarchyMaster.setLevel5(level5);
	}

	/**
	* Sets the level5 alias of this item hierarchy master.
	*
	* @param level5Alias the level5 alias of this item hierarchy master
	*/
	@Override
	public void setLevel5Alias(java.lang.String level5Alias) {
		_itemHierarchyMaster.setLevel5Alias(level5Alias);
	}

	/**
	* Sets the level6 of this item hierarchy master.
	*
	* @param level6 the level6 of this item hierarchy master
	*/
	@Override
	public void setLevel6(java.lang.String level6) {
		_itemHierarchyMaster.setLevel6(level6);
	}

	/**
	* Sets the level6 alias of this item hierarchy master.
	*
	* @param level6Alias the level6 alias of this item hierarchy master
	*/
	@Override
	public void setLevel6Alias(java.lang.String level6Alias) {
		_itemHierarchyMaster.setLevel6Alias(level6Alias);
	}

	/**
	* Sets the level7 of this item hierarchy master.
	*
	* @param level7 the level7 of this item hierarchy master
	*/
	@Override
	public void setLevel7(java.lang.String level7) {
		_itemHierarchyMaster.setLevel7(level7);
	}

	/**
	* Sets the level7 alias of this item hierarchy master.
	*
	* @param level7Alias the level7 alias of this item hierarchy master
	*/
	@Override
	public void setLevel7Alias(java.lang.String level7Alias) {
		_itemHierarchyMaster.setLevel7Alias(level7Alias);
	}

	/**
	* Sets the level8 of this item hierarchy master.
	*
	* @param level8 the level8 of this item hierarchy master
	*/
	@Override
	public void setLevel8(java.lang.String level8) {
		_itemHierarchyMaster.setLevel8(level8);
	}

	/**
	* Sets the level8 alias of this item hierarchy master.
	*
	* @param level8Alias the level8 alias of this item hierarchy master
	*/
	@Override
	public void setLevel8Alias(java.lang.String level8Alias) {
		_itemHierarchyMaster.setLevel8Alias(level8Alias);
	}

	/**
	* Sets the level9 of this item hierarchy master.
	*
	* @param level9 the level9 of this item hierarchy master
	*/
	@Override
	public void setLevel9(java.lang.String level9) {
		_itemHierarchyMaster.setLevel9(level9);
	}

	/**
	* Sets the level9 alias of this item hierarchy master.
	*
	* @param level9Alias the level9 alias of this item hierarchy master
	*/
	@Override
	public void setLevel9Alias(java.lang.String level9Alias) {
		_itemHierarchyMaster.setLevel9Alias(level9Alias);
	}

	/**
	* Sets the modified by of this item hierarchy master.
	*
	* @param modifiedBy the modified by of this item hierarchy master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_itemHierarchyMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this item hierarchy master.
	*
	* @param modifiedDate the modified date of this item hierarchy master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_itemHierarchyMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_itemHierarchyMaster.setNew(n);
	}

	/**
	* Sets the primary key of this item hierarchy master.
	*
	* @param primaryKey the primary key of this item hierarchy master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_itemHierarchyMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_itemHierarchyMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this item hierarchy master is record lock status.
	*
	* @param recordLockStatus the record lock status of this item hierarchy master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_itemHierarchyMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this item hierarchy master.
	*
	* @param source the source of this item hierarchy master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_itemHierarchyMaster.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ItemHierarchyMaster> toCacheModel() {
		return _itemHierarchyMaster.toCacheModel();
	}

	@Override
	public ItemHierarchyMaster toEscapedModel() {
		return new ItemHierarchyMasterWrapper(_itemHierarchyMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _itemHierarchyMaster.toString();
	}

	@Override
	public ItemHierarchyMaster toUnescapedModel() {
		return new ItemHierarchyMasterWrapper(_itemHierarchyMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _itemHierarchyMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemHierarchyMasterWrapper)) {
			return false;
		}

		ItemHierarchyMasterWrapper itemHierarchyMasterWrapper = (ItemHierarchyMasterWrapper)obj;

		if (Objects.equals(_itemHierarchyMaster,
					itemHierarchyMasterWrapper._itemHierarchyMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemHierarchyMaster getWrappedModel() {
		return _itemHierarchyMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _itemHierarchyMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _itemHierarchyMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_itemHierarchyMaster.resetOriginalValues();
	}

	private final ItemHierarchyMaster _itemHierarchyMaster;
}