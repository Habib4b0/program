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

import com.stpl.app.exception.NoSuchUsergroupBusinessroleException;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.app.service.persistence.UsergroupBusinessrolePersistence;
import com.stpl.app.service.persistence.UsergroupBusinessroleUtil;

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
public class UsergroupBusinessrolePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = UsergroupBusinessroleUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UsergroupBusinessrole> iterator = _usergroupBusinessroles.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		UsergroupBusinessrole usergroupBusinessrole = _persistence.create(pk);

		Assert.assertNotNull(usergroupBusinessrole);

		Assert.assertEquals(usergroupBusinessrole.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UsergroupBusinessrole newUsergroupBusinessrole = addUsergroupBusinessrole();

		_persistence.remove(newUsergroupBusinessrole);

		UsergroupBusinessrole existingUsergroupBusinessrole = _persistence.fetchByPrimaryKey(newUsergroupBusinessrole.getPrimaryKey());

		Assert.assertNull(existingUsergroupBusinessrole);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUsergroupBusinessrole();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		UsergroupBusinessrole newUsergroupBusinessrole = _persistence.create(pk);

		newUsergroupBusinessrole.setCreatedBy(RandomTestUtil.nextInt());

		newUsergroupBusinessrole.setBusinessroleMasterSid(RandomTestUtil.nextInt());

		newUsergroupBusinessrole.setUsersSid(RandomTestUtil.nextInt());

		newUsergroupBusinessrole.setModifiedBy(RandomTestUtil.nextInt());

		newUsergroupBusinessrole.setCreatedDate(RandomTestUtil.nextDate());

		newUsergroupBusinessrole.setProcessed(RandomTestUtil.randomString());

		newUsergroupBusinessrole.setUsergroupId(RandomTestUtil.nextInt());

		newUsergroupBusinessrole.setVersionNo(RandomTestUtil.nextInt());

		newUsergroupBusinessrole.setIsActive(RandomTestUtil.randomString());

		newUsergroupBusinessrole.setModifiedDate(RandomTestUtil.nextDate());

		_usergroupBusinessroles.add(_persistence.update(
				newUsergroupBusinessrole));

		UsergroupBusinessrole existingUsergroupBusinessrole = _persistence.findByPrimaryKey(newUsergroupBusinessrole.getPrimaryKey());

		Assert.assertEquals(existingUsergroupBusinessrole.getCreatedBy(),
			newUsergroupBusinessrole.getCreatedBy());
		Assert.assertEquals(existingUsergroupBusinessrole.getBusinessroleMasterSid(),
			newUsergroupBusinessrole.getBusinessroleMasterSid());
		Assert.assertEquals(existingUsergroupBusinessrole.getUsersSid(),
			newUsergroupBusinessrole.getUsersSid());
		Assert.assertEquals(existingUsergroupBusinessrole.getModifiedBy(),
			newUsergroupBusinessrole.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingUsergroupBusinessrole.getCreatedDate()),
			Time.getShortTimestamp(newUsergroupBusinessrole.getCreatedDate()));
		Assert.assertEquals(existingUsergroupBusinessrole.getProcessed(),
			newUsergroupBusinessrole.getProcessed());
		Assert.assertEquals(existingUsergroupBusinessrole.getUsergroupId(),
			newUsergroupBusinessrole.getUsergroupId());
		Assert.assertEquals(existingUsergroupBusinessrole.getVersionNo(),
			newUsergroupBusinessrole.getVersionNo());
		Assert.assertEquals(existingUsergroupBusinessrole.getIsActive(),
			newUsergroupBusinessrole.getIsActive());
		Assert.assertEquals(existingUsergroupBusinessrole.getUsergroupBusinessroleSid(),
			newUsergroupBusinessrole.getUsergroupBusinessroleSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingUsergroupBusinessrole.getModifiedDate()),
			Time.getShortTimestamp(newUsergroupBusinessrole.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UsergroupBusinessrole newUsergroupBusinessrole = addUsergroupBusinessrole();

		UsergroupBusinessrole existingUsergroupBusinessrole = _persistence.findByPrimaryKey(newUsergroupBusinessrole.getPrimaryKey());

		Assert.assertEquals(existingUsergroupBusinessrole,
			newUsergroupBusinessrole);
	}

	@Test(expected = NoSuchUsergroupBusinessroleException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<UsergroupBusinessrole> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("USERGROUP_BUSINESSROLE",
			"createdBy", true, "businessroleMasterSid", true, "usersSid", true,
			"modifiedBy", true, "createdDate", true, "processed", true,
			"usergroupId", true, "versionNo", true, "isActive", true,
			"usergroupBusinessroleSid", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UsergroupBusinessrole newUsergroupBusinessrole = addUsergroupBusinessrole();

		UsergroupBusinessrole existingUsergroupBusinessrole = _persistence.fetchByPrimaryKey(newUsergroupBusinessrole.getPrimaryKey());

		Assert.assertEquals(existingUsergroupBusinessrole,
			newUsergroupBusinessrole);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		UsergroupBusinessrole missingUsergroupBusinessrole = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUsergroupBusinessrole);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		UsergroupBusinessrole newUsergroupBusinessrole1 = addUsergroupBusinessrole();
		UsergroupBusinessrole newUsergroupBusinessrole2 = addUsergroupBusinessrole();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUsergroupBusinessrole1.getPrimaryKey());
		primaryKeys.add(newUsergroupBusinessrole2.getPrimaryKey());

		Map<Serializable, UsergroupBusinessrole> usergroupBusinessroles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, usergroupBusinessroles.size());
		Assert.assertEquals(newUsergroupBusinessrole1,
			usergroupBusinessroles.get(
				newUsergroupBusinessrole1.getPrimaryKey()));
		Assert.assertEquals(newUsergroupBusinessrole2,
			usergroupBusinessroles.get(
				newUsergroupBusinessrole2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UsergroupBusinessrole> usergroupBusinessroles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(usergroupBusinessroles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		UsergroupBusinessrole newUsergroupBusinessrole = addUsergroupBusinessrole();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUsergroupBusinessrole.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UsergroupBusinessrole> usergroupBusinessroles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, usergroupBusinessroles.size());
		Assert.assertEquals(newUsergroupBusinessrole,
			usergroupBusinessroles.get(newUsergroupBusinessrole.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UsergroupBusinessrole> usergroupBusinessroles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(usergroupBusinessroles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		UsergroupBusinessrole newUsergroupBusinessrole = addUsergroupBusinessrole();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUsergroupBusinessrole.getPrimaryKey());

		Map<Serializable, UsergroupBusinessrole> usergroupBusinessroles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, usergroupBusinessroles.size());
		Assert.assertEquals(newUsergroupBusinessrole,
			usergroupBusinessroles.get(newUsergroupBusinessrole.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UsergroupBusinessroleLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<UsergroupBusinessrole>() {
				@Override
				public void performAction(
					UsergroupBusinessrole usergroupBusinessrole) {
					Assert.assertNotNull(usergroupBusinessrole);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UsergroupBusinessrole newUsergroupBusinessrole = addUsergroupBusinessrole();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupBusinessrole.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"usergroupBusinessroleSid",
				newUsergroupBusinessrole.getUsergroupBusinessroleSid()));

		List<UsergroupBusinessrole> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UsergroupBusinessrole existingUsergroupBusinessrole = result.get(0);

		Assert.assertEquals(existingUsergroupBusinessrole,
			newUsergroupBusinessrole);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupBusinessrole.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"usergroupBusinessroleSid", RandomTestUtil.nextInt()));

		List<UsergroupBusinessrole> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UsergroupBusinessrole newUsergroupBusinessrole = addUsergroupBusinessrole();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupBusinessrole.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"usergroupBusinessroleSid"));

		Object newUsergroupBusinessroleSid = newUsergroupBusinessrole.getUsergroupBusinessroleSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"usergroupBusinessroleSid",
				new Object[] { newUsergroupBusinessroleSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUsergroupBusinessroleSid = result.get(0);

		Assert.assertEquals(existingUsergroupBusinessroleSid,
			newUsergroupBusinessroleSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupBusinessrole.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"usergroupBusinessroleSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"usergroupBusinessroleSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected UsergroupBusinessrole addUsergroupBusinessrole()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		UsergroupBusinessrole usergroupBusinessrole = _persistence.create(pk);

		usergroupBusinessrole.setCreatedBy(RandomTestUtil.nextInt());

		usergroupBusinessrole.setBusinessroleMasterSid(RandomTestUtil.nextInt());

		usergroupBusinessrole.setUsersSid(RandomTestUtil.nextInt());

		usergroupBusinessrole.setModifiedBy(RandomTestUtil.nextInt());

		usergroupBusinessrole.setCreatedDate(RandomTestUtil.nextDate());

		usergroupBusinessrole.setProcessed(RandomTestUtil.randomString());

		usergroupBusinessrole.setUsergroupId(RandomTestUtil.nextInt());

		usergroupBusinessrole.setVersionNo(RandomTestUtil.nextInt());

		usergroupBusinessrole.setIsActive(RandomTestUtil.randomString());

		usergroupBusinessrole.setModifiedDate(RandomTestUtil.nextDate());

		_usergroupBusinessroles.add(_persistence.update(usergroupBusinessrole));

		return usergroupBusinessrole;
	}

	private List<UsergroupBusinessrole> _usergroupBusinessroles = new ArrayList<UsergroupBusinessrole>();
	private UsergroupBusinessrolePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}