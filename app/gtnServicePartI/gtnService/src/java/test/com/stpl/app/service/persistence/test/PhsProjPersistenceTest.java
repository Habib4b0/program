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

import com.stpl.app.exception.NoSuchPhsProjException;
import com.stpl.app.model.PhsProj;
import com.stpl.app.service.PhsProjLocalServiceUtil;
import com.stpl.app.service.persistence.PhsProjPK;
import com.stpl.app.service.persistence.PhsProjPersistence;
import com.stpl.app.service.persistence.PhsProjUtil;

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
public class PhsProjPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PhsProjUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PhsProj> iterator = _phsProjs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		PhsProjPK pk = new PhsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsProj phsProj = _persistence.create(pk);

		Assert.assertNotNull(phsProj);

		Assert.assertEquals(phsProj.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PhsProj newPhsProj = addPhsProj();

		_persistence.remove(newPhsProj);

		PhsProj existingPhsProj = _persistence.fetchByPrimaryKey(newPhsProj.getPrimaryKey());

		Assert.assertNull(existingPhsProj);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPhsProj();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		PhsProjPK pk = new PhsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsProj newPhsProj = _persistence.create(pk);

		newPhsProj.setAdjustment(RandomTestUtil.nextDouble());

		newPhsProj.setProjectionPrice(RandomTestUtil.nextDouble());

		newPhsProj.setNotes(RandomTestUtil.randomString());

		_phsProjs.add(_persistence.update(newPhsProj));

		PhsProj existingPhsProj = _persistence.findByPrimaryKey(newPhsProj.getPrimaryKey());

		AssertUtils.assertEquals(existingPhsProj.getAdjustment(),
			newPhsProj.getAdjustment());
		Assert.assertEquals(existingPhsProj.getPeriodSid(),
			newPhsProj.getPeriodSid());
		Assert.assertEquals(existingPhsProj.getPriceType(),
			newPhsProj.getPriceType());
		AssertUtils.assertEquals(existingPhsProj.getProjectionPrice(),
			newPhsProj.getProjectionPrice());
		Assert.assertEquals(existingPhsProj.getNotes(), newPhsProj.getNotes());
		Assert.assertEquals(existingPhsProj.getNaProjDetailsSid(),
			newPhsProj.getNaProjDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PhsProj newPhsProj = addPhsProj();

		PhsProj existingPhsProj = _persistence.findByPrimaryKey(newPhsProj.getPrimaryKey());

		Assert.assertEquals(existingPhsProj, newPhsProj);
	}

	@Test(expected = NoSuchPhsProjException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		PhsProjPK pk = new PhsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PhsProj newPhsProj = addPhsProj();

		PhsProj existingPhsProj = _persistence.fetchByPrimaryKey(newPhsProj.getPrimaryKey());

		Assert.assertEquals(existingPhsProj, newPhsProj);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		PhsProjPK pk = new PhsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsProj missingPhsProj = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPhsProj);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PhsProj newPhsProj1 = addPhsProj();
		PhsProj newPhsProj2 = addPhsProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhsProj1.getPrimaryKey());
		primaryKeys.add(newPhsProj2.getPrimaryKey());

		Map<Serializable, PhsProj> phsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, phsProjs.size());
		Assert.assertEquals(newPhsProj1,
			phsProjs.get(newPhsProj1.getPrimaryKey()));
		Assert.assertEquals(newPhsProj2,
			phsProjs.get(newPhsProj2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		PhsProjPK pk1 = new PhsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsProjPK pk2 = new PhsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PhsProj> phsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(phsProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PhsProj newPhsProj = addPhsProj();

		PhsProjPK pk = new PhsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhsProj.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PhsProj> phsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, phsProjs.size());
		Assert.assertEquals(newPhsProj, phsProjs.get(newPhsProj.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PhsProj> phsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(phsProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PhsProj newPhsProj = addPhsProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhsProj.getPrimaryKey());

		Map<Serializable, PhsProj> phsProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, phsProjs.size());
		Assert.assertEquals(newPhsProj, phsProjs.get(newPhsProj.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PhsProjLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PhsProj>() {
				@Override
				public void performAction(PhsProj phsProj) {
					Assert.assertNotNull(phsProj);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PhsProj newPhsProj = addPhsProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PhsProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newPhsProj.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newPhsProj.getPriceType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				newPhsProj.getNaProjDetailsSid()));

		List<PhsProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PhsProj existingPhsProj = result.get(0);

		Assert.assertEquals(existingPhsProj, newPhsProj);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PhsProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				RandomTestUtil.nextInt()));

		List<PhsProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PhsProj newPhsProj = addPhsProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PhsProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newPhsProj.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PhsProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PhsProj addPhsProj() throws Exception {
		PhsProjPK pk = new PhsProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsProj phsProj = _persistence.create(pk);

		phsProj.setAdjustment(RandomTestUtil.nextDouble());

		phsProj.setProjectionPrice(RandomTestUtil.nextDouble());

		phsProj.setNotes(RandomTestUtil.randomString());

		_phsProjs.add(_persistence.update(phsProj));

		return phsProj;
	}

	private List<PhsProj> _phsProjs = new ArrayList<PhsProj>();
	private PhsProjPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}