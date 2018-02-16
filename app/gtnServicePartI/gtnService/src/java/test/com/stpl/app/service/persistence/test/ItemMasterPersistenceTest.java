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

package com.stpl.app.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchItemMasterException;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ItemMasterPersistence;
import com.stpl.app.service.persistence.ItemMasterUtil;

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
public class ItemMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemMaster> iterator = _itemMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemMaster itemMaster = _persistence.create(pk);

		Assert.assertNotNull(itemMaster);

		Assert.assertEquals(itemMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemMaster newItemMaster = addItemMaster();

		_persistence.remove(newItemMaster);

		ItemMaster existingItemMaster = _persistence.fetchByPrimaryKey(newItemMaster.getPrimaryKey());

		Assert.assertNull(existingItemMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemMaster newItemMaster = _persistence.create(pk);

		newItemMaster.setItemStatus(RandomTestUtil.nextInt());

		newItemMaster.setItemDesc(RandomTestUtil.randomString());

		newItemMaster.setAuthorizedGenericStartDate(RandomTestUtil.nextDate());

		newItemMaster.setAcquiredAmp(RandomTestUtil.nextDouble());

		newItemMaster.setNewFormulationStartDate(RandomTestUtil.nextDate());

		newItemMaster.setMarketTerminationDate(RandomTestUtil.nextDate());

		newItemMaster.setObraBamp(RandomTestUtil.nextDouble());

		newItemMaster.setModifiedDate(RandomTestUtil.nextDate());

		newItemMaster.setTherapeuticClass(RandomTestUtil.nextInt());

		newItemMaster.setOrganizationKey(RandomTestUtil.nextInt());

		newItemMaster.setAcquiredBamp(RandomTestUtil.nextDouble());

		newItemMaster.setPediatricExclusiveEndDate(RandomTestUtil.nextDate());

		newItemMaster.setSource(RandomTestUtil.randomString());

		newItemMaster.setNewFormulation(RandomTestUtil.randomString());

		newItemMaster.setDivestitureDate(RandomTestUtil.nextDate());

		newItemMaster.setPrimaryUom(RandomTestUtil.nextInt());

		newItemMaster.setNewFormulationEndDate(RandomTestUtil.nextDate());

		newItemMaster.setModifiedBy(RandomTestUtil.nextInt());

		newItemMaster.setInboundStatus(RandomTestUtil.randomString());

		newItemMaster.setPackageSizeCode(RandomTestUtil.randomString());

		newItemMaster.setSecondaryUom(RandomTestUtil.nextInt());

		newItemMaster.setDiscontinuationDate(RandomTestUtil.nextDate());

		newItemMaster.setPackageSizeIntroDate(RandomTestUtil.nextDate());

		newItemMaster.setManufacturerId(RandomTestUtil.randomString());

		newItemMaster.setItemEndDate(RandomTestUtil.nextDate());

		newItemMaster.setItemFamilyId(RandomTestUtil.randomString());

		newItemMaster.setStrength(RandomTestUtil.nextInt());

		newItemMaster.setItemCategory(RandomTestUtil.nextInt());

		newItemMaster.setUpps(RandomTestUtil.nextDouble());

		newItemMaster.setShelfLifeType(RandomTestUtil.nextInt());

		newItemMaster.setPediatricExclusiveIndicator(RandomTestUtil.randomString());

		newItemMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newItemMaster.setItemTypeIndication(RandomTestUtil.randomString());

		newItemMaster.setAcquisitionDate(RandomTestUtil.nextDate());

		newItemMaster.setClottingFactorIndicator(RandomTestUtil.randomString());

		newItemMaster.setForm(RandomTestUtil.nextInt());

		newItemMaster.setItemName(RandomTestUtil.randomString());

		newItemMaster.setShelfLife(RandomTestUtil.randomString());

		newItemMaster.setPediatricExclusiveStartDate(RandomTestUtil.nextDate());

		newItemMaster.setFirstSaleDate(RandomTestUtil.nextDate());

		newItemMaster.setItemType(RandomTestUtil.nextInt());

		newItemMaster.setItemId(RandomTestUtil.randomString());

		newItemMaster.setBrandMasterSid(RandomTestUtil.nextInt());

		newItemMaster.setBaselineAmp(RandomTestUtil.nextDouble());

		newItemMaster.setDualPricingIndicator(RandomTestUtil.randomString());

		newItemMaster.setDosesPerUnit(RandomTestUtil.randomString());

		newItemMaster.setCreatedDate(RandomTestUtil.nextDate());

		newItemMaster.setCreatedBy(RandomTestUtil.nextInt());

		newItemMaster.setAuthorizedGeneric(RandomTestUtil.randomString());

		newItemMaster.setItemStartDate(RandomTestUtil.nextDate());

		newItemMaster.setNdc9(RandomTestUtil.randomString());

		newItemMaster.setAuthorizedGenericEndDate(RandomTestUtil.nextDate());

		newItemMaster.setItemNo(RandomTestUtil.randomString());

		newItemMaster.setPackageSize(RandomTestUtil.randomString());

		newItemMaster.setNdc8(RandomTestUtil.randomString());

		newItemMaster.setBaseCpi(RandomTestUtil.nextDouble());

		newItemMaster.setLabelerCode(RandomTestUtil.randomString());

		newItemMaster.setItemClass(RandomTestUtil.nextInt());

		newItemMaster.setClottingFactorEndDate(RandomTestUtil.nextDate());

		newItemMaster.setDra(RandomTestUtil.nextDouble());

		newItemMaster.setBaseCpiPeriod(RandomTestUtil.nextDate());

		newItemMaster.setNewFormulationIndicator(RandomTestUtil.randomString());

		newItemMaster.setLastLotExpirationDate(RandomTestUtil.nextDate());

		newItemMaster.setBatchId(RandomTestUtil.randomString());

		newItemMaster.setItemCode(RandomTestUtil.randomString());

		newItemMaster.setClottingFactorStartDate(RandomTestUtil.nextDate());

		newItemMaster.setNonFederalExpirationDate(RandomTestUtil.nextDate());

		newItemMaster.setInternalNotes(RandomTestUtil.randomString());

		newItemMaster.setBaseCpiPrecision(RandomTestUtil.nextInt());

		newItemMaster.setBaselineAmpPrecision(RandomTestUtil.nextInt());

		_itemMasters.add(_persistence.update(newItemMaster));

		ItemMaster existingItemMaster = _persistence.findByPrimaryKey(newItemMaster.getPrimaryKey());

		Assert.assertEquals(existingItemMaster.getItemStatus(),
			newItemMaster.getItemStatus());
		Assert.assertEquals(existingItemMaster.getItemDesc(),
			newItemMaster.getItemDesc());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getAuthorizedGenericStartDate()),
			Time.getShortTimestamp(
				newItemMaster.getAuthorizedGenericStartDate()));
		AssertUtils.assertEquals(existingItemMaster.getAcquiredAmp(),
			newItemMaster.getAcquiredAmp());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getNewFormulationStartDate()),
			Time.getShortTimestamp(newItemMaster.getNewFormulationStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getMarketTerminationDate()),
			Time.getShortTimestamp(newItemMaster.getMarketTerminationDate()));
		AssertUtils.assertEquals(existingItemMaster.getObraBamp(),
			newItemMaster.getObraBamp());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getModifiedDate()),
			Time.getShortTimestamp(newItemMaster.getModifiedDate()));
		Assert.assertEquals(existingItemMaster.getTherapeuticClass(),
			newItemMaster.getTherapeuticClass());
		Assert.assertEquals(existingItemMaster.getOrganizationKey(),
			newItemMaster.getOrganizationKey());
		AssertUtils.assertEquals(existingItemMaster.getAcquiredBamp(),
			newItemMaster.getAcquiredBamp());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getPediatricExclusiveEndDate()),
			Time.getShortTimestamp(newItemMaster.getPediatricExclusiveEndDate()));
		Assert.assertEquals(existingItemMaster.getSource(),
			newItemMaster.getSource());
		Assert.assertEquals(existingItemMaster.getNewFormulation(),
			newItemMaster.getNewFormulation());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getDivestitureDate()),
			Time.getShortTimestamp(newItemMaster.getDivestitureDate()));
		Assert.assertEquals(existingItemMaster.getPrimaryUom(),
			newItemMaster.getPrimaryUom());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getNewFormulationEndDate()),
			Time.getShortTimestamp(newItemMaster.getNewFormulationEndDate()));
		Assert.assertEquals(existingItemMaster.getModifiedBy(),
			newItemMaster.getModifiedBy());
		Assert.assertEquals(existingItemMaster.getInboundStatus(),
			newItemMaster.getInboundStatus());
		Assert.assertEquals(existingItemMaster.getPackageSizeCode(),
			newItemMaster.getPackageSizeCode());
		Assert.assertEquals(existingItemMaster.getSecondaryUom(),
			newItemMaster.getSecondaryUom());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getDiscontinuationDate()),
			Time.getShortTimestamp(newItemMaster.getDiscontinuationDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getPackageSizeIntroDate()),
			Time.getShortTimestamp(newItemMaster.getPackageSizeIntroDate()));
		Assert.assertEquals(existingItemMaster.getManufacturerId(),
			newItemMaster.getManufacturerId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getItemEndDate()),
			Time.getShortTimestamp(newItemMaster.getItemEndDate()));
		Assert.assertEquals(existingItemMaster.getItemFamilyId(),
			newItemMaster.getItemFamilyId());
		Assert.assertEquals(existingItemMaster.getStrength(),
			newItemMaster.getStrength());
		Assert.assertEquals(existingItemMaster.getItemCategory(),
			newItemMaster.getItemCategory());
		AssertUtils.assertEquals(existingItemMaster.getUpps(),
			newItemMaster.getUpps());
		Assert.assertEquals(existingItemMaster.getShelfLifeType(),
			newItemMaster.getShelfLifeType());
		Assert.assertEquals(existingItemMaster.getPediatricExclusiveIndicator(),
			newItemMaster.getPediatricExclusiveIndicator());
		Assert.assertEquals(existingItemMaster.getRecordLockStatus(),
			newItemMaster.getRecordLockStatus());
		Assert.assertEquals(existingItemMaster.getItemTypeIndication(),
			newItemMaster.getItemTypeIndication());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getAcquisitionDate()),
			Time.getShortTimestamp(newItemMaster.getAcquisitionDate()));
		Assert.assertEquals(existingItemMaster.getClottingFactorIndicator(),
			newItemMaster.getClottingFactorIndicator());
		Assert.assertEquals(existingItemMaster.getForm(),
			newItemMaster.getForm());
		Assert.assertEquals(existingItemMaster.getItemName(),
			newItemMaster.getItemName());
		Assert.assertEquals(existingItemMaster.getShelfLife(),
			newItemMaster.getShelfLife());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getPediatricExclusiveStartDate()),
			Time.getShortTimestamp(
				newItemMaster.getPediatricExclusiveStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getFirstSaleDate()),
			Time.getShortTimestamp(newItemMaster.getFirstSaleDate()));
		Assert.assertEquals(existingItemMaster.getItemMasterSid(),
			newItemMaster.getItemMasterSid());
		Assert.assertEquals(existingItemMaster.getItemType(),
			newItemMaster.getItemType());
		Assert.assertEquals(existingItemMaster.getItemId(),
			newItemMaster.getItemId());
		Assert.assertEquals(existingItemMaster.getBrandMasterSid(),
			newItemMaster.getBrandMasterSid());
		AssertUtils.assertEquals(existingItemMaster.getBaselineAmp(),
			newItemMaster.getBaselineAmp());
		Assert.assertEquals(existingItemMaster.getDualPricingIndicator(),
			newItemMaster.getDualPricingIndicator());
		Assert.assertEquals(existingItemMaster.getDosesPerUnit(),
			newItemMaster.getDosesPerUnit());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getCreatedDate()),
			Time.getShortTimestamp(newItemMaster.getCreatedDate()));
		Assert.assertEquals(existingItemMaster.getCreatedBy(),
			newItemMaster.getCreatedBy());
		Assert.assertEquals(existingItemMaster.getAuthorizedGeneric(),
			newItemMaster.getAuthorizedGeneric());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getItemStartDate()),
			Time.getShortTimestamp(newItemMaster.getItemStartDate()));
		Assert.assertEquals(existingItemMaster.getNdc9(),
			newItemMaster.getNdc9());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getAuthorizedGenericEndDate()),
			Time.getShortTimestamp(newItemMaster.getAuthorizedGenericEndDate()));
		Assert.assertEquals(existingItemMaster.getItemNo(),
			newItemMaster.getItemNo());
		Assert.assertEquals(existingItemMaster.getPackageSize(),
			newItemMaster.getPackageSize());
		Assert.assertEquals(existingItemMaster.getNdc8(),
			newItemMaster.getNdc8());
		AssertUtils.assertEquals(existingItemMaster.getBaseCpi(),
			newItemMaster.getBaseCpi());
		Assert.assertEquals(existingItemMaster.getLabelerCode(),
			newItemMaster.getLabelerCode());
		Assert.assertEquals(existingItemMaster.getItemClass(),
			newItemMaster.getItemClass());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getClottingFactorEndDate()),
			Time.getShortTimestamp(newItemMaster.getClottingFactorEndDate()));
		AssertUtils.assertEquals(existingItemMaster.getDra(),
			newItemMaster.getDra());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getBaseCpiPeriod()),
			Time.getShortTimestamp(newItemMaster.getBaseCpiPeriod()));
		Assert.assertEquals(existingItemMaster.getNewFormulationIndicator(),
			newItemMaster.getNewFormulationIndicator());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getLastLotExpirationDate()),
			Time.getShortTimestamp(newItemMaster.getLastLotExpirationDate()));
		Assert.assertEquals(existingItemMaster.getBatchId(),
			newItemMaster.getBatchId());
		Assert.assertEquals(existingItemMaster.getItemCode(),
			newItemMaster.getItemCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getClottingFactorStartDate()),
			Time.getShortTimestamp(newItemMaster.getClottingFactorStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemMaster.getNonFederalExpirationDate()),
			Time.getShortTimestamp(newItemMaster.getNonFederalExpirationDate()));
		Assert.assertEquals(existingItemMaster.getInternalNotes(),
			newItemMaster.getInternalNotes());
		Assert.assertEquals(existingItemMaster.getBaseCpiPrecision(),
			newItemMaster.getBaseCpiPrecision());
		Assert.assertEquals(existingItemMaster.getBaselineAmpPrecision(),
			newItemMaster.getBaselineAmpPrecision());
	}

	@Test
	public void testCountByItemNo() throws Exception {
		_persistence.countByItemNo(StringPool.BLANK);

		_persistence.countByItemNo(StringPool.NULL);

		_persistence.countByItemNo((String)null);
	}

	@Test
	public void testCountByItemId() throws Exception {
		_persistence.countByItemId(StringPool.BLANK);

		_persistence.countByItemId(StringPool.NULL);

		_persistence.countByItemId((String)null);
	}

	@Test
	public void testCountByItemName() throws Exception {
		_persistence.countByItemName(StringPool.BLANK);

		_persistence.countByItemName(StringPool.NULL);

		_persistence.countByItemName((String)null);
	}

	@Test
	public void testCountByItemType() throws Exception {
		_persistence.countByItemType(RandomTestUtil.nextInt());

		_persistence.countByItemType(0);
	}

	@Test
	public void testCountByItemStatus() throws Exception {
		_persistence.countByItemStatus(RandomTestUtil.nextInt());

		_persistence.countByItemStatus(0);
	}

	@Test
	public void testCountByManufacturerNo() throws Exception {
		_persistence.countByManufacturerNo(StringPool.BLANK);

		_persistence.countByManufacturerNo(StringPool.NULL);

		_persistence.countByManufacturerNo((String)null);
	}

	@Test
	public void testCountByForm() throws Exception {
		_persistence.countByForm(RandomTestUtil.nextInt());

		_persistence.countByForm(0);
	}

	@Test
	public void testCountByStrength() throws Exception {
		_persistence.countByStrength(RandomTestUtil.nextInt());

		_persistence.countByStrength(0);
	}

	@Test
	public void testCountByPrimaryUom() throws Exception {
		_persistence.countByPrimaryUom(RandomTestUtil.nextInt());

		_persistence.countByPrimaryUom(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemMaster newItemMaster = addItemMaster();

		ItemMaster existingItemMaster = _persistence.findByPrimaryKey(newItemMaster.getPrimaryKey());

		Assert.assertEquals(existingItemMaster, newItemMaster);
	}

	@Test(expected = NoSuchItemMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_MASTER", "itemStatus",
			true, "itemDesc", true, "authorizedGenericStartDate", true,
			"acquiredAmp", true, "newFormulationStartDate", true,
			"marketTerminationDate", true, "obraBamp", true, "modifiedDate",
			true, "therapeuticClass", true, "organizationKey", true,
			"acquiredBamp", true, "pediatricExclusiveEndDate", true, "source",
			true, "newFormulation", true, "divestitureDate", true,
			"primaryUom", true, "newFormulationEndDate", true, "modifiedBy",
			true, "inboundStatus", true, "packageSizeCode", true,
			"secondaryUom", true, "discontinuationDate", true,
			"packageSizeIntroDate", true, "manufacturerId", true,
			"itemEndDate", true, "itemFamilyId", true, "strength", true,
			"itemCategory", true, "upps", true, "shelfLifeType", true,
			"pediatricExclusiveIndicator", true, "recordLockStatus", true,
			"itemTypeIndication", true, "acquisitionDate", true,
			"clottingFactorIndicator", true, "form", true, "itemName", true,
			"shelfLife", true, "pediatricExclusiveStartDate", true,
			"firstSaleDate", true, "itemMasterSid", true, "itemType", true,
			"itemId", true, "brandMasterSid", true, "baselineAmp", true,
			"dualPricingIndicator", true, "dosesPerUnit", true, "createdDate",
			true, "createdBy", true, "authorizedGeneric", true,
			"itemStartDate", true, "ndc9", true, "authorizedGenericEndDate",
			true, "itemNo", true, "packageSize", true, "ndc8", true, "baseCpi",
			true, "labelerCode", true, "itemClass", true,
			"clottingFactorEndDate", true, "dra", true, "baseCpiPeriod", true,
			"newFormulationIndicator", true, "lastLotExpirationDate", true,
			"batchId", true, "itemCode", true, "clottingFactorStartDate", true,
			"nonFederalExpirationDate", true, "internalNotes", true,
			"baseCpiPrecision", true, "baselineAmpPrecision", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemMaster newItemMaster = addItemMaster();

		ItemMaster existingItemMaster = _persistence.fetchByPrimaryKey(newItemMaster.getPrimaryKey());

		Assert.assertEquals(existingItemMaster, newItemMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemMaster missingItemMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemMaster newItemMaster1 = addItemMaster();
		ItemMaster newItemMaster2 = addItemMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemMaster1.getPrimaryKey());
		primaryKeys.add(newItemMaster2.getPrimaryKey());

		Map<Serializable, ItemMaster> itemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemMasters.size());
		Assert.assertEquals(newItemMaster1,
			itemMasters.get(newItemMaster1.getPrimaryKey()));
		Assert.assertEquals(newItemMaster2,
			itemMasters.get(newItemMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemMaster> itemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemMaster newItemMaster = addItemMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemMaster> itemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemMasters.size());
		Assert.assertEquals(newItemMaster,
			itemMasters.get(newItemMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemMaster> itemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemMaster newItemMaster = addItemMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemMaster.getPrimaryKey());

		Map<Serializable, ItemMaster> itemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemMasters.size());
		Assert.assertEquals(newItemMaster,
			itemMasters.get(newItemMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemMaster>() {
				@Override
				public void performAction(ItemMaster itemMaster) {
					Assert.assertNotNull(itemMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemMaster newItemMaster = addItemMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid",
				newItemMaster.getItemMasterSid()));

		List<ItemMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemMaster existingItemMaster = result.get(0);

		Assert.assertEquals(existingItemMaster, newItemMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid",
				RandomTestUtil.nextInt()));

		List<ItemMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemMaster newItemMaster = addItemMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemMasterSid"));

		Object newItemMasterSid = newItemMaster.getItemMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid",
				new Object[] { newItemMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemMasterSid = result.get(0);

		Assert.assertEquals(existingItemMasterSid, newItemMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ItemMaster addItemMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemMaster itemMaster = _persistence.create(pk);

		itemMaster.setItemStatus(RandomTestUtil.nextInt());

		itemMaster.setItemDesc(RandomTestUtil.randomString());

		itemMaster.setAuthorizedGenericStartDate(RandomTestUtil.nextDate());

		itemMaster.setAcquiredAmp(RandomTestUtil.nextDouble());

		itemMaster.setNewFormulationStartDate(RandomTestUtil.nextDate());

		itemMaster.setMarketTerminationDate(RandomTestUtil.nextDate());

		itemMaster.setObraBamp(RandomTestUtil.nextDouble());

		itemMaster.setModifiedDate(RandomTestUtil.nextDate());

		itemMaster.setTherapeuticClass(RandomTestUtil.nextInt());

		itemMaster.setOrganizationKey(RandomTestUtil.nextInt());

		itemMaster.setAcquiredBamp(RandomTestUtil.nextDouble());

		itemMaster.setPediatricExclusiveEndDate(RandomTestUtil.nextDate());

		itemMaster.setSource(RandomTestUtil.randomString());

		itemMaster.setNewFormulation(RandomTestUtil.randomString());

		itemMaster.setDivestitureDate(RandomTestUtil.nextDate());

		itemMaster.setPrimaryUom(RandomTestUtil.nextInt());

		itemMaster.setNewFormulationEndDate(RandomTestUtil.nextDate());

		itemMaster.setModifiedBy(RandomTestUtil.nextInt());

		itemMaster.setInboundStatus(RandomTestUtil.randomString());

		itemMaster.setPackageSizeCode(RandomTestUtil.randomString());

		itemMaster.setSecondaryUom(RandomTestUtil.nextInt());

		itemMaster.setDiscontinuationDate(RandomTestUtil.nextDate());

		itemMaster.setPackageSizeIntroDate(RandomTestUtil.nextDate());

		itemMaster.setManufacturerId(RandomTestUtil.randomString());

		itemMaster.setItemEndDate(RandomTestUtil.nextDate());

		itemMaster.setItemFamilyId(RandomTestUtil.randomString());

		itemMaster.setStrength(RandomTestUtil.nextInt());

		itemMaster.setItemCategory(RandomTestUtil.nextInt());

		itemMaster.setUpps(RandomTestUtil.nextDouble());

		itemMaster.setShelfLifeType(RandomTestUtil.nextInt());

		itemMaster.setPediatricExclusiveIndicator(RandomTestUtil.randomString());

		itemMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		itemMaster.setItemTypeIndication(RandomTestUtil.randomString());

		itemMaster.setAcquisitionDate(RandomTestUtil.nextDate());

		itemMaster.setClottingFactorIndicator(RandomTestUtil.randomString());

		itemMaster.setForm(RandomTestUtil.nextInt());

		itemMaster.setItemName(RandomTestUtil.randomString());

		itemMaster.setShelfLife(RandomTestUtil.randomString());

		itemMaster.setPediatricExclusiveStartDate(RandomTestUtil.nextDate());

		itemMaster.setFirstSaleDate(RandomTestUtil.nextDate());

		itemMaster.setItemType(RandomTestUtil.nextInt());

		itemMaster.setItemId(RandomTestUtil.randomString());

		itemMaster.setBrandMasterSid(RandomTestUtil.nextInt());

		itemMaster.setBaselineAmp(RandomTestUtil.nextDouble());

		itemMaster.setDualPricingIndicator(RandomTestUtil.randomString());

		itemMaster.setDosesPerUnit(RandomTestUtil.randomString());

		itemMaster.setCreatedDate(RandomTestUtil.nextDate());

		itemMaster.setCreatedBy(RandomTestUtil.nextInt());

		itemMaster.setAuthorizedGeneric(RandomTestUtil.randomString());

		itemMaster.setItemStartDate(RandomTestUtil.nextDate());

		itemMaster.setNdc9(RandomTestUtil.randomString());

		itemMaster.setAuthorizedGenericEndDate(RandomTestUtil.nextDate());

		itemMaster.setItemNo(RandomTestUtil.randomString());

		itemMaster.setPackageSize(RandomTestUtil.randomString());

		itemMaster.setNdc8(RandomTestUtil.randomString());

		itemMaster.setBaseCpi(RandomTestUtil.nextDouble());

		itemMaster.setLabelerCode(RandomTestUtil.randomString());

		itemMaster.setItemClass(RandomTestUtil.nextInt());

		itemMaster.setClottingFactorEndDate(RandomTestUtil.nextDate());

		itemMaster.setDra(RandomTestUtil.nextDouble());

		itemMaster.setBaseCpiPeriod(RandomTestUtil.nextDate());

		itemMaster.setNewFormulationIndicator(RandomTestUtil.randomString());

		itemMaster.setLastLotExpirationDate(RandomTestUtil.nextDate());

		itemMaster.setBatchId(RandomTestUtil.randomString());

		itemMaster.setItemCode(RandomTestUtil.randomString());

		itemMaster.setClottingFactorStartDate(RandomTestUtil.nextDate());

		itemMaster.setNonFederalExpirationDate(RandomTestUtil.nextDate());

		itemMaster.setInternalNotes(RandomTestUtil.randomString());

		itemMaster.setBaseCpiPrecision(RandomTestUtil.nextInt());

		itemMaster.setBaselineAmpPrecision(RandomTestUtil.nextInt());

		_itemMasters.add(_persistence.update(itemMaster));

		return itemMaster;
	}

	private List<ItemMaster> _itemMasters = new ArrayList<ItemMaster>();
	private ItemMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}