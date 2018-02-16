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

import com.stpl.app.exception.NoSuchForecastingViewMasterException;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.service.ForecastingViewMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ForecastingViewMasterPersistence;
import com.stpl.app.service.persistence.ForecastingViewMasterUtil;

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
public class ForecastingViewMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ForecastingViewMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ForecastingViewMaster> iterator = _forecastingViewMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingViewMaster forecastingViewMaster = _persistence.create(pk);

		Assert.assertNotNull(forecastingViewMaster);

		Assert.assertEquals(forecastingViewMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ForecastingViewMaster newForecastingViewMaster = addForecastingViewMaster();

		_persistence.remove(newForecastingViewMaster);

		ForecastingViewMaster existingForecastingViewMaster = _persistence.fetchByPrimaryKey(newForecastingViewMaster.getPrimaryKey());

		Assert.assertNull(existingForecastingViewMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addForecastingViewMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingViewMaster newForecastingViewMaster = _persistence.create(pk);

		newForecastingViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		newForecastingViewMaster.setCreatedBy(RandomTestUtil.randomString());

		newForecastingViewMaster.setViewType(RandomTestUtil.randomString());

		newForecastingViewMaster.setProjectionId(RandomTestUtil.nextInt());

		newForecastingViewMaster.setModifiedBy(RandomTestUtil.randomString());

		newForecastingViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		newForecastingViewMaster.setViewName(RandomTestUtil.randomString());

		_forecastingViewMasters.add(_persistence.update(
				newForecastingViewMaster));

		ForecastingViewMaster existingForecastingViewMaster = _persistence.findByPrimaryKey(newForecastingViewMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastingViewMaster.getCreatedDate()),
			Time.getShortTimestamp(newForecastingViewMaster.getCreatedDate()));
		Assert.assertEquals(existingForecastingViewMaster.getCreatedBy(),
			newForecastingViewMaster.getCreatedBy());
		Assert.assertEquals(existingForecastingViewMaster.getViewType(),
			newForecastingViewMaster.getViewType());
		Assert.assertEquals(existingForecastingViewMaster.getViewId(),
			newForecastingViewMaster.getViewId());
		Assert.assertEquals(existingForecastingViewMaster.getProjectionId(),
			newForecastingViewMaster.getProjectionId());
		Assert.assertEquals(existingForecastingViewMaster.getModifiedBy(),
			newForecastingViewMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastingViewMaster.getModifiedDate()),
			Time.getShortTimestamp(newForecastingViewMaster.getModifiedDate()));
		Assert.assertEquals(existingForecastingViewMaster.getViewName(),
			newForecastingViewMaster.getViewName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ForecastingViewMaster newForecastingViewMaster = addForecastingViewMaster();

		ForecastingViewMaster existingForecastingViewMaster = _persistence.findByPrimaryKey(newForecastingViewMaster.getPrimaryKey());

		Assert.assertEquals(existingForecastingViewMaster,
			newForecastingViewMaster);
	}

	@Test(expected = NoSuchForecastingViewMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ForecastingViewMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FORECASTING_VIEW_MASTER",
			"createdDate", true, "createdBy", true, "viewType", true, "viewId",
			true, "projectionId", true, "modifiedBy", true, "modifiedDate",
			true, "viewName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ForecastingViewMaster newForecastingViewMaster = addForecastingViewMaster();

		ForecastingViewMaster existingForecastingViewMaster = _persistence.fetchByPrimaryKey(newForecastingViewMaster.getPrimaryKey());

		Assert.assertEquals(existingForecastingViewMaster,
			newForecastingViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingViewMaster missingForecastingViewMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingForecastingViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ForecastingViewMaster newForecastingViewMaster1 = addForecastingViewMaster();
		ForecastingViewMaster newForecastingViewMaster2 = addForecastingViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingViewMaster1.getPrimaryKey());
		primaryKeys.add(newForecastingViewMaster2.getPrimaryKey());

		Map<Serializable, ForecastingViewMaster> forecastingViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, forecastingViewMasters.size());
		Assert.assertEquals(newForecastingViewMaster1,
			forecastingViewMasters.get(
				newForecastingViewMaster1.getPrimaryKey()));
		Assert.assertEquals(newForecastingViewMaster2,
			forecastingViewMasters.get(
				newForecastingViewMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ForecastingViewMaster> forecastingViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(forecastingViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ForecastingViewMaster newForecastingViewMaster = addForecastingViewMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingViewMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ForecastingViewMaster> forecastingViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, forecastingViewMasters.size());
		Assert.assertEquals(newForecastingViewMaster,
			forecastingViewMasters.get(newForecastingViewMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ForecastingViewMaster> forecastingViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(forecastingViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ForecastingViewMaster newForecastingViewMaster = addForecastingViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingViewMaster.getPrimaryKey());

		Map<Serializable, ForecastingViewMaster> forecastingViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, forecastingViewMasters.size());
		Assert.assertEquals(newForecastingViewMaster,
			forecastingViewMasters.get(newForecastingViewMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ForecastingViewMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ForecastingViewMaster>() {
				@Override
				public void performAction(
					ForecastingViewMaster forecastingViewMaster) {
					Assert.assertNotNull(forecastingViewMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ForecastingViewMaster newForecastingViewMaster = addForecastingViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("viewId",
				newForecastingViewMaster.getViewId()));

		List<ForecastingViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ForecastingViewMaster existingForecastingViewMaster = result.get(0);

		Assert.assertEquals(existingForecastingViewMaster,
			newForecastingViewMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("viewId",
				RandomTestUtil.nextInt()));

		List<ForecastingViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ForecastingViewMaster newForecastingViewMaster = addForecastingViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("viewId"));

		Object newViewId = newForecastingViewMaster.getViewId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("viewId",
				new Object[] { newViewId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingViewId = result.get(0);

		Assert.assertEquals(existingViewId, newViewId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("viewId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("viewId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ForecastingViewMaster addForecastingViewMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingViewMaster forecastingViewMaster = _persistence.create(pk);

		forecastingViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		forecastingViewMaster.setCreatedBy(RandomTestUtil.randomString());

		forecastingViewMaster.setViewType(RandomTestUtil.randomString());

		forecastingViewMaster.setProjectionId(RandomTestUtil.nextInt());

		forecastingViewMaster.setModifiedBy(RandomTestUtil.randomString());

		forecastingViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		forecastingViewMaster.setViewName(RandomTestUtil.randomString());

		_forecastingViewMasters.add(_persistence.update(forecastingViewMaster));

		return forecastingViewMaster;
	}

	private List<ForecastingViewMaster> _forecastingViewMasters = new ArrayList<ForecastingViewMaster>();
	private ForecastingViewMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}