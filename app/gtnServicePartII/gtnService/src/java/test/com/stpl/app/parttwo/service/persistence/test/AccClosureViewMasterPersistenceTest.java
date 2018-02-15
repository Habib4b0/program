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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchAccClosureViewMasterException;
import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.app.parttwo.service.AccClosureViewMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.AccClosureViewMasterPersistence;
import com.stpl.app.parttwo.service.persistence.AccClosureViewMasterUtil;

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
public class AccClosureViewMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = AccClosureViewMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AccClosureViewMaster> iterator = _accClosureViewMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureViewMaster accClosureViewMaster = _persistence.create(pk);

		Assert.assertNotNull(accClosureViewMaster);

		Assert.assertEquals(accClosureViewMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AccClosureViewMaster newAccClosureViewMaster = addAccClosureViewMaster();

		_persistence.remove(newAccClosureViewMaster);

		AccClosureViewMaster existingAccClosureViewMaster = _persistence.fetchByPrimaryKey(newAccClosureViewMaster.getPrimaryKey());

		Assert.assertNull(existingAccClosureViewMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAccClosureViewMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureViewMaster newAccClosureViewMaster = _persistence.create(pk);

		newAccClosureViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		newAccClosureViewMaster.setCreatedBy(RandomTestUtil.nextInt());

		newAccClosureViewMaster.setViewType(RandomTestUtil.randomString());

		newAccClosureViewMaster.setAccClosureMasterSid(RandomTestUtil.nextInt());

		newAccClosureViewMaster.setModifiedBy(RandomTestUtil.nextInt());

		newAccClosureViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		newAccClosureViewMaster.setViewName(RandomTestUtil.randomString());

		_accClosureViewMasters.add(_persistence.update(newAccClosureViewMaster));

		AccClosureViewMaster existingAccClosureViewMaster = _persistence.findByPrimaryKey(newAccClosureViewMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingAccClosureViewMaster.getCreatedDate()),
			Time.getShortTimestamp(newAccClosureViewMaster.getCreatedDate()));
		Assert.assertEquals(existingAccClosureViewMaster.getCreatedBy(),
			newAccClosureViewMaster.getCreatedBy());
		Assert.assertEquals(existingAccClosureViewMaster.getViewType(),
			newAccClosureViewMaster.getViewType());
		Assert.assertEquals(existingAccClosureViewMaster.getAccClosureMasterSid(),
			newAccClosureViewMaster.getAccClosureMasterSid());
		Assert.assertEquals(existingAccClosureViewMaster.getModifiedBy(),
			newAccClosureViewMaster.getModifiedBy());
		Assert.assertEquals(existingAccClosureViewMaster.getAccClosureViewMasterSid(),
			newAccClosureViewMaster.getAccClosureViewMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccClosureViewMaster.getModifiedDate()),
			Time.getShortTimestamp(newAccClosureViewMaster.getModifiedDate()));
		Assert.assertEquals(existingAccClosureViewMaster.getViewName(),
			newAccClosureViewMaster.getViewName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AccClosureViewMaster newAccClosureViewMaster = addAccClosureViewMaster();

		AccClosureViewMaster existingAccClosureViewMaster = _persistence.findByPrimaryKey(newAccClosureViewMaster.getPrimaryKey());

		Assert.assertEquals(existingAccClosureViewMaster,
			newAccClosureViewMaster);
	}

	@Test(expected = NoSuchAccClosureViewMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AccClosureViewMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Acc_Closure_View_Master",
			"createdDate", true, "createdBy", true, "viewType", true,
			"accClosureMasterSid", true, "modifiedBy", true,
			"accClosureViewMasterSid", true, "modifiedDate", true, "viewName",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AccClosureViewMaster newAccClosureViewMaster = addAccClosureViewMaster();

		AccClosureViewMaster existingAccClosureViewMaster = _persistence.fetchByPrimaryKey(newAccClosureViewMaster.getPrimaryKey());

		Assert.assertEquals(existingAccClosureViewMaster,
			newAccClosureViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureViewMaster missingAccClosureViewMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAccClosureViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AccClosureViewMaster newAccClosureViewMaster1 = addAccClosureViewMaster();
		AccClosureViewMaster newAccClosureViewMaster2 = addAccClosureViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureViewMaster1.getPrimaryKey());
		primaryKeys.add(newAccClosureViewMaster2.getPrimaryKey());

		Map<Serializable, AccClosureViewMaster> accClosureViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, accClosureViewMasters.size());
		Assert.assertEquals(newAccClosureViewMaster1,
			accClosureViewMasters.get(newAccClosureViewMaster1.getPrimaryKey()));
		Assert.assertEquals(newAccClosureViewMaster2,
			accClosureViewMasters.get(newAccClosureViewMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AccClosureViewMaster> accClosureViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accClosureViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AccClosureViewMaster newAccClosureViewMaster = addAccClosureViewMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureViewMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AccClosureViewMaster> accClosureViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accClosureViewMasters.size());
		Assert.assertEquals(newAccClosureViewMaster,
			accClosureViewMasters.get(newAccClosureViewMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AccClosureViewMaster> accClosureViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accClosureViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AccClosureViewMaster newAccClosureViewMaster = addAccClosureViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureViewMaster.getPrimaryKey());

		Map<Serializable, AccClosureViewMaster> accClosureViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accClosureViewMasters.size());
		Assert.assertEquals(newAccClosureViewMaster,
			accClosureViewMasters.get(newAccClosureViewMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AccClosureViewMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AccClosureViewMaster>() {
				@Override
				public void performAction(
					AccClosureViewMaster accClosureViewMaster) {
					Assert.assertNotNull(accClosureViewMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AccClosureViewMaster newAccClosureViewMaster = addAccClosureViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureViewMasterSid",
				newAccClosureViewMaster.getAccClosureViewMasterSid()));

		List<AccClosureViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AccClosureViewMaster existingAccClosureViewMaster = result.get(0);

		Assert.assertEquals(existingAccClosureViewMaster,
			newAccClosureViewMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureViewMasterSid",
				RandomTestUtil.nextInt()));

		List<AccClosureViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AccClosureViewMaster newAccClosureViewMaster = addAccClosureViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureViewMasterSid"));

		Object newAccClosureViewMasterSid = newAccClosureViewMaster.getAccClosureViewMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureViewMasterSid",
				new Object[] { newAccClosureViewMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccClosureViewMasterSid = result.get(0);

		Assert.assertEquals(existingAccClosureViewMasterSid,
			newAccClosureViewMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureViewMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureViewMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AccClosureViewMaster addAccClosureViewMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureViewMaster accClosureViewMaster = _persistence.create(pk);

		accClosureViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		accClosureViewMaster.setCreatedBy(RandomTestUtil.nextInt());

		accClosureViewMaster.setViewType(RandomTestUtil.randomString());

		accClosureViewMaster.setAccClosureMasterSid(RandomTestUtil.nextInt());

		accClosureViewMaster.setModifiedBy(RandomTestUtil.nextInt());

		accClosureViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		accClosureViewMaster.setViewName(RandomTestUtil.randomString());

		_accClosureViewMasters.add(_persistence.update(accClosureViewMaster));

		return accClosureViewMaster;
	}

	private List<AccClosureViewMaster> _accClosureViewMasters = new ArrayList<AccClosureViewMaster>();
	private AccClosureViewMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}