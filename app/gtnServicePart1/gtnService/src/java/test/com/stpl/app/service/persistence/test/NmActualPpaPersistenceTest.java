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

import com.stpl.app.exception.NoSuchNmActualPpaException;
import com.stpl.app.model.NmActualPpa;
import com.stpl.app.service.NmActualPpaLocalServiceUtil;
import com.stpl.app.service.persistence.NmActualPpaPK;
import com.stpl.app.service.persistence.NmActualPpaPersistence;
import com.stpl.app.service.persistence.NmActualPpaUtil;

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
public class NmActualPpaPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmActualPpaUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmActualPpa> iterator = _nmActualPpas.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		NmActualPpaPK pk = new NmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualPpa nmActualPpa = _persistence.create(pk);

		Assert.assertNotNull(nmActualPpa);

		Assert.assertEquals(nmActualPpa.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmActualPpa newNmActualPpa = addNmActualPpa();

		_persistence.remove(newNmActualPpa);

		NmActualPpa existingNmActualPpa = _persistence.fetchByPrimaryKey(newNmActualPpa.getPrimaryKey());

		Assert.assertNull(existingNmActualPpa);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmActualPpa();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		NmActualPpaPK pk = new NmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualPpa newNmActualPpa = _persistence.create(pk);

		newNmActualPpa.setActualRate(RandomTestUtil.nextDouble());

		newNmActualPpa.setActualDiscountDollar(RandomTestUtil.nextDouble());

		newNmActualPpa.setActualDiscountUnits(RandomTestUtil.nextDouble());

		newNmActualPpa.setActualSales(RandomTestUtil.nextDouble());

		_nmActualPpas.add(_persistence.update(newNmActualPpa));

		NmActualPpa existingNmActualPpa = _persistence.findByPrimaryKey(newNmActualPpa.getPrimaryKey());

		AssertUtils.assertEquals(existingNmActualPpa.getActualRate(),
			newNmActualPpa.getActualRate());
		Assert.assertEquals(existingNmActualPpa.getPeriodSid(),
			newNmActualPpa.getPeriodSid());
		Assert.assertEquals(existingNmActualPpa.getProjectionDetailsSid(),
			newNmActualPpa.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingNmActualPpa.getActualDiscountDollar(),
			newNmActualPpa.getActualDiscountDollar());
		AssertUtils.assertEquals(existingNmActualPpa.getActualDiscountUnits(),
			newNmActualPpa.getActualDiscountUnits());
		AssertUtils.assertEquals(existingNmActualPpa.getActualSales(),
			newNmActualPpa.getActualSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmActualPpa newNmActualPpa = addNmActualPpa();

		NmActualPpa existingNmActualPpa = _persistence.findByPrimaryKey(newNmActualPpa.getPrimaryKey());

		Assert.assertEquals(existingNmActualPpa, newNmActualPpa);
	}

	@Test(expected = NoSuchNmActualPpaException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		NmActualPpaPK pk = new NmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmActualPpa newNmActualPpa = addNmActualPpa();

		NmActualPpa existingNmActualPpa = _persistence.fetchByPrimaryKey(newNmActualPpa.getPrimaryKey());

		Assert.assertEquals(existingNmActualPpa, newNmActualPpa);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		NmActualPpaPK pk = new NmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualPpa missingNmActualPpa = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmActualPpa);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmActualPpa newNmActualPpa1 = addNmActualPpa();
		NmActualPpa newNmActualPpa2 = addNmActualPpa();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmActualPpa1.getPrimaryKey());
		primaryKeys.add(newNmActualPpa2.getPrimaryKey());

		Map<Serializable, NmActualPpa> nmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmActualPpas.size());
		Assert.assertEquals(newNmActualPpa1,
			nmActualPpas.get(newNmActualPpa1.getPrimaryKey()));
		Assert.assertEquals(newNmActualPpa2,
			nmActualPpas.get(newNmActualPpa2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		NmActualPpaPK pk1 = new NmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualPpaPK pk2 = new NmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmActualPpa> nmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmActualPpas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmActualPpa newNmActualPpa = addNmActualPpa();

		NmActualPpaPK pk = new NmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmActualPpa.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmActualPpa> nmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmActualPpas.size());
		Assert.assertEquals(newNmActualPpa,
			nmActualPpas.get(newNmActualPpa.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmActualPpa> nmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmActualPpas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmActualPpa newNmActualPpa = addNmActualPpa();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmActualPpa.getPrimaryKey());

		Map<Serializable, NmActualPpa> nmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmActualPpas.size());
		Assert.assertEquals(newNmActualPpa,
			nmActualPpas.get(newNmActualPpa.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmActualPpaLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmActualPpa>() {
				@Override
				public void performAction(NmActualPpa nmActualPpa) {
					Assert.assertNotNull(nmActualPpa);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmActualPpa newNmActualPpa = addNmActualPpa();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmActualPpa.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newNmActualPpa.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newNmActualPpa.getProjectionDetailsSid()));

		List<NmActualPpa> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmActualPpa existingNmActualPpa = result.get(0);

		Assert.assertEquals(existingNmActualPpa, newNmActualPpa);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmActualPpa.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<NmActualPpa> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmActualPpa newNmActualPpa = addNmActualPpa();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmActualPpa.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newNmActualPpa.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmActualPpa.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmActualPpa addNmActualPpa() throws Exception {
		NmActualPpaPK pk = new NmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmActualPpa nmActualPpa = _persistence.create(pk);

		nmActualPpa.setActualRate(RandomTestUtil.nextDouble());

		nmActualPpa.setActualDiscountDollar(RandomTestUtil.nextDouble());

		nmActualPpa.setActualDiscountUnits(RandomTestUtil.nextDouble());

		nmActualPpa.setActualSales(RandomTestUtil.nextDouble());

		_nmActualPpas.add(_persistence.update(nmActualPpa));

		return nmActualPpa;
	}

	private List<NmActualPpa> _nmActualPpas = new ArrayList<NmActualPpa>();
	private NmActualPpaPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}