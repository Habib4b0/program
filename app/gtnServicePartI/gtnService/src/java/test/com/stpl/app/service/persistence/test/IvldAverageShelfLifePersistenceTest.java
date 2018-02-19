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

import com.stpl.app.exception.NoSuchIvldAverageShelfLifeException;
import com.stpl.app.model.IvldAverageShelfLife;
import com.stpl.app.service.IvldAverageShelfLifeLocalServiceUtil;
import com.stpl.app.service.persistence.IvldAverageShelfLifePersistence;
import com.stpl.app.service.persistence.IvldAverageShelfLifeUtil;

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
public class IvldAverageShelfLifePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldAverageShelfLifeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldAverageShelfLife> iterator = _ivldAverageShelfLifes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldAverageShelfLife ivldAverageShelfLife = _persistence.create(pk);

		Assert.assertNotNull(ivldAverageShelfLife);

		Assert.assertEquals(ivldAverageShelfLife.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldAverageShelfLife newIvldAverageShelfLife = addIvldAverageShelfLife();

		_persistence.remove(newIvldAverageShelfLife);

		IvldAverageShelfLife existingIvldAverageShelfLife = _persistence.fetchByPrimaryKey(newIvldAverageShelfLife.getPrimaryKey());

		Assert.assertNull(existingIvldAverageShelfLife);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldAverageShelfLife();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldAverageShelfLife newIvldAverageShelfLife = _persistence.create(pk);

		newIvldAverageShelfLife.setReasonForFailure(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setItemId(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setAvgShelfLife(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setModifiedDate(RandomTestUtil.nextDate());

		newIvldAverageShelfLife.setCreatedBy(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setCreatedDate(RandomTestUtil.nextDate());

		newIvldAverageShelfLife.setSource(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setItemIdType(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setBatchId(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setAverageShelfLifeIntfid(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setErrorField(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setErrorCode(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldAverageShelfLife.setModifiedBy(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldAverageShelfLife.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldAverageShelfLifes.add(_persistence.update(newIvldAverageShelfLife));

		IvldAverageShelfLife existingIvldAverageShelfLife = _persistence.findByPrimaryKey(newIvldAverageShelfLife.getPrimaryKey());

		Assert.assertEquals(existingIvldAverageShelfLife.getReasonForFailure(),
			newIvldAverageShelfLife.getReasonForFailure());
		Assert.assertEquals(existingIvldAverageShelfLife.getIvldAverageShelfLifeSid(),
			newIvldAverageShelfLife.getIvldAverageShelfLifeSid());
		Assert.assertEquals(existingIvldAverageShelfLife.getItemId(),
			newIvldAverageShelfLife.getItemId());
		Assert.assertEquals(existingIvldAverageShelfLife.getAvgShelfLife(),
			newIvldAverageShelfLife.getAvgShelfLife());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldAverageShelfLife.getModifiedDate()),
			Time.getShortTimestamp(newIvldAverageShelfLife.getModifiedDate()));
		Assert.assertEquals(existingIvldAverageShelfLife.getCreatedBy(),
			newIvldAverageShelfLife.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldAverageShelfLife.getCreatedDate()),
			Time.getShortTimestamp(newIvldAverageShelfLife.getCreatedDate()));
		Assert.assertEquals(existingIvldAverageShelfLife.getSource(),
			newIvldAverageShelfLife.getSource());
		Assert.assertEquals(existingIvldAverageShelfLife.getItemIdType(),
			newIvldAverageShelfLife.getItemIdType());
		Assert.assertEquals(existingIvldAverageShelfLife.getBatchId(),
			newIvldAverageShelfLife.getBatchId());
		Assert.assertEquals(existingIvldAverageShelfLife.getAddChgDelIndicator(),
			newIvldAverageShelfLife.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldAverageShelfLife.getAverageShelfLifeIntfid(),
			newIvldAverageShelfLife.getAverageShelfLifeIntfid());
		Assert.assertEquals(existingIvldAverageShelfLife.getErrorField(),
			newIvldAverageShelfLife.getErrorField());
		Assert.assertEquals(existingIvldAverageShelfLife.getErrorCode(),
			newIvldAverageShelfLife.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldAverageShelfLife.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldAverageShelfLife.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldAverageShelfLife.getModifiedBy(),
			newIvldAverageShelfLife.getModifiedBy());
		Assert.assertEquals(existingIvldAverageShelfLife.getReprocessedFlag(),
			newIvldAverageShelfLife.getReprocessedFlag());
		Assert.assertEquals(existingIvldAverageShelfLife.getCheckRecord(),
			newIvldAverageShelfLife.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldAverageShelfLife newIvldAverageShelfLife = addIvldAverageShelfLife();

		IvldAverageShelfLife existingIvldAverageShelfLife = _persistence.findByPrimaryKey(newIvldAverageShelfLife.getPrimaryKey());

		Assert.assertEquals(existingIvldAverageShelfLife,
			newIvldAverageShelfLife);
	}

	@Test(expected = NoSuchIvldAverageShelfLifeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldAverageShelfLife> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_AVERAGE_SHELF_LIFE",
			"reasonForFailure", true, "ivldAverageShelfLifeSid", true,
			"itemId", true, "avgShelfLife", true, "modifiedDate", true,
			"createdBy", true, "createdDate", true, "source", true,
			"itemIdType", true, "batchId", true, "addChgDelIndicator", true,
			"averageShelfLifeIntfid", true, "errorField", true, "errorCode",
			true, "intfInsertedDate", true, "modifiedBy", true,
			"reprocessedFlag", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldAverageShelfLife newIvldAverageShelfLife = addIvldAverageShelfLife();

		IvldAverageShelfLife existingIvldAverageShelfLife = _persistence.fetchByPrimaryKey(newIvldAverageShelfLife.getPrimaryKey());

		Assert.assertEquals(existingIvldAverageShelfLife,
			newIvldAverageShelfLife);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldAverageShelfLife missingIvldAverageShelfLife = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldAverageShelfLife);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldAverageShelfLife newIvldAverageShelfLife1 = addIvldAverageShelfLife();
		IvldAverageShelfLife newIvldAverageShelfLife2 = addIvldAverageShelfLife();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldAverageShelfLife1.getPrimaryKey());
		primaryKeys.add(newIvldAverageShelfLife2.getPrimaryKey());

		Map<Serializable, IvldAverageShelfLife> ivldAverageShelfLifes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldAverageShelfLifes.size());
		Assert.assertEquals(newIvldAverageShelfLife1,
			ivldAverageShelfLifes.get(newIvldAverageShelfLife1.getPrimaryKey()));
		Assert.assertEquals(newIvldAverageShelfLife2,
			ivldAverageShelfLifes.get(newIvldAverageShelfLife2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldAverageShelfLife> ivldAverageShelfLifes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldAverageShelfLifes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldAverageShelfLife newIvldAverageShelfLife = addIvldAverageShelfLife();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldAverageShelfLife.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldAverageShelfLife> ivldAverageShelfLifes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldAverageShelfLifes.size());
		Assert.assertEquals(newIvldAverageShelfLife,
			ivldAverageShelfLifes.get(newIvldAverageShelfLife.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldAverageShelfLife> ivldAverageShelfLifes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldAverageShelfLifes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldAverageShelfLife newIvldAverageShelfLife = addIvldAverageShelfLife();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldAverageShelfLife.getPrimaryKey());

		Map<Serializable, IvldAverageShelfLife> ivldAverageShelfLifes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldAverageShelfLifes.size());
		Assert.assertEquals(newIvldAverageShelfLife,
			ivldAverageShelfLifes.get(newIvldAverageShelfLife.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldAverageShelfLifeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldAverageShelfLife>() {
				@Override
				public void performAction(
					IvldAverageShelfLife ivldAverageShelfLife) {
					Assert.assertNotNull(ivldAverageShelfLife);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldAverageShelfLife newIvldAverageShelfLife = addIvldAverageShelfLife();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldAverageShelfLife.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldAverageShelfLifeSid",
				newIvldAverageShelfLife.getIvldAverageShelfLifeSid()));

		List<IvldAverageShelfLife> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldAverageShelfLife existingIvldAverageShelfLife = result.get(0);

		Assert.assertEquals(existingIvldAverageShelfLife,
			newIvldAverageShelfLife);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldAverageShelfLife.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldAverageShelfLifeSid",
				RandomTestUtil.nextInt()));

		List<IvldAverageShelfLife> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldAverageShelfLife newIvldAverageShelfLife = addIvldAverageShelfLife();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldAverageShelfLife.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldAverageShelfLifeSid"));

		Object newIvldAverageShelfLifeSid = newIvldAverageShelfLife.getIvldAverageShelfLifeSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldAverageShelfLifeSid",
				new Object[] { newIvldAverageShelfLifeSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldAverageShelfLifeSid = result.get(0);

		Assert.assertEquals(existingIvldAverageShelfLifeSid,
			newIvldAverageShelfLifeSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldAverageShelfLife.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldAverageShelfLifeSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldAverageShelfLifeSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldAverageShelfLife addIvldAverageShelfLife()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldAverageShelfLife ivldAverageShelfLife = _persistence.create(pk);

		ivldAverageShelfLife.setReasonForFailure(RandomTestUtil.randomString());

		ivldAverageShelfLife.setItemId(RandomTestUtil.randomString());

		ivldAverageShelfLife.setAvgShelfLife(RandomTestUtil.randomString());

		ivldAverageShelfLife.setModifiedDate(RandomTestUtil.nextDate());

		ivldAverageShelfLife.setCreatedBy(RandomTestUtil.randomString());

		ivldAverageShelfLife.setCreatedDate(RandomTestUtil.nextDate());

		ivldAverageShelfLife.setSource(RandomTestUtil.randomString());

		ivldAverageShelfLife.setItemIdType(RandomTestUtil.randomString());

		ivldAverageShelfLife.setBatchId(RandomTestUtil.randomString());

		ivldAverageShelfLife.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldAverageShelfLife.setAverageShelfLifeIntfid(RandomTestUtil.randomString());

		ivldAverageShelfLife.setErrorField(RandomTestUtil.randomString());

		ivldAverageShelfLife.setErrorCode(RandomTestUtil.randomString());

		ivldAverageShelfLife.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldAverageShelfLife.setModifiedBy(RandomTestUtil.randomString());

		ivldAverageShelfLife.setReprocessedFlag(RandomTestUtil.randomString());

		ivldAverageShelfLife.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldAverageShelfLifes.add(_persistence.update(ivldAverageShelfLife));

		return ivldAverageShelfLife;
	}

	private List<IvldAverageShelfLife> _ivldAverageShelfLifes = new ArrayList<IvldAverageShelfLife>();
	private IvldAverageShelfLifePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}