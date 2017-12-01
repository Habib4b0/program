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

import com.stpl.app.exception.NoSuchNmActualDiscountException;
import com.stpl.app.model.NmActualDiscount;
import com.stpl.app.service.NmActualDiscountLocalServiceUtil;
import com.stpl.app.service.persistence.NmActualDiscountPK;
import com.stpl.app.service.persistence.NmActualDiscountPersistence;
import com.stpl.app.service.persistence.NmActualDiscountUtil;

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
public class NmActualDiscountPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmActualDiscountUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmActualDiscount> iterator = _nmActualDiscounts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		NmActualDiscountPK pk = new NmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualDiscount nmActualDiscount = _persistence.create(pk);

		Assert.assertNotNull(nmActualDiscount);

		Assert.assertEquals(nmActualDiscount.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmActualDiscount newNmActualDiscount = addNmActualDiscount();

		_persistence.remove(newNmActualDiscount);

		NmActualDiscount existingNmActualDiscount = _persistence.fetchByPrimaryKey(newNmActualDiscount.getPrimaryKey());

		Assert.assertNull(existingNmActualDiscount);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmActualDiscount();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		NmActualDiscountPK pk = new NmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualDiscount newNmActualDiscount = _persistence.create(pk);

		newNmActualDiscount.setActualRate(RandomTestUtil.nextDouble());

		newNmActualDiscount.setActualSales(RandomTestUtil.nextDouble());

		_nmActualDiscounts.add(_persistence.update(newNmActualDiscount));

		NmActualDiscount existingNmActualDiscount = _persistence.findByPrimaryKey(newNmActualDiscount.getPrimaryKey());

		AssertUtils.assertEquals(existingNmActualDiscount.getActualRate(),
			newNmActualDiscount.getActualRate());
		Assert.assertEquals(existingNmActualDiscount.getPeriodSid(),
			newNmActualDiscount.getPeriodSid());
		Assert.assertEquals(existingNmActualDiscount.getProjectionDetailsSid(),
			newNmActualDiscount.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingNmActualDiscount.getActualSales(),
			newNmActualDiscount.getActualSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmActualDiscount newNmActualDiscount = addNmActualDiscount();

		NmActualDiscount existingNmActualDiscount = _persistence.findByPrimaryKey(newNmActualDiscount.getPrimaryKey());

		Assert.assertEquals(existingNmActualDiscount, newNmActualDiscount);
	}

	@Test(expected = NoSuchNmActualDiscountException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		NmActualDiscountPK pk = new NmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmActualDiscount newNmActualDiscount = addNmActualDiscount();

		NmActualDiscount existingNmActualDiscount = _persistence.fetchByPrimaryKey(newNmActualDiscount.getPrimaryKey());

		Assert.assertEquals(existingNmActualDiscount, newNmActualDiscount);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		NmActualDiscountPK pk = new NmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualDiscount missingNmActualDiscount = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmActualDiscount);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmActualDiscount newNmActualDiscount1 = addNmActualDiscount();
		NmActualDiscount newNmActualDiscount2 = addNmActualDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmActualDiscount1.getPrimaryKey());
		primaryKeys.add(newNmActualDiscount2.getPrimaryKey());

		Map<Serializable, NmActualDiscount> nmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmActualDiscounts.size());
		Assert.assertEquals(newNmActualDiscount1,
			nmActualDiscounts.get(newNmActualDiscount1.getPrimaryKey()));
		Assert.assertEquals(newNmActualDiscount2,
			nmActualDiscounts.get(newNmActualDiscount2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		NmActualDiscountPK pk1 = new NmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualDiscountPK pk2 = new NmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmActualDiscount> nmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmActualDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmActualDiscount newNmActualDiscount = addNmActualDiscount();

		NmActualDiscountPK pk = new NmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmActualDiscount.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmActualDiscount> nmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmActualDiscounts.size());
		Assert.assertEquals(newNmActualDiscount,
			nmActualDiscounts.get(newNmActualDiscount.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmActualDiscount> nmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmActualDiscounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmActualDiscount newNmActualDiscount = addNmActualDiscount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmActualDiscount.getPrimaryKey());

		Map<Serializable, NmActualDiscount> nmActualDiscounts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmActualDiscounts.size());
		Assert.assertEquals(newNmActualDiscount,
			nmActualDiscounts.get(newNmActualDiscount.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmActualDiscountLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmActualDiscount>() {
				@Override
				public void performAction(NmActualDiscount nmActualDiscount) {
					Assert.assertNotNull(nmActualDiscount);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmActualDiscount newNmActualDiscount = addNmActualDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newNmActualDiscount.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newNmActualDiscount.getProjectionDetailsSid()));

		List<NmActualDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmActualDiscount existingNmActualDiscount = result.get(0);

		Assert.assertEquals(existingNmActualDiscount, newNmActualDiscount);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<NmActualDiscount> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmActualDiscount newNmActualDiscount = addNmActualDiscount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newNmActualDiscount.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmActualDiscount.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmActualDiscount addNmActualDiscount() throws Exception {
		NmActualDiscountPK pk = new NmActualDiscountPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualDiscount nmActualDiscount = _persistence.create(pk);

		nmActualDiscount.setActualRate(RandomTestUtil.nextDouble());

		nmActualDiscount.setActualSales(RandomTestUtil.nextDouble());

		_nmActualDiscounts.add(_persistence.update(nmActualDiscount));

		return nmActualDiscount;
	}

	private List<NmActualDiscount> _nmActualDiscounts = new ArrayList<NmActualDiscount>();
	private NmActualDiscountPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}