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

import com.stpl.app.exception.NoSuchUsergroupDomainMasterException;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;
import com.stpl.app.service.persistence.UsergroupDomainMasterPersistence;
import com.stpl.app.service.persistence.UsergroupDomainMasterUtil;

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
public class UsergroupDomainMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = UsergroupDomainMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UsergroupDomainMaster> iterator = _usergroupDomainMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		UsergroupDomainMaster usergroupDomainMaster = _persistence.create(pk);

		Assert.assertNotNull(usergroupDomainMaster);

		Assert.assertEquals(usergroupDomainMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UsergroupDomainMaster newUsergroupDomainMaster = addUsergroupDomainMaster();

		_persistence.remove(newUsergroupDomainMaster);

		UsergroupDomainMaster existingUsergroupDomainMaster = _persistence.fetchByPrimaryKey(newUsergroupDomainMaster.getPrimaryKey());

		Assert.assertNull(existingUsergroupDomainMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUsergroupDomainMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		UsergroupDomainMaster newUsergroupDomainMaster = _persistence.create(pk);

		newUsergroupDomainMaster.setCreatedBy(RandomTestUtil.nextInt());

		newUsergroupDomainMaster.setUsersSid(RandomTestUtil.nextInt());

		newUsergroupDomainMaster.setModifiedBy(RandomTestUtil.nextInt());

		newUsergroupDomainMaster.setCreatedDate(RandomTestUtil.nextDate());

		newUsergroupDomainMaster.setDomainId(RandomTestUtil.nextInt());

		newUsergroupDomainMaster.setProcessed(RandomTestUtil.randomString());

		newUsergroupDomainMaster.setUsergroupId(RandomTestUtil.nextInt());

		newUsergroupDomainMaster.setVersionNo(RandomTestUtil.nextInt());

		newUsergroupDomainMaster.setIsActive(RandomTestUtil.randomString());

		newUsergroupDomainMaster.setModifiedDate(RandomTestUtil.nextDate());

		_usergroupDomainMasters.add(_persistence.update(
				newUsergroupDomainMaster));

		UsergroupDomainMaster existingUsergroupDomainMaster = _persistence.findByPrimaryKey(newUsergroupDomainMaster.getPrimaryKey());

		Assert.assertEquals(existingUsergroupDomainMaster.getCreatedBy(),
			newUsergroupDomainMaster.getCreatedBy());
		Assert.assertEquals(existingUsergroupDomainMaster.getUsergroupDomainSid(),
			newUsergroupDomainMaster.getUsergroupDomainSid());
		Assert.assertEquals(existingUsergroupDomainMaster.getUsersSid(),
			newUsergroupDomainMaster.getUsersSid());
		Assert.assertEquals(existingUsergroupDomainMaster.getModifiedBy(),
			newUsergroupDomainMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingUsergroupDomainMaster.getCreatedDate()),
			Time.getShortTimestamp(newUsergroupDomainMaster.getCreatedDate()));
		Assert.assertEquals(existingUsergroupDomainMaster.getDomainId(),
			newUsergroupDomainMaster.getDomainId());
		Assert.assertEquals(existingUsergroupDomainMaster.getProcessed(),
			newUsergroupDomainMaster.getProcessed());
		Assert.assertEquals(existingUsergroupDomainMaster.getUsergroupId(),
			newUsergroupDomainMaster.getUsergroupId());
		Assert.assertEquals(existingUsergroupDomainMaster.getVersionNo(),
			newUsergroupDomainMaster.getVersionNo());
		Assert.assertEquals(existingUsergroupDomainMaster.getIsActive(),
			newUsergroupDomainMaster.getIsActive());
		Assert.assertEquals(Time.getShortTimestamp(
				existingUsergroupDomainMaster.getModifiedDate()),
			Time.getShortTimestamp(newUsergroupDomainMaster.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UsergroupDomainMaster newUsergroupDomainMaster = addUsergroupDomainMaster();

		UsergroupDomainMaster existingUsergroupDomainMaster = _persistence.findByPrimaryKey(newUsergroupDomainMaster.getPrimaryKey());

		Assert.assertEquals(existingUsergroupDomainMaster,
			newUsergroupDomainMaster);
	}

	@Test(expected = NoSuchUsergroupDomainMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<UsergroupDomainMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("USERGROUP_DOMAIN_MASTER",
			"createdBy", true, "usergroupDomainSid", true, "usersSid", true,
			"modifiedBy", true, "createdDate", true, "domainId", true,
			"processed", true, "usergroupId", true, "versionNo", true,
			"isActive", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UsergroupDomainMaster newUsergroupDomainMaster = addUsergroupDomainMaster();

		UsergroupDomainMaster existingUsergroupDomainMaster = _persistence.fetchByPrimaryKey(newUsergroupDomainMaster.getPrimaryKey());

		Assert.assertEquals(existingUsergroupDomainMaster,
			newUsergroupDomainMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		UsergroupDomainMaster missingUsergroupDomainMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUsergroupDomainMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		UsergroupDomainMaster newUsergroupDomainMaster1 = addUsergroupDomainMaster();
		UsergroupDomainMaster newUsergroupDomainMaster2 = addUsergroupDomainMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUsergroupDomainMaster1.getPrimaryKey());
		primaryKeys.add(newUsergroupDomainMaster2.getPrimaryKey());

		Map<Serializable, UsergroupDomainMaster> usergroupDomainMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, usergroupDomainMasters.size());
		Assert.assertEquals(newUsergroupDomainMaster1,
			usergroupDomainMasters.get(
				newUsergroupDomainMaster1.getPrimaryKey()));
		Assert.assertEquals(newUsergroupDomainMaster2,
			usergroupDomainMasters.get(
				newUsergroupDomainMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UsergroupDomainMaster> usergroupDomainMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(usergroupDomainMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		UsergroupDomainMaster newUsergroupDomainMaster = addUsergroupDomainMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUsergroupDomainMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UsergroupDomainMaster> usergroupDomainMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, usergroupDomainMasters.size());
		Assert.assertEquals(newUsergroupDomainMaster,
			usergroupDomainMasters.get(newUsergroupDomainMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UsergroupDomainMaster> usergroupDomainMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(usergroupDomainMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		UsergroupDomainMaster newUsergroupDomainMaster = addUsergroupDomainMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUsergroupDomainMaster.getPrimaryKey());

		Map<Serializable, UsergroupDomainMaster> usergroupDomainMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, usergroupDomainMasters.size());
		Assert.assertEquals(newUsergroupDomainMaster,
			usergroupDomainMasters.get(newUsergroupDomainMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UsergroupDomainMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<UsergroupDomainMaster>() {
				@Override
				public void performAction(
					UsergroupDomainMaster usergroupDomainMaster) {
					Assert.assertNotNull(usergroupDomainMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UsergroupDomainMaster newUsergroupDomainMaster = addUsergroupDomainMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupDomainMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("usergroupDomainSid",
				newUsergroupDomainMaster.getUsergroupDomainSid()));

		List<UsergroupDomainMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UsergroupDomainMaster existingUsergroupDomainMaster = result.get(0);

		Assert.assertEquals(existingUsergroupDomainMaster,
			newUsergroupDomainMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupDomainMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("usergroupDomainSid",
				RandomTestUtil.nextInt()));

		List<UsergroupDomainMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UsergroupDomainMaster newUsergroupDomainMaster = addUsergroupDomainMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupDomainMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"usergroupDomainSid"));

		Object newUsergroupDomainSid = newUsergroupDomainMaster.getUsergroupDomainSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("usergroupDomainSid",
				new Object[] { newUsergroupDomainSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUsergroupDomainSid = result.get(0);

		Assert.assertEquals(existingUsergroupDomainSid, newUsergroupDomainSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupDomainMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"usergroupDomainSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("usergroupDomainSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected UsergroupDomainMaster addUsergroupDomainMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		UsergroupDomainMaster usergroupDomainMaster = _persistence.create(pk);

		usergroupDomainMaster.setCreatedBy(RandomTestUtil.nextInt());

		usergroupDomainMaster.setUsersSid(RandomTestUtil.nextInt());

		usergroupDomainMaster.setModifiedBy(RandomTestUtil.nextInt());

		usergroupDomainMaster.setCreatedDate(RandomTestUtil.nextDate());

		usergroupDomainMaster.setDomainId(RandomTestUtil.nextInt());

		usergroupDomainMaster.setProcessed(RandomTestUtil.randomString());

		usergroupDomainMaster.setUsergroupId(RandomTestUtil.nextInt());

		usergroupDomainMaster.setVersionNo(RandomTestUtil.nextInt());

		usergroupDomainMaster.setIsActive(RandomTestUtil.randomString());

		usergroupDomainMaster.setModifiedDate(RandomTestUtil.nextDate());

		_usergroupDomainMasters.add(_persistence.update(usergroupDomainMaster));

		return usergroupDomainMaster;
	}

	private List<UsergroupDomainMaster> _usergroupDomainMasters = new ArrayList<UsergroupDomainMaster>();
	private UsergroupDomainMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}