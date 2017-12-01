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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchBusinessroleMasterException;
import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
import com.stpl.app.service.persistence.BusinessroleMasterPersistence;
import com.stpl.app.service.persistence.BusinessroleMasterUtil;

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
public class BusinessroleMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = BusinessroleMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<BusinessroleMaster> iterator = _businessroleMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BusinessroleMaster businessroleMaster = _persistence.create(pk);

		Assert.assertNotNull(businessroleMaster);

		Assert.assertEquals(businessroleMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		BusinessroleMaster newBusinessroleMaster = addBusinessroleMaster();

		_persistence.remove(newBusinessroleMaster);

		BusinessroleMaster existingBusinessroleMaster = _persistence.fetchByPrimaryKey(newBusinessroleMaster.getPrimaryKey());

		Assert.assertNull(existingBusinessroleMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBusinessroleMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BusinessroleMaster newBusinessroleMaster = _persistence.create(pk);

		newBusinessroleMaster.setCreatedDate(RandomTestUtil.nextDate());

		newBusinessroleMaster.setCreatedBy(RandomTestUtil.nextInt());

		newBusinessroleMaster.setUsersSid(RandomTestUtil.nextInt());

		newBusinessroleMaster.setHierarchyLevel(RandomTestUtil.nextInt());

		newBusinessroleMaster.setProcessed(RandomTestUtil.randomString());

		newBusinessroleMaster.setBusinessroleName(RandomTestUtil.randomString());

		newBusinessroleMaster.setVersionNo(RandomTestUtil.nextInt());

		newBusinessroleMaster.setModifiedBy(RandomTestUtil.nextInt());

		newBusinessroleMaster.setModifiedDate(RandomTestUtil.nextDate());

		newBusinessroleMaster.setBusinessroleDesc(RandomTestUtil.randomString());

		newBusinessroleMaster.setIsActive(RandomTestUtil.randomString());

		_businessroleMasters.add(_persistence.update(newBusinessroleMaster));

		BusinessroleMaster existingBusinessroleMaster = _persistence.findByPrimaryKey(newBusinessroleMaster.getPrimaryKey());

		Assert.assertEquals(existingBusinessroleMaster.getBusinessroleMasterSid(),
			newBusinessroleMaster.getBusinessroleMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBusinessroleMaster.getCreatedDate()),
			Time.getShortTimestamp(newBusinessroleMaster.getCreatedDate()));
		Assert.assertEquals(existingBusinessroleMaster.getCreatedBy(),
			newBusinessroleMaster.getCreatedBy());
		Assert.assertEquals(existingBusinessroleMaster.getUsersSid(),
			newBusinessroleMaster.getUsersSid());
		Assert.assertEquals(existingBusinessroleMaster.getHierarchyLevel(),
			newBusinessroleMaster.getHierarchyLevel());
		Assert.assertEquals(existingBusinessroleMaster.getProcessed(),
			newBusinessroleMaster.getProcessed());
		Assert.assertEquals(existingBusinessroleMaster.getBusinessroleName(),
			newBusinessroleMaster.getBusinessroleName());
		Assert.assertEquals(existingBusinessroleMaster.getVersionNo(),
			newBusinessroleMaster.getVersionNo());
		Assert.assertEquals(existingBusinessroleMaster.getModifiedBy(),
			newBusinessroleMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBusinessroleMaster.getModifiedDate()),
			Time.getShortTimestamp(newBusinessroleMaster.getModifiedDate()));
		Assert.assertEquals(existingBusinessroleMaster.getBusinessroleDesc(),
			newBusinessroleMaster.getBusinessroleDesc());
		Assert.assertEquals(existingBusinessroleMaster.getIsActive(),
			newBusinessroleMaster.getIsActive());
	}

	@Test
	public void testCountByBusinessroleName() throws Exception {
		_persistence.countByBusinessroleName(StringPool.BLANK);

		_persistence.countByBusinessroleName(StringPool.NULL);

		_persistence.countByBusinessroleName((String)null);
	}

	@Test
	public void testCountByBusinessRoleUnique() throws Exception {
		_persistence.countByBusinessRoleUnique(StringPool.BLANK);

		_persistence.countByBusinessRoleUnique(StringPool.NULL);

		_persistence.countByBusinessRoleUnique((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		BusinessroleMaster newBusinessroleMaster = addBusinessroleMaster();

		BusinessroleMaster existingBusinessroleMaster = _persistence.findByPrimaryKey(newBusinessroleMaster.getPrimaryKey());

		Assert.assertEquals(existingBusinessroleMaster, newBusinessroleMaster);
	}

	@Test(expected = NoSuchBusinessroleMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<BusinessroleMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("BUSINESSROLE_MASTER",
			"businessroleMasterSid", true, "createdDate", true, "createdBy",
			true, "usersSid", true, "hierarchyLevel", true, "processed", true,
			"businessroleName", true, "versionNo", true, "modifiedBy", true,
			"modifiedDate", true, "businessroleDesc", true, "isActive", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		BusinessroleMaster newBusinessroleMaster = addBusinessroleMaster();

		BusinessroleMaster existingBusinessroleMaster = _persistence.fetchByPrimaryKey(newBusinessroleMaster.getPrimaryKey());

		Assert.assertEquals(existingBusinessroleMaster, newBusinessroleMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BusinessroleMaster missingBusinessroleMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBusinessroleMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		BusinessroleMaster newBusinessroleMaster1 = addBusinessroleMaster();
		BusinessroleMaster newBusinessroleMaster2 = addBusinessroleMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBusinessroleMaster1.getPrimaryKey());
		primaryKeys.add(newBusinessroleMaster2.getPrimaryKey());

		Map<Serializable, BusinessroleMaster> businessroleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, businessroleMasters.size());
		Assert.assertEquals(newBusinessroleMaster1,
			businessroleMasters.get(newBusinessroleMaster1.getPrimaryKey()));
		Assert.assertEquals(newBusinessroleMaster2,
			businessroleMasters.get(newBusinessroleMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, BusinessroleMaster> businessroleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(businessroleMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		BusinessroleMaster newBusinessroleMaster = addBusinessroleMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBusinessroleMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, BusinessroleMaster> businessroleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, businessroleMasters.size());
		Assert.assertEquals(newBusinessroleMaster,
			businessroleMasters.get(newBusinessroleMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, BusinessroleMaster> businessroleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(businessroleMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		BusinessroleMaster newBusinessroleMaster = addBusinessroleMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBusinessroleMaster.getPrimaryKey());

		Map<Serializable, BusinessroleMaster> businessroleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, businessroleMasters.size());
		Assert.assertEquals(newBusinessroleMaster,
			businessroleMasters.get(newBusinessroleMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = BusinessroleMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<BusinessroleMaster>() {
				@Override
				public void performAction(BusinessroleMaster businessroleMaster) {
					Assert.assertNotNull(businessroleMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		BusinessroleMaster newBusinessroleMaster = addBusinessroleMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BusinessroleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("businessroleMasterSid",
				newBusinessroleMaster.getBusinessroleMasterSid()));

		List<BusinessroleMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		BusinessroleMaster existingBusinessroleMaster = result.get(0);

		Assert.assertEquals(existingBusinessroleMaster, newBusinessroleMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BusinessroleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("businessroleMasterSid",
				RandomTestUtil.nextInt()));

		List<BusinessroleMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		BusinessroleMaster newBusinessroleMaster = addBusinessroleMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BusinessroleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"businessroleMasterSid"));

		Object newBusinessroleMasterSid = newBusinessroleMaster.getBusinessroleMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("businessroleMasterSid",
				new Object[] { newBusinessroleMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingBusinessroleMasterSid = result.get(0);

		Assert.assertEquals(existingBusinessroleMasterSid,
			newBusinessroleMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BusinessroleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"businessroleMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("businessroleMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected BusinessroleMaster addBusinessroleMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		BusinessroleMaster businessroleMaster = _persistence.create(pk);

		businessroleMaster.setCreatedDate(RandomTestUtil.nextDate());

		businessroleMaster.setCreatedBy(RandomTestUtil.nextInt());

		businessroleMaster.setUsersSid(RandomTestUtil.nextInt());

		businessroleMaster.setHierarchyLevel(RandomTestUtil.nextInt());

		businessroleMaster.setProcessed(RandomTestUtil.randomString());

		businessroleMaster.setBusinessroleName(RandomTestUtil.randomString());

		businessroleMaster.setVersionNo(RandomTestUtil.nextInt());

		businessroleMaster.setModifiedBy(RandomTestUtil.nextInt());

		businessroleMaster.setModifiedDate(RandomTestUtil.nextDate());

		businessroleMaster.setBusinessroleDesc(RandomTestUtil.randomString());

		businessroleMaster.setIsActive(RandomTestUtil.randomString());

		_businessroleMasters.add(_persistence.update(businessroleMaster));

		return businessroleMaster;
	}

	private List<BusinessroleMaster> _businessroleMasters = new ArrayList<BusinessroleMaster>();
	private BusinessroleMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}