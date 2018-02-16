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

import com.stpl.app.exception.NoSuchInventoryWdProjMasException;
import com.stpl.app.model.InventoryWdProjMas;
import com.stpl.app.service.InventoryWdProjMasLocalServiceUtil;
import com.stpl.app.service.persistence.InventoryWdProjMasPersistence;
import com.stpl.app.service.persistence.InventoryWdProjMasUtil;

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
public class InventoryWdProjMasPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = InventoryWdProjMasUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<InventoryWdProjMas> iterator = _inventoryWdProjMases.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		InventoryWdProjMas inventoryWdProjMas = _persistence.create(pk);

		Assert.assertNotNull(inventoryWdProjMas);

		Assert.assertEquals(inventoryWdProjMas.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		InventoryWdProjMas newInventoryWdProjMas = addInventoryWdProjMas();

		_persistence.remove(newInventoryWdProjMas);

		InventoryWdProjMas existingInventoryWdProjMas = _persistence.fetchByPrimaryKey(newInventoryWdProjMas.getPrimaryKey());

		Assert.assertNull(existingInventoryWdProjMas);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addInventoryWdProjMas();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		InventoryWdProjMas newInventoryWdProjMas = _persistence.create(pk);

		newInventoryWdProjMas.setWeek(RandomTestUtil.randomString());

		newInventoryWdProjMas.setItemMasterSid(RandomTestUtil.nextInt());

		newInventoryWdProjMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		newInventoryWdProjMas.setCountry(RandomTestUtil.randomString());

		newInventoryWdProjMas.setYear(RandomTestUtil.randomString());

		newInventoryWdProjMas.setItemId(RandomTestUtil.randomString());

		newInventoryWdProjMas.setModifiedDate(RandomTestUtil.nextDate());

		newInventoryWdProjMas.setOrganizationKey(RandomTestUtil.randomString());

		newInventoryWdProjMas.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newInventoryWdProjMas.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newInventoryWdProjMas.setSource(RandomTestUtil.randomString());

		newInventoryWdProjMas.setCreatedDate(RandomTestUtil.nextDate());

		newInventoryWdProjMas.setCreatedBy(RandomTestUtil.randomString());

		newInventoryWdProjMas.setDay(RandomTestUtil.randomString());

		newInventoryWdProjMas.setForecastVer(RandomTestUtil.randomString());

		newInventoryWdProjMas.setBatchId(RandomTestUtil.randomString());

		newInventoryWdProjMas.setItemIdentifier(RandomTestUtil.randomString());

		newInventoryWdProjMas.setInboundStatus(RandomTestUtil.randomString());

		newInventoryWdProjMas.setModifiedBy(RandomTestUtil.randomString());

		newInventoryWdProjMas.setMonth(RandomTestUtil.randomString());

		newInventoryWdProjMas.setForecastName(RandomTestUtil.randomString());

		newInventoryWdProjMas.setAmountWithdrawn(RandomTestUtil.randomString());

		_inventoryWdProjMases.add(_persistence.update(newInventoryWdProjMas));

		InventoryWdProjMas existingInventoryWdProjMas = _persistence.findByPrimaryKey(newInventoryWdProjMas.getPrimaryKey());

		Assert.assertEquals(existingInventoryWdProjMas.getWeek(),
			newInventoryWdProjMas.getWeek());
		Assert.assertEquals(existingInventoryWdProjMas.getItemMasterSid(),
			newInventoryWdProjMas.getItemMasterSid());
		Assert.assertEquals(existingInventoryWdProjMas.getUnitsWithdrawn(),
			newInventoryWdProjMas.getUnitsWithdrawn());
		Assert.assertEquals(existingInventoryWdProjMas.getCountry(),
			newInventoryWdProjMas.getCountry());
		Assert.assertEquals(existingInventoryWdProjMas.getYear(),
			newInventoryWdProjMas.getYear());
		Assert.assertEquals(existingInventoryWdProjMas.getItemId(),
			newInventoryWdProjMas.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingInventoryWdProjMas.getModifiedDate()),
			Time.getShortTimestamp(newInventoryWdProjMas.getModifiedDate()));
		Assert.assertEquals(existingInventoryWdProjMas.getOrganizationKey(),
			newInventoryWdProjMas.getOrganizationKey());
		Assert.assertEquals(existingInventoryWdProjMas.getRecordLockStatus(),
			newInventoryWdProjMas.getRecordLockStatus());
		Assert.assertEquals(existingInventoryWdProjMas.getItemIdentifierCodeQualifier(),
			newInventoryWdProjMas.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingInventoryWdProjMas.getSource(),
			newInventoryWdProjMas.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingInventoryWdProjMas.getCreatedDate()),
			Time.getShortTimestamp(newInventoryWdProjMas.getCreatedDate()));
		Assert.assertEquals(existingInventoryWdProjMas.getCreatedBy(),
			newInventoryWdProjMas.getCreatedBy());
		Assert.assertEquals(existingInventoryWdProjMas.getInventoryWdProjMasSid(),
			newInventoryWdProjMas.getInventoryWdProjMasSid());
		Assert.assertEquals(existingInventoryWdProjMas.getDay(),
			newInventoryWdProjMas.getDay());
		Assert.assertEquals(existingInventoryWdProjMas.getForecastVer(),
			newInventoryWdProjMas.getForecastVer());
		Assert.assertEquals(existingInventoryWdProjMas.getBatchId(),
			newInventoryWdProjMas.getBatchId());
		Assert.assertEquals(existingInventoryWdProjMas.getItemIdentifier(),
			newInventoryWdProjMas.getItemIdentifier());
		Assert.assertEquals(existingInventoryWdProjMas.getInboundStatus(),
			newInventoryWdProjMas.getInboundStatus());
		Assert.assertEquals(existingInventoryWdProjMas.getModifiedBy(),
			newInventoryWdProjMas.getModifiedBy());
		Assert.assertEquals(existingInventoryWdProjMas.getMonth(),
			newInventoryWdProjMas.getMonth());
		Assert.assertEquals(existingInventoryWdProjMas.getForecastName(),
			newInventoryWdProjMas.getForecastName());
		Assert.assertEquals(existingInventoryWdProjMas.getAmountWithdrawn(),
			newInventoryWdProjMas.getAmountWithdrawn());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		InventoryWdProjMas newInventoryWdProjMas = addInventoryWdProjMas();

		InventoryWdProjMas existingInventoryWdProjMas = _persistence.findByPrimaryKey(newInventoryWdProjMas.getPrimaryKey());

		Assert.assertEquals(existingInventoryWdProjMas, newInventoryWdProjMas);
	}

	@Test(expected = NoSuchInventoryWdProjMasException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<InventoryWdProjMas> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("INVENTORY_WD_PROJ_MAS",
			"week", true, "itemMasterSid", true, "unitsWithdrawn", true,
			"country", true, "year", true, "itemId", true, "modifiedDate",
			true, "organizationKey", true, "recordLockStatus", true,
			"itemIdentifierCodeQualifier", true, "source", true, "createdDate",
			true, "createdBy", true, "inventoryWdProjMasSid", true, "day",
			true, "forecastVer", true, "batchId", true, "itemIdentifier", true,
			"inboundStatus", true, "modifiedBy", true, "month", true,
			"forecastName", true, "amountWithdrawn", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		InventoryWdProjMas newInventoryWdProjMas = addInventoryWdProjMas();

		InventoryWdProjMas existingInventoryWdProjMas = _persistence.fetchByPrimaryKey(newInventoryWdProjMas.getPrimaryKey());

		Assert.assertEquals(existingInventoryWdProjMas, newInventoryWdProjMas);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		InventoryWdProjMas missingInventoryWdProjMas = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingInventoryWdProjMas);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		InventoryWdProjMas newInventoryWdProjMas1 = addInventoryWdProjMas();
		InventoryWdProjMas newInventoryWdProjMas2 = addInventoryWdProjMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newInventoryWdProjMas1.getPrimaryKey());
		primaryKeys.add(newInventoryWdProjMas2.getPrimaryKey());

		Map<Serializable, InventoryWdProjMas> inventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, inventoryWdProjMases.size());
		Assert.assertEquals(newInventoryWdProjMas1,
			inventoryWdProjMases.get(newInventoryWdProjMas1.getPrimaryKey()));
		Assert.assertEquals(newInventoryWdProjMas2,
			inventoryWdProjMases.get(newInventoryWdProjMas2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, InventoryWdProjMas> inventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(inventoryWdProjMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		InventoryWdProjMas newInventoryWdProjMas = addInventoryWdProjMas();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newInventoryWdProjMas.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, InventoryWdProjMas> inventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, inventoryWdProjMases.size());
		Assert.assertEquals(newInventoryWdProjMas,
			inventoryWdProjMases.get(newInventoryWdProjMas.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, InventoryWdProjMas> inventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(inventoryWdProjMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		InventoryWdProjMas newInventoryWdProjMas = addInventoryWdProjMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newInventoryWdProjMas.getPrimaryKey());

		Map<Serializable, InventoryWdProjMas> inventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, inventoryWdProjMases.size());
		Assert.assertEquals(newInventoryWdProjMas,
			inventoryWdProjMases.get(newInventoryWdProjMas.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = InventoryWdProjMasLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<InventoryWdProjMas>() {
				@Override
				public void performAction(InventoryWdProjMas inventoryWdProjMas) {
					Assert.assertNotNull(inventoryWdProjMas);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		InventoryWdProjMas newInventoryWdProjMas = addInventoryWdProjMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(InventoryWdProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("inventoryWdProjMasSid",
				newInventoryWdProjMas.getInventoryWdProjMasSid()));

		List<InventoryWdProjMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		InventoryWdProjMas existingInventoryWdProjMas = result.get(0);

		Assert.assertEquals(existingInventoryWdProjMas, newInventoryWdProjMas);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(InventoryWdProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("inventoryWdProjMasSid",
				RandomTestUtil.nextInt()));

		List<InventoryWdProjMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		InventoryWdProjMas newInventoryWdProjMas = addInventoryWdProjMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(InventoryWdProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"inventoryWdProjMasSid"));

		Object newInventoryWdProjMasSid = newInventoryWdProjMas.getInventoryWdProjMasSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("inventoryWdProjMasSid",
				new Object[] { newInventoryWdProjMasSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingInventoryWdProjMasSid = result.get(0);

		Assert.assertEquals(existingInventoryWdProjMasSid,
			newInventoryWdProjMasSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(InventoryWdProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"inventoryWdProjMasSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("inventoryWdProjMasSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected InventoryWdProjMas addInventoryWdProjMas()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		InventoryWdProjMas inventoryWdProjMas = _persistence.create(pk);

		inventoryWdProjMas.setWeek(RandomTestUtil.randomString());

		inventoryWdProjMas.setItemMasterSid(RandomTestUtil.nextInt());

		inventoryWdProjMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		inventoryWdProjMas.setCountry(RandomTestUtil.randomString());

		inventoryWdProjMas.setYear(RandomTestUtil.randomString());

		inventoryWdProjMas.setItemId(RandomTestUtil.randomString());

		inventoryWdProjMas.setModifiedDate(RandomTestUtil.nextDate());

		inventoryWdProjMas.setOrganizationKey(RandomTestUtil.randomString());

		inventoryWdProjMas.setRecordLockStatus(RandomTestUtil.randomBoolean());

		inventoryWdProjMas.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		inventoryWdProjMas.setSource(RandomTestUtil.randomString());

		inventoryWdProjMas.setCreatedDate(RandomTestUtil.nextDate());

		inventoryWdProjMas.setCreatedBy(RandomTestUtil.randomString());

		inventoryWdProjMas.setDay(RandomTestUtil.randomString());

		inventoryWdProjMas.setForecastVer(RandomTestUtil.randomString());

		inventoryWdProjMas.setBatchId(RandomTestUtil.randomString());

		inventoryWdProjMas.setItemIdentifier(RandomTestUtil.randomString());

		inventoryWdProjMas.setInboundStatus(RandomTestUtil.randomString());

		inventoryWdProjMas.setModifiedBy(RandomTestUtil.randomString());

		inventoryWdProjMas.setMonth(RandomTestUtil.randomString());

		inventoryWdProjMas.setForecastName(RandomTestUtil.randomString());

		inventoryWdProjMas.setAmountWithdrawn(RandomTestUtil.randomString());

		_inventoryWdProjMases.add(_persistence.update(inventoryWdProjMas));

		return inventoryWdProjMas;
	}

	private List<InventoryWdProjMas> _inventoryWdProjMases = new ArrayList<InventoryWdProjMas>();
	private InventoryWdProjMasPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}