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

import com.stpl.app.exception.NoSuchChActualDiscountException;
import com.stpl.app.model.ChActualDiscount;
import com.stpl.app.service.ChActualDiscountLocalServiceUtil;
import com.stpl.app.service.persistence.ChActualDiscountPK;
import com.stpl.app.service.persistence.ChActualDiscountPersistence;
import com.stpl.app.service.persistence.ChActualDiscountUtil;

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
public class ChActualDiscountPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ChActualDiscountUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ChActualDiscount> iterator = _chActualDiscounts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		ChActualDiscountPK pk = new ChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChActualDiscount chActualDiscount = _persistence.create(pk);

		Assert.assertNotNull(chActualDiscount);

		Assert.assertEquals(chActualDiscount.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ChActualDiscount newChActualDiscount = addChActualDiscount();

		_persistence.remove(newChActualDiscount);

		ChActualDiscount existingChActualDiscount = _persistence.fetchByPrimaryKey(newChActualDiscount.getPrimaryKey());

		Assert.assertNull(existingChActualDiscount);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addChActualDiscount();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		ChActualDiscountPK pk = new ChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChActualDiscount newChActualDiscount = _persistence.create(pk);

		newChActualDiscount.setActualRate(RandomTestUtil.nextDouble());

		newChActualDiscount.setActualSales(RandomTestUtil.nextDouble());

		_chActualDiscounts.add(_persistence.update(newChActualDiscount));

		ChActualDiscount existingChActualDiscount = _persistence.findByPrimaryKey(newChActualDiscount.getPrimaryKey());

		AssertUtils.assertEquals(existingChActualDiscount.getActualRate(),
			newChActualDiscount.getActualRate());
		Assert.assertEquals(existingChActualDiscount.getPeriodSid(),
			newChActualDiscount.getPeriodSid());
		Assert.assertEquals(existingChActualDiscount.getProjectionDetailsSid(),
			newChActualDiscount.getProjectionDetailsSid());
		Assert.assertEquals(existingChActualDiscount.getRsModelSid(),
			newChActualDiscount.getRsModelSid());
		AssertUtils.assertEquals(existingChActualDiscount.getActualSales(),
			newChActualDiscount.getActualSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ChActualDiscount newChActualDiscount = addChActualDiscount();

		ChActualDiscount existingChActualDiscount = _persistence.findByPrimaryKey(newChActualDiscount.getPrimaryKey());

		Assert.assertEquals(existingChActualDiscount, newChActualDiscount);
	}

	@Test(expected = NoSuchChActualDiscountException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		ChActualDiscountPK pk = new ChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ChActualDiscount newChActualDiscount = addChActualDiscount();

		ChActualDiscount existingChActualDiscount = _persistence.fetchByPrimaryKey(newChActualDiscount.getPrimaryKey());

		Assert.assertEquals(existingChActualDiscount, newChActualDiscount);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		ChActualDiscountPK pk = new ChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChActualDiscount missingChActualDiscount = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingChActualDiscount);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ChActualDiscount newChActualDiscount1 = addChActualDiscount();
		ChActualDiscount newChActualDiscount2 = addChActualDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChActualDiscount1.getPrimaryKey());
		primaryKeys.add(newChActualDiscount2.getPrimaryKey());

		Map<Serializable, ChActualDiscount> chActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, chActualDiscounts.size());
		Assert.assertEquals(newChActualDiscount1,
			chActualDiscounts.get(newChActualDiscount1.getPrimaryKey()));
		Assert.assertEquals(newChActualDiscount2,
			chActualDiscounts.get(newChActualDiscount2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		ChActualDiscountPK pk1 = new ChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChActualDiscountPK pk2 = new ChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ChActualDiscount> chActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chActualDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ChActualDiscount newChActualDiscount = addChActualDiscount();

		ChActualDiscountPK pk = new ChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChActualDiscount.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ChActualDiscount> chActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chActualDiscounts.size());
		Assert.assertEquals(newChActualDiscount,
			chActualDiscounts.get(newChActualDiscount.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ChActualDiscount> chActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chActualDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ChActualDiscount newChActualDiscount = addChActualDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChActualDiscount.getPrimaryKey());

		Map<Serializable, ChActualDiscount> chActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chActualDiscounts.size());
		Assert.assertEquals(newChActualDiscount,
			chActualDiscounts.get(newChActualDiscount.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ChActualDiscountLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ChActualDiscount>() {
				@Override
				public void performAction(ChActualDiscount chActualDiscount) {
					Assert.assertNotNull(chActualDiscount);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ChActualDiscount newChActualDiscount = addChActualDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newChActualDiscount.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newChActualDiscount.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newChActualDiscount.getRsModelSid()));

		List<ChActualDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ChActualDiscount existingChActualDiscount = result.get(0);

		Assert.assertEquals(existingChActualDiscount, newChActualDiscount);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));

		List<ChActualDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ChActualDiscount newChActualDiscount = addChActualDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newChActualDiscount.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ChActualDiscount addChActualDiscount() throws Exception {
		ChActualDiscountPK pk = new ChActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		ChActualDiscount chActualDiscount = _persistence.create(pk);

		chActualDiscount.setActualRate(RandomTestUtil.nextDouble());

		chActualDiscount.setActualSales(RandomTestUtil.nextDouble());

		_chActualDiscounts.add(_persistence.update(chActualDiscount));

		return chActualDiscount;
	}

	private List<ChActualDiscount> _chActualDiscounts = new ArrayList<ChActualDiscount>();
	private ChActualDiscountPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}