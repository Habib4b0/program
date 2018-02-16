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

package com.stpl.app.parttwo.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchIvldItemMasterException;
import com.stpl.app.parttwo.model.IvldItemMaster;
import com.stpl.app.parttwo.service.IvldItemMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldItemMasterPersistence;
import com.stpl.app.parttwo.service.persistence.IvldItemMasterUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class IvldItemMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldItemMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldItemMaster> iterator = _ivldItemMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemMaster ivldItemMaster = _persistence.create(pk);

		Assert.assertNotNull(ivldItemMaster);

		Assert.assertEquals(ivldItemMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldItemMaster newIvldItemMaster = addIvldItemMaster();

		_persistence.remove(newIvldItemMaster);

		IvldItemMaster existingIvldItemMaster = _persistence.fetchByPrimaryKey(newIvldItemMaster.getPrimaryKey());

		Assert.assertNull(existingIvldItemMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldItemMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemMaster newIvldItemMaster = _persistence.create(pk);

		newIvldItemMaster.setItemNo(RandomTestUtil.randomString());

		newIvldItemMaster.setUdc6(RandomTestUtil.randomString());

		newIvldItemMaster.setCreatedDate(RandomTestUtil.nextDate());

		newIvldItemMaster.setNewFormulationIndicator(RandomTestUtil.randomString());

		newIvldItemMaster.setUdc5(RandomTestUtil.randomString());

		newIvldItemMaster.setNewFormulationEndDate(RandomTestUtil.randomString());

		newIvldItemMaster.setUdc4(RandomTestUtil.randomString());

		newIvldItemMaster.setClottingFactorStartDate(RandomTestUtil.randomString());

		newIvldItemMaster.setSecondaryUom(RandomTestUtil.randomString());

		newIvldItemMaster.setItemDesc(RandomTestUtil.randomString());

		newIvldItemMaster.setAuthorizedGenericEndDate(RandomTestUtil.randomString());

		newIvldItemMaster.setManufacturerName(RandomTestUtil.randomString());

		newIvldItemMaster.setItemName(RandomTestUtil.randomString());

		newIvldItemMaster.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldItemMaster.setStatus(RandomTestUtil.randomString());

		newIvldItemMaster.setBaseCpi(RandomTestUtil.randomString());

		newIvldItemMaster.setBaselineAmp(RandomTestUtil.randomString());

		newIvldItemMaster.setAuthorizedGeneric(RandomTestUtil.randomString());

		newIvldItemMaster.setTherapeuticClass(RandomTestUtil.randomString());

		newIvldItemMaster.setItemFamilyId(RandomTestUtil.randomString());

		newIvldItemMaster.setPediatricExclusiveStartDate(RandomTestUtil.randomString());

		newIvldItemMaster.setCreatedBy(RandomTestUtil.randomString());

		newIvldItemMaster.setPrimaryUom(RandomTestUtil.randomString());

		newIvldItemMaster.setNdc9(RandomTestUtil.randomString());

		newIvldItemMaster.setItemId(RandomTestUtil.randomString());

		newIvldItemMaster.setLastLotExpirationDate(RandomTestUtil.randomString());

		newIvldItemMaster.setErrorField(RandomTestUtil.randomString());

		newIvldItemMaster.setItemCode(RandomTestUtil.randomString());

		newIvldItemMaster.setStrength(RandomTestUtil.randomString());

		newIvldItemMaster.setModifiedDate(RandomTestUtil.nextDate());

		newIvldItemMaster.setBrand(RandomTestUtil.randomString());

		newIvldItemMaster.setNdc8(RandomTestUtil.randomString());

		newIvldItemMaster.setLabelerCode(RandomTestUtil.randomString());

		newIvldItemMaster.setUdc3(RandomTestUtil.randomString());

		newIvldItemMaster.setSource(RandomTestUtil.randomString());

		newIvldItemMaster.setUdc2(RandomTestUtil.randomString());

		newIvldItemMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldItemMaster.setUdc1(RandomTestUtil.randomString());

		newIvldItemMaster.setAcquiredAmp(RandomTestUtil.randomString());

		newIvldItemMaster.setDiscontinuationDate(RandomTestUtil.randomString());

		newIvldItemMaster.setItemMasterIntfid(RandomTestUtil.randomString());

		newIvldItemMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldItemMaster.setDivestitureDate(RandomTestUtil.randomString());

		newIvldItemMaster.setModifiedBy(RandomTestUtil.randomString());

		newIvldItemMaster.setBaseCpiPeriod(RandomTestUtil.randomString());

		newIvldItemMaster.setClottingFactorEndDate(RandomTestUtil.randomString());

		newIvldItemMaster.setDosesPerUnit(RandomTestUtil.randomString());

		newIvldItemMaster.setManufacturerId(RandomTestUtil.randomString());

		newIvldItemMaster.setClottingFactorIndicator(RandomTestUtil.randomString());

		newIvldItemMaster.setBatchId(RandomTestUtil.randomString());

		newIvldItemMaster.setAcquisitionDate(RandomTestUtil.randomString());

		newIvldItemMaster.setDualPricingIndicator(RandomTestUtil.randomString());

		newIvldItemMaster.setNonFederalExpirationDate(RandomTestUtil.randomString());

		newIvldItemMaster.setErrorCode(RandomTestUtil.randomString());

		newIvldItemMaster.setNewFormulation(RandomTestUtil.randomString());

		newIvldItemMaster.setObraBamp(RandomTestUtil.randomString());

		newIvldItemMaster.setBrandId(RandomTestUtil.randomString());

		newIvldItemMaster.setItemStatus(RandomTestUtil.randomString());

		newIvldItemMaster.setAuthorizedGenericStartDate(RandomTestUtil.randomString());

		newIvldItemMaster.setNewFormulationStartDate(RandomTestUtil.randomString());

		newIvldItemMaster.setItemCategory(RandomTestUtil.randomString());

		newIvldItemMaster.setItemEndDate(RandomTestUtil.randomString());

		newIvldItemMaster.setItemType(RandomTestUtil.randomString());

		newIvldItemMaster.setPediatricExclusiveEndDate(RandomTestUtil.randomString());

		newIvldItemMaster.setOrganizationKey(RandomTestUtil.randomString());

		newIvldItemMaster.setFirstSaleDate(RandomTestUtil.randomString());

		newIvldItemMaster.setShelfLifeType(RandomTestUtil.randomString());

		newIvldItemMaster.setItemStartDate(RandomTestUtil.randomString());

		newIvldItemMaster.setItemTypeIndication(RandomTestUtil.randomString());

		newIvldItemMaster.setAcquiredBamp(RandomTestUtil.randomString());

		newIvldItemMaster.setForm(RandomTestUtil.randomString());

		newIvldItemMaster.setItemClass(RandomTestUtil.randomString());

		newIvldItemMaster.setManufacturerNo(RandomTestUtil.randomString());

		newIvldItemMaster.setPediatricExclusiveIndicator(RandomTestUtil.randomString());

		newIvldItemMaster.setPackageSizeCode(RandomTestUtil.randomString());

		newIvldItemMaster.setDisplayBrand(RandomTestUtil.randomString());

		newIvldItemMaster.setDra(RandomTestUtil.randomString());

		newIvldItemMaster.setReasonForFailure(RandomTestUtil.randomString());

		newIvldItemMaster.setPackageSizeIntroDate(RandomTestUtil.randomString());

		newIvldItemMaster.setUpps(RandomTestUtil.randomString());

		newIvldItemMaster.setPackageSize(RandomTestUtil.randomString());

		newIvldItemMaster.setShelfLife(RandomTestUtil.randomString());

		newIvldItemMaster.setMarketTerminationDate(RandomTestUtil.randomString());

		newIvldItemMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newIvldItemMaster.setBaseCpiPrecision(RandomTestUtil.nextInt());

		newIvldItemMaster.setBaselineAmpPrecision(RandomTestUtil.nextInt());

		_ivldItemMasters.add(_persistence.update(newIvldItemMaster));

		IvldItemMaster existingIvldItemMaster = _persistence.findByPrimaryKey(newIvldItemMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldItemMaster.getItemNo(),
			newIvldItemMaster.getItemNo());
		Assert.assertEquals(existingIvldItemMaster.getUdc6(),
			newIvldItemMaster.getUdc6());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemMaster.getCreatedDate()),
			Time.getShortTimestamp(newIvldItemMaster.getCreatedDate()));
		Assert.assertEquals(existingIvldItemMaster.getNewFormulationIndicator(),
			newIvldItemMaster.getNewFormulationIndicator());
		Assert.assertEquals(existingIvldItemMaster.getUdc5(),
			newIvldItemMaster.getUdc5());
		Assert.assertEquals(existingIvldItemMaster.getNewFormulationEndDate(),
			newIvldItemMaster.getNewFormulationEndDate());
		Assert.assertEquals(existingIvldItemMaster.getUdc4(),
			newIvldItemMaster.getUdc4());
		Assert.assertEquals(existingIvldItemMaster.getClottingFactorStartDate(),
			newIvldItemMaster.getClottingFactorStartDate());
		Assert.assertEquals(existingIvldItemMaster.getSecondaryUom(),
			newIvldItemMaster.getSecondaryUom());
		Assert.assertEquals(existingIvldItemMaster.getItemDesc(),
			newIvldItemMaster.getItemDesc());
		Assert.assertEquals(existingIvldItemMaster.getAuthorizedGenericEndDate(),
			newIvldItemMaster.getAuthorizedGenericEndDate());
		Assert.assertEquals(existingIvldItemMaster.getManufacturerName(),
			newIvldItemMaster.getManufacturerName());
		Assert.assertEquals(existingIvldItemMaster.getItemName(),
			newIvldItemMaster.getItemName());
		Assert.assertEquals(existingIvldItemMaster.getReprocessedFlag(),
			newIvldItemMaster.getReprocessedFlag());
		Assert.assertEquals(existingIvldItemMaster.getStatus(),
			newIvldItemMaster.getStatus());
		Assert.assertEquals(existingIvldItemMaster.getBaseCpi(),
			newIvldItemMaster.getBaseCpi());
		Assert.assertEquals(existingIvldItemMaster.getBaselineAmp(),
			newIvldItemMaster.getBaselineAmp());
		Assert.assertEquals(existingIvldItemMaster.getAuthorizedGeneric(),
			newIvldItemMaster.getAuthorizedGeneric());
		Assert.assertEquals(existingIvldItemMaster.getTherapeuticClass(),
			newIvldItemMaster.getTherapeuticClass());
		Assert.assertEquals(existingIvldItemMaster.getItemFamilyId(),
			newIvldItemMaster.getItemFamilyId());
		Assert.assertEquals(existingIvldItemMaster.getPediatricExclusiveStartDate(),
			newIvldItemMaster.getPediatricExclusiveStartDate());
		Assert.assertEquals(existingIvldItemMaster.getCreatedBy(),
			newIvldItemMaster.getCreatedBy());
		Assert.assertEquals(existingIvldItemMaster.getPrimaryUom(),
			newIvldItemMaster.getPrimaryUom());
		Assert.assertEquals(existingIvldItemMaster.getNdc9(),
			newIvldItemMaster.getNdc9());
		Assert.assertEquals(existingIvldItemMaster.getItemId(),
			newIvldItemMaster.getItemId());
		Assert.assertEquals(existingIvldItemMaster.getLastLotExpirationDate(),
			newIvldItemMaster.getLastLotExpirationDate());
		Assert.assertEquals(existingIvldItemMaster.getErrorField(),
			newIvldItemMaster.getErrorField());
		Assert.assertEquals(existingIvldItemMaster.getItemCode(),
			newIvldItemMaster.getItemCode());
		Assert.assertEquals(existingIvldItemMaster.getStrength(),
			newIvldItemMaster.getStrength());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemMaster.getModifiedDate()),
			Time.getShortTimestamp(newIvldItemMaster.getModifiedDate()));
		Assert.assertEquals(existingIvldItemMaster.getBrand(),
			newIvldItemMaster.getBrand());
		Assert.assertEquals(existingIvldItemMaster.getNdc8(),
			newIvldItemMaster.getNdc8());
		Assert.assertEquals(existingIvldItemMaster.getLabelerCode(),
			newIvldItemMaster.getLabelerCode());
		Assert.assertEquals(existingIvldItemMaster.getUdc3(),
			newIvldItemMaster.getUdc3());
		Assert.assertEquals(existingIvldItemMaster.getSource(),
			newIvldItemMaster.getSource());
		Assert.assertEquals(existingIvldItemMaster.getUdc2(),
			newIvldItemMaster.getUdc2());
		Assert.assertEquals(existingIvldItemMaster.getAddChgDelIndicator(),
			newIvldItemMaster.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldItemMaster.getUdc1(),
			newIvldItemMaster.getUdc1());
		Assert.assertEquals(existingIvldItemMaster.getAcquiredAmp(),
			newIvldItemMaster.getAcquiredAmp());
		Assert.assertEquals(existingIvldItemMaster.getDiscontinuationDate(),
			newIvldItemMaster.getDiscontinuationDate());
		Assert.assertEquals(existingIvldItemMaster.getItemMasterIntfid(),
			newIvldItemMaster.getItemMasterIntfid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemMaster.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldItemMaster.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldItemMaster.getDivestitureDate(),
			newIvldItemMaster.getDivestitureDate());
		Assert.assertEquals(existingIvldItemMaster.getModifiedBy(),
			newIvldItemMaster.getModifiedBy());
		Assert.assertEquals(existingIvldItemMaster.getBaseCpiPeriod(),
			newIvldItemMaster.getBaseCpiPeriod());
		Assert.assertEquals(existingIvldItemMaster.getClottingFactorEndDate(),
			newIvldItemMaster.getClottingFactorEndDate());
		Assert.assertEquals(existingIvldItemMaster.getDosesPerUnit(),
			newIvldItemMaster.getDosesPerUnit());
		Assert.assertEquals(existingIvldItemMaster.getManufacturerId(),
			newIvldItemMaster.getManufacturerId());
		Assert.assertEquals(existingIvldItemMaster.getClottingFactorIndicator(),
			newIvldItemMaster.getClottingFactorIndicator());
		Assert.assertEquals(existingIvldItemMaster.getBatchId(),
			newIvldItemMaster.getBatchId());
		Assert.assertEquals(existingIvldItemMaster.getAcquisitionDate(),
			newIvldItemMaster.getAcquisitionDate());
		Assert.assertEquals(existingIvldItemMaster.getDualPricingIndicator(),
			newIvldItemMaster.getDualPricingIndicator());
		Assert.assertEquals(existingIvldItemMaster.getNonFederalExpirationDate(),
			newIvldItemMaster.getNonFederalExpirationDate());
		Assert.assertEquals(existingIvldItemMaster.getErrorCode(),
			newIvldItemMaster.getErrorCode());
		Assert.assertEquals(existingIvldItemMaster.getNewFormulation(),
			newIvldItemMaster.getNewFormulation());
		Assert.assertEquals(existingIvldItemMaster.getObraBamp(),
			newIvldItemMaster.getObraBamp());
		Assert.assertEquals(existingIvldItemMaster.getBrandId(),
			newIvldItemMaster.getBrandId());
		Assert.assertEquals(existingIvldItemMaster.getItemStatus(),
			newIvldItemMaster.getItemStatus());
		Assert.assertEquals(existingIvldItemMaster.getAuthorizedGenericStartDate(),
			newIvldItemMaster.getAuthorizedGenericStartDate());
		Assert.assertEquals(existingIvldItemMaster.getNewFormulationStartDate(),
			newIvldItemMaster.getNewFormulationStartDate());
		Assert.assertEquals(existingIvldItemMaster.getItemCategory(),
			newIvldItemMaster.getItemCategory());
		Assert.assertEquals(existingIvldItemMaster.getItemEndDate(),
			newIvldItemMaster.getItemEndDate());
		Assert.assertEquals(existingIvldItemMaster.getItemType(),
			newIvldItemMaster.getItemType());
		Assert.assertEquals(existingIvldItemMaster.getPediatricExclusiveEndDate(),
			newIvldItemMaster.getPediatricExclusiveEndDate());
		Assert.assertEquals(existingIvldItemMaster.getOrganizationKey(),
			newIvldItemMaster.getOrganizationKey());
		Assert.assertEquals(existingIvldItemMaster.getFirstSaleDate(),
			newIvldItemMaster.getFirstSaleDate());
		Assert.assertEquals(existingIvldItemMaster.getShelfLifeType(),
			newIvldItemMaster.getShelfLifeType());
		Assert.assertEquals(existingIvldItemMaster.getItemStartDate(),
			newIvldItemMaster.getItemStartDate());
		Assert.assertEquals(existingIvldItemMaster.getItemTypeIndication(),
			newIvldItemMaster.getItemTypeIndication());
		Assert.assertEquals(existingIvldItemMaster.getAcquiredBamp(),
			newIvldItemMaster.getAcquiredBamp());
		Assert.assertEquals(existingIvldItemMaster.getForm(),
			newIvldItemMaster.getForm());
		Assert.assertEquals(existingIvldItemMaster.getItemClass(),
			newIvldItemMaster.getItemClass());
		Assert.assertEquals(existingIvldItemMaster.getManufacturerNo(),
			newIvldItemMaster.getManufacturerNo());
		Assert.assertEquals(existingIvldItemMaster.getPediatricExclusiveIndicator(),
			newIvldItemMaster.getPediatricExclusiveIndicator());
		Assert.assertEquals(existingIvldItemMaster.getPackageSizeCode(),
			newIvldItemMaster.getPackageSizeCode());
		Assert.assertEquals(existingIvldItemMaster.getDisplayBrand(),
			newIvldItemMaster.getDisplayBrand());
		Assert.assertEquals(existingIvldItemMaster.getDra(),
			newIvldItemMaster.getDra());
		Assert.assertEquals(existingIvldItemMaster.getReasonForFailure(),
			newIvldItemMaster.getReasonForFailure());
		Assert.assertEquals(existingIvldItemMaster.getPackageSizeIntroDate(),
			newIvldItemMaster.getPackageSizeIntroDate());
		Assert.assertEquals(existingIvldItemMaster.getUpps(),
			newIvldItemMaster.getUpps());
		Assert.assertEquals(existingIvldItemMaster.getIvldItemMasterSid(),
			newIvldItemMaster.getIvldItemMasterSid());
		Assert.assertEquals(existingIvldItemMaster.getPackageSize(),
			newIvldItemMaster.getPackageSize());
		Assert.assertEquals(existingIvldItemMaster.getShelfLife(),
			newIvldItemMaster.getShelfLife());
		Assert.assertEquals(existingIvldItemMaster.getMarketTerminationDate(),
			newIvldItemMaster.getMarketTerminationDate());
		Assert.assertEquals(existingIvldItemMaster.getCheckRecord(),
			newIvldItemMaster.getCheckRecord());
		Assert.assertEquals(existingIvldItemMaster.getBaseCpiPrecision(),
			newIvldItemMaster.getBaseCpiPrecision());
		Assert.assertEquals(existingIvldItemMaster.getBaselineAmpPrecision(),
			newIvldItemMaster.getBaselineAmpPrecision());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldItemMaster newIvldItemMaster = addIvldItemMaster();

		IvldItemMaster existingIvldItemMaster = _persistence.findByPrimaryKey(newIvldItemMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldItemMaster, newIvldItemMaster);
	}

	@Test(expected = NoSuchIvldItemMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldItemMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_ITEM_MASTER",
			"itemNo", true, "udc6", true, "createdDate", true,
			"newFormulationIndicator", true, "udc5", true,
			"newFormulationEndDate", true, "udc4", true,
			"clottingFactorStartDate", true, "secondaryUom", true, "itemDesc",
			true, "authorizedGenericEndDate", true, "manufacturerName", true,
			"itemName", true, "reprocessedFlag", true, "status", true,
			"baseCpi", true, "baselineAmp", true, "authorizedGeneric", true,
			"therapeuticClass", true, "itemFamilyId", true,
			"pediatricExclusiveStartDate", true, "createdBy", true,
			"primaryUom", true, "ndc9", true, "itemId", true,
			"lastLotExpirationDate", true, "errorField", true, "itemCode",
			true, "strength", true, "modifiedDate", true, "brand", true,
			"ndc8", true, "labelerCode", true, "udc3", true, "source", true,
			"udc2", true, "addChgDelIndicator", true, "udc1", true,
			"acquiredAmp", true, "discontinuationDate", true,
			"itemMasterIntfid", true, "intfInsertedDate", true,
			"divestitureDate", true, "modifiedBy", true, "baseCpiPeriod", true,
			"clottingFactorEndDate", true, "dosesPerUnit", true,
			"manufacturerId", true, "clottingFactorIndicator", true, "batchId",
			true, "acquisitionDate", true, "dualPricingIndicator", true,
			"nonFederalExpirationDate", true, "errorCode", true,
			"newFormulation", true, "obraBamp", true, "brandId", true,
			"itemStatus", true, "authorizedGenericStartDate", true,
			"newFormulationStartDate", true, "itemCategory", true,
			"itemEndDate", true, "itemType", true, "pediatricExclusiveEndDate",
			true, "organizationKey", true, "firstSaleDate", true,
			"shelfLifeType", true, "itemStartDate", true, "itemTypeIndication",
			true, "acquiredBamp", true, "form", true, "itemClass", true,
			"manufacturerNo", true, "pediatricExclusiveIndicator", true,
			"packageSizeCode", true, "displayBrand", true, "dra", true,
			"reasonForFailure", true, "packageSizeIntroDate", true, "upps",
			true, "ivldItemMasterSid", true, "packageSize", true, "shelfLife",
			true, "marketTerminationDate", true, "checkRecord", true,
			"baseCpiPrecision", true, "baselineAmpPrecision", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldItemMaster newIvldItemMaster = addIvldItemMaster();

		IvldItemMaster existingIvldItemMaster = _persistence.fetchByPrimaryKey(newIvldItemMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldItemMaster, newIvldItemMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemMaster missingIvldItemMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldItemMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldItemMaster newIvldItemMaster1 = addIvldItemMaster();
		IvldItemMaster newIvldItemMaster2 = addIvldItemMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemMaster1.getPrimaryKey());
		primaryKeys.add(newIvldItemMaster2.getPrimaryKey());

		Map<Serializable, IvldItemMaster> ivldItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldItemMasters.size());
		Assert.assertEquals(newIvldItemMaster1,
			ivldItemMasters.get(newIvldItemMaster1.getPrimaryKey()));
		Assert.assertEquals(newIvldItemMaster2,
			ivldItemMasters.get(newIvldItemMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldItemMaster> ivldItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldItemMaster newIvldItemMaster = addIvldItemMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldItemMaster> ivldItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemMasters.size());
		Assert.assertEquals(newIvldItemMaster,
			ivldItemMasters.get(newIvldItemMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldItemMaster> ivldItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldItemMaster newIvldItemMaster = addIvldItemMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemMaster.getPrimaryKey());

		Map<Serializable, IvldItemMaster> ivldItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemMasters.size());
		Assert.assertEquals(newIvldItemMaster,
			ivldItemMasters.get(newIvldItemMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldItemMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldItemMaster>() {
				@Override
				public void performAction(IvldItemMaster ivldItemMaster) {
					Assert.assertNotNull(ivldItemMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldItemMaster newIvldItemMaster = addIvldItemMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldItemMasterSid",
				newIvldItemMaster.getIvldItemMasterSid()));

		List<IvldItemMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldItemMaster existingIvldItemMaster = result.get(0);

		Assert.assertEquals(existingIvldItemMaster, newIvldItemMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldItemMasterSid",
				RandomTestUtil.nextInt()));

		List<IvldItemMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldItemMaster newIvldItemMaster = addIvldItemMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemMasterSid"));

		Object newIvldItemMasterSid = newIvldItemMaster.getIvldItemMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldItemMasterSid",
				new Object[] { newIvldItemMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldItemMasterSid = result.get(0);

		Assert.assertEquals(existingIvldItemMasterSid, newIvldItemMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldItemMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldItemMaster addIvldItemMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemMaster ivldItemMaster = _persistence.create(pk);

		ivldItemMaster.setItemNo(RandomTestUtil.randomString());

		ivldItemMaster.setUdc6(RandomTestUtil.randomString());

		ivldItemMaster.setCreatedDate(RandomTestUtil.nextDate());

		ivldItemMaster.setNewFormulationIndicator(RandomTestUtil.randomString());

		ivldItemMaster.setUdc5(RandomTestUtil.randomString());

		ivldItemMaster.setNewFormulationEndDate(RandomTestUtil.randomString());

		ivldItemMaster.setUdc4(RandomTestUtil.randomString());

		ivldItemMaster.setClottingFactorStartDate(RandomTestUtil.randomString());

		ivldItemMaster.setSecondaryUom(RandomTestUtil.randomString());

		ivldItemMaster.setItemDesc(RandomTestUtil.randomString());

		ivldItemMaster.setAuthorizedGenericEndDate(RandomTestUtil.randomString());

		ivldItemMaster.setManufacturerName(RandomTestUtil.randomString());

		ivldItemMaster.setItemName(RandomTestUtil.randomString());

		ivldItemMaster.setReprocessedFlag(RandomTestUtil.randomString());

		ivldItemMaster.setStatus(RandomTestUtil.randomString());

		ivldItemMaster.setBaseCpi(RandomTestUtil.randomString());

		ivldItemMaster.setBaselineAmp(RandomTestUtil.randomString());

		ivldItemMaster.setAuthorizedGeneric(RandomTestUtil.randomString());

		ivldItemMaster.setTherapeuticClass(RandomTestUtil.randomString());

		ivldItemMaster.setItemFamilyId(RandomTestUtil.randomString());

		ivldItemMaster.setPediatricExclusiveStartDate(RandomTestUtil.randomString());

		ivldItemMaster.setCreatedBy(RandomTestUtil.randomString());

		ivldItemMaster.setPrimaryUom(RandomTestUtil.randomString());

		ivldItemMaster.setNdc9(RandomTestUtil.randomString());

		ivldItemMaster.setItemId(RandomTestUtil.randomString());

		ivldItemMaster.setLastLotExpirationDate(RandomTestUtil.randomString());

		ivldItemMaster.setErrorField(RandomTestUtil.randomString());

		ivldItemMaster.setItemCode(RandomTestUtil.randomString());

		ivldItemMaster.setStrength(RandomTestUtil.randomString());

		ivldItemMaster.setModifiedDate(RandomTestUtil.nextDate());

		ivldItemMaster.setBrand(RandomTestUtil.randomString());

		ivldItemMaster.setNdc8(RandomTestUtil.randomString());

		ivldItemMaster.setLabelerCode(RandomTestUtil.randomString());

		ivldItemMaster.setUdc3(RandomTestUtil.randomString());

		ivldItemMaster.setSource(RandomTestUtil.randomString());

		ivldItemMaster.setUdc2(RandomTestUtil.randomString());

		ivldItemMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldItemMaster.setUdc1(RandomTestUtil.randomString());

		ivldItemMaster.setAcquiredAmp(RandomTestUtil.randomString());

		ivldItemMaster.setDiscontinuationDate(RandomTestUtil.randomString());

		ivldItemMaster.setItemMasterIntfid(RandomTestUtil.randomString());

		ivldItemMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldItemMaster.setDivestitureDate(RandomTestUtil.randomString());

		ivldItemMaster.setModifiedBy(RandomTestUtil.randomString());

		ivldItemMaster.setBaseCpiPeriod(RandomTestUtil.randomString());

		ivldItemMaster.setClottingFactorEndDate(RandomTestUtil.randomString());

		ivldItemMaster.setDosesPerUnit(RandomTestUtil.randomString());

		ivldItemMaster.setManufacturerId(RandomTestUtil.randomString());

		ivldItemMaster.setClottingFactorIndicator(RandomTestUtil.randomString());

		ivldItemMaster.setBatchId(RandomTestUtil.randomString());

		ivldItemMaster.setAcquisitionDate(RandomTestUtil.randomString());

		ivldItemMaster.setDualPricingIndicator(RandomTestUtil.randomString());

		ivldItemMaster.setNonFederalExpirationDate(RandomTestUtil.randomString());

		ivldItemMaster.setErrorCode(RandomTestUtil.randomString());

		ivldItemMaster.setNewFormulation(RandomTestUtil.randomString());

		ivldItemMaster.setObraBamp(RandomTestUtil.randomString());

		ivldItemMaster.setBrandId(RandomTestUtil.randomString());

		ivldItemMaster.setItemStatus(RandomTestUtil.randomString());

		ivldItemMaster.setAuthorizedGenericStartDate(RandomTestUtil.randomString());

		ivldItemMaster.setNewFormulationStartDate(RandomTestUtil.randomString());

		ivldItemMaster.setItemCategory(RandomTestUtil.randomString());

		ivldItemMaster.setItemEndDate(RandomTestUtil.randomString());

		ivldItemMaster.setItemType(RandomTestUtil.randomString());

		ivldItemMaster.setPediatricExclusiveEndDate(RandomTestUtil.randomString());

		ivldItemMaster.setOrganizationKey(RandomTestUtil.randomString());

		ivldItemMaster.setFirstSaleDate(RandomTestUtil.randomString());

		ivldItemMaster.setShelfLifeType(RandomTestUtil.randomString());

		ivldItemMaster.setItemStartDate(RandomTestUtil.randomString());

		ivldItemMaster.setItemTypeIndication(RandomTestUtil.randomString());

		ivldItemMaster.setAcquiredBamp(RandomTestUtil.randomString());

		ivldItemMaster.setForm(RandomTestUtil.randomString());

		ivldItemMaster.setItemClass(RandomTestUtil.randomString());

		ivldItemMaster.setManufacturerNo(RandomTestUtil.randomString());

		ivldItemMaster.setPediatricExclusiveIndicator(RandomTestUtil.randomString());

		ivldItemMaster.setPackageSizeCode(RandomTestUtil.randomString());

		ivldItemMaster.setDisplayBrand(RandomTestUtil.randomString());

		ivldItemMaster.setDra(RandomTestUtil.randomString());

		ivldItemMaster.setReasonForFailure(RandomTestUtil.randomString());

		ivldItemMaster.setPackageSizeIntroDate(RandomTestUtil.randomString());

		ivldItemMaster.setUpps(RandomTestUtil.randomString());

		ivldItemMaster.setPackageSize(RandomTestUtil.randomString());

		ivldItemMaster.setShelfLife(RandomTestUtil.randomString());

		ivldItemMaster.setMarketTerminationDate(RandomTestUtil.randomString());

		ivldItemMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		ivldItemMaster.setBaseCpiPrecision(RandomTestUtil.nextInt());

		ivldItemMaster.setBaselineAmpPrecision(RandomTestUtil.nextInt());

		_ivldItemMasters.add(_persistence.update(ivldItemMaster));

		return ivldItemMaster;
	}

	private List<IvldItemMaster> _ivldItemMasters = new ArrayList<IvldItemMaster>();
	private IvldItemMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}