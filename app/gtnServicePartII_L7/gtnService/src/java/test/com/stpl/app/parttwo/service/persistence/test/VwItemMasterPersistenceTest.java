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
import com.liferay.portal.kernel.test.AssertUtils;
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

import com.stpl.app.parttwo.exception.NoSuchVwItemMasterException;
import com.stpl.app.parttwo.model.VwItemMaster;
import com.stpl.app.parttwo.service.VwItemMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwItemMasterPersistence;
import com.stpl.app.parttwo.service.persistence.VwItemMasterUtil;

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
public class VwItemMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwItemMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwItemMaster> iterator = _vwItemMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemMaster vwItemMaster = _persistence.create(pk);

		Assert.assertNotNull(vwItemMaster);

		Assert.assertEquals(vwItemMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwItemMaster newVwItemMaster = addVwItemMaster();

		_persistence.remove(newVwItemMaster);

		VwItemMaster existingVwItemMaster = _persistence.fetchByPrimaryKey(newVwItemMaster.getPrimaryKey());

		Assert.assertNull(existingVwItemMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwItemMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemMaster newVwItemMaster = _persistence.create(pk);

		newVwItemMaster.setItemStatus(RandomTestUtil.randomString());

		newVwItemMaster.setItemDesc(RandomTestUtil.randomString());

		newVwItemMaster.setAcquiredAmp(RandomTestUtil.randomString());

		newVwItemMaster.setAuthorizedGenericStartDate(RandomTestUtil.nextDate());

		newVwItemMaster.setNewFormulationStartDate(RandomTestUtil.nextDate());

		newVwItemMaster.setMarketTerminationDate(RandomTestUtil.nextDate());

		newVwItemMaster.setObraBamp(RandomTestUtil.randomString());

		newVwItemMaster.setBrand(RandomTestUtil.randomString());

		newVwItemMaster.setManufacturerNo(RandomTestUtil.randomString());

		newVwItemMaster.setModifiedDate(RandomTestUtil.nextDate());

		newVwItemMaster.setTherapeuticClass(RandomTestUtil.randomString());

		newVwItemMaster.setOrganizationKey(RandomTestUtil.randomString());

		newVwItemMaster.setAcquiredBamp(RandomTestUtil.randomString());

		newVwItemMaster.setPediatricExclusiveEndDate(RandomTestUtil.nextDate());

		newVwItemMaster.setSource(RandomTestUtil.randomString());

		newVwItemMaster.setNewFormulation(RandomTestUtil.randomString());

		newVwItemMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwItemMaster.setDivestitureDate(RandomTestUtil.nextDate());

		newVwItemMaster.setShelfLife(RandomTestUtil.randomString());

		newVwItemMaster.setPrimaryUom(RandomTestUtil.randomString());

		newVwItemMaster.setNewFormulationEndDate(RandomTestUtil.nextDate());

		newVwItemMaster.setModifiedBy(RandomTestUtil.randomString());

		newVwItemMaster.setPackageSizeCode(RandomTestUtil.randomString());

		newVwItemMaster.setSecondaryUom(RandomTestUtil.randomString());

		newVwItemMaster.setUdc6(RandomTestUtil.randomString());

		newVwItemMaster.setUdc5(RandomTestUtil.randomString());

		newVwItemMaster.setDiscontinuationDate(RandomTestUtil.nextDate());

		newVwItemMaster.setUdc4(RandomTestUtil.randomString());

		newVwItemMaster.setUdc1(RandomTestUtil.randomString());

		newVwItemMaster.setUdc2(RandomTestUtil.randomString());

		newVwItemMaster.setPackageSizeIntroDate(RandomTestUtil.nextDate());

		newVwItemMaster.setUdc3(RandomTestUtil.randomString());

		newVwItemMaster.setItemEndDate(RandomTestUtil.nextDate());

		newVwItemMaster.setManufacturerId(RandomTestUtil.randomString());

		newVwItemMaster.setItemFamilyId(RandomTestUtil.randomString());

		newVwItemMaster.setStrength(RandomTestUtil.randomString());

		newVwItemMaster.setItemCategory(RandomTestUtil.randomString());

		newVwItemMaster.setUpps(RandomTestUtil.nextDouble());

		newVwItemMaster.setShelfLifeType(RandomTestUtil.randomString());

		newVwItemMaster.setPediatricExclusiveIndicator(RandomTestUtil.randomString());

		newVwItemMaster.setItemTypeIndication(RandomTestUtil.randomString());

		newVwItemMaster.setAcquisitionDate(RandomTestUtil.nextDate());

		newVwItemMaster.setClottingFactorIndicator(RandomTestUtil.randomString());

		newVwItemMaster.setForm(RandomTestUtil.randomString());

		newVwItemMaster.setItemName(RandomTestUtil.randomString());

		newVwItemMaster.setManufacturerName(RandomTestUtil.randomString());

		newVwItemMaster.setPediatricExclusiveStartDate(RandomTestUtil.nextDate());

		newVwItemMaster.setFirstSaleDate(RandomTestUtil.nextDate());

		newVwItemMaster.setItemType(RandomTestUtil.randomString());

		newVwItemMaster.setItemId(RandomTestUtil.randomString());

		newVwItemMaster.setBaselineAmp(RandomTestUtil.randomString());

		newVwItemMaster.setDosesPerUnit(RandomTestUtil.randomString());

		newVwItemMaster.setDualPricingIndicator(RandomTestUtil.randomString());

		newVwItemMaster.setBaseCpi(RandomTestUtil.randomString());

		newVwItemMaster.setCreatedDate(RandomTestUtil.nextDate());

		newVwItemMaster.setCreatedBy(RandomTestUtil.randomString());

		newVwItemMaster.setItemStartDate(RandomTestUtil.nextDate());

		newVwItemMaster.setAuthorizedGeneric(RandomTestUtil.randomString());

		newVwItemMaster.setNdc9(RandomTestUtil.randomString());

		newVwItemMaster.setAuthorizedGenericEndDate(RandomTestUtil.nextDate());

		newVwItemMaster.setItemNo(RandomTestUtil.randomString());

		newVwItemMaster.setPackageSize(RandomTestUtil.randomString());

		newVwItemMaster.setNdc8(RandomTestUtil.randomString());

		newVwItemMaster.setItemClass(RandomTestUtil.randomString());

		newVwItemMaster.setLabelerCode(RandomTestUtil.randomString());

		newVwItemMaster.setDisplayBrand(RandomTestUtil.randomString());

		newVwItemMaster.setClottingFactorEndDate(RandomTestUtil.nextDate());

		newVwItemMaster.setDra(RandomTestUtil.randomString());

		newVwItemMaster.setBrandId(RandomTestUtil.randomString());

		newVwItemMaster.setBaseCpiPeriod(RandomTestUtil.nextDate());

		newVwItemMaster.setNewFormulationIndicator(RandomTestUtil.randomString());

		newVwItemMaster.setLastLotExpirationDate(RandomTestUtil.nextDate());

		newVwItemMaster.setBatchId(RandomTestUtil.randomString());

		newVwItemMaster.setItemCode(RandomTestUtil.randomString());

		newVwItemMaster.setClottingFactorStartDate(RandomTestUtil.nextDate());

		newVwItemMaster.setNonFederalExpirationDate(RandomTestUtil.nextDate());

		newVwItemMaster.setBaseCpiPrecision(RandomTestUtil.nextInt());

		newVwItemMaster.setBaselineAmpPrecision(RandomTestUtil.nextInt());

		_vwItemMasters.add(_persistence.update(newVwItemMaster));

		VwItemMaster existingVwItemMaster = _persistence.findByPrimaryKey(newVwItemMaster.getPrimaryKey());

		Assert.assertEquals(existingVwItemMaster.getItemStatus(),
			newVwItemMaster.getItemStatus());
		Assert.assertEquals(existingVwItemMaster.getItemDesc(),
			newVwItemMaster.getItemDesc());
		Assert.assertEquals(existingVwItemMaster.getAcquiredAmp(),
			newVwItemMaster.getAcquiredAmp());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getAuthorizedGenericStartDate()),
			Time.getShortTimestamp(
				newVwItemMaster.getAuthorizedGenericStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getNewFormulationStartDate()),
			Time.getShortTimestamp(newVwItemMaster.getNewFormulationStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getMarketTerminationDate()),
			Time.getShortTimestamp(newVwItemMaster.getMarketTerminationDate()));
		Assert.assertEquals(existingVwItemMaster.getObraBamp(),
			newVwItemMaster.getObraBamp());
		Assert.assertEquals(existingVwItemMaster.getBrand(),
			newVwItemMaster.getBrand());
		Assert.assertEquals(existingVwItemMaster.getManufacturerNo(),
			newVwItemMaster.getManufacturerNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getModifiedDate()),
			Time.getShortTimestamp(newVwItemMaster.getModifiedDate()));
		Assert.assertEquals(existingVwItemMaster.getTherapeuticClass(),
			newVwItemMaster.getTherapeuticClass());
		Assert.assertEquals(existingVwItemMaster.getOrganizationKey(),
			newVwItemMaster.getOrganizationKey());
		Assert.assertEquals(existingVwItemMaster.getAcquiredBamp(),
			newVwItemMaster.getAcquiredBamp());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getPediatricExclusiveEndDate()),
			Time.getShortTimestamp(
				newVwItemMaster.getPediatricExclusiveEndDate()));
		Assert.assertEquals(existingVwItemMaster.getSource(),
			newVwItemMaster.getSource());
		Assert.assertEquals(existingVwItemMaster.getNewFormulation(),
			newVwItemMaster.getNewFormulation());
		Assert.assertEquals(existingVwItemMaster.getAddChgDelIndicator(),
			newVwItemMaster.getAddChgDelIndicator());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getDivestitureDate()),
			Time.getShortTimestamp(newVwItemMaster.getDivestitureDate()));
		Assert.assertEquals(existingVwItemMaster.getShelfLife(),
			newVwItemMaster.getShelfLife());
		Assert.assertEquals(existingVwItemMaster.getPrimaryUom(),
			newVwItemMaster.getPrimaryUom());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getNewFormulationEndDate()),
			Time.getShortTimestamp(newVwItemMaster.getNewFormulationEndDate()));
		Assert.assertEquals(existingVwItemMaster.getModifiedBy(),
			newVwItemMaster.getModifiedBy());
		Assert.assertEquals(existingVwItemMaster.getPackageSizeCode(),
			newVwItemMaster.getPackageSizeCode());
		Assert.assertEquals(existingVwItemMaster.getSecondaryUom(),
			newVwItemMaster.getSecondaryUom());
		Assert.assertEquals(existingVwItemMaster.getUdc6(),
			newVwItemMaster.getUdc6());
		Assert.assertEquals(existingVwItemMaster.getUdc5(),
			newVwItemMaster.getUdc5());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getDiscontinuationDate()),
			Time.getShortTimestamp(newVwItemMaster.getDiscontinuationDate()));
		Assert.assertEquals(existingVwItemMaster.getUdc4(),
			newVwItemMaster.getUdc4());
		Assert.assertEquals(existingVwItemMaster.getUdc1(),
			newVwItemMaster.getUdc1());
		Assert.assertEquals(existingVwItemMaster.getUdc2(),
			newVwItemMaster.getUdc2());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getPackageSizeIntroDate()),
			Time.getShortTimestamp(newVwItemMaster.getPackageSizeIntroDate()));
		Assert.assertEquals(existingVwItemMaster.getUdc3(),
			newVwItemMaster.getUdc3());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getItemEndDate()),
			Time.getShortTimestamp(newVwItemMaster.getItemEndDate()));
		Assert.assertEquals(existingVwItemMaster.getManufacturerId(),
			newVwItemMaster.getManufacturerId());
		Assert.assertEquals(existingVwItemMaster.getItemFamilyId(),
			newVwItemMaster.getItemFamilyId());
		Assert.assertEquals(existingVwItemMaster.getStrength(),
			newVwItemMaster.getStrength());
		Assert.assertEquals(existingVwItemMaster.getItemCategory(),
			newVwItemMaster.getItemCategory());
		AssertUtils.assertEquals(existingVwItemMaster.getUpps(),
			newVwItemMaster.getUpps());
		Assert.assertEquals(existingVwItemMaster.getShelfLifeType(),
			newVwItemMaster.getShelfLifeType());
		Assert.assertEquals(existingVwItemMaster.getPediatricExclusiveIndicator(),
			newVwItemMaster.getPediatricExclusiveIndicator());
		Assert.assertEquals(existingVwItemMaster.getItemTypeIndication(),
			newVwItemMaster.getItemTypeIndication());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getAcquisitionDate()),
			Time.getShortTimestamp(newVwItemMaster.getAcquisitionDate()));
		Assert.assertEquals(existingVwItemMaster.getClottingFactorIndicator(),
			newVwItemMaster.getClottingFactorIndicator());
		Assert.assertEquals(existingVwItemMaster.getForm(),
			newVwItemMaster.getForm());
		Assert.assertEquals(existingVwItemMaster.getItemName(),
			newVwItemMaster.getItemName());
		Assert.assertEquals(existingVwItemMaster.getManufacturerName(),
			newVwItemMaster.getManufacturerName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getPediatricExclusiveStartDate()),
			Time.getShortTimestamp(
				newVwItemMaster.getPediatricExclusiveStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getFirstSaleDate()),
			Time.getShortTimestamp(newVwItemMaster.getFirstSaleDate()));
		Assert.assertEquals(existingVwItemMaster.getItemMasterSid(),
			newVwItemMaster.getItemMasterSid());
		Assert.assertEquals(existingVwItemMaster.getItemType(),
			newVwItemMaster.getItemType());
		Assert.assertEquals(existingVwItemMaster.getItemId(),
			newVwItemMaster.getItemId());
		Assert.assertEquals(existingVwItemMaster.getBaselineAmp(),
			newVwItemMaster.getBaselineAmp());
		Assert.assertEquals(existingVwItemMaster.getDosesPerUnit(),
			newVwItemMaster.getDosesPerUnit());
		Assert.assertEquals(existingVwItemMaster.getDualPricingIndicator(),
			newVwItemMaster.getDualPricingIndicator());
		Assert.assertEquals(existingVwItemMaster.getBaseCpi(),
			newVwItemMaster.getBaseCpi());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getCreatedDate()),
			Time.getShortTimestamp(newVwItemMaster.getCreatedDate()));
		Assert.assertEquals(existingVwItemMaster.getCreatedBy(),
			newVwItemMaster.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getItemStartDate()),
			Time.getShortTimestamp(newVwItemMaster.getItemStartDate()));
		Assert.assertEquals(existingVwItemMaster.getAuthorizedGeneric(),
			newVwItemMaster.getAuthorizedGeneric());
		Assert.assertEquals(existingVwItemMaster.getNdc9(),
			newVwItemMaster.getNdc9());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getAuthorizedGenericEndDate()),
			Time.getShortTimestamp(
				newVwItemMaster.getAuthorizedGenericEndDate()));
		Assert.assertEquals(existingVwItemMaster.getItemNo(),
			newVwItemMaster.getItemNo());
		Assert.assertEquals(existingVwItemMaster.getPackageSize(),
			newVwItemMaster.getPackageSize());
		Assert.assertEquals(existingVwItemMaster.getNdc8(),
			newVwItemMaster.getNdc8());
		Assert.assertEquals(existingVwItemMaster.getItemClass(),
			newVwItemMaster.getItemClass());
		Assert.assertEquals(existingVwItemMaster.getLabelerCode(),
			newVwItemMaster.getLabelerCode());
		Assert.assertEquals(existingVwItemMaster.getDisplayBrand(),
			newVwItemMaster.getDisplayBrand());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getClottingFactorEndDate()),
			Time.getShortTimestamp(newVwItemMaster.getClottingFactorEndDate()));
		Assert.assertEquals(existingVwItemMaster.getDra(),
			newVwItemMaster.getDra());
		Assert.assertEquals(existingVwItemMaster.getBrandId(),
			newVwItemMaster.getBrandId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getBaseCpiPeriod()),
			Time.getShortTimestamp(newVwItemMaster.getBaseCpiPeriod()));
		Assert.assertEquals(existingVwItemMaster.getNewFormulationIndicator(),
			newVwItemMaster.getNewFormulationIndicator());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getLastLotExpirationDate()),
			Time.getShortTimestamp(newVwItemMaster.getLastLotExpirationDate()));
		Assert.assertEquals(existingVwItemMaster.getBatchId(),
			newVwItemMaster.getBatchId());
		Assert.assertEquals(existingVwItemMaster.getItemCode(),
			newVwItemMaster.getItemCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getClottingFactorStartDate()),
			Time.getShortTimestamp(newVwItemMaster.getClottingFactorStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemMaster.getNonFederalExpirationDate()),
			Time.getShortTimestamp(
				newVwItemMaster.getNonFederalExpirationDate()));
		Assert.assertEquals(existingVwItemMaster.getBaseCpiPrecision(),
			newVwItemMaster.getBaseCpiPrecision());
		Assert.assertEquals(existingVwItemMaster.getBaselineAmpPrecision(),
			newVwItemMaster.getBaselineAmpPrecision());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwItemMaster newVwItemMaster = addVwItemMaster();

		VwItemMaster existingVwItemMaster = _persistence.findByPrimaryKey(newVwItemMaster.getPrimaryKey());

		Assert.assertEquals(existingVwItemMaster, newVwItemMaster);
	}

	@Test(expected = NoSuchVwItemMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwItemMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_ITEM_MASTER",
			"itemStatus", true, "itemDesc", true, "acquiredAmp", true,
			"authorizedGenericStartDate", true, "newFormulationStartDate",
			true, "marketTerminationDate", true, "obraBamp", true, "brand",
			true, "manufacturerNo", true, "modifiedDate", true,
			"therapeuticClass", true, "organizationKey", true, "acquiredBamp",
			true, "pediatricExclusiveEndDate", true, "source", true,
			"newFormulation", true, "addChgDelIndicator", true,
			"divestitureDate", true, "shelfLife", true, "primaryUom", true,
			"newFormulationEndDate", true, "modifiedBy", true,
			"packageSizeCode", true, "secondaryUom", true, "udc6", true,
			"udc5", true, "discontinuationDate", true, "udc4", true, "udc1",
			true, "udc2", true, "packageSizeIntroDate", true, "udc3", true,
			"itemEndDate", true, "manufacturerId", true, "itemFamilyId", true,
			"strength", true, "itemCategory", true, "upps", true,
			"shelfLifeType", true, "pediatricExclusiveIndicator", true,
			"itemTypeIndication", true, "acquisitionDate", true,
			"clottingFactorIndicator", true, "form", true, "itemName", true,
			"manufacturerName", true, "pediatricExclusiveStartDate", true,
			"firstSaleDate", true, "itemMasterSid", true, "itemType", true,
			"itemId", true, "baselineAmp", true, "dosesPerUnit", true,
			"dualPricingIndicator", true, "baseCpi", true, "createdDate", true,
			"createdBy", true, "itemStartDate", true, "authorizedGeneric",
			true, "ndc9", true, "authorizedGenericEndDate", true, "itemNo",
			true, "packageSize", true, "ndc8", true, "itemClass", true,
			"labelerCode", true, "displayBrand", true, "clottingFactorEndDate",
			true, "dra", true, "brandId", true, "baseCpiPeriod", true,
			"newFormulationIndicator", true, "lastLotExpirationDate", true,
			"batchId", true, "itemCode", true, "clottingFactorStartDate", true,
			"nonFederalExpirationDate", true, "baseCpiPrecision", true,
			"baselineAmpPrecision", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwItemMaster newVwItemMaster = addVwItemMaster();

		VwItemMaster existingVwItemMaster = _persistence.fetchByPrimaryKey(newVwItemMaster.getPrimaryKey());

		Assert.assertEquals(existingVwItemMaster, newVwItemMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemMaster missingVwItemMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwItemMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwItemMaster newVwItemMaster1 = addVwItemMaster();
		VwItemMaster newVwItemMaster2 = addVwItemMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemMaster1.getPrimaryKey());
		primaryKeys.add(newVwItemMaster2.getPrimaryKey());

		Map<Serializable, VwItemMaster> vwItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwItemMasters.size());
		Assert.assertEquals(newVwItemMaster1,
			vwItemMasters.get(newVwItemMaster1.getPrimaryKey()));
		Assert.assertEquals(newVwItemMaster2,
			vwItemMasters.get(newVwItemMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwItemMaster> vwItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwItemMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwItemMaster newVwItemMaster = addVwItemMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwItemMaster> vwItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwItemMasters.size());
		Assert.assertEquals(newVwItemMaster,
			vwItemMasters.get(newVwItemMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwItemMaster> vwItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwItemMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwItemMaster newVwItemMaster = addVwItemMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemMaster.getPrimaryKey());

		Map<Serializable, VwItemMaster> vwItemMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwItemMasters.size());
		Assert.assertEquals(newVwItemMaster,
			vwItemMasters.get(newVwItemMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwItemMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwItemMaster>() {
				@Override
				public void performAction(VwItemMaster vwItemMaster) {
					Assert.assertNotNull(vwItemMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwItemMaster newVwItemMaster = addVwItemMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid",
				newVwItemMaster.getItemMasterSid()));

		List<VwItemMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwItemMaster existingVwItemMaster = result.get(0);

		Assert.assertEquals(existingVwItemMaster, newVwItemMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid",
				RandomTestUtil.nextInt()));

		List<VwItemMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwItemMaster newVwItemMaster = addVwItemMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemMasterSid"));

		Object newItemMasterSid = newVwItemMaster.getItemMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid",
				new Object[] { newItemMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemMasterSid = result.get(0);

		Assert.assertEquals(existingItemMasterSid, newItemMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwItemMaster addVwItemMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemMaster vwItemMaster = _persistence.create(pk);

		vwItemMaster.setItemStatus(RandomTestUtil.randomString());

		vwItemMaster.setItemDesc(RandomTestUtil.randomString());

		vwItemMaster.setAcquiredAmp(RandomTestUtil.randomString());

		vwItemMaster.setAuthorizedGenericStartDate(RandomTestUtil.nextDate());

		vwItemMaster.setNewFormulationStartDate(RandomTestUtil.nextDate());

		vwItemMaster.setMarketTerminationDate(RandomTestUtil.nextDate());

		vwItemMaster.setObraBamp(RandomTestUtil.randomString());

		vwItemMaster.setBrand(RandomTestUtil.randomString());

		vwItemMaster.setManufacturerNo(RandomTestUtil.randomString());

		vwItemMaster.setModifiedDate(RandomTestUtil.nextDate());

		vwItemMaster.setTherapeuticClass(RandomTestUtil.randomString());

		vwItemMaster.setOrganizationKey(RandomTestUtil.randomString());

		vwItemMaster.setAcquiredBamp(RandomTestUtil.randomString());

		vwItemMaster.setPediatricExclusiveEndDate(RandomTestUtil.nextDate());

		vwItemMaster.setSource(RandomTestUtil.randomString());

		vwItemMaster.setNewFormulation(RandomTestUtil.randomString());

		vwItemMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwItemMaster.setDivestitureDate(RandomTestUtil.nextDate());

		vwItemMaster.setShelfLife(RandomTestUtil.randomString());

		vwItemMaster.setPrimaryUom(RandomTestUtil.randomString());

		vwItemMaster.setNewFormulationEndDate(RandomTestUtil.nextDate());

		vwItemMaster.setModifiedBy(RandomTestUtil.randomString());

		vwItemMaster.setPackageSizeCode(RandomTestUtil.randomString());

		vwItemMaster.setSecondaryUom(RandomTestUtil.randomString());

		vwItemMaster.setUdc6(RandomTestUtil.randomString());

		vwItemMaster.setUdc5(RandomTestUtil.randomString());

		vwItemMaster.setDiscontinuationDate(RandomTestUtil.nextDate());

		vwItemMaster.setUdc4(RandomTestUtil.randomString());

		vwItemMaster.setUdc1(RandomTestUtil.randomString());

		vwItemMaster.setUdc2(RandomTestUtil.randomString());

		vwItemMaster.setPackageSizeIntroDate(RandomTestUtil.nextDate());

		vwItemMaster.setUdc3(RandomTestUtil.randomString());

		vwItemMaster.setItemEndDate(RandomTestUtil.nextDate());

		vwItemMaster.setManufacturerId(RandomTestUtil.randomString());

		vwItemMaster.setItemFamilyId(RandomTestUtil.randomString());

		vwItemMaster.setStrength(RandomTestUtil.randomString());

		vwItemMaster.setItemCategory(RandomTestUtil.randomString());

		vwItemMaster.setUpps(RandomTestUtil.nextDouble());

		vwItemMaster.setShelfLifeType(RandomTestUtil.randomString());

		vwItemMaster.setPediatricExclusiveIndicator(RandomTestUtil.randomString());

		vwItemMaster.setItemTypeIndication(RandomTestUtil.randomString());

		vwItemMaster.setAcquisitionDate(RandomTestUtil.nextDate());

		vwItemMaster.setClottingFactorIndicator(RandomTestUtil.randomString());

		vwItemMaster.setForm(RandomTestUtil.randomString());

		vwItemMaster.setItemName(RandomTestUtil.randomString());

		vwItemMaster.setManufacturerName(RandomTestUtil.randomString());

		vwItemMaster.setPediatricExclusiveStartDate(RandomTestUtil.nextDate());

		vwItemMaster.setFirstSaleDate(RandomTestUtil.nextDate());

		vwItemMaster.setItemType(RandomTestUtil.randomString());

		vwItemMaster.setItemId(RandomTestUtil.randomString());

		vwItemMaster.setBaselineAmp(RandomTestUtil.randomString());

		vwItemMaster.setDosesPerUnit(RandomTestUtil.randomString());

		vwItemMaster.setDualPricingIndicator(RandomTestUtil.randomString());

		vwItemMaster.setBaseCpi(RandomTestUtil.randomString());

		vwItemMaster.setCreatedDate(RandomTestUtil.nextDate());

		vwItemMaster.setCreatedBy(RandomTestUtil.randomString());

		vwItemMaster.setItemStartDate(RandomTestUtil.nextDate());

		vwItemMaster.setAuthorizedGeneric(RandomTestUtil.randomString());

		vwItemMaster.setNdc9(RandomTestUtil.randomString());

		vwItemMaster.setAuthorizedGenericEndDate(RandomTestUtil.nextDate());

		vwItemMaster.setItemNo(RandomTestUtil.randomString());

		vwItemMaster.setPackageSize(RandomTestUtil.randomString());

		vwItemMaster.setNdc8(RandomTestUtil.randomString());

		vwItemMaster.setItemClass(RandomTestUtil.randomString());

		vwItemMaster.setLabelerCode(RandomTestUtil.randomString());

		vwItemMaster.setDisplayBrand(RandomTestUtil.randomString());

		vwItemMaster.setClottingFactorEndDate(RandomTestUtil.nextDate());

		vwItemMaster.setDra(RandomTestUtil.randomString());

		vwItemMaster.setBrandId(RandomTestUtil.randomString());

		vwItemMaster.setBaseCpiPeriod(RandomTestUtil.nextDate());

		vwItemMaster.setNewFormulationIndicator(RandomTestUtil.randomString());

		vwItemMaster.setLastLotExpirationDate(RandomTestUtil.nextDate());

		vwItemMaster.setBatchId(RandomTestUtil.randomString());

		vwItemMaster.setItemCode(RandomTestUtil.randomString());

		vwItemMaster.setClottingFactorStartDate(RandomTestUtil.nextDate());

		vwItemMaster.setNonFederalExpirationDate(RandomTestUtil.nextDate());

		vwItemMaster.setBaseCpiPrecision(RandomTestUtil.nextInt());

		vwItemMaster.setBaselineAmpPrecision(RandomTestUtil.nextInt());

		_vwItemMasters.add(_persistence.update(vwItemMaster));

		return vwItemMaster;
	}

	private List<VwItemMaster> _vwItemMasters = new ArrayList<VwItemMaster>();
	private VwItemMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}