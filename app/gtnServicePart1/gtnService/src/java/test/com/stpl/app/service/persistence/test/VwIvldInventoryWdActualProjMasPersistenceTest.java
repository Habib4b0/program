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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchVwIvldInventoryWdActualProjMasException;
import com.stpl.app.model.VwIvldInventoryWdActualProjMas;
import com.stpl.app.service.VwIvldInventoryWdActualProjMasLocalServiceUtil;
import com.stpl.app.service.persistence.VwIvldInventoryWdActualProjMasPersistence;
import com.stpl.app.service.persistence.VwIvldInventoryWdActualProjMasUtil;

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
public class VwIvldInventoryWdActualProjMasPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = VwIvldInventoryWdActualProjMasUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwIvldInventoryWdActualProjMas> iterator = _vwIvldInventoryWdActualProjMases.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = _persistence.create(pk);

		Assert.assertNotNull(vwIvldInventoryWdActualProjMas);

		Assert.assertEquals(vwIvldInventoryWdActualProjMas.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas = addVwIvldInventoryWdActualProjMas();

		_persistence.remove(newVwIvldInventoryWdActualProjMas);

		VwIvldInventoryWdActualProjMas existingVwIvldInventoryWdActualProjMas = _persistence.fetchByPrimaryKey(newVwIvldInventoryWdActualProjMas.getPrimaryKey());

		Assert.assertNull(existingVwIvldInventoryWdActualProjMas);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwIvldInventoryWdActualProjMas();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas = _persistence.create(pk);

		newVwIvldInventoryWdActualProjMas.setQuantityOnOrder(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setWeek(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setPrice(RandomTestUtil.nextDouble());

		newVwIvldInventoryWdActualProjMas.setAmountOnHand(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setIsMaster(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setCompanyName(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setYear(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setItemId(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setModifiedDate(RandomTestUtil.nextDate());

		newVwIvldInventoryWdActualProjMas.setOrganizationKey(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setSource(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setCreatedBy(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setCreatedDate(RandomTestUtil.nextDate());

		newVwIvldInventoryWdActualProjMas.setDay(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setUnitsOnHand(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setAmountReceived(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setErrorCode(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setIntfInsertedDate(RandomTestUtil.nextDate());

		newVwIvldInventoryWdActualProjMas.setModifiedBy(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setMonth(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setReprocessedFlag(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setAmountWithdrawn(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setQuantityReceived(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setReasonForFailure(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setCountry(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setCompanyStringId(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setIsForecast(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setInventoryWdActualProjMasIntfId(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setForecastVer(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setBatchId(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setItemName(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setErrorField(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setAmountOnOrder(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setForecastName(RandomTestUtil.randomString());

		newVwIvldInventoryWdActualProjMas.setCheckRecord(RandomTestUtil.randomBoolean());

		_vwIvldInventoryWdActualProjMases.add(_persistence.update(
				newVwIvldInventoryWdActualProjMas));

		VwIvldInventoryWdActualProjMas existingVwIvldInventoryWdActualProjMas = _persistence.findByPrimaryKey(newVwIvldInventoryWdActualProjMas.getPrimaryKey());

		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getIvldInventoryWdActualProjMasSid(),
			newVwIvldInventoryWdActualProjMas.getIvldInventoryWdActualProjMasSid());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getQuantityOnOrder(),
			newVwIvldInventoryWdActualProjMas.getQuantityOnOrder());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getWeek(),
			newVwIvldInventoryWdActualProjMas.getWeek());
		AssertUtils.assertEquals(existingVwIvldInventoryWdActualProjMas.getPrice(),
			newVwIvldInventoryWdActualProjMas.getPrice());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getAmountOnHand(),
			newVwIvldInventoryWdActualProjMas.getAmountOnHand());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getIsMaster(),
			newVwIvldInventoryWdActualProjMas.getIsMaster());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getCompanyName(),
			newVwIvldInventoryWdActualProjMas.getCompanyName());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getYear(),
			newVwIvldInventoryWdActualProjMas.getYear());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getItemId(),
			newVwIvldInventoryWdActualProjMas.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwIvldInventoryWdActualProjMas.getModifiedDate()),
			Time.getShortTimestamp(
				newVwIvldInventoryWdActualProjMas.getModifiedDate()));
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getOrganizationKey(),
			newVwIvldInventoryWdActualProjMas.getOrganizationKey());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getSource(),
			newVwIvldInventoryWdActualProjMas.getSource());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getCreatedBy(),
			newVwIvldInventoryWdActualProjMas.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwIvldInventoryWdActualProjMas.getCreatedDate()),
			Time.getShortTimestamp(
				newVwIvldInventoryWdActualProjMas.getCreatedDate()));
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getDay(),
			newVwIvldInventoryWdActualProjMas.getDay());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getAddChgDelIndicator(),
			newVwIvldInventoryWdActualProjMas.getAddChgDelIndicator());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getUnitsOnHand(),
			newVwIvldInventoryWdActualProjMas.getUnitsOnHand());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getAmountReceived(),
			newVwIvldInventoryWdActualProjMas.getAmountReceived());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getErrorCode(),
			newVwIvldInventoryWdActualProjMas.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwIvldInventoryWdActualProjMas.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newVwIvldInventoryWdActualProjMas.getIntfInsertedDate()));
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getModifiedBy(),
			newVwIvldInventoryWdActualProjMas.getModifiedBy());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getMonth(),
			newVwIvldInventoryWdActualProjMas.getMonth());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getReprocessedFlag(),
			newVwIvldInventoryWdActualProjMas.getReprocessedFlag());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getAmountWithdrawn(),
			newVwIvldInventoryWdActualProjMas.getAmountWithdrawn());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getQuantityReceived(),
			newVwIvldInventoryWdActualProjMas.getQuantityReceived());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getUnitsWithdrawn(),
			newVwIvldInventoryWdActualProjMas.getUnitsWithdrawn());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getReasonForFailure(),
			newVwIvldInventoryWdActualProjMas.getReasonForFailure());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getCountry(),
			newVwIvldInventoryWdActualProjMas.getCountry());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getCompanyStringId(),
			newVwIvldInventoryWdActualProjMas.getCompanyStringId());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getIsForecast(),
			newVwIvldInventoryWdActualProjMas.getIsForecast());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getInventoryWdActualProjMasIntfId(),
			newVwIvldInventoryWdActualProjMas.getInventoryWdActualProjMasIntfId());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getForecastVer(),
			newVwIvldInventoryWdActualProjMas.getForecastVer());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getBatchId(),
			newVwIvldInventoryWdActualProjMas.getBatchId());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getItemName(),
			newVwIvldInventoryWdActualProjMas.getItemName());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getErrorField(),
			newVwIvldInventoryWdActualProjMas.getErrorField());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getAmountOnOrder(),
			newVwIvldInventoryWdActualProjMas.getAmountOnOrder());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getForecastName(),
			newVwIvldInventoryWdActualProjMas.getForecastName());
		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas.getCheckRecord(),
			newVwIvldInventoryWdActualProjMas.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas = addVwIvldInventoryWdActualProjMas();

		VwIvldInventoryWdActualProjMas existingVwIvldInventoryWdActualProjMas = _persistence.findByPrimaryKey(newVwIvldInventoryWdActualProjMas.getPrimaryKey());

		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas,
			newVwIvldInventoryWdActualProjMas);
	}

	@Test(expected = NoSuchVwIvldInventoryWdActualProjMasException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwIvldInventoryWdActualProjMas> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS",
			"ivldInventoryWdActualProjMasSid", true, "quantityOnOrder", true,
			"week", true, "price", true, "amountOnHand", true, "isMaster",
			true, "companyName", true, "year", true, "itemId", true,
			"modifiedDate", true, "organizationKey", true, "source", true,
			"createdBy", true, "createdDate", true, "day", true,
			"addChgDelIndicator", true, "unitsOnHand", true, "amountReceived",
			true, "errorCode", true, "intfInsertedDate", true, "modifiedBy",
			true, "month", true, "reprocessedFlag", true, "amountWithdrawn",
			true, "quantityReceived", true, "unitsWithdrawn", true,
			"reasonForFailure", true, "country", true, "companyStringId", true,
			"isForecast", true, "inventoryWdActualProjMasIntfId", true,
			"forecastVer", true, "batchId", true, "itemName", true,
			"errorField", true, "amountOnOrder", true, "forecastName", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas = addVwIvldInventoryWdActualProjMas();

		VwIvldInventoryWdActualProjMas existingVwIvldInventoryWdActualProjMas = _persistence.fetchByPrimaryKey(newVwIvldInventoryWdActualProjMas.getPrimaryKey());

		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas,
			newVwIvldInventoryWdActualProjMas);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldInventoryWdActualProjMas missingVwIvldInventoryWdActualProjMas = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwIvldInventoryWdActualProjMas);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas1 = addVwIvldInventoryWdActualProjMas();
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas2 = addVwIvldInventoryWdActualProjMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldInventoryWdActualProjMas1.getPrimaryKey());
		primaryKeys.add(newVwIvldInventoryWdActualProjMas2.getPrimaryKey());

		Map<Serializable, VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwIvldInventoryWdActualProjMases.size());
		Assert.assertEquals(newVwIvldInventoryWdActualProjMas1,
			vwIvldInventoryWdActualProjMases.get(
				newVwIvldInventoryWdActualProjMas1.getPrimaryKey()));
		Assert.assertEquals(newVwIvldInventoryWdActualProjMas2,
			vwIvldInventoryWdActualProjMases.get(
				newVwIvldInventoryWdActualProjMas2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwIvldInventoryWdActualProjMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas = addVwIvldInventoryWdActualProjMas();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldInventoryWdActualProjMas.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwIvldInventoryWdActualProjMases.size());
		Assert.assertEquals(newVwIvldInventoryWdActualProjMas,
			vwIvldInventoryWdActualProjMases.get(
				newVwIvldInventoryWdActualProjMas.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwIvldInventoryWdActualProjMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas = addVwIvldInventoryWdActualProjMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldInventoryWdActualProjMas.getPrimaryKey());

		Map<Serializable, VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwIvldInventoryWdActualProjMases.size());
		Assert.assertEquals(newVwIvldInventoryWdActualProjMas,
			vwIvldInventoryWdActualProjMases.get(
				newVwIvldInventoryWdActualProjMas.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwIvldInventoryWdActualProjMasLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwIvldInventoryWdActualProjMas>() {
				@Override
				public void performAction(
					VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
					Assert.assertNotNull(vwIvldInventoryWdActualProjMas);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas = addVwIvldInventoryWdActualProjMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldInventoryWdActualProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldInventoryWdActualProjMasSid",
				newVwIvldInventoryWdActualProjMas.getIvldInventoryWdActualProjMasSid()));

		List<VwIvldInventoryWdActualProjMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwIvldInventoryWdActualProjMas existingVwIvldInventoryWdActualProjMas = result.get(0);

		Assert.assertEquals(existingVwIvldInventoryWdActualProjMas,
			newVwIvldInventoryWdActualProjMas);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldInventoryWdActualProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldInventoryWdActualProjMasSid", RandomTestUtil.nextInt()));

		List<VwIvldInventoryWdActualProjMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwIvldInventoryWdActualProjMas newVwIvldInventoryWdActualProjMas = addVwIvldInventoryWdActualProjMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldInventoryWdActualProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldInventoryWdActualProjMasSid"));

		Object newIvldInventoryWdActualProjMasSid = newVwIvldInventoryWdActualProjMas.getIvldInventoryWdActualProjMasSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldInventoryWdActualProjMasSid",
				new Object[] { newIvldInventoryWdActualProjMasSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldInventoryWdActualProjMasSid = result.get(0);

		Assert.assertEquals(existingIvldInventoryWdActualProjMasSid,
			newIvldInventoryWdActualProjMasSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldInventoryWdActualProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldInventoryWdActualProjMasSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldInventoryWdActualProjMasSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwIvldInventoryWdActualProjMas addVwIvldInventoryWdActualProjMas()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = _persistence.create(pk);

		vwIvldInventoryWdActualProjMas.setQuantityOnOrder(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setWeek(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setPrice(RandomTestUtil.nextDouble());

		vwIvldInventoryWdActualProjMas.setAmountOnHand(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setIsMaster(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setCompanyName(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setYear(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setItemId(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setModifiedDate(RandomTestUtil.nextDate());

		vwIvldInventoryWdActualProjMas.setOrganizationKey(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setSource(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setCreatedBy(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setCreatedDate(RandomTestUtil.nextDate());

		vwIvldInventoryWdActualProjMas.setDay(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setUnitsOnHand(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setAmountReceived(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setErrorCode(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setIntfInsertedDate(RandomTestUtil.nextDate());

		vwIvldInventoryWdActualProjMas.setModifiedBy(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setMonth(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setReprocessedFlag(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setAmountWithdrawn(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setQuantityReceived(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setReasonForFailure(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setCountry(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setCompanyStringId(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setIsForecast(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setInventoryWdActualProjMasIntfId(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setForecastVer(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setBatchId(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setItemName(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setErrorField(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setAmountOnOrder(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setForecastName(RandomTestUtil.randomString());

		vwIvldInventoryWdActualProjMas.setCheckRecord(RandomTestUtil.randomBoolean());

		_vwIvldInventoryWdActualProjMases.add(_persistence.update(
				vwIvldInventoryWdActualProjMas));

		return vwIvldInventoryWdActualProjMas;
	}

	private List<VwIvldInventoryWdActualProjMas> _vwIvldInventoryWdActualProjMases =
		new ArrayList<VwIvldInventoryWdActualProjMas>();
	private VwIvldInventoryWdActualProjMasPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}