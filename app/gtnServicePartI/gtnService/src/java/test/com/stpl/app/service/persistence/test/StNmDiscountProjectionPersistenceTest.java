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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchStNmDiscountProjectionException;
import com.stpl.app.model.StNmDiscountProjection;
import com.stpl.app.service.StNmDiscountProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.StNmDiscountProjectionPK;
import com.stpl.app.service.persistence.StNmDiscountProjectionPersistence;
import com.stpl.app.service.persistence.StNmDiscountProjectionUtil;

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
public class StNmDiscountProjectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNmDiscountProjectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNmDiscountProjection> iterator = _stNmDiscountProjections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNmDiscountProjectionPK pk = new StNmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmDiscountProjection stNmDiscountProjection = _persistence.create(pk);

		Assert.assertNotNull(stNmDiscountProjection);

		Assert.assertEquals(stNmDiscountProjection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNmDiscountProjection newStNmDiscountProjection = addStNmDiscountProjection();

		_persistence.remove(newStNmDiscountProjection);

		StNmDiscountProjection existingStNmDiscountProjection = _persistence.fetchByPrimaryKey(newStNmDiscountProjection.getPrimaryKey());

		Assert.assertNull(existingStNmDiscountProjection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNmDiscountProjection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNmDiscountProjectionPK pk = new StNmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmDiscountProjection newStNmDiscountProjection = _persistence.create(pk);

		newStNmDiscountProjection.setProjectionRate(RandomTestUtil.nextDouble());

		newStNmDiscountProjection.setAdjustmentValue(RandomTestUtil.nextDouble());

		newStNmDiscountProjection.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNmDiscountProjection.setDiscountRate(RandomTestUtil.nextDouble());

		newStNmDiscountProjection.setProjectionSales(RandomTestUtil.nextDouble());

		newStNmDiscountProjection.setAdjustmentType(RandomTestUtil.randomString());

		newStNmDiscountProjection.setAdjustmentBasis(RandomTestUtil.randomString());

		newStNmDiscountProjection.setAdjustmentMethodology(RandomTestUtil.randomString());

		_stNmDiscountProjections.add(_persistence.update(
				newStNmDiscountProjection));

		StNmDiscountProjection existingStNmDiscountProjection = _persistence.findByPrimaryKey(newStNmDiscountProjection.getPrimaryKey());

		AssertUtils.assertEquals(existingStNmDiscountProjection.getProjectionRate(),
			newStNmDiscountProjection.getProjectionRate());
		AssertUtils.assertEquals(existingStNmDiscountProjection.getAdjustmentValue(),
			newStNmDiscountProjection.getAdjustmentValue());
		Assert.assertEquals(existingStNmDiscountProjection.getUserId(),
			newStNmDiscountProjection.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmDiscountProjection.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStNmDiscountProjection.getLastModifiedDate()));
		AssertUtils.assertEquals(existingStNmDiscountProjection.getDiscountRate(),
			newStNmDiscountProjection.getDiscountRate());
		AssertUtils.assertEquals(existingStNmDiscountProjection.getProjectionSales(),
			newStNmDiscountProjection.getProjectionSales());
		Assert.assertEquals(existingStNmDiscountProjection.getAdjustmentType(),
			newStNmDiscountProjection.getAdjustmentType());
		Assert.assertEquals(existingStNmDiscountProjection.getAdjustmentBasis(),
			newStNmDiscountProjection.getAdjustmentBasis());
		Assert.assertEquals(existingStNmDiscountProjection.getPeriodSid(),
			newStNmDiscountProjection.getPeriodSid());
		Assert.assertEquals(existingStNmDiscountProjection.getAdjustmentMethodology(),
			newStNmDiscountProjection.getAdjustmentMethodology());
		Assert.assertEquals(existingStNmDiscountProjection.getProjectionDetailsSid(),
			newStNmDiscountProjection.getProjectionDetailsSid());
		Assert.assertEquals(existingStNmDiscountProjection.getRsModelSid(),
			newStNmDiscountProjection.getRsModelSid());
		Assert.assertEquals(existingStNmDiscountProjection.getSessionId(),
			newStNmDiscountProjection.getSessionId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNmDiscountProjection newStNmDiscountProjection = addStNmDiscountProjection();

		StNmDiscountProjection existingStNmDiscountProjection = _persistence.findByPrimaryKey(newStNmDiscountProjection.getPrimaryKey());

		Assert.assertEquals(existingStNmDiscountProjection,
			newStNmDiscountProjection);
	}

	@Test(expected = NoSuchStNmDiscountProjectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNmDiscountProjectionPK pk = new StNmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNmDiscountProjection newStNmDiscountProjection = addStNmDiscountProjection();

		StNmDiscountProjection existingStNmDiscountProjection = _persistence.fetchByPrimaryKey(newStNmDiscountProjection.getPrimaryKey());

		Assert.assertEquals(existingStNmDiscountProjection,
			newStNmDiscountProjection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNmDiscountProjectionPK pk = new StNmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmDiscountProjection missingStNmDiscountProjection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNmDiscountProjection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNmDiscountProjection newStNmDiscountProjection1 = addStNmDiscountProjection();
		StNmDiscountProjection newStNmDiscountProjection2 = addStNmDiscountProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmDiscountProjection1.getPrimaryKey());
		primaryKeys.add(newStNmDiscountProjection2.getPrimaryKey());

		Map<Serializable, StNmDiscountProjection> stNmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNmDiscountProjections.size());
		Assert.assertEquals(newStNmDiscountProjection1,
			stNmDiscountProjections.get(
				newStNmDiscountProjection1.getPrimaryKey()));
		Assert.assertEquals(newStNmDiscountProjection2,
			stNmDiscountProjections.get(
				newStNmDiscountProjection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNmDiscountProjectionPK pk1 = new StNmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmDiscountProjectionPK pk2 = new StNmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNmDiscountProjection> stNmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmDiscountProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNmDiscountProjection newStNmDiscountProjection = addStNmDiscountProjection();

		StNmDiscountProjectionPK pk = new StNmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmDiscountProjection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNmDiscountProjection> stNmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmDiscountProjections.size());
		Assert.assertEquals(newStNmDiscountProjection,
			stNmDiscountProjections.get(
				newStNmDiscountProjection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNmDiscountProjection> stNmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmDiscountProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNmDiscountProjection newStNmDiscountProjection = addStNmDiscountProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmDiscountProjection.getPrimaryKey());

		Map<Serializable, StNmDiscountProjection> stNmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmDiscountProjections.size());
		Assert.assertEquals(newStNmDiscountProjection,
			stNmDiscountProjections.get(
				newStNmDiscountProjection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StNmDiscountProjectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StNmDiscountProjection>() {
				@Override
				public void performAction(
					StNmDiscountProjection stNmDiscountProjection) {
					Assert.assertNotNull(stNmDiscountProjection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNmDiscountProjection newStNmDiscountProjection = addStNmDiscountProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmDiscountProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNmDiscountProjection.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStNmDiscountProjection.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStNmDiscountProjection.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newStNmDiscountProjection.getRsModelSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNmDiscountProjection.getSessionId()));

		List<StNmDiscountProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNmDiscountProjection existingStNmDiscountProjection = result.get(0);

		Assert.assertEquals(existingStNmDiscountProjection,
			newStNmDiscountProjection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmDiscountProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNmDiscountProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNmDiscountProjection newStNmDiscountProjection = addStNmDiscountProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmDiscountProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		Object newUserId = newStNmDiscountProjection.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmDiscountProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNmDiscountProjection addStNmDiscountProjection()
		throws Exception {
		StNmDiscountProjectionPK pk = new StNmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmDiscountProjection stNmDiscountProjection = _persistence.create(pk);

		stNmDiscountProjection.setProjectionRate(RandomTestUtil.nextDouble());

		stNmDiscountProjection.setAdjustmentValue(RandomTestUtil.nextDouble());

		stNmDiscountProjection.setLastModifiedDate(RandomTestUtil.nextDate());

		stNmDiscountProjection.setDiscountRate(RandomTestUtil.nextDouble());

		stNmDiscountProjection.setProjectionSales(RandomTestUtil.nextDouble());

		stNmDiscountProjection.setAdjustmentType(RandomTestUtil.randomString());

		stNmDiscountProjection.setAdjustmentBasis(RandomTestUtil.randomString());

		stNmDiscountProjection.setAdjustmentMethodology(RandomTestUtil.randomString());

		_stNmDiscountProjections.add(_persistence.update(stNmDiscountProjection));

		return stNmDiscountProjection;
	}

	private List<StNmDiscountProjection> _stNmDiscountProjections = new ArrayList<StNmDiscountProjection>();
	private StNmDiscountProjectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}