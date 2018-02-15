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

import com.stpl.app.exception.NoSuchIvldInventoryWdActualMasException;
import com.stpl.app.model.IvldInventoryWdActualMas;
import com.stpl.app.service.IvldInventoryWdActualMasLocalServiceUtil;
import com.stpl.app.service.persistence.IvldInventoryWdActualMasPersistence;
import com.stpl.app.service.persistence.IvldInventoryWdActualMasUtil;

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
public class IvldInventoryWdActualMasPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldInventoryWdActualMasUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldInventoryWdActualMas> iterator = _ivldInventoryWdActualMases.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldInventoryWdActualMas ivldInventoryWdActualMas = _persistence.create(pk);

		Assert.assertNotNull(ivldInventoryWdActualMas);

		Assert.assertEquals(ivldInventoryWdActualMas.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldInventoryWdActualMas newIvldInventoryWdActualMas = addIvldInventoryWdActualMas();

		_persistence.remove(newIvldInventoryWdActualMas);

		IvldInventoryWdActualMas existingIvldInventoryWdActualMas = _persistence.fetchByPrimaryKey(newIvldInventoryWdActualMas.getPrimaryKey());

		Assert.assertNull(existingIvldInventoryWdActualMas);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldInventoryWdActualMas();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldInventoryWdActualMas newIvldInventoryWdActualMas = _persistence.create(pk);

		newIvldInventoryWdActualMas.setQuantityOnOrder(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setWeek(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setAmountOnHand(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setYear(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setItemId(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setModifiedDate(RandomTestUtil.nextDate());

		newIvldInventoryWdActualMas.setOrganizationKey(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setCreatedBy(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setCreatedDate(RandomTestUtil.nextDate());

		newIvldInventoryWdActualMas.setSource(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setDay(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setUnitsOnHand(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setAmountReceived(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setItemIdentifier(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setErrorCode(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldInventoryWdActualMas.setModifiedBy(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setMonth(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setAmountWithdrawn(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setInventoryWdActualMasIntfId(RandomTestUtil.nextInt());

		newIvldInventoryWdActualMas.setQuantityReceived(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setReasonForFailure(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setCountry(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setBatchId(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setErrorField(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setAmountOnOrder(RandomTestUtil.randomString());

		newIvldInventoryWdActualMas.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldInventoryWdActualMases.add(_persistence.update(
				newIvldInventoryWdActualMas));

		IvldInventoryWdActualMas existingIvldInventoryWdActualMas = _persistence.findByPrimaryKey(newIvldInventoryWdActualMas.getPrimaryKey());

		Assert.assertEquals(existingIvldInventoryWdActualMas.getQuantityOnOrder(),
			newIvldInventoryWdActualMas.getQuantityOnOrder());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getWeek(),
			newIvldInventoryWdActualMas.getWeek());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getAmountOnHand(),
			newIvldInventoryWdActualMas.getAmountOnHand());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getYear(),
			newIvldInventoryWdActualMas.getYear());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getItemId(),
			newIvldInventoryWdActualMas.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldInventoryWdActualMas.getModifiedDate()),
			Time.getShortTimestamp(
				newIvldInventoryWdActualMas.getModifiedDate()));
		Assert.assertEquals(existingIvldInventoryWdActualMas.getOrganizationKey(),
			newIvldInventoryWdActualMas.getOrganizationKey());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getCreatedBy(),
			newIvldInventoryWdActualMas.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldInventoryWdActualMas.getCreatedDate()),
			Time.getShortTimestamp(newIvldInventoryWdActualMas.getCreatedDate()));
		Assert.assertEquals(existingIvldInventoryWdActualMas.getSource(),
			newIvldInventoryWdActualMas.getSource());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getIvldInventoryWdActualMasSid(),
			newIvldInventoryWdActualMas.getIvldInventoryWdActualMasSid());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getDay(),
			newIvldInventoryWdActualMas.getDay());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getAddChgDelIndicator(),
			newIvldInventoryWdActualMas.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getUnitsOnHand(),
			newIvldInventoryWdActualMas.getUnitsOnHand());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getAmountReceived(),
			newIvldInventoryWdActualMas.getAmountReceived());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getItemIdentifier(),
			newIvldInventoryWdActualMas.getItemIdentifier());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getErrorCode(),
			newIvldInventoryWdActualMas.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldInventoryWdActualMas.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldInventoryWdActualMas.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldInventoryWdActualMas.getModifiedBy(),
			newIvldInventoryWdActualMas.getModifiedBy());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getMonth(),
			newIvldInventoryWdActualMas.getMonth());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getReprocessedFlag(),
			newIvldInventoryWdActualMas.getReprocessedFlag());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getAmountWithdrawn(),
			newIvldInventoryWdActualMas.getAmountWithdrawn());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getInventoryWdActualMasIntfId(),
			newIvldInventoryWdActualMas.getInventoryWdActualMasIntfId());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getQuantityReceived(),
			newIvldInventoryWdActualMas.getQuantityReceived());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getUnitsWithdrawn(),
			newIvldInventoryWdActualMas.getUnitsWithdrawn());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getReasonForFailure(),
			newIvldInventoryWdActualMas.getReasonForFailure());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getCountry(),
			newIvldInventoryWdActualMas.getCountry());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getItemIdentifierCodeQualifier(),
			newIvldInventoryWdActualMas.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getBatchId(),
			newIvldInventoryWdActualMas.getBatchId());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getErrorField(),
			newIvldInventoryWdActualMas.getErrorField());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getAmountOnOrder(),
			newIvldInventoryWdActualMas.getAmountOnOrder());
		Assert.assertEquals(existingIvldInventoryWdActualMas.getCheckRecord(),
			newIvldInventoryWdActualMas.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldInventoryWdActualMas newIvldInventoryWdActualMas = addIvldInventoryWdActualMas();

		IvldInventoryWdActualMas existingIvldInventoryWdActualMas = _persistence.findByPrimaryKey(newIvldInventoryWdActualMas.getPrimaryKey());

		Assert.assertEquals(existingIvldInventoryWdActualMas,
			newIvldInventoryWdActualMas);
	}

	@Test(expected = NoSuchIvldInventoryWdActualMasException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldInventoryWdActualMas> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_INVENTORY_WD_ACTUAL_MAS",
			"quantityOnOrder", true, "week", true, "amountOnHand", true,
			"year", true, "itemId", true, "modifiedDate", true,
			"organizationKey", true, "createdBy", true, "createdDate", true,
			"source", true, "ivldInventoryWdActualMasSid", true, "day", true,
			"addChgDelIndicator", true, "unitsOnHand", true, "amountReceived",
			true, "itemIdentifier", true, "errorCode", true,
			"intfInsertedDate", true, "modifiedBy", true, "month", true,
			"reprocessedFlag", true, "amountWithdrawn", true,
			"inventoryWdActualMasIntfId", true, "quantityReceived", true,
			"unitsWithdrawn", true, "reasonForFailure", true, "country", true,
			"itemIdentifierCodeQualifier", true, "batchId", true, "errorField",
			true, "amountOnOrder", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldInventoryWdActualMas newIvldInventoryWdActualMas = addIvldInventoryWdActualMas();

		IvldInventoryWdActualMas existingIvldInventoryWdActualMas = _persistence.fetchByPrimaryKey(newIvldInventoryWdActualMas.getPrimaryKey());

		Assert.assertEquals(existingIvldInventoryWdActualMas,
			newIvldInventoryWdActualMas);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldInventoryWdActualMas missingIvldInventoryWdActualMas = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldInventoryWdActualMas);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldInventoryWdActualMas newIvldInventoryWdActualMas1 = addIvldInventoryWdActualMas();
		IvldInventoryWdActualMas newIvldInventoryWdActualMas2 = addIvldInventoryWdActualMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldInventoryWdActualMas1.getPrimaryKey());
		primaryKeys.add(newIvldInventoryWdActualMas2.getPrimaryKey());

		Map<Serializable, IvldInventoryWdActualMas> ivldInventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldInventoryWdActualMases.size());
		Assert.assertEquals(newIvldInventoryWdActualMas1,
			ivldInventoryWdActualMases.get(
				newIvldInventoryWdActualMas1.getPrimaryKey()));
		Assert.assertEquals(newIvldInventoryWdActualMas2,
			ivldInventoryWdActualMases.get(
				newIvldInventoryWdActualMas2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldInventoryWdActualMas> ivldInventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldInventoryWdActualMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldInventoryWdActualMas newIvldInventoryWdActualMas = addIvldInventoryWdActualMas();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldInventoryWdActualMas.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldInventoryWdActualMas> ivldInventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldInventoryWdActualMases.size());
		Assert.assertEquals(newIvldInventoryWdActualMas,
			ivldInventoryWdActualMases.get(
				newIvldInventoryWdActualMas.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldInventoryWdActualMas> ivldInventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldInventoryWdActualMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldInventoryWdActualMas newIvldInventoryWdActualMas = addIvldInventoryWdActualMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldInventoryWdActualMas.getPrimaryKey());

		Map<Serializable, IvldInventoryWdActualMas> ivldInventoryWdActualMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldInventoryWdActualMases.size());
		Assert.assertEquals(newIvldInventoryWdActualMas,
			ivldInventoryWdActualMases.get(
				newIvldInventoryWdActualMas.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldInventoryWdActualMasLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldInventoryWdActualMas>() {
				@Override
				public void performAction(
					IvldInventoryWdActualMas ivldInventoryWdActualMas) {
					Assert.assertNotNull(ivldInventoryWdActualMas);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldInventoryWdActualMas newIvldInventoryWdActualMas = addIvldInventoryWdActualMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldInventoryWdActualMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldInventoryWdActualMasSid",
				newIvldInventoryWdActualMas.getIvldInventoryWdActualMasSid()));

		List<IvldInventoryWdActualMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldInventoryWdActualMas existingIvldInventoryWdActualMas = result.get(0);

		Assert.assertEquals(existingIvldInventoryWdActualMas,
			newIvldInventoryWdActualMas);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldInventoryWdActualMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldInventoryWdActualMasSid", RandomTestUtil.nextInt()));

		List<IvldInventoryWdActualMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldInventoryWdActualMas newIvldInventoryWdActualMas = addIvldInventoryWdActualMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldInventoryWdActualMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldInventoryWdActualMasSid"));

		Object newIvldInventoryWdActualMasSid = newIvldInventoryWdActualMas.getIvldInventoryWdActualMasSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldInventoryWdActualMasSid",
				new Object[] { newIvldInventoryWdActualMasSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldInventoryWdActualMasSid = result.get(0);

		Assert.assertEquals(existingIvldInventoryWdActualMasSid,
			newIvldInventoryWdActualMasSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldInventoryWdActualMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldInventoryWdActualMasSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldInventoryWdActualMasSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldInventoryWdActualMas addIvldInventoryWdActualMas()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldInventoryWdActualMas ivldInventoryWdActualMas = _persistence.create(pk);

		ivldInventoryWdActualMas.setQuantityOnOrder(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setWeek(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setAmountOnHand(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setYear(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setItemId(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setModifiedDate(RandomTestUtil.nextDate());

		ivldInventoryWdActualMas.setOrganizationKey(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setCreatedBy(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setCreatedDate(RandomTestUtil.nextDate());

		ivldInventoryWdActualMas.setSource(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setDay(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setUnitsOnHand(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setAmountReceived(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setItemIdentifier(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setErrorCode(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldInventoryWdActualMas.setModifiedBy(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setMonth(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setReprocessedFlag(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setAmountWithdrawn(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setInventoryWdActualMasIntfId(RandomTestUtil.nextInt());

		ivldInventoryWdActualMas.setQuantityReceived(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setReasonForFailure(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setCountry(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setBatchId(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setErrorField(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setAmountOnOrder(RandomTestUtil.randomString());

		ivldInventoryWdActualMas.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldInventoryWdActualMases.add(_persistence.update(
				ivldInventoryWdActualMas));

		return ivldInventoryWdActualMas;
	}

	private List<IvldInventoryWdActualMas> _ivldInventoryWdActualMases = new ArrayList<IvldInventoryWdActualMas>();
	private IvldInventoryWdActualMasPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}