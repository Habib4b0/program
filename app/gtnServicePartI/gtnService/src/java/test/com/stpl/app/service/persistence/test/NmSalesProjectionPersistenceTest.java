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

import com.stpl.app.exception.NoSuchNmSalesProjectionException;
import com.stpl.app.model.NmSalesProjection;
import com.stpl.app.service.NmSalesProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.NmSalesProjectionPK;
import com.stpl.app.service.persistence.NmSalesProjectionPersistence;
import com.stpl.app.service.persistence.NmSalesProjectionUtil;

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
public class NmSalesProjectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmSalesProjectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmSalesProjection> iterator = _nmSalesProjections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		NmSalesProjectionPK pk = new NmSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmSalesProjection nmSalesProjection = _persistence.create(pk);

		Assert.assertNotNull(nmSalesProjection);

		Assert.assertEquals(nmSalesProjection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmSalesProjection newNmSalesProjection = addNmSalesProjection();

		_persistence.remove(newNmSalesProjection);

		NmSalesProjection existingNmSalesProjection = _persistence.fetchByPrimaryKey(newNmSalesProjection.getPrimaryKey());

		Assert.assertNull(existingNmSalesProjection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmSalesProjection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		NmSalesProjectionPK pk = new NmSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmSalesProjection newNmSalesProjection = _persistence.create(pk);

		newNmSalesProjection.setProductGrowth(RandomTestUtil.nextDouble());

		newNmSalesProjection.setAccountGrowth(RandomTestUtil.nextDouble());

		newNmSalesProjection.setProjectionUnits(RandomTestUtil.nextDouble());

		newNmSalesProjection.setProjectionSales(RandomTestUtil.nextDouble());

		_nmSalesProjections.add(_persistence.update(newNmSalesProjection));

		NmSalesProjection existingNmSalesProjection = _persistence.findByPrimaryKey(newNmSalesProjection.getPrimaryKey());

		Assert.assertEquals(existingNmSalesProjection.getPeriodSid(),
			newNmSalesProjection.getPeriodSid());
		AssertUtils.assertEquals(existingNmSalesProjection.getProductGrowth(),
			newNmSalesProjection.getProductGrowth());
		Assert.assertEquals(existingNmSalesProjection.getProjectionDetailsSid(),
			newNmSalesProjection.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingNmSalesProjection.getAccountGrowth(),
			newNmSalesProjection.getAccountGrowth());
		AssertUtils.assertEquals(existingNmSalesProjection.getProjectionUnits(),
			newNmSalesProjection.getProjectionUnits());
		AssertUtils.assertEquals(existingNmSalesProjection.getProjectionSales(),
			newNmSalesProjection.getProjectionSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmSalesProjection newNmSalesProjection = addNmSalesProjection();

		NmSalesProjection existingNmSalesProjection = _persistence.findByPrimaryKey(newNmSalesProjection.getPrimaryKey());

		Assert.assertEquals(existingNmSalesProjection, newNmSalesProjection);
	}

	@Test(expected = NoSuchNmSalesProjectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		NmSalesProjectionPK pk = new NmSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmSalesProjection newNmSalesProjection = addNmSalesProjection();

		NmSalesProjection existingNmSalesProjection = _persistence.fetchByPrimaryKey(newNmSalesProjection.getPrimaryKey());

		Assert.assertEquals(existingNmSalesProjection, newNmSalesProjection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		NmSalesProjectionPK pk = new NmSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmSalesProjection missingNmSalesProjection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmSalesProjection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmSalesProjection newNmSalesProjection1 = addNmSalesProjection();
		NmSalesProjection newNmSalesProjection2 = addNmSalesProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmSalesProjection1.getPrimaryKey());
		primaryKeys.add(newNmSalesProjection2.getPrimaryKey());

		Map<Serializable, NmSalesProjection> nmSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmSalesProjections.size());
		Assert.assertEquals(newNmSalesProjection1,
			nmSalesProjections.get(newNmSalesProjection1.getPrimaryKey()));
		Assert.assertEquals(newNmSalesProjection2,
			nmSalesProjections.get(newNmSalesProjection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		NmSalesProjectionPK pk1 = new NmSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmSalesProjectionPK pk2 = new NmSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmSalesProjection> nmSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmSalesProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmSalesProjection newNmSalesProjection = addNmSalesProjection();

		NmSalesProjectionPK pk = new NmSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmSalesProjection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmSalesProjection> nmSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmSalesProjections.size());
		Assert.assertEquals(newNmSalesProjection,
			nmSalesProjections.get(newNmSalesProjection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmSalesProjection> nmSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmSalesProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmSalesProjection newNmSalesProjection = addNmSalesProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmSalesProjection.getPrimaryKey());

		Map<Serializable, NmSalesProjection> nmSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmSalesProjections.size());
		Assert.assertEquals(newNmSalesProjection,
			nmSalesProjections.get(newNmSalesProjection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmSalesProjectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmSalesProjection>() {
				@Override
				public void performAction(NmSalesProjection nmSalesProjection) {
					Assert.assertNotNull(nmSalesProjection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmSalesProjection newNmSalesProjection = addNmSalesProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmSalesProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newNmSalesProjection.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newNmSalesProjection.getProjectionDetailsSid()));

		List<NmSalesProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmSalesProjection existingNmSalesProjection = result.get(0);

		Assert.assertEquals(existingNmSalesProjection, newNmSalesProjection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmSalesProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<NmSalesProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmSalesProjection newNmSalesProjection = addNmSalesProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmSalesProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newNmSalesProjection.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmSalesProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmSalesProjection addNmSalesProjection()
		throws Exception {
		NmSalesProjectionPK pk = new NmSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmSalesProjection nmSalesProjection = _persistence.create(pk);

		nmSalesProjection.setProductGrowth(RandomTestUtil.nextDouble());

		nmSalesProjection.setAccountGrowth(RandomTestUtil.nextDouble());

		nmSalesProjection.setProjectionUnits(RandomTestUtil.nextDouble());

		nmSalesProjection.setProjectionSales(RandomTestUtil.nextDouble());

		_nmSalesProjections.add(_persistence.update(nmSalesProjection));

		return nmSalesProjection;
	}

	private List<NmSalesProjection> _nmSalesProjections = new ArrayList<NmSalesProjection>();
	private NmSalesProjectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}