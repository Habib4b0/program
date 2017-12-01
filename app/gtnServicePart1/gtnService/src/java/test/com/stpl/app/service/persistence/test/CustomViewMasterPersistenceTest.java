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

import com.stpl.app.exception.NoSuchCustomViewMasterException;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.service.CustomViewMasterLocalServiceUtil;
import com.stpl.app.service.persistence.CustomViewMasterPersistence;
import com.stpl.app.service.persistence.CustomViewMasterUtil;

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
public class CustomViewMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CustomViewMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CustomViewMaster> iterator = _customViewMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomViewMaster customViewMaster = _persistence.create(pk);

		Assert.assertNotNull(customViewMaster);

		Assert.assertEquals(customViewMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CustomViewMaster newCustomViewMaster = addCustomViewMaster();

		_persistence.remove(newCustomViewMaster);

		CustomViewMaster existingCustomViewMaster = _persistence.fetchByPrimaryKey(newCustomViewMaster.getPrimaryKey());

		Assert.assertNull(existingCustomViewMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCustomViewMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomViewMaster newCustomViewMaster = _persistence.create(pk);

		newCustomViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		newCustomViewMaster.setCreatedBy(RandomTestUtil.nextInt());

		newCustomViewMaster.setProjectionMasterSid(RandomTestUtil.nextInt());

		newCustomViewMaster.setModifiedBy(RandomTestUtil.nextInt());

		newCustomViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		newCustomViewMaster.setViewName(RandomTestUtil.randomString());

		_customViewMasters.add(_persistence.update(newCustomViewMaster));

		CustomViewMaster existingCustomViewMaster = _persistence.findByPrimaryKey(newCustomViewMaster.getPrimaryKey());

		Assert.assertEquals(existingCustomViewMaster.getCustomViewMasterSid(),
			newCustomViewMaster.getCustomViewMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomViewMaster.getCreatedDate()),
			Time.getShortTimestamp(newCustomViewMaster.getCreatedDate()));
		Assert.assertEquals(existingCustomViewMaster.getCreatedBy(),
			newCustomViewMaster.getCreatedBy());
		Assert.assertEquals(existingCustomViewMaster.getProjectionMasterSid(),
			newCustomViewMaster.getProjectionMasterSid());
		Assert.assertEquals(existingCustomViewMaster.getModifiedBy(),
			newCustomViewMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomViewMaster.getModifiedDate()),
			Time.getShortTimestamp(newCustomViewMaster.getModifiedDate()));
		Assert.assertEquals(existingCustomViewMaster.getViewName(),
			newCustomViewMaster.getViewName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CustomViewMaster newCustomViewMaster = addCustomViewMaster();

		CustomViewMaster existingCustomViewMaster = _persistence.findByPrimaryKey(newCustomViewMaster.getPrimaryKey());

		Assert.assertEquals(existingCustomViewMaster, newCustomViewMaster);
	}

	@Test(expected = NoSuchCustomViewMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CustomViewMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CUSTOM_VIEW_MASTER",
			"customViewMasterSid", true, "createdDate", true, "createdBy",
			true, "projectionMasterSid", true, "modifiedBy", true,
			"modifiedDate", true, "viewName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CustomViewMaster newCustomViewMaster = addCustomViewMaster();

		CustomViewMaster existingCustomViewMaster = _persistence.fetchByPrimaryKey(newCustomViewMaster.getPrimaryKey());

		Assert.assertEquals(existingCustomViewMaster, newCustomViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomViewMaster missingCustomViewMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCustomViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CustomViewMaster newCustomViewMaster1 = addCustomViewMaster();
		CustomViewMaster newCustomViewMaster2 = addCustomViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomViewMaster1.getPrimaryKey());
		primaryKeys.add(newCustomViewMaster2.getPrimaryKey());

		Map<Serializable, CustomViewMaster> customViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, customViewMasters.size());
		Assert.assertEquals(newCustomViewMaster1,
			customViewMasters.get(newCustomViewMaster1.getPrimaryKey()));
		Assert.assertEquals(newCustomViewMaster2,
			customViewMasters.get(newCustomViewMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CustomViewMaster> customViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(customViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CustomViewMaster newCustomViewMaster = addCustomViewMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomViewMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CustomViewMaster> customViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, customViewMasters.size());
		Assert.assertEquals(newCustomViewMaster,
			customViewMasters.get(newCustomViewMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CustomViewMaster> customViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(customViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CustomViewMaster newCustomViewMaster = addCustomViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomViewMaster.getPrimaryKey());

		Map<Serializable, CustomViewMaster> customViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, customViewMasters.size());
		Assert.assertEquals(newCustomViewMaster,
			customViewMasters.get(newCustomViewMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CustomViewMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CustomViewMaster>() {
				@Override
				public void performAction(CustomViewMaster customViewMaster) {
					Assert.assertNotNull(customViewMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CustomViewMaster newCustomViewMaster = addCustomViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customViewMasterSid",
				newCustomViewMaster.getCustomViewMasterSid()));

		List<CustomViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CustomViewMaster existingCustomViewMaster = result.get(0);

		Assert.assertEquals(existingCustomViewMaster, newCustomViewMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customViewMasterSid",
				RandomTestUtil.nextInt()));

		List<CustomViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CustomViewMaster newCustomViewMaster = addCustomViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customViewMasterSid"));

		Object newCustomViewMasterSid = newCustomViewMaster.getCustomViewMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("customViewMasterSid",
				new Object[] { newCustomViewMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCustomViewMasterSid = result.get(0);

		Assert.assertEquals(existingCustomViewMasterSid, newCustomViewMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customViewMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("customViewMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CustomViewMaster addCustomViewMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomViewMaster customViewMaster = _persistence.create(pk);

		customViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		customViewMaster.setCreatedBy(RandomTestUtil.nextInt());

		customViewMaster.setProjectionMasterSid(RandomTestUtil.nextInt());

		customViewMaster.setModifiedBy(RandomTestUtil.nextInt());

		customViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		customViewMaster.setViewName(RandomTestUtil.randomString());

		_customViewMasters.add(_persistence.update(customViewMaster));

		return customViewMaster;
	}

	private List<CustomViewMaster> _customViewMasters = new ArrayList<CustomViewMaster>();
	private CustomViewMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}