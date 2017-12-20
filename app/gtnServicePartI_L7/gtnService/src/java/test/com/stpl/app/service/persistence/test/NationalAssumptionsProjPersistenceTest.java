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

import com.stpl.app.exception.NoSuchNationalAssumptionsProjException;
import com.stpl.app.model.NationalAssumptionsProj;
import com.stpl.app.service.NationalAssumptionsProjLocalServiceUtil;
import com.stpl.app.service.persistence.NationalAssumptionsProjPK;
import com.stpl.app.service.persistence.NationalAssumptionsProjPersistence;
import com.stpl.app.service.persistence.NationalAssumptionsProjUtil;

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
public class NationalAssumptionsProjPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NationalAssumptionsProjUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NationalAssumptionsProj> iterator = _nationalAssumptionsProjs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		NationalAssumptionsProjPK pk = new NationalAssumptionsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsProj nationalAssumptionsProj = _persistence.create(pk);

		Assert.assertNotNull(nationalAssumptionsProj);

		Assert.assertEquals(nationalAssumptionsProj.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NationalAssumptionsProj newNationalAssumptionsProj = addNationalAssumptionsProj();

		_persistence.remove(newNationalAssumptionsProj);

		NationalAssumptionsProj existingNationalAssumptionsProj = _persistence.fetchByPrimaryKey(newNationalAssumptionsProj.getPrimaryKey());

		Assert.assertNull(existingNationalAssumptionsProj);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNationalAssumptionsProj();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		NationalAssumptionsProjPK pk = new NationalAssumptionsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsProj newNationalAssumptionsProj = _persistence.create(pk);

		newNationalAssumptionsProj.setProjectionPrice(RandomTestUtil.nextDouble());

		_nationalAssumptionsProjs.add(_persistence.update(
				newNationalAssumptionsProj));

		NationalAssumptionsProj existingNationalAssumptionsProj = _persistence.findByPrimaryKey(newNationalAssumptionsProj.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptionsProj.getPeriodSid(),
			newNationalAssumptionsProj.getPeriodSid());
		Assert.assertEquals(existingNationalAssumptionsProj.getItemMasterSid(),
			newNationalAssumptionsProj.getItemMasterSid());
		Assert.assertEquals(existingNationalAssumptionsProj.getPriceType(),
			newNationalAssumptionsProj.getPriceType());
		AssertUtils.assertEquals(existingNationalAssumptionsProj.getProjectionPrice(),
			newNationalAssumptionsProj.getProjectionPrice());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NationalAssumptionsProj newNationalAssumptionsProj = addNationalAssumptionsProj();

		NationalAssumptionsProj existingNationalAssumptionsProj = _persistence.findByPrimaryKey(newNationalAssumptionsProj.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptionsProj,
			newNationalAssumptionsProj);
	}

	@Test(expected = NoSuchNationalAssumptionsProjException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		NationalAssumptionsProjPK pk = new NationalAssumptionsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NationalAssumptionsProj newNationalAssumptionsProj = addNationalAssumptionsProj();

		NationalAssumptionsProj existingNationalAssumptionsProj = _persistence.fetchByPrimaryKey(newNationalAssumptionsProj.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptionsProj,
			newNationalAssumptionsProj);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		NationalAssumptionsProjPK pk = new NationalAssumptionsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsProj missingNationalAssumptionsProj = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNationalAssumptionsProj);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NationalAssumptionsProj newNationalAssumptionsProj1 = addNationalAssumptionsProj();
		NationalAssumptionsProj newNationalAssumptionsProj2 = addNationalAssumptionsProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptionsProj1.getPrimaryKey());
		primaryKeys.add(newNationalAssumptionsProj2.getPrimaryKey());

		Map<Serializable, NationalAssumptionsProj> nationalAssumptionsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nationalAssumptionsProjs.size());
		Assert.assertEquals(newNationalAssumptionsProj1,
			nationalAssumptionsProjs.get(
				newNationalAssumptionsProj1.getPrimaryKey()));
		Assert.assertEquals(newNationalAssumptionsProj2,
			nationalAssumptionsProjs.get(
				newNationalAssumptionsProj2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		NationalAssumptionsProjPK pk1 = new NationalAssumptionsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsProjPK pk2 = new NationalAssumptionsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NationalAssumptionsProj> nationalAssumptionsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nationalAssumptionsProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NationalAssumptionsProj newNationalAssumptionsProj = addNationalAssumptionsProj();

		NationalAssumptionsProjPK pk = new NationalAssumptionsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptionsProj.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NationalAssumptionsProj> nationalAssumptionsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nationalAssumptionsProjs.size());
		Assert.assertEquals(newNationalAssumptionsProj,
			nationalAssumptionsProjs.get(
				newNationalAssumptionsProj.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NationalAssumptionsProj> nationalAssumptionsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nationalAssumptionsProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NationalAssumptionsProj newNationalAssumptionsProj = addNationalAssumptionsProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptionsProj.getPrimaryKey());

		Map<Serializable, NationalAssumptionsProj> nationalAssumptionsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nationalAssumptionsProjs.size());
		Assert.assertEquals(newNationalAssumptionsProj,
			nationalAssumptionsProjs.get(
				newNationalAssumptionsProj.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NationalAssumptionsProjLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NationalAssumptionsProj>() {
				@Override
				public void performAction(
					NationalAssumptionsProj nationalAssumptionsProj) {
					Assert.assertNotNull(nationalAssumptionsProj);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NationalAssumptionsProj newNationalAssumptionsProj = addNationalAssumptionsProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptionsProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newNationalAssumptionsProj.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				newNationalAssumptionsProj.getItemMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newNationalAssumptionsProj.getPriceType()));

		List<NationalAssumptionsProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NationalAssumptionsProj existingNationalAssumptionsProj = result.get(0);

		Assert.assertEquals(existingNationalAssumptionsProj,
			newNationalAssumptionsProj);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptionsProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));

		List<NationalAssumptionsProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NationalAssumptionsProj newNationalAssumptionsProj = addNationalAssumptionsProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptionsProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newNationalAssumptionsProj.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptionsProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NationalAssumptionsProj addNationalAssumptionsProj()
		throws Exception {
		NationalAssumptionsProjPK pk = new NationalAssumptionsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsProj nationalAssumptionsProj = _persistence.create(pk);

		nationalAssumptionsProj.setProjectionPrice(RandomTestUtil.nextDouble());

		_nationalAssumptionsProjs.add(_persistence.update(
				nationalAssumptionsProj));

		return nationalAssumptionsProj;
	}

	private List<NationalAssumptionsProj> _nationalAssumptionsProjs = new ArrayList<NationalAssumptionsProj>();
	private NationalAssumptionsProjPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}