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

import com.stpl.app.exception.NoSuchStChActualDiscountException;
import com.stpl.app.model.StChActualDiscount;
import com.stpl.app.service.StChActualDiscountLocalServiceUtil;
import com.stpl.app.service.persistence.StChActualDiscountPK;
import com.stpl.app.service.persistence.StChActualDiscountPersistence;
import com.stpl.app.service.persistence.StChActualDiscountUtil;

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
public class StChActualDiscountPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StChActualDiscountUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StChActualDiscount> iterator = _stChActualDiscounts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StChActualDiscountPK pk = new StChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChActualDiscount stChActualDiscount = _persistence.create(pk);

		Assert.assertNotNull(stChActualDiscount);

		Assert.assertEquals(stChActualDiscount.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StChActualDiscount newStChActualDiscount = addStChActualDiscount();

		_persistence.remove(newStChActualDiscount);

		StChActualDiscount existingStChActualDiscount = _persistence.fetchByPrimaryKey(newStChActualDiscount.getPrimaryKey());

		Assert.assertNull(existingStChActualDiscount);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStChActualDiscount();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StChActualDiscountPK pk = new StChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChActualDiscount newStChActualDiscount = _persistence.create(pk);

		newStChActualDiscount.setLastModifiedDate(RandomTestUtil.nextDate());

		newStChActualDiscount.setActualRate(RandomTestUtil.nextDouble());

		newStChActualDiscount.setActualSales(RandomTestUtil.nextDouble());

		_stChActualDiscounts.add(_persistence.update(newStChActualDiscount));

		StChActualDiscount existingStChActualDiscount = _persistence.findByPrimaryKey(newStChActualDiscount.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStChActualDiscount.getLastModifiedDate()),
			Time.getShortTimestamp(newStChActualDiscount.getLastModifiedDate()));
		AssertUtils.assertEquals(existingStChActualDiscount.getActualRate(),
			newStChActualDiscount.getActualRate());
		Assert.assertEquals(existingStChActualDiscount.getPeriodSid(),
			newStChActualDiscount.getPeriodSid());
		Assert.assertEquals(existingStChActualDiscount.getProjectionDetailsSid(),
			newStChActualDiscount.getProjectionDetailsSid());
		Assert.assertEquals(existingStChActualDiscount.getUserId(),
			newStChActualDiscount.getUserId());
		Assert.assertEquals(existingStChActualDiscount.getSessionId(),
			newStChActualDiscount.getSessionId());
		Assert.assertEquals(existingStChActualDiscount.getRsModelSid(),
			newStChActualDiscount.getRsModelSid());
		AssertUtils.assertEquals(existingStChActualDiscount.getActualSales(),
			newStChActualDiscount.getActualSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StChActualDiscount newStChActualDiscount = addStChActualDiscount();

		StChActualDiscount existingStChActualDiscount = _persistence.findByPrimaryKey(newStChActualDiscount.getPrimaryKey());

		Assert.assertEquals(existingStChActualDiscount, newStChActualDiscount);
	}

	@Test(expected = NoSuchStChActualDiscountException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StChActualDiscountPK pk = new StChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StChActualDiscount newStChActualDiscount = addStChActualDiscount();

		StChActualDiscount existingStChActualDiscount = _persistence.fetchByPrimaryKey(newStChActualDiscount.getPrimaryKey());

		Assert.assertEquals(existingStChActualDiscount, newStChActualDiscount);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StChActualDiscountPK pk = new StChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChActualDiscount missingStChActualDiscount = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStChActualDiscount);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StChActualDiscount newStChActualDiscount1 = addStChActualDiscount();
		StChActualDiscount newStChActualDiscount2 = addStChActualDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChActualDiscount1.getPrimaryKey());
		primaryKeys.add(newStChActualDiscount2.getPrimaryKey());

		Map<Serializable, StChActualDiscount> stChActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stChActualDiscounts.size());
		Assert.assertEquals(newStChActualDiscount1,
			stChActualDiscounts.get(newStChActualDiscount1.getPrimaryKey()));
		Assert.assertEquals(newStChActualDiscount2,
			stChActualDiscounts.get(newStChActualDiscount2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StChActualDiscountPK pk1 = new StChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChActualDiscountPK pk2 = new StChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StChActualDiscount> stChActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChActualDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StChActualDiscount newStChActualDiscount = addStChActualDiscount();

		StChActualDiscountPK pk = new StChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChActualDiscount.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StChActualDiscount> stChActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChActualDiscounts.size());
		Assert.assertEquals(newStChActualDiscount,
			stChActualDiscounts.get(newStChActualDiscount.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StChActualDiscount> stChActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChActualDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StChActualDiscount newStChActualDiscount = addStChActualDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChActualDiscount.getPrimaryKey());

		Map<Serializable, StChActualDiscount> stChActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChActualDiscounts.size());
		Assert.assertEquals(newStChActualDiscount,
			stChActualDiscounts.get(newStChActualDiscount.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StChActualDiscountLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StChActualDiscount>() {
				@Override
				public void performAction(StChActualDiscount stChActualDiscount) {
					Assert.assertNotNull(stChActualDiscount);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StChActualDiscount newStChActualDiscount = addStChActualDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStChActualDiscount.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStChActualDiscount.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStChActualDiscount.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStChActualDiscount.getSessionId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newStChActualDiscount.getRsModelSid()));

		List<StChActualDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StChActualDiscount existingStChActualDiscount = result.get(0);

		Assert.assertEquals(existingStChActualDiscount, newStChActualDiscount);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));

		List<StChActualDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StChActualDiscount newStChActualDiscount = addStChActualDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newStChActualDiscount.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StChActualDiscount addStChActualDiscount()
		throws Exception {
		StChActualDiscountPK pk = new StChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChActualDiscount stChActualDiscount = _persistence.create(pk);

		stChActualDiscount.setLastModifiedDate(RandomTestUtil.nextDate());

		stChActualDiscount.setActualRate(RandomTestUtil.nextDouble());

		stChActualDiscount.setActualSales(RandomTestUtil.nextDouble());

		_stChActualDiscounts.add(_persistence.update(stChActualDiscount));

		return stChActualDiscount;
	}

	private List<StChActualDiscount> _stChActualDiscounts = new ArrayList<StChActualDiscount>();
	private StChActualDiscountPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}