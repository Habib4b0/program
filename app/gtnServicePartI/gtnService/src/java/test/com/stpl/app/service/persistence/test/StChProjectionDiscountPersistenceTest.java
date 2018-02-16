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

import com.stpl.app.exception.NoSuchStChProjectionDiscountException;
import com.stpl.app.model.StChProjectionDiscount;
import com.stpl.app.service.StChProjectionDiscountLocalServiceUtil;
import com.stpl.app.service.persistence.StChProjectionDiscountPK;
import com.stpl.app.service.persistence.StChProjectionDiscountPersistence;
import com.stpl.app.service.persistence.StChProjectionDiscountUtil;

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
public class StChProjectionDiscountPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StChProjectionDiscountUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StChProjectionDiscount> iterator = _stChProjectionDiscounts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StChProjectionDiscountPK pk = new StChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChProjectionDiscount stChProjectionDiscount = _persistence.create(pk);

		Assert.assertNotNull(stChProjectionDiscount);

		Assert.assertEquals(stChProjectionDiscount.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StChProjectionDiscount newStChProjectionDiscount = addStChProjectionDiscount();

		_persistence.remove(newStChProjectionDiscount);

		StChProjectionDiscount existingStChProjectionDiscount = _persistence.fetchByPrimaryKey(newStChProjectionDiscount.getPrimaryKey());

		Assert.assertNull(existingStChProjectionDiscount);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStChProjectionDiscount();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StChProjectionDiscountPK pk = new StChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChProjectionDiscount newStChProjectionDiscount = _persistence.create(pk);

		newStChProjectionDiscount.setLastModifiedDate(RandomTestUtil.nextDate());

		newStChProjectionDiscount.setAdjustmentMethodology(RandomTestUtil.randomString());

		newStChProjectionDiscount.setProductGrowth(RandomTestUtil.nextDouble());

		newStChProjectionDiscount.setProjectionRate(RandomTestUtil.nextDouble());

		newStChProjectionDiscount.setAccountGrowth(RandomTestUtil.nextDouble());

		newStChProjectionDiscount.setDiscountAmount(RandomTestUtil.nextDouble());

		newStChProjectionDiscount.setDiscountRate(RandomTestUtil.nextDouble());

		newStChProjectionDiscount.setAdjustmentBasis(RandomTestUtil.randomString());

		newStChProjectionDiscount.setAdjustmentValue(RandomTestUtil.nextDouble());

		newStChProjectionDiscount.setAdjustmentType(RandomTestUtil.randomString());

		newStChProjectionDiscount.setProjectionSales(RandomTestUtil.nextDouble());

		_stChProjectionDiscounts.add(_persistence.update(
				newStChProjectionDiscount));

		StChProjectionDiscount existingStChProjectionDiscount = _persistence.findByPrimaryKey(newStChProjectionDiscount.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStChProjectionDiscount.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStChProjectionDiscount.getLastModifiedDate()));
		Assert.assertEquals(existingStChProjectionDiscount.getAdjustmentMethodology(),
			newStChProjectionDiscount.getAdjustmentMethodology());
		AssertUtils.assertEquals(existingStChProjectionDiscount.getProductGrowth(),
			newStChProjectionDiscount.getProductGrowth());
		AssertUtils.assertEquals(existingStChProjectionDiscount.getProjectionRate(),
			newStChProjectionDiscount.getProjectionRate());
		Assert.assertEquals(existingStChProjectionDiscount.getProjectionDetailsSid(),
			newStChProjectionDiscount.getProjectionDetailsSid());
		Assert.assertEquals(existingStChProjectionDiscount.getUserId(),
			newStChProjectionDiscount.getUserId());
		AssertUtils.assertEquals(existingStChProjectionDiscount.getAccountGrowth(),
			newStChProjectionDiscount.getAccountGrowth());
		AssertUtils.assertEquals(existingStChProjectionDiscount.getDiscountAmount(),
			newStChProjectionDiscount.getDiscountAmount());
		AssertUtils.assertEquals(existingStChProjectionDiscount.getDiscountRate(),
			newStChProjectionDiscount.getDiscountRate());
		Assert.assertEquals(existingStChProjectionDiscount.getPeriodSid(),
			newStChProjectionDiscount.getPeriodSid());
		Assert.assertEquals(existingStChProjectionDiscount.getAdjustmentBasis(),
			newStChProjectionDiscount.getAdjustmentBasis());
		Assert.assertEquals(existingStChProjectionDiscount.getSessionId(),
			newStChProjectionDiscount.getSessionId());
		AssertUtils.assertEquals(existingStChProjectionDiscount.getAdjustmentValue(),
			newStChProjectionDiscount.getAdjustmentValue());
		Assert.assertEquals(existingStChProjectionDiscount.getAdjustmentType(),
			newStChProjectionDiscount.getAdjustmentType());
		Assert.assertEquals(existingStChProjectionDiscount.getRsModelSid(),
			newStChProjectionDiscount.getRsModelSid());
		AssertUtils.assertEquals(existingStChProjectionDiscount.getProjectionSales(),
			newStChProjectionDiscount.getProjectionSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StChProjectionDiscount newStChProjectionDiscount = addStChProjectionDiscount();

		StChProjectionDiscount existingStChProjectionDiscount = _persistence.findByPrimaryKey(newStChProjectionDiscount.getPrimaryKey());

		Assert.assertEquals(existingStChProjectionDiscount,
			newStChProjectionDiscount);
	}

	@Test(expected = NoSuchStChProjectionDiscountException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StChProjectionDiscountPK pk = new StChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StChProjectionDiscount newStChProjectionDiscount = addStChProjectionDiscount();

		StChProjectionDiscount existingStChProjectionDiscount = _persistence.fetchByPrimaryKey(newStChProjectionDiscount.getPrimaryKey());

		Assert.assertEquals(existingStChProjectionDiscount,
			newStChProjectionDiscount);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StChProjectionDiscountPK pk = new StChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChProjectionDiscount missingStChProjectionDiscount = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStChProjectionDiscount);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StChProjectionDiscount newStChProjectionDiscount1 = addStChProjectionDiscount();
		StChProjectionDiscount newStChProjectionDiscount2 = addStChProjectionDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChProjectionDiscount1.getPrimaryKey());
		primaryKeys.add(newStChProjectionDiscount2.getPrimaryKey());

		Map<Serializable, StChProjectionDiscount> stChProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stChProjectionDiscounts.size());
		Assert.assertEquals(newStChProjectionDiscount1,
			stChProjectionDiscounts.get(
				newStChProjectionDiscount1.getPrimaryKey()));
		Assert.assertEquals(newStChProjectionDiscount2,
			stChProjectionDiscounts.get(
				newStChProjectionDiscount2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StChProjectionDiscountPK pk1 = new StChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChProjectionDiscountPK pk2 = new StChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StChProjectionDiscount> stChProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChProjectionDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StChProjectionDiscount newStChProjectionDiscount = addStChProjectionDiscount();

		StChProjectionDiscountPK pk = new StChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChProjectionDiscount.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StChProjectionDiscount> stChProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChProjectionDiscounts.size());
		Assert.assertEquals(newStChProjectionDiscount,
			stChProjectionDiscounts.get(
				newStChProjectionDiscount.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StChProjectionDiscount> stChProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChProjectionDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StChProjectionDiscount newStChProjectionDiscount = addStChProjectionDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChProjectionDiscount.getPrimaryKey());

		Map<Serializable, StChProjectionDiscount> stChProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChProjectionDiscounts.size());
		Assert.assertEquals(newStChProjectionDiscount,
			stChProjectionDiscounts.get(
				newStChProjectionDiscount.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StChProjectionDiscountLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StChProjectionDiscount>() {
				@Override
				public void performAction(
					StChProjectionDiscount stChProjectionDiscount) {
					Assert.assertNotNull(stChProjectionDiscount);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StChProjectionDiscount newStChProjectionDiscount = addStChProjectionDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChProjectionDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStChProjectionDiscount.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStChProjectionDiscount.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStChProjectionDiscount.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStChProjectionDiscount.getSessionId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newStChProjectionDiscount.getRsModelSid()));

		List<StChProjectionDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StChProjectionDiscount existingStChProjectionDiscount = result.get(0);

		Assert.assertEquals(existingStChProjectionDiscount,
			newStChProjectionDiscount);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChProjectionDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));

		List<StChProjectionDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StChProjectionDiscount newStChProjectionDiscount = addStChProjectionDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChProjectionDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		Object newProjectionDetailsSid = newStChProjectionDiscount.getProjectionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { newProjectionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionDetailsSid = result.get(0);

		Assert.assertEquals(existingProjectionDetailsSid,
			newProjectionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChProjectionDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StChProjectionDiscount addStChProjectionDiscount()
		throws Exception {
		StChProjectionDiscountPK pk = new StChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChProjectionDiscount stChProjectionDiscount = _persistence.create(pk);

		stChProjectionDiscount.setLastModifiedDate(RandomTestUtil.nextDate());

		stChProjectionDiscount.setAdjustmentMethodology(RandomTestUtil.randomString());

		stChProjectionDiscount.setProductGrowth(RandomTestUtil.nextDouble());

		stChProjectionDiscount.setProjectionRate(RandomTestUtil.nextDouble());

		stChProjectionDiscount.setAccountGrowth(RandomTestUtil.nextDouble());

		stChProjectionDiscount.setDiscountAmount(RandomTestUtil.nextDouble());

		stChProjectionDiscount.setDiscountRate(RandomTestUtil.nextDouble());

		stChProjectionDiscount.setAdjustmentBasis(RandomTestUtil.randomString());

		stChProjectionDiscount.setAdjustmentValue(RandomTestUtil.nextDouble());

		stChProjectionDiscount.setAdjustmentType(RandomTestUtil.randomString());

		stChProjectionDiscount.setProjectionSales(RandomTestUtil.nextDouble());

		_stChProjectionDiscounts.add(_persistence.update(stChProjectionDiscount));

		return stChProjectionDiscount;
	}

	private List<StChProjectionDiscount> _stChProjectionDiscounts = new ArrayList<StChProjectionDiscount>();
	private StChProjectionDiscountPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}