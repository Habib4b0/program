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

import com.stpl.app.exception.NoSuchStNmActualDiscountException;
import com.stpl.app.model.StNmActualDiscount;
import com.stpl.app.service.StNmActualDiscountLocalServiceUtil;
import com.stpl.app.service.persistence.StNmActualDiscountPK;
import com.stpl.app.service.persistence.StNmActualDiscountPersistence;
import com.stpl.app.service.persistence.StNmActualDiscountUtil;

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
public class StNmActualDiscountPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNmActualDiscountUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNmActualDiscount> iterator = _stNmActualDiscounts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNmActualDiscountPK pk = new StNmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmActualDiscount stNmActualDiscount = _persistence.create(pk);

		Assert.assertNotNull(stNmActualDiscount);

		Assert.assertEquals(stNmActualDiscount.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNmActualDiscount newStNmActualDiscount = addStNmActualDiscount();

		_persistence.remove(newStNmActualDiscount);

		StNmActualDiscount existingStNmActualDiscount = _persistence.fetchByPrimaryKey(newStNmActualDiscount.getPrimaryKey());

		Assert.assertNull(existingStNmActualDiscount);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNmActualDiscount();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNmActualDiscountPK pk = new StNmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmActualDiscount newStNmActualDiscount = _persistence.create(pk);

		newStNmActualDiscount.setActualSales(RandomTestUtil.nextDouble());

		newStNmActualDiscount.setActualRate(RandomTestUtil.nextDouble());

		newStNmActualDiscount.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNmActualDiscount.setActualProjectionSales(RandomTestUtil.nextDouble());

		newStNmActualDiscount.setActualProjectionRate(RandomTestUtil.nextDouble());

		_stNmActualDiscounts.add(_persistence.update(newStNmActualDiscount));

		StNmActualDiscount existingStNmActualDiscount = _persistence.findByPrimaryKey(newStNmActualDiscount.getPrimaryKey());

		AssertUtils.assertEquals(existingStNmActualDiscount.getActualSales(),
			newStNmActualDiscount.getActualSales());
		Assert.assertEquals(existingStNmActualDiscount.getPeriodSid(),
			newStNmActualDiscount.getPeriodSid());
		AssertUtils.assertEquals(existingStNmActualDiscount.getActualRate(),
			newStNmActualDiscount.getActualRate());
		Assert.assertEquals(existingStNmActualDiscount.getUserId(),
			newStNmActualDiscount.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmActualDiscount.getLastModifiedDate()),
			Time.getShortTimestamp(newStNmActualDiscount.getLastModifiedDate()));
		AssertUtils.assertEquals(existingStNmActualDiscount.getActualProjectionSales(),
			newStNmActualDiscount.getActualProjectionSales());
		AssertUtils.assertEquals(existingStNmActualDiscount.getActualProjectionRate(),
			newStNmActualDiscount.getActualProjectionRate());
		Assert.assertEquals(existingStNmActualDiscount.getProjectionDetailsSid(),
			newStNmActualDiscount.getProjectionDetailsSid());
		Assert.assertEquals(existingStNmActualDiscount.getRsModelSid(),
			newStNmActualDiscount.getRsModelSid());
		Assert.assertEquals(existingStNmActualDiscount.getSessionId(),
			newStNmActualDiscount.getSessionId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNmActualDiscount newStNmActualDiscount = addStNmActualDiscount();

		StNmActualDiscount existingStNmActualDiscount = _persistence.findByPrimaryKey(newStNmActualDiscount.getPrimaryKey());

		Assert.assertEquals(existingStNmActualDiscount, newStNmActualDiscount);
	}

	@Test(expected = NoSuchStNmActualDiscountException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNmActualDiscountPK pk = new StNmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNmActualDiscount newStNmActualDiscount = addStNmActualDiscount();

		StNmActualDiscount existingStNmActualDiscount = _persistence.fetchByPrimaryKey(newStNmActualDiscount.getPrimaryKey());

		Assert.assertEquals(existingStNmActualDiscount, newStNmActualDiscount);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNmActualDiscountPK pk = new StNmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmActualDiscount missingStNmActualDiscount = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNmActualDiscount);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNmActualDiscount newStNmActualDiscount1 = addStNmActualDiscount();
		StNmActualDiscount newStNmActualDiscount2 = addStNmActualDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmActualDiscount1.getPrimaryKey());
		primaryKeys.add(newStNmActualDiscount2.getPrimaryKey());

		Map<Serializable, StNmActualDiscount> stNmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNmActualDiscounts.size());
		Assert.assertEquals(newStNmActualDiscount1,
			stNmActualDiscounts.get(newStNmActualDiscount1.getPrimaryKey()));
		Assert.assertEquals(newStNmActualDiscount2,
			stNmActualDiscounts.get(newStNmActualDiscount2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNmActualDiscountPK pk1 = new StNmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmActualDiscountPK pk2 = new StNmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNmActualDiscount> stNmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmActualDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNmActualDiscount newStNmActualDiscount = addStNmActualDiscount();

		StNmActualDiscountPK pk = new StNmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmActualDiscount.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNmActualDiscount> stNmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmActualDiscounts.size());
		Assert.assertEquals(newStNmActualDiscount,
			stNmActualDiscounts.get(newStNmActualDiscount.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNmActualDiscount> stNmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmActualDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNmActualDiscount newStNmActualDiscount = addStNmActualDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmActualDiscount.getPrimaryKey());

		Map<Serializable, StNmActualDiscount> stNmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmActualDiscounts.size());
		Assert.assertEquals(newStNmActualDiscount,
			stNmActualDiscounts.get(newStNmActualDiscount.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StNmActualDiscountLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StNmActualDiscount>() {
				@Override
				public void performAction(StNmActualDiscount stNmActualDiscount) {
					Assert.assertNotNull(stNmActualDiscount);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNmActualDiscount newStNmActualDiscount = addStNmActualDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStNmActualDiscount.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNmActualDiscount.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStNmActualDiscount.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newStNmActualDiscount.getRsModelSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNmActualDiscount.getSessionId()));

		List<StNmActualDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNmActualDiscount existingStNmActualDiscount = result.get(0);

		Assert.assertEquals(existingStNmActualDiscount, newStNmActualDiscount);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNmActualDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNmActualDiscount newStNmActualDiscount = addStNmActualDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newStNmActualDiscount.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNmActualDiscount addStNmActualDiscount()
		throws Exception {
		StNmActualDiscountPK pk = new StNmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmActualDiscount stNmActualDiscount = _persistence.create(pk);

		stNmActualDiscount.setActualSales(RandomTestUtil.nextDouble());

		stNmActualDiscount.setActualRate(RandomTestUtil.nextDouble());

		stNmActualDiscount.setLastModifiedDate(RandomTestUtil.nextDate());

		stNmActualDiscount.setActualProjectionSales(RandomTestUtil.nextDouble());

		stNmActualDiscount.setActualProjectionRate(RandomTestUtil.nextDouble());

		_stNmActualDiscounts.add(_persistence.update(stNmActualDiscount));

		return stNmActualDiscount;
	}

	private List<StNmActualDiscount> _stNmActualDiscounts = new ArrayList<StNmActualDiscount>();
	private StNmActualDiscountPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}