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

import com.stpl.app.exception.NoSuchIvldInventoryWdProjMasException;
import com.stpl.app.model.IvldInventoryWdProjMas;
import com.stpl.app.service.IvldInventoryWdProjMasLocalServiceUtil;
import com.stpl.app.service.persistence.IvldInventoryWdProjMasPersistence;
import com.stpl.app.service.persistence.IvldInventoryWdProjMasUtil;

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
public class IvldInventoryWdProjMasPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldInventoryWdProjMasUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldInventoryWdProjMas> iterator = _ivldInventoryWdProjMases.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldInventoryWdProjMas ivldInventoryWdProjMas = _persistence.create(pk);

		Assert.assertNotNull(ivldInventoryWdProjMas);

		Assert.assertEquals(ivldInventoryWdProjMas.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldInventoryWdProjMas newIvldInventoryWdProjMas = addIvldInventoryWdProjMas();

		_persistence.remove(newIvldInventoryWdProjMas);

		IvldInventoryWdProjMas existingIvldInventoryWdProjMas = _persistence.fetchByPrimaryKey(newIvldInventoryWdProjMas.getPrimaryKey());

		Assert.assertNull(existingIvldInventoryWdProjMas);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldInventoryWdProjMas();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldInventoryWdProjMas newIvldInventoryWdProjMas = _persistence.create(pk);

		newIvldInventoryWdProjMas.setInventoryWdProjMasIntfId(RandomTestUtil.nextInt());

		newIvldInventoryWdProjMas.setWeek(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setReasonForFailure(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setCountry(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setYear(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setItemId(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setModifiedDate(RandomTestUtil.nextDate());

		newIvldInventoryWdProjMas.setOrganizationKey(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setSource(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setCreatedDate(RandomTestUtil.nextDate());

		newIvldInventoryWdProjMas.setCreatedBy(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setDay(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setForecastVer(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setBatchId(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setItemIdentifier(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setErrorField(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setErrorCode(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldInventoryWdProjMas.setModifiedBy(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setMonth(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setForecastName(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setAmountWithdrawn(RandomTestUtil.randomString());

		newIvldInventoryWdProjMas.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldInventoryWdProjMases.add(_persistence.update(
				newIvldInventoryWdProjMas));

		IvldInventoryWdProjMas existingIvldInventoryWdProjMas = _persistence.findByPrimaryKey(newIvldInventoryWdProjMas.getPrimaryKey());

		Assert.assertEquals(existingIvldInventoryWdProjMas.getInventoryWdProjMasIntfId(),
			newIvldInventoryWdProjMas.getInventoryWdProjMasIntfId());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getWeek(),
			newIvldInventoryWdProjMas.getWeek());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getUnitsWithdrawn(),
			newIvldInventoryWdProjMas.getUnitsWithdrawn());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getReasonForFailure(),
			newIvldInventoryWdProjMas.getReasonForFailure());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getCountry(),
			newIvldInventoryWdProjMas.getCountry());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getYear(),
			newIvldInventoryWdProjMas.getYear());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getItemId(),
			newIvldInventoryWdProjMas.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldInventoryWdProjMas.getModifiedDate()),
			Time.getShortTimestamp(newIvldInventoryWdProjMas.getModifiedDate()));
		Assert.assertEquals(existingIvldInventoryWdProjMas.getOrganizationKey(),
			newIvldInventoryWdProjMas.getOrganizationKey());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getItemIdentifierCodeQualifier(),
			newIvldInventoryWdProjMas.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getSource(),
			newIvldInventoryWdProjMas.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldInventoryWdProjMas.getCreatedDate()),
			Time.getShortTimestamp(newIvldInventoryWdProjMas.getCreatedDate()));
		Assert.assertEquals(existingIvldInventoryWdProjMas.getCreatedBy(),
			newIvldInventoryWdProjMas.getCreatedBy());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getDay(),
			newIvldInventoryWdProjMas.getDay());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getForecastVer(),
			newIvldInventoryWdProjMas.getForecastVer());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getBatchId(),
			newIvldInventoryWdProjMas.getBatchId());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getAddChgDelIndicator(),
			newIvldInventoryWdProjMas.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getItemIdentifier(),
			newIvldInventoryWdProjMas.getItemIdentifier());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getErrorField(),
			newIvldInventoryWdProjMas.getErrorField());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getErrorCode(),
			newIvldInventoryWdProjMas.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldInventoryWdProjMas.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldInventoryWdProjMas.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldInventoryWdProjMas.getModifiedBy(),
			newIvldInventoryWdProjMas.getModifiedBy());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getIvldInventoryWdProjMasSid(),
			newIvldInventoryWdProjMas.getIvldInventoryWdProjMasSid());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getMonth(),
			newIvldInventoryWdProjMas.getMonth());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getReprocessedFlag(),
			newIvldInventoryWdProjMas.getReprocessedFlag());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getForecastName(),
			newIvldInventoryWdProjMas.getForecastName());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getAmountWithdrawn(),
			newIvldInventoryWdProjMas.getAmountWithdrawn());
		Assert.assertEquals(existingIvldInventoryWdProjMas.getCheckRecord(),
			newIvldInventoryWdProjMas.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldInventoryWdProjMas newIvldInventoryWdProjMas = addIvldInventoryWdProjMas();

		IvldInventoryWdProjMas existingIvldInventoryWdProjMas = _persistence.findByPrimaryKey(newIvldInventoryWdProjMas.getPrimaryKey());

		Assert.assertEquals(existingIvldInventoryWdProjMas,
			newIvldInventoryWdProjMas);
	}

	@Test(expected = NoSuchIvldInventoryWdProjMasException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldInventoryWdProjMas> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_INVENTORY_WD_PROJ_MAS",
			"inventoryWdProjMasIntfId", true, "week", true, "unitsWithdrawn",
			true, "reasonForFailure", true, "country", true, "year", true,
			"itemId", true, "modifiedDate", true, "organizationKey", true,
			"itemIdentifierCodeQualifier", true, "source", true, "createdDate",
			true, "createdBy", true, "day", true, "forecastVer", true,
			"batchId", true, "addChgDelIndicator", true, "itemIdentifier",
			true, "errorField", true, "errorCode", true, "intfInsertedDate",
			true, "modifiedBy", true, "ivldInventoryWdProjMasSid", true,
			"month", true, "reprocessedFlag", true, "forecastName", true,
			"amountWithdrawn", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldInventoryWdProjMas newIvldInventoryWdProjMas = addIvldInventoryWdProjMas();

		IvldInventoryWdProjMas existingIvldInventoryWdProjMas = _persistence.fetchByPrimaryKey(newIvldInventoryWdProjMas.getPrimaryKey());

		Assert.assertEquals(existingIvldInventoryWdProjMas,
			newIvldInventoryWdProjMas);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldInventoryWdProjMas missingIvldInventoryWdProjMas = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldInventoryWdProjMas);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldInventoryWdProjMas newIvldInventoryWdProjMas1 = addIvldInventoryWdProjMas();
		IvldInventoryWdProjMas newIvldInventoryWdProjMas2 = addIvldInventoryWdProjMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldInventoryWdProjMas1.getPrimaryKey());
		primaryKeys.add(newIvldInventoryWdProjMas2.getPrimaryKey());

		Map<Serializable, IvldInventoryWdProjMas> ivldInventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldInventoryWdProjMases.size());
		Assert.assertEquals(newIvldInventoryWdProjMas1,
			ivldInventoryWdProjMases.get(
				newIvldInventoryWdProjMas1.getPrimaryKey()));
		Assert.assertEquals(newIvldInventoryWdProjMas2,
			ivldInventoryWdProjMases.get(
				newIvldInventoryWdProjMas2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldInventoryWdProjMas> ivldInventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldInventoryWdProjMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldInventoryWdProjMas newIvldInventoryWdProjMas = addIvldInventoryWdProjMas();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldInventoryWdProjMas.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldInventoryWdProjMas> ivldInventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldInventoryWdProjMases.size());
		Assert.assertEquals(newIvldInventoryWdProjMas,
			ivldInventoryWdProjMases.get(
				newIvldInventoryWdProjMas.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldInventoryWdProjMas> ivldInventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldInventoryWdProjMases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldInventoryWdProjMas newIvldInventoryWdProjMas = addIvldInventoryWdProjMas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldInventoryWdProjMas.getPrimaryKey());

		Map<Serializable, IvldInventoryWdProjMas> ivldInventoryWdProjMases = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldInventoryWdProjMases.size());
		Assert.assertEquals(newIvldInventoryWdProjMas,
			ivldInventoryWdProjMases.get(
				newIvldInventoryWdProjMas.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldInventoryWdProjMasLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldInventoryWdProjMas>() {
				@Override
				public void performAction(
					IvldInventoryWdProjMas ivldInventoryWdProjMas) {
					Assert.assertNotNull(ivldInventoryWdProjMas);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldInventoryWdProjMas newIvldInventoryWdProjMas = addIvldInventoryWdProjMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldInventoryWdProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldInventoryWdProjMasSid",
				newIvldInventoryWdProjMas.getIvldInventoryWdProjMasSid()));

		List<IvldInventoryWdProjMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldInventoryWdProjMas existingIvldInventoryWdProjMas = result.get(0);

		Assert.assertEquals(existingIvldInventoryWdProjMas,
			newIvldInventoryWdProjMas);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldInventoryWdProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldInventoryWdProjMasSid", RandomTestUtil.nextInt()));

		List<IvldInventoryWdProjMas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldInventoryWdProjMas newIvldInventoryWdProjMas = addIvldInventoryWdProjMas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldInventoryWdProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldInventoryWdProjMasSid"));

		Object newIvldInventoryWdProjMasSid = newIvldInventoryWdProjMas.getIvldInventoryWdProjMasSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldInventoryWdProjMasSid",
				new Object[] { newIvldInventoryWdProjMasSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldInventoryWdProjMasSid = result.get(0);

		Assert.assertEquals(existingIvldInventoryWdProjMasSid,
			newIvldInventoryWdProjMasSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldInventoryWdProjMas.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldInventoryWdProjMasSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldInventoryWdProjMasSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldInventoryWdProjMas addIvldInventoryWdProjMas()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldInventoryWdProjMas ivldInventoryWdProjMas = _persistence.create(pk);

		ivldInventoryWdProjMas.setInventoryWdProjMasIntfId(RandomTestUtil.nextInt());

		ivldInventoryWdProjMas.setWeek(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setUnitsWithdrawn(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setReasonForFailure(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setCountry(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setYear(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setItemId(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setModifiedDate(RandomTestUtil.nextDate());

		ivldInventoryWdProjMas.setOrganizationKey(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setSource(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setCreatedDate(RandomTestUtil.nextDate());

		ivldInventoryWdProjMas.setCreatedBy(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setDay(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setForecastVer(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setBatchId(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setItemIdentifier(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setErrorField(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setErrorCode(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldInventoryWdProjMas.setModifiedBy(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setMonth(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setReprocessedFlag(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setForecastName(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setAmountWithdrawn(RandomTestUtil.randomString());

		ivldInventoryWdProjMas.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldInventoryWdProjMases.add(_persistence.update(
				ivldInventoryWdProjMas));

		return ivldInventoryWdProjMas;
	}

	private List<IvldInventoryWdProjMas> _ivldInventoryWdProjMases = new ArrayList<IvldInventoryWdProjMas>();
	private IvldInventoryWdProjMasPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}