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

import com.stpl.app.exception.NoSuchMedicaidUraActualsException;
import com.stpl.app.model.MedicaidUraActuals;
import com.stpl.app.service.MedicaidUraActualsLocalServiceUtil;
import com.stpl.app.service.persistence.MedicaidUraActualsPK;
import com.stpl.app.service.persistence.MedicaidUraActualsPersistence;
import com.stpl.app.service.persistence.MedicaidUraActualsUtil;

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
public class MedicaidUraActualsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MedicaidUraActualsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MedicaidUraActuals> iterator = _medicaidUraActualses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		MedicaidUraActualsPK pk = new MedicaidUraActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraActuals medicaidUraActuals = _persistence.create(pk);

		Assert.assertNotNull(medicaidUraActuals);

		Assert.assertEquals(medicaidUraActuals.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MedicaidUraActuals newMedicaidUraActuals = addMedicaidUraActuals();

		_persistence.remove(newMedicaidUraActuals);

		MedicaidUraActuals existingMedicaidUraActuals = _persistence.fetchByPrimaryKey(newMedicaidUraActuals.getPrimaryKey());

		Assert.assertNull(existingMedicaidUraActuals);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMedicaidUraActuals();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		MedicaidUraActualsPK pk = new MedicaidUraActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraActuals newMedicaidUraActuals = _persistence.create(pk);

		newMedicaidUraActuals.setActualPrice(RandomTestUtil.nextDouble());

		newMedicaidUraActuals.setNotes(RandomTestUtil.randomString());

		newMedicaidUraActuals.setBaseYear(RandomTestUtil.nextDouble());

		_medicaidUraActualses.add(_persistence.update(newMedicaidUraActuals));

		MedicaidUraActuals existingMedicaidUraActuals = _persistence.findByPrimaryKey(newMedicaidUraActuals.getPrimaryKey());

		Assert.assertEquals(existingMedicaidUraActuals.getPeriodSid(),
			newMedicaidUraActuals.getPeriodSid());
		Assert.assertEquals(existingMedicaidUraActuals.getPriceType(),
			newMedicaidUraActuals.getPriceType());
		AssertUtils.assertEquals(existingMedicaidUraActuals.getActualPrice(),
			newMedicaidUraActuals.getActualPrice());
		Assert.assertEquals(existingMedicaidUraActuals.getNotes(),
			newMedicaidUraActuals.getNotes());
		Assert.assertEquals(existingMedicaidUraActuals.getNaProjDetailsSid(),
			newMedicaidUraActuals.getNaProjDetailsSid());
		AssertUtils.assertEquals(existingMedicaidUraActuals.getBaseYear(),
			newMedicaidUraActuals.getBaseYear());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MedicaidUraActuals newMedicaidUraActuals = addMedicaidUraActuals();

		MedicaidUraActuals existingMedicaidUraActuals = _persistence.findByPrimaryKey(newMedicaidUraActuals.getPrimaryKey());

		Assert.assertEquals(existingMedicaidUraActuals, newMedicaidUraActuals);
	}

	@Test(expected = NoSuchMedicaidUraActualsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		MedicaidUraActualsPK pk = new MedicaidUraActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MedicaidUraActuals newMedicaidUraActuals = addMedicaidUraActuals();

		MedicaidUraActuals existingMedicaidUraActuals = _persistence.fetchByPrimaryKey(newMedicaidUraActuals.getPrimaryKey());

		Assert.assertEquals(existingMedicaidUraActuals, newMedicaidUraActuals);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		MedicaidUraActualsPK pk = new MedicaidUraActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraActuals missingMedicaidUraActuals = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMedicaidUraActuals);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MedicaidUraActuals newMedicaidUraActuals1 = addMedicaidUraActuals();
		MedicaidUraActuals newMedicaidUraActuals2 = addMedicaidUraActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidUraActuals1.getPrimaryKey());
		primaryKeys.add(newMedicaidUraActuals2.getPrimaryKey());

		Map<Serializable, MedicaidUraActuals> medicaidUraActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, medicaidUraActualses.size());
		Assert.assertEquals(newMedicaidUraActuals1,
			medicaidUraActualses.get(newMedicaidUraActuals1.getPrimaryKey()));
		Assert.assertEquals(newMedicaidUraActuals2,
			medicaidUraActualses.get(newMedicaidUraActuals2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		MedicaidUraActualsPK pk1 = new MedicaidUraActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraActualsPK pk2 = new MedicaidUraActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MedicaidUraActuals> medicaidUraActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(medicaidUraActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MedicaidUraActuals newMedicaidUraActuals = addMedicaidUraActuals();

		MedicaidUraActualsPK pk = new MedicaidUraActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidUraActuals.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MedicaidUraActuals> medicaidUraActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, medicaidUraActualses.size());
		Assert.assertEquals(newMedicaidUraActuals,
			medicaidUraActualses.get(newMedicaidUraActuals.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MedicaidUraActuals> medicaidUraActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(medicaidUraActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MedicaidUraActuals newMedicaidUraActuals = addMedicaidUraActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMedicaidUraActuals.getPrimaryKey());

		Map<Serializable, MedicaidUraActuals> medicaidUraActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, medicaidUraActualses.size());
		Assert.assertEquals(newMedicaidUraActuals,
			medicaidUraActualses.get(newMedicaidUraActuals.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MedicaidUraActualsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MedicaidUraActuals>() {
				@Override
				public void performAction(MedicaidUraActuals medicaidUraActuals) {
					Assert.assertNotNull(medicaidUraActuals);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MedicaidUraActuals newMedicaidUraActuals = addMedicaidUraActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidUraActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newMedicaidUraActuals.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newMedicaidUraActuals.getPriceType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				newMedicaidUraActuals.getNaProjDetailsSid()));

		List<MedicaidUraActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MedicaidUraActuals existingMedicaidUraActuals = result.get(0);

		Assert.assertEquals(existingMedicaidUraActuals, newMedicaidUraActuals);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidUraActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				RandomTestUtil.nextInt()));

		List<MedicaidUraActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MedicaidUraActuals newMedicaidUraActuals = addMedicaidUraActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidUraActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newMedicaidUraActuals.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MedicaidUraActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MedicaidUraActuals addMedicaidUraActuals()
		throws Exception {
		MedicaidUraActualsPK pk = new MedicaidUraActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		MedicaidUraActuals medicaidUraActuals = _persistence.create(pk);

		medicaidUraActuals.setActualPrice(RandomTestUtil.nextDouble());

		medicaidUraActuals.setNotes(RandomTestUtil.randomString());

		medicaidUraActuals.setBaseYear(RandomTestUtil.nextDouble());

		_medicaidUraActualses.add(_persistence.update(medicaidUraActuals));

		return medicaidUraActuals;
	}

	private List<MedicaidUraActuals> _medicaidUraActualses = new ArrayList<MedicaidUraActuals>();
	private MedicaidUraActualsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}