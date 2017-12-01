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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchPeriodException;
import com.stpl.app.model.Period;
import com.stpl.app.service.PeriodLocalServiceUtil;
import com.stpl.app.service.persistence.PeriodPersistence;
import com.stpl.app.service.persistence.PeriodUtil;

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
public class PeriodPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PeriodUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Period> iterator = _periods.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		Period period = _persistence.create(pk);

		Assert.assertNotNull(period);

		Assert.assertEquals(period.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Period newPeriod = addPeriod();

		_persistence.remove(newPeriod);

		Period existingPeriod = _persistence.fetchByPrimaryKey(newPeriod.getPrimaryKey());

		Assert.assertNull(existingPeriod);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPeriod();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		Period newPeriod = _persistence.create(pk);

		newPeriod.setPeriodDate(RandomTestUtil.nextDate());

		newPeriod.setQuarter(RandomTestUtil.nextInt());

		newPeriod.setYear(RandomTestUtil.nextInt());

		newPeriod.setSemiAnnual(RandomTestUtil.nextInt());

		newPeriod.setMonth(RandomTestUtil.nextInt());

		_periods.add(_persistence.update(newPeriod));

		Period existingPeriod = _persistence.findByPrimaryKey(newPeriod.getPrimaryKey());

		Assert.assertEquals(existingPeriod.getPeriodSid(),
			newPeriod.getPeriodSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPeriod.getPeriodDate()),
			Time.getShortTimestamp(newPeriod.getPeriodDate()));
		Assert.assertEquals(existingPeriod.getQuarter(), newPeriod.getQuarter());
		Assert.assertEquals(existingPeriod.getYear(), newPeriod.getYear());
		Assert.assertEquals(existingPeriod.getSemiAnnual(),
			newPeriod.getSemiAnnual());
		Assert.assertEquals(existingPeriod.getMonth(), newPeriod.getMonth());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Period newPeriod = addPeriod();

		Period existingPeriod = _persistence.findByPrimaryKey(newPeriod.getPrimaryKey());

		Assert.assertEquals(existingPeriod, newPeriod);
	}

	@Test(expected = NoSuchPeriodException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Period> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PERIOD", "periodSid", true,
			"periodDate", true, "quarter", true, "year", true, "semiAnnual",
			true, "month", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Period newPeriod = addPeriod();

		Period existingPeriod = _persistence.fetchByPrimaryKey(newPeriod.getPrimaryKey());

		Assert.assertEquals(existingPeriod, newPeriod);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		Period missingPeriod = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPeriod);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Period newPeriod1 = addPeriod();
		Period newPeriod2 = addPeriod();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPeriod1.getPrimaryKey());
		primaryKeys.add(newPeriod2.getPrimaryKey());

		Map<Serializable, Period> periods = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, periods.size());
		Assert.assertEquals(newPeriod1, periods.get(newPeriod1.getPrimaryKey()));
		Assert.assertEquals(newPeriod2, periods.get(newPeriod2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Period> periods = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(periods.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Period newPeriod = addPeriod();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPeriod.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Period> periods = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, periods.size());
		Assert.assertEquals(newPeriod, periods.get(newPeriod.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Period> periods = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(periods.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Period newPeriod = addPeriod();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPeriod.getPrimaryKey());

		Map<Serializable, Period> periods = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, periods.size());
		Assert.assertEquals(newPeriod, periods.get(newPeriod.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PeriodLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Period>() {
				@Override
				public void performAction(Period period) {
					Assert.assertNotNull(period);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Period newPeriod = addPeriod();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Period.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("periodSid",
				newPeriod.getPeriodSid()));

		List<Period> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Period existingPeriod = result.get(0);

		Assert.assertEquals(existingPeriod, newPeriod);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Period.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("periodSid",
				RandomTestUtil.nextInt()));

		List<Period> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Period newPeriod = addPeriod();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Period.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("periodSid"));

		Object newPeriodSid = newPeriod.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Period.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Period addPeriod() throws Exception {
		int pk = RandomTestUtil.nextInt();

		Period period = _persistence.create(pk);

		period.setPeriodDate(RandomTestUtil.nextDate());

		period.setQuarter(RandomTestUtil.nextInt());

		period.setYear(RandomTestUtil.nextInt());

		period.setSemiAnnual(RandomTestUtil.nextInt());

		period.setMonth(RandomTestUtil.nextInt());

		_periods.add(_persistence.update(period));

		return period;
	}

	private List<Period> _periods = new ArrayList<Period>();
	private PeriodPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}