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

import com.stpl.app.exception.NoSuchInventoryWdActualMasException;
import com.stpl.app.model.InventoryWdActualMas;
import com.stpl.app.service.InventoryWdActualMasLocalServiceUtil;
import com.stpl.app.service.persistence.InventoryWdActualMasPersistence;
import com.stpl.app.service.persistence.InventoryWdActualMasUtil;

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
public class InventoryWdActualMasPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = InventoryWdActualMasUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<InventoryWdActualMas> iterator = _inventoryWdActualMases.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		InventoryWdActualMas inventoryWdActualMas = _persistence.create(pk);

		Assert.assertNotNull(inventoryWdActualMas);

		Assert.assertEquals(inventoryWdActualMas.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		InventoryWdActualMas newInventoryWdActualMas = addInventoryWdActualMas();

		_persistence.remove(newInventoryWdActualMas);

		InventoryWdActualMas existingInventoryWdActualMas = _persistence.fetchByPrimaryKey(newInventoryWdActualMas.getPrimaryKey());

		Assert.assertNull(existingInventoryWdActualMas);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addInventoryWdActualMas();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		InventoryWdActualMas newInventoryWdActualMas = _persistence.create(pk);

		newInventoryWdActualMas.setQuantityOnOrder(RandomTestUtil.randomString());

		newInventoryWdActualMas.setWeek(RandomTestUtil.randomString());

		newInventoryWdActualMas.setAmountOnHand(RandomTestUtil.randomString());

		newInventoryWdActualMas.setItemMasterSid(RandomTestUtil.nextInt());

		newInventoryWdActualMas.setYear(RandomTestUtil.randomString());

		newInventoryWdActualMas.setItemId(RandomTestUtil.randomString());

		newInventoryWdActualMas.setModifiedDate(RandomTestUtil.nextDate());

		newInventoryWdActualMas.setOrganizationKey(RandomTestUtil.randomString());

		newInventoryWdActualMas.setSource(RandomTestUtil.randomString());

		newInventoryWdActualMas.setCreatedBy(RandomTestUtil.randomString());

		newInventoryWdActualMas.setCreatedDate(RandomTestUtil.nextDate());

		newInventoryWdActualMas.setDay(RandomTestUtil.randomString());

		newInventoryWdActualMas.setUnitsOnHand(RandomTestUtil.randomString());

		newInventoryWdActualMas.setAmountReceived(RandomTestUtil.randomString());

		newInventoryWdActualMas.setItemIdentifier(RandomTestUtil.randomString());

		newInventoryWdActualMas.setModifiedBy(RandomTestUtil.randomString());

		newInventoryWdActualMas.setInboundStatus(RandomTestUtil.randomString());

		newInventoryWdActualMas.setMonth(RandomTestUtil.randomString());

		newInventoryWdActualMas.setAmountWithdrawn(RandomTestUtil.randomString());

		newInventoryWdActualMas.setQuantityReceived(RandomTestUtil.randomString());

		newInventoryWdActualMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		newInventoryWdActualMas.setCountry(RandomTestUtil.randomString());

		newInventoryWdActualMas.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newInventoryWdActualMas.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newInventoryWdActualMas.setBatchId(RandomTestUtil.randomString());

		newInventoryWdActualMas.setAmountOnOrder(RandomTestUtil.randomString());

		_inventoryWdActualMases.add(_persistence.update(newInventoryWdActualMas));

		InventoryWdActualMas existingInventoryWdActualMas = _persistence.findByPrimaryKey(newInventoryWdActualMas.getPrimaryKey());

		Assert.assertEquals(existingInventoryWdActualMas.getQuantityOnOrder(),
			newInventoryWdActualMas.getQuantityOnOrder());
		Assert.assertEquals(existingInventoryWdActualMas.getWeek(),
			newInventoryWdActualMas.getWeek());
		Assert.assertEquals(existingInventoryWdActualMas.getAmountOnHand(),
			newInventoryWdActualMas.getAmountOnHand());
		Assert.assertEquals(existingInventoryWdActualMas.getItemMasterSid(),
			newInventoryWdActualMas.getItemMasterSid());
		Assert.assertEquals(existingInventoryWdActualMas.getInventoryWdActualMasSid(),
			newInventoryWdActualMas.getInventoryWdActualMasSid());
		Assert.assertEquals(existingInventoryWdActualMas.getYear(),
			newInventoryWdActualMas.getYear());
		Assert.assertEquals(existingInventoryWdActualMas.getItemId(),
			newInventoryWdActualMas.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingInventoryWdActualMas.getModifiedDate()),
			Time.getShortTimestamp(newInventoryWdActualMas.getModifiedDate()));
		Assert.assertEquals(existingInventoryWdActualMas.getOrganizationKey(),
			newInventoryWdActualMas.getOrganizationKey());
		Assert.assertEquals(existingInventoryWdActualMas.getSource(),
			newInventoryWdActualMas.getSource());
		Assert.assertEquals(existingInventoryWdActualMas.getCreatedBy(),
			newInventoryWdActualMas.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingInventoryWdActualMas.getCreatedDate()),
			Time.getShortTimestamp(newInventoryWdActualMas.getCreatedDate()));
		Assert.assertEquals(existingInventoryWdActualMas.getDay(),
			newInventoryWdActualMas.getDay());
		Assert.assertEquals(existingInventoryWdActualMas.getUnitsOnHand(),
			newInventoryWdActualMas.getUnitsOnHand());
		Assert.assertEquals(existingInventoryWdActualMas.getAmountReceived(),
			newInventoryWdActualMas.getAmountReceived());
		Assert.assertEquals(existingInventoryWdActualMas.getItemIdentifier(),
			newInventoryWdActualMas.getItemIdentifier());
		Assert.assertEquals(existingInventoryWdActualMas.getModifiedBy(),
			newInventoryWdActualMas.getModifiedBy());
		Assert.assertEquals(existingInventoryWdActualMas.getInboundStatus(),
			newInventoryWdActualMas.getInboundStatus());
		Assert.assertEquals(existingInventoryWdActualMas.getMonth(),
			newInventoryWdActualMas.getMonth());
		Assert.assertEquals(existingInventoryWdActualMas.getAmountWithdrawn(),
			newInventoryWdActualMas.getAmountWithdrawn());
		Assert.assertEquals(existingInventoryWdActualMas.getQuantityReceived(),
			newInventoryWdActualMas.getQuantityReceived());
		Assert.assertEquals(existingInventoryWdActualMas.getUnitsWithdrawn(),
			newInventoryWdActualMas.getUnitsWithdrawn());
		Assert.assertEquals(existingInventoryWdActualMas.getCountry(),
			newInventoryWdActualMas.getCountry());
		Assert.assertEquals(existingInventoryWdActualMas.getRecordLockStatus(),
			newInventoryWdActualMas.getRecordLockStatus());
		Assert.assertEquals(existingInventoryWdActualMas.getItemIdentifierCodeQualifier(),
			newInventoryWdActualMas.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingInventoryWdActualMas.getBatchId(),
			newInventoryWdActualMas.getBatchId());
		Assert.assertEquals(existingInventoryWdActualMas.getAmountOnOrder(),
			newInventoryWdActualMas.getAmountOnOrder());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		InventoryWdActualMas newInventoryWdActualMas = addInventoryWdActualMas();

		InventoryWdActualMas existingInventoryWdActualMas = _persistence.findByPrimaryKey(newInventoryWdActualMas.getPrimaryKey());

		Assert.assertEquals(existingInventoryWdActualMas,
			newInventoryWdActualMas);
	}

	@Test(expected = NoSuchInventoryWdActualMasException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<InventoryWdActualMas> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("INVENTORY_WD_ACTUAL_MAS",
			"quantityOnOrder", true, "week", true, "amountOnHand", true,
			"itemMasterSid", true, "inventoryWdActualMasSid", true, "year",
			true, "itemId", true, "modifiedDate", true, "organizationKey",
			true, "source", true, "createdBy", true, "createdDate", true,
			"day", true, "unitsOnHand", true, "amountReceived", true,
			"itemIdentifier", true, "modifiedBy", true, "inboundStatus", true,
			"month", true, "amountWithdrawn", true, "quantityReceived", true,
			"unitsWithdrawn", true, "country", true, "recordLockStatus", true,
			"itemIdentifierCodeQualifier", true, "batchId", true,
			"amountOnOrder", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		InventoryWdActualMas newInventoryWdActualMas = addInventoryWdActualMas();

		InventoryWdActualMas existingInventoryWdActualMas = _persistence.fetchByPrimaryKey(newInventoryWdActualMas.getPrimaryKey());

		Assert.assertEquals(existingInventoryWdActualMas,
			newInventoryWdActualMas);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		InventoryWdActualMas missingInventoryWdActualMas = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingInventoryWdActualMas);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		InventoryWdActualMas newInventoryWdActualMas1 = addInventoryWdActualMas();
		InventoryWdActualMas newInventoryWdActualMas2 = addInventoryWdActualMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newInventoryWdActualMas1.getPrimaryKey());
		primaryKeys.add(newInventoryWdActualMas2.getPrimaryKey());

		Map<Serializable, InventoryWdActualMas> inventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, inventoryWdActualMases.size());
		Assert.assertEquals(newInventoryWdActualMas1,
			inventoryWdActualMases.get(newInventoryWdActualMas1.getPrimaryKey()));
		Assert.assertEquals(newInventoryWdActualMas2,
			inventoryWdActualMases.get(newInventoryWdActualMas2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, InventoryWdActualMas> inventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(inventoryWdActualMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		InventoryWdActualMas newInventoryWdActualMas = addInventoryWdActualMas();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newInventoryWdActualMas.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, InventoryWdActualMas> inventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, inventoryWdActualMases.size());
		Assert.assertEquals(newInventoryWdActualMas,
			inventoryWdActualMases.get(newInventoryWdActualMas.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, InventoryWdActualMas> inventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(inventoryWdActualMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		InventoryWdActualMas newInventoryWdActualMas = addInventoryWdActualMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newInventoryWdActualMas.getPrimaryKey());

		Map<Serializable, InventoryWdActualMas> inventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, inventoryWdActualMases.size());
		Assert.assertEquals(newInventoryWdActualMas,
			inventoryWdActualMases.get(newInventoryWdActualMas.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = InventoryWdActualMasLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<InventoryWdActualMas>() {
				@Override
				public void performAction(
					InventoryWdActualMas inventoryWdActualMas) {
					Assert.assertNotNull(inventoryWdActualMas);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		InventoryWdActualMas newInventoryWdActualMas = addInventoryWdActualMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(InventoryWdActualMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("inventoryWdActualMasSid",
				newInventoryWdActualMas.getInventoryWdActualMasSid()));

		List<InventoryWdActualMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		InventoryWdActualMas existingInventoryWdActualMas = result.get(0);

		Assert.assertEquals(existingInventoryWdActualMas,
			newInventoryWdActualMas);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(InventoryWdActualMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("inventoryWdActualMasSid",
				RandomTestUtil.nextInt()));

		List<InventoryWdActualMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		InventoryWdActualMas newInventoryWdActualMas = addInventoryWdActualMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(InventoryWdActualMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"inventoryWdActualMasSid"));

		Object newInventoryWdActualMasSid = newInventoryWdActualMas.getInventoryWdActualMasSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("inventoryWdActualMasSid",
				new Object[] { newInventoryWdActualMasSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingInventoryWdActualMasSid = result.get(0);

		Assert.assertEquals(existingInventoryWdActualMasSid,
			newInventoryWdActualMasSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(InventoryWdActualMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"inventoryWdActualMasSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("inventoryWdActualMasSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected InventoryWdActualMas addInventoryWdActualMas()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		InventoryWdActualMas inventoryWdActualMas = _persistence.create(pk);

		inventoryWdActualMas.setQuantityOnOrder(RandomTestUtil.randomString());

		inventoryWdActualMas.setWeek(RandomTestUtil.randomString());

		inventoryWdActualMas.setAmountOnHand(RandomTestUtil.randomString());

		inventoryWdActualMas.setItemMasterSid(RandomTestUtil.nextInt());

		inventoryWdActualMas.setYear(RandomTestUtil.randomString());

		inventoryWdActualMas.setItemId(RandomTestUtil.randomString());

		inventoryWdActualMas.setModifiedDate(RandomTestUtil.nextDate());

		inventoryWdActualMas.setOrganizationKey(RandomTestUtil.randomString());

		inventoryWdActualMas.setSource(RandomTestUtil.randomString());

		inventoryWdActualMas.setCreatedBy(RandomTestUtil.randomString());

		inventoryWdActualMas.setCreatedDate(RandomTestUtil.nextDate());

		inventoryWdActualMas.setDay(RandomTestUtil.randomString());

		inventoryWdActualMas.setUnitsOnHand(RandomTestUtil.randomString());

		inventoryWdActualMas.setAmountReceived(RandomTestUtil.randomString());

		inventoryWdActualMas.setItemIdentifier(RandomTestUtil.randomString());

		inventoryWdActualMas.setModifiedBy(RandomTestUtil.randomString());

		inventoryWdActualMas.setInboundStatus(RandomTestUtil.randomString());

		inventoryWdActualMas.setMonth(RandomTestUtil.randomString());

		inventoryWdActualMas.setAmountWithdrawn(RandomTestUtil.randomString());

		inventoryWdActualMas.setQuantityReceived(RandomTestUtil.randomString());

		inventoryWdActualMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		inventoryWdActualMas.setCountry(RandomTestUtil.randomString());

		inventoryWdActualMas.setRecordLockStatus(RandomTestUtil.randomBoolean());

		inventoryWdActualMas.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		inventoryWdActualMas.setBatchId(RandomTestUtil.randomString());

		inventoryWdActualMas.setAmountOnOrder(RandomTestUtil.randomString());

		_inventoryWdActualMases.add(_persistence.update(inventoryWdActualMas));

		return inventoryWdActualMas;
	}

	private List<InventoryWdActualMas> _inventoryWdActualMases = new ArrayList<InventoryWdActualMas>();
	private InventoryWdActualMasPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}