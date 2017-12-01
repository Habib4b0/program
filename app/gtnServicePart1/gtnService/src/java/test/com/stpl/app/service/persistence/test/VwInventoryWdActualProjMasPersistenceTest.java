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

import com.stpl.app.exception.NoSuchVwInventoryWdActualProjMasException;
import com.stpl.app.model.VwInventoryWdActualProjMas;
import com.stpl.app.service.VwInventoryWdActualProjMasLocalServiceUtil;
import com.stpl.app.service.persistence.VwInventoryWdActualProjMasPersistence;
import com.stpl.app.service.persistence.VwInventoryWdActualProjMasUtil;

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
public class VwInventoryWdActualProjMasPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = VwInventoryWdActualProjMasUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwInventoryWdActualProjMas> iterator = _vwInventoryWdActualProjMases.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwInventoryWdActualProjMas vwInventoryWdActualProjMas = _persistence.create(pk);

		Assert.assertNotNull(vwInventoryWdActualProjMas);

		Assert.assertEquals(vwInventoryWdActualProjMas.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas = addVwInventoryWdActualProjMas();

		_persistence.remove(newVwInventoryWdActualProjMas);

		VwInventoryWdActualProjMas existingVwInventoryWdActualProjMas = _persistence.fetchByPrimaryKey(newVwInventoryWdActualProjMas.getPrimaryKey());

		Assert.assertNull(existingVwInventoryWdActualProjMas);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwInventoryWdActualProjMas();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas = _persistence.create(pk);

		newVwInventoryWdActualProjMas.setQuantityOnOrder(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setWeek(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setPrice(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setAmountOnHand(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setIsMaster(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setCompanyName(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setYear(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setItemId(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setModifiedDate(RandomTestUtil.nextDate());

		newVwInventoryWdActualProjMas.setOrganizationKey(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setSource(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setCreatedBy(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setCreatedDate(RandomTestUtil.nextDate());

		newVwInventoryWdActualProjMas.setDay(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setUnitsOnHand(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setAmountReceived(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setModifiedBy(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setMonth(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setAmountWithdrawn(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setQuantityReceived(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setUnitsWithdrawn(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setCountry(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setCompanyStringId(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setIsForecast(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setForecastVer(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setBatchId(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setItemName(RandomTestUtil.randomString());

		newVwInventoryWdActualProjMas.setAmountOnOrder(RandomTestUtil.nextDouble());

		newVwInventoryWdActualProjMas.setForecastName(RandomTestUtil.randomString());

		_vwInventoryWdActualProjMases.add(_persistence.update(
				newVwInventoryWdActualProjMas));

		VwInventoryWdActualProjMas existingVwInventoryWdActualProjMas = _persistence.findByPrimaryKey(newVwInventoryWdActualProjMas.getPrimaryKey());

		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getQuantityOnOrder(),
			newVwInventoryWdActualProjMas.getQuantityOnOrder());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getWeek(),
			newVwInventoryWdActualProjMas.getWeek());
		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getPrice(),
			newVwInventoryWdActualProjMas.getPrice());
		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getAmountOnHand(),
			newVwInventoryWdActualProjMas.getAmountOnHand());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getIsMaster(),
			newVwInventoryWdActualProjMas.getIsMaster());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getCompanyName(),
			newVwInventoryWdActualProjMas.getCompanyName());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getYear(),
			newVwInventoryWdActualProjMas.getYear());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getItemId(),
			newVwInventoryWdActualProjMas.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwInventoryWdActualProjMas.getModifiedDate()),
			Time.getShortTimestamp(
				newVwInventoryWdActualProjMas.getModifiedDate()));
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getOrganizationKey(),
			newVwInventoryWdActualProjMas.getOrganizationKey());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getInventoryWdActualProjMasSid(),
			newVwInventoryWdActualProjMas.getInventoryWdActualProjMasSid());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getSource(),
			newVwInventoryWdActualProjMas.getSource());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getCreatedBy(),
			newVwInventoryWdActualProjMas.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwInventoryWdActualProjMas.getCreatedDate()),
			Time.getShortTimestamp(
				newVwInventoryWdActualProjMas.getCreatedDate()));
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getDay(),
			newVwInventoryWdActualProjMas.getDay());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getAddChgDelIndicator(),
			newVwInventoryWdActualProjMas.getAddChgDelIndicator());
		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getUnitsOnHand(),
			newVwInventoryWdActualProjMas.getUnitsOnHand());
		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getAmountReceived(),
			newVwInventoryWdActualProjMas.getAmountReceived());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getModifiedBy(),
			newVwInventoryWdActualProjMas.getModifiedBy());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getMonth(),
			newVwInventoryWdActualProjMas.getMonth());
		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getAmountWithdrawn(),
			newVwInventoryWdActualProjMas.getAmountWithdrawn());
		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getQuantityReceived(),
			newVwInventoryWdActualProjMas.getQuantityReceived());
		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getUnitsWithdrawn(),
			newVwInventoryWdActualProjMas.getUnitsWithdrawn());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getCountry(),
			newVwInventoryWdActualProjMas.getCountry());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getCompanyStringId(),
			newVwInventoryWdActualProjMas.getCompanyStringId());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getIsForecast(),
			newVwInventoryWdActualProjMas.getIsForecast());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getForecastVer(),
			newVwInventoryWdActualProjMas.getForecastVer());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getBatchId(),
			newVwInventoryWdActualProjMas.getBatchId());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getItemName(),
			newVwInventoryWdActualProjMas.getItemName());
		AssertUtils.assertEquals(existingVwInventoryWdActualProjMas.getAmountOnOrder(),
			newVwInventoryWdActualProjMas.getAmountOnOrder());
		Assert.assertEquals(existingVwInventoryWdActualProjMas.getForecastName(),
			newVwInventoryWdActualProjMas.getForecastName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas = addVwInventoryWdActualProjMas();

		VwInventoryWdActualProjMas existingVwInventoryWdActualProjMas = _persistence.findByPrimaryKey(newVwInventoryWdActualProjMas.getPrimaryKey());

		Assert.assertEquals(existingVwInventoryWdActualProjMas,
			newVwInventoryWdActualProjMas);
	}

	@Test(expected = NoSuchVwInventoryWdActualProjMasException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwInventoryWdActualProjMas> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_INVENTORY_WD_ACTUAL_PROJ_MAS",
			"quantityOnOrder", true, "week", true, "price", true,
			"amountOnHand", true, "isMaster", true, "companyName", true,
			"year", true, "itemId", true, "modifiedDate", true,
			"organizationKey", true, "inventoryWdActualProjMasSid", true,
			"source", true, "createdBy", true, "createdDate", true, "day",
			true, "addChgDelIndicator", true, "unitsOnHand", true,
			"amountReceived", true, "modifiedBy", true, "month", true,
			"amountWithdrawn", true, "quantityReceived", true,
			"unitsWithdrawn", true, "country", true, "companyStringId", true,
			"isForecast", true, "forecastVer", true, "batchId", true,
			"itemName", true, "amountOnOrder", true, "forecastName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas = addVwInventoryWdActualProjMas();

		VwInventoryWdActualProjMas existingVwInventoryWdActualProjMas = _persistence.fetchByPrimaryKey(newVwInventoryWdActualProjMas.getPrimaryKey());

		Assert.assertEquals(existingVwInventoryWdActualProjMas,
			newVwInventoryWdActualProjMas);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwInventoryWdActualProjMas missingVwInventoryWdActualProjMas = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwInventoryWdActualProjMas);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas1 = addVwInventoryWdActualProjMas();
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas2 = addVwInventoryWdActualProjMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwInventoryWdActualProjMas1.getPrimaryKey());
		primaryKeys.add(newVwInventoryWdActualProjMas2.getPrimaryKey());

		Map<Serializable, VwInventoryWdActualProjMas> vwInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwInventoryWdActualProjMases.size());
		Assert.assertEquals(newVwInventoryWdActualProjMas1,
			vwInventoryWdActualProjMases.get(
				newVwInventoryWdActualProjMas1.getPrimaryKey()));
		Assert.assertEquals(newVwInventoryWdActualProjMas2,
			vwInventoryWdActualProjMases.get(
				newVwInventoryWdActualProjMas2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwInventoryWdActualProjMas> vwInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwInventoryWdActualProjMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas = addVwInventoryWdActualProjMas();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwInventoryWdActualProjMas.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwInventoryWdActualProjMas> vwInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwInventoryWdActualProjMases.size());
		Assert.assertEquals(newVwInventoryWdActualProjMas,
			vwInventoryWdActualProjMases.get(
				newVwInventoryWdActualProjMas.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwInventoryWdActualProjMas> vwInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwInventoryWdActualProjMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas = addVwInventoryWdActualProjMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwInventoryWdActualProjMas.getPrimaryKey());

		Map<Serializable, VwInventoryWdActualProjMas> vwInventoryWdActualProjMases =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwInventoryWdActualProjMases.size());
		Assert.assertEquals(newVwInventoryWdActualProjMas,
			vwInventoryWdActualProjMases.get(
				newVwInventoryWdActualProjMas.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwInventoryWdActualProjMasLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwInventoryWdActualProjMas>() {
				@Override
				public void performAction(
					VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
					Assert.assertNotNull(vwInventoryWdActualProjMas);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas = addVwInventoryWdActualProjMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwInventoryWdActualProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"inventoryWdActualProjMasSid",
				newVwInventoryWdActualProjMas.getInventoryWdActualProjMasSid()));

		List<VwInventoryWdActualProjMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwInventoryWdActualProjMas existingVwInventoryWdActualProjMas = result.get(0);

		Assert.assertEquals(existingVwInventoryWdActualProjMas,
			newVwInventoryWdActualProjMas);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwInventoryWdActualProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"inventoryWdActualProjMasSid", RandomTestUtil.nextInt()));

		List<VwInventoryWdActualProjMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwInventoryWdActualProjMas newVwInventoryWdActualProjMas = addVwInventoryWdActualProjMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwInventoryWdActualProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"inventoryWdActualProjMasSid"));

		Object newInventoryWdActualProjMasSid = newVwInventoryWdActualProjMas.getInventoryWdActualProjMasSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"inventoryWdActualProjMasSid",
				new Object[] { newInventoryWdActualProjMasSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingInventoryWdActualProjMasSid = result.get(0);

		Assert.assertEquals(existingInventoryWdActualProjMasSid,
			newInventoryWdActualProjMasSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwInventoryWdActualProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"inventoryWdActualProjMasSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"inventoryWdActualProjMasSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwInventoryWdActualProjMas addVwInventoryWdActualProjMas()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwInventoryWdActualProjMas vwInventoryWdActualProjMas = _persistence.create(pk);

		vwInventoryWdActualProjMas.setQuantityOnOrder(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setWeek(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setPrice(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setAmountOnHand(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setIsMaster(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setCompanyName(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setYear(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setItemId(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setModifiedDate(RandomTestUtil.nextDate());

		vwInventoryWdActualProjMas.setOrganizationKey(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setSource(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setCreatedBy(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setCreatedDate(RandomTestUtil.nextDate());

		vwInventoryWdActualProjMas.setDay(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setUnitsOnHand(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setAmountReceived(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setModifiedBy(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setMonth(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setAmountWithdrawn(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setQuantityReceived(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setUnitsWithdrawn(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setCountry(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setCompanyStringId(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setIsForecast(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setForecastVer(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setBatchId(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setItemName(RandomTestUtil.randomString());

		vwInventoryWdActualProjMas.setAmountOnOrder(RandomTestUtil.nextDouble());

		vwInventoryWdActualProjMas.setForecastName(RandomTestUtil.randomString());

		_vwInventoryWdActualProjMases.add(_persistence.update(
				vwInventoryWdActualProjMas));

		return vwInventoryWdActualProjMas;
	}

	private List<VwInventoryWdActualProjMas> _vwInventoryWdActualProjMases = new ArrayList<VwInventoryWdActualProjMas>();
	private VwInventoryWdActualProjMasPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}