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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchChProjectionDiscountException;
import com.stpl.app.model.ChProjectionDiscount;
import com.stpl.app.service.ChProjectionDiscountLocalServiceUtil;
import com.stpl.app.service.persistence.ChProjectionDiscountPK;
import com.stpl.app.service.persistence.ChProjectionDiscountPersistence;
import com.stpl.app.service.persistence.ChProjectionDiscountUtil;

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
public class ChProjectionDiscountPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ChProjectionDiscountUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ChProjectionDiscount> iterator = _chProjectionDiscounts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		ChProjectionDiscountPK pk = new ChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChProjectionDiscount chProjectionDiscount = _persistence.create(pk);

		Assert.assertNotNull(chProjectionDiscount);

		Assert.assertEquals(chProjectionDiscount.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ChProjectionDiscount newChProjectionDiscount = addChProjectionDiscount();

		_persistence.remove(newChProjectionDiscount);

		ChProjectionDiscount existingChProjectionDiscount = _persistence.fetchByPrimaryKey(newChProjectionDiscount.getPrimaryKey());

		Assert.assertNull(existingChProjectionDiscount);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addChProjectionDiscount();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		ChProjectionDiscountPK pk = new ChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChProjectionDiscount newChProjectionDiscount = _persistence.create(pk);

		newChProjectionDiscount.setAdjustmentMethodology(RandomTestUtil.randomString());

		newChProjectionDiscount.setProductGrowth(RandomTestUtil.nextDouble());

		newChProjectionDiscount.setProjectionRate(RandomTestUtil.nextDouble());

		newChProjectionDiscount.setAccountGrowth(RandomTestUtil.nextDouble());

		newChProjectionDiscount.setDiscountAmount(RandomTestUtil.nextDouble());

		newChProjectionDiscount.setDiscountRate(RandomTestUtil.nextDouble());

		newChProjectionDiscount.setAdjustmentBasis(RandomTestUtil.randomString());

		newChProjectionDiscount.setAdjustmentValue(RandomTestUtil.nextDouble());

		newChProjectionDiscount.setAdjustmentType(RandomTestUtil.randomString());

		newChProjectionDiscount.setProjectionSales(RandomTestUtil.nextDouble());

		_chProjectionDiscounts.add(_persistence.update(newChProjectionDiscount));

		ChProjectionDiscount existingChProjectionDiscount = _persistence.findByPrimaryKey(newChProjectionDiscount.getPrimaryKey());

		Assert.assertEquals(existingChProjectionDiscount.getAdjustmentMethodology(),
			newChProjectionDiscount.getAdjustmentMethodology());
		AssertUtils.assertEquals(existingChProjectionDiscount.getProductGrowth(),
			newChProjectionDiscount.getProductGrowth());
		AssertUtils.assertEquals(existingChProjectionDiscount.getProjectionRate(),
			newChProjectionDiscount.getProjectionRate());
		Assert.assertEquals(existingChProjectionDiscount.getProjectionDetailsSid(),
			newChProjectionDiscount.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingChProjectionDiscount.getAccountGrowth(),
			newChProjectionDiscount.getAccountGrowth());
		AssertUtils.assertEquals(existingChProjectionDiscount.getDiscountAmount(),
			newChProjectionDiscount.getDiscountAmount());
		AssertUtils.assertEquals(existingChProjectionDiscount.getDiscountRate(),
			newChProjectionDiscount.getDiscountRate());
		Assert.assertEquals(existingChProjectionDiscount.getPeriodSid(),
			newChProjectionDiscount.getPeriodSid());
		Assert.assertEquals(existingChProjectionDiscount.getAdjustmentBasis(),
			newChProjectionDiscount.getAdjustmentBasis());
		AssertUtils.assertEquals(existingChProjectionDiscount.getAdjustmentValue(),
			newChProjectionDiscount.getAdjustmentValue());
		Assert.assertEquals(existingChProjectionDiscount.getAdjustmentType(),
			newChProjectionDiscount.getAdjustmentType());
		Assert.assertEquals(existingChProjectionDiscount.getRsModelSid(),
			newChProjectionDiscount.getRsModelSid());
		AssertUtils.assertEquals(existingChProjectionDiscount.getProjectionSales(),
			newChProjectionDiscount.getProjectionSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ChProjectionDiscount newChProjectionDiscount = addChProjectionDiscount();

		ChProjectionDiscount existingChProjectionDiscount = _persistence.findByPrimaryKey(newChProjectionDiscount.getPrimaryKey());

		Assert.assertEquals(existingChProjectionDiscount,
			newChProjectionDiscount);
	}

	@Test(expected = NoSuchChProjectionDiscountException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		ChProjectionDiscountPK pk = new ChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ChProjectionDiscount newChProjectionDiscount = addChProjectionDiscount();

		ChProjectionDiscount existingChProjectionDiscount = _persistence.fetchByPrimaryKey(newChProjectionDiscount.getPrimaryKey());

		Assert.assertEquals(existingChProjectionDiscount,
			newChProjectionDiscount);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		ChProjectionDiscountPK pk = new ChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChProjectionDiscount missingChProjectionDiscount = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingChProjectionDiscount);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ChProjectionDiscount newChProjectionDiscount1 = addChProjectionDiscount();
		ChProjectionDiscount newChProjectionDiscount2 = addChProjectionDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChProjectionDiscount1.getPrimaryKey());
		primaryKeys.add(newChProjectionDiscount2.getPrimaryKey());

		Map<Serializable, ChProjectionDiscount> chProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, chProjectionDiscounts.size());
		Assert.assertEquals(newChProjectionDiscount1,
			chProjectionDiscounts.get(newChProjectionDiscount1.getPrimaryKey()));
		Assert.assertEquals(newChProjectionDiscount2,
			chProjectionDiscounts.get(newChProjectionDiscount2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		ChProjectionDiscountPK pk1 = new ChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChProjectionDiscountPK pk2 = new ChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ChProjectionDiscount> chProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chProjectionDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ChProjectionDiscount newChProjectionDiscount = addChProjectionDiscount();

		ChProjectionDiscountPK pk = new ChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChProjectionDiscount.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ChProjectionDiscount> chProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chProjectionDiscounts.size());
		Assert.assertEquals(newChProjectionDiscount,
			chProjectionDiscounts.get(newChProjectionDiscount.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ChProjectionDiscount> chProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chProjectionDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ChProjectionDiscount newChProjectionDiscount = addChProjectionDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChProjectionDiscount.getPrimaryKey());

		Map<Serializable, ChProjectionDiscount> chProjectionDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chProjectionDiscounts.size());
		Assert.assertEquals(newChProjectionDiscount,
			chProjectionDiscounts.get(newChProjectionDiscount.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ChProjectionDiscountLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ChProjectionDiscount>() {
				@Override
				public void performAction(
					ChProjectionDiscount chProjectionDiscount) {
					Assert.assertNotNull(chProjectionDiscount);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ChProjectionDiscount newChProjectionDiscount = addChProjectionDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChProjectionDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newChProjectionDiscount.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newChProjectionDiscount.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newChProjectionDiscount.getRsModelSid()));

		List<ChProjectionDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ChProjectionDiscount existingChProjectionDiscount = result.get(0);

		Assert.assertEquals(existingChProjectionDiscount,
			newChProjectionDiscount);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChProjectionDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));

		List<ChProjectionDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ChProjectionDiscount newChProjectionDiscount = addChProjectionDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChProjectionDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		Object newProjectionDetailsSid = newChProjectionDiscount.getProjectionDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChProjectionDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ChProjectionDiscount addChProjectionDiscount()
		throws Exception {
		ChProjectionDiscountPK pk = new ChProjectionDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChProjectionDiscount chProjectionDiscount = _persistence.create(pk);

		chProjectionDiscount.setAdjustmentMethodology(RandomTestUtil.randomString());

		chProjectionDiscount.setProductGrowth(RandomTestUtil.nextDouble());

		chProjectionDiscount.setProjectionRate(RandomTestUtil.nextDouble());

		chProjectionDiscount.setAccountGrowth(RandomTestUtil.nextDouble());

		chProjectionDiscount.setDiscountAmount(RandomTestUtil.nextDouble());

		chProjectionDiscount.setDiscountRate(RandomTestUtil.nextDouble());

		chProjectionDiscount.setAdjustmentBasis(RandomTestUtil.randomString());

		chProjectionDiscount.setAdjustmentValue(RandomTestUtil.nextDouble());

		chProjectionDiscount.setAdjustmentType(RandomTestUtil.randomString());

		chProjectionDiscount.setProjectionSales(RandomTestUtil.nextDouble());

		_chProjectionDiscounts.add(_persistence.update(chProjectionDiscount));

		return chProjectionDiscount;
	}

	private List<ChProjectionDiscount> _chProjectionDiscounts = new ArrayList<ChProjectionDiscount>();
	private ChProjectionDiscountPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}