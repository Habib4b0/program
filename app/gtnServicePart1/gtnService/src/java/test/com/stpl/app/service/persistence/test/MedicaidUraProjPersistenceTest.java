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

import com.stpl.app.exception.NoSuchMedicaidUraProjException;
import com.stpl.app.model.MedicaidUraProj;
import com.stpl.app.service.MedicaidUraProjLocalServiceUtil;
import com.stpl.app.service.persistence.MedicaidUraProjPK;
import com.stpl.app.service.persistence.MedicaidUraProjPersistence;
import com.stpl.app.service.persistence.MedicaidUraProjUtil;

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
public class MedicaidUraProjPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MedicaidUraProjUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MedicaidUraProj> iterator = _medicaidUraProjs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		MedicaidUraProjPK pk = new MedicaidUraProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraProj medicaidUraProj = _persistence.create(pk);

		Assert.assertNotNull(medicaidUraProj);

		Assert.assertEquals(medicaidUraProj.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MedicaidUraProj newMedicaidUraProj = addMedicaidUraProj();

		_persistence.remove(newMedicaidUraProj);

		MedicaidUraProj existingMedicaidUraProj = _persistence.fetchByPrimaryKey(newMedicaidUraProj.getPrimaryKey());

		Assert.assertNull(existingMedicaidUraProj);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMedicaidUraProj();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		MedicaidUraProjPK pk = new MedicaidUraProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraProj newMedicaidUraProj = _persistence.create(pk);

		newMedicaidUraProj.setAdjustment(RandomTestUtil.nextDouble());

		newMedicaidUraProj.setProjectionPrice(RandomTestUtil.nextDouble());

		newMedicaidUraProj.setNotes(RandomTestUtil.randomString());

		_medicaidUraProjs.add(_persistence.update(newMedicaidUraProj));

		MedicaidUraProj existingMedicaidUraProj = _persistence.findByPrimaryKey(newMedicaidUraProj.getPrimaryKey());

		AssertUtils.assertEquals(existingMedicaidUraProj.getAdjustment(),
			newMedicaidUraProj.getAdjustment());
		Assert.assertEquals(existingMedicaidUraProj.getPeriodSid(),
			newMedicaidUraProj.getPeriodSid());
		Assert.assertEquals(existingMedicaidUraProj.getPriceType(),
			newMedicaidUraProj.getPriceType());
		AssertUtils.assertEquals(existingMedicaidUraProj.getProjectionPrice(),
			newMedicaidUraProj.getProjectionPrice());
		Assert.assertEquals(existingMedicaidUraProj.getNotes(),
			newMedicaidUraProj.getNotes());
		Assert.assertEquals(existingMedicaidUraProj.getNaProjDetailsSid(),
			newMedicaidUraProj.getNaProjDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MedicaidUraProj newMedicaidUraProj = addMedicaidUraProj();

		MedicaidUraProj existingMedicaidUraProj = _persistence.findByPrimaryKey(newMedicaidUraProj.getPrimaryKey());

		Assert.assertEquals(existingMedicaidUraProj, newMedicaidUraProj);
	}

	@Test(expected = NoSuchMedicaidUraProjException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		MedicaidUraProjPK pk = new MedicaidUraProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MedicaidUraProj newMedicaidUraProj = addMedicaidUraProj();

		MedicaidUraProj existingMedicaidUraProj = _persistence.fetchByPrimaryKey(newMedicaidUraProj.getPrimaryKey());

		Assert.assertEquals(existingMedicaidUraProj, newMedicaidUraProj);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		MedicaidUraProjPK pk = new MedicaidUraProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraProj missingMedicaidUraProj = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMedicaidUraProj);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MedicaidUraProj newMedicaidUraProj1 = addMedicaidUraProj();
		MedicaidUraProj newMedicaidUraProj2 = addMedicaidUraProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidUraProj1.getPrimaryKey());
		primaryKeys.add(newMedicaidUraProj2.getPrimaryKey());

		Map<Serializable, MedicaidUraProj> medicaidUraProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, medicaidUraProjs.size());
		Assert.assertEquals(newMedicaidUraProj1,
			medicaidUraProjs.get(newMedicaidUraProj1.getPrimaryKey()));
		Assert.assertEquals(newMedicaidUraProj2,
			medicaidUraProjs.get(newMedicaidUraProj2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		MedicaidUraProjPK pk1 = new MedicaidUraProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraProjPK pk2 = new MedicaidUraProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MedicaidUraProj> medicaidUraProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(medicaidUraProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MedicaidUraProj newMedicaidUraProj = addMedicaidUraProj();

		MedicaidUraProjPK pk = new MedicaidUraProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidUraProj.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MedicaidUraProj> medicaidUraProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, medicaidUraProjs.size());
		Assert.assertEquals(newMedicaidUraProj,
			medicaidUraProjs.get(newMedicaidUraProj.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MedicaidUraProj> medicaidUraProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(medicaidUraProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MedicaidUraProj newMedicaidUraProj = addMedicaidUraProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidUraProj.getPrimaryKey());

		Map<Serializable, MedicaidUraProj> medicaidUraProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, medicaidUraProjs.size());
		Assert.assertEquals(newMedicaidUraProj,
			medicaidUraProjs.get(newMedicaidUraProj.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MedicaidUraProjLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MedicaidUraProj>() {
				@Override
				public void performAction(MedicaidUraProj medicaidUraProj) {
					Assert.assertNotNull(medicaidUraProj);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MedicaidUraProj newMedicaidUraProj = addMedicaidUraProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidUraProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newMedicaidUraProj.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newMedicaidUraProj.getPriceType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				newMedicaidUraProj.getNaProjDetailsSid()));

		List<MedicaidUraProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MedicaidUraProj existingMedicaidUraProj = result.get(0);

		Assert.assertEquals(existingMedicaidUraProj, newMedicaidUraProj);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidUraProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				RandomTestUtil.nextInt()));

		List<MedicaidUraProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MedicaidUraProj newMedicaidUraProj = addMedicaidUraProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidUraProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newMedicaidUraProj.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidUraProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MedicaidUraProj addMedicaidUraProj() throws Exception {
		MedicaidUraProjPK pk = new MedicaidUraProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraProj medicaidUraProj = _persistence.create(pk);

		medicaidUraProj.setAdjustment(RandomTestUtil.nextDouble());

		medicaidUraProj.setProjectionPrice(RandomTestUtil.nextDouble());

		medicaidUraProj.setNotes(RandomTestUtil.randomString());

		_medicaidUraProjs.add(_persistence.update(medicaidUraProj));

		return medicaidUraProj;
	}

	private List<MedicaidUraProj> _medicaidUraProjs = new ArrayList<MedicaidUraProj>();
	private MedicaidUraProjPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}