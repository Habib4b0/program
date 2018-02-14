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

import com.stpl.app.exception.NoSuchBusinessroleModuleException;
import com.stpl.app.model.BusinessroleModule;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.persistence.BusinessroleModulePersistence;
import com.stpl.app.service.persistence.BusinessroleModuleUtil;

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
public class BusinessroleModulePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = BusinessroleModuleUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<BusinessroleModule> iterator = _businessroleModules.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BusinessroleModule businessroleModule = _persistence.create(pk);

		Assert.assertNotNull(businessroleModule);

		Assert.assertEquals(businessroleModule.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		BusinessroleModule newBusinessroleModule = addBusinessroleModule();

		_persistence.remove(newBusinessroleModule);

		BusinessroleModule existingBusinessroleModule = _persistence.fetchByPrimaryKey(newBusinessroleModule.getPrimaryKey());

		Assert.assertNull(existingBusinessroleModule);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBusinessroleModule();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BusinessroleModule newBusinessroleModule = _persistence.create(pk);

		newBusinessroleModule.setCreatedBy(RandomTestUtil.nextInt());

		newBusinessroleModule.setBusinessroleMasterSid(RandomTestUtil.nextInt());

		newBusinessroleModule.setAddFlag(RandomTestUtil.randomString());

		newBusinessroleModule.setViewFlag(RandomTestUtil.randomString());

		newBusinessroleModule.setModifiedBy(RandomTestUtil.nextInt());

		newBusinessroleModule.setCreatedDate(RandomTestUtil.nextDate());

		newBusinessroleModule.setSubmodulePropertyId(RandomTestUtil.nextInt());

		newBusinessroleModule.setEditFlag(RandomTestUtil.randomString());

		newBusinessroleModule.setVersionNo(RandomTestUtil.nextInt());

		newBusinessroleModule.setAccessModule(RandomTestUtil.randomString());

		newBusinessroleModule.setModifiedDate(RandomTestUtil.nextDate());

		_businessroleModules.add(_persistence.update(newBusinessroleModule));

		BusinessroleModule existingBusinessroleModule = _persistence.findByPrimaryKey(newBusinessroleModule.getPrimaryKey());

		Assert.assertEquals(existingBusinessroleModule.getCreatedBy(),
			newBusinessroleModule.getCreatedBy());
		Assert.assertEquals(existingBusinessroleModule.getBusinessroleModuleSid(),
			newBusinessroleModule.getBusinessroleModuleSid());
		Assert.assertEquals(existingBusinessroleModule.getBusinessroleMasterSid(),
			newBusinessroleModule.getBusinessroleMasterSid());
		Assert.assertEquals(existingBusinessroleModule.getAddFlag(),
			newBusinessroleModule.getAddFlag());
		Assert.assertEquals(existingBusinessroleModule.getViewFlag(),
			newBusinessroleModule.getViewFlag());
		Assert.assertEquals(existingBusinessroleModule.getModifiedBy(),
			newBusinessroleModule.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBusinessroleModule.getCreatedDate()),
			Time.getShortTimestamp(newBusinessroleModule.getCreatedDate()));
		Assert.assertEquals(existingBusinessroleModule.getSubmodulePropertyId(),
			newBusinessroleModule.getSubmodulePropertyId());
		Assert.assertEquals(existingBusinessroleModule.getEditFlag(),
			newBusinessroleModule.getEditFlag());
		Assert.assertEquals(existingBusinessroleModule.getVersionNo(),
			newBusinessroleModule.getVersionNo());
		Assert.assertEquals(existingBusinessroleModule.getAccessModule(),
			newBusinessroleModule.getAccessModule());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBusinessroleModule.getModifiedDate()),
			Time.getShortTimestamp(newBusinessroleModule.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		BusinessroleModule newBusinessroleModule = addBusinessroleModule();

		BusinessroleModule existingBusinessroleModule = _persistence.findByPrimaryKey(newBusinessroleModule.getPrimaryKey());

		Assert.assertEquals(existingBusinessroleModule, newBusinessroleModule);
	}

	@Test(expected = NoSuchBusinessroleModuleException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<BusinessroleModule> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("BUSINESSROLE_MODULE",
			"createdBy", true, "businessroleModuleSid", true,
			"businessroleMasterSid", true, "addFlag", true, "viewFlag", true,
			"modifiedBy", true, "createdDate", true, "submodulePropertyId",
			true, "editFlag", true, "versionNo", true, "accessModule", true,
			"modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		BusinessroleModule newBusinessroleModule = addBusinessroleModule();

		BusinessroleModule existingBusinessroleModule = _persistence.fetchByPrimaryKey(newBusinessroleModule.getPrimaryKey());

		Assert.assertEquals(existingBusinessroleModule, newBusinessroleModule);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BusinessroleModule missingBusinessroleModule = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBusinessroleModule);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		BusinessroleModule newBusinessroleModule1 = addBusinessroleModule();
		BusinessroleModule newBusinessroleModule2 = addBusinessroleModule();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBusinessroleModule1.getPrimaryKey());
		primaryKeys.add(newBusinessroleModule2.getPrimaryKey());

		Map<Serializable, BusinessroleModule> businessroleModules = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, businessroleModules.size());
		Assert.assertEquals(newBusinessroleModule1,
			businessroleModules.get(newBusinessroleModule1.getPrimaryKey()));
		Assert.assertEquals(newBusinessroleModule2,
			businessroleModules.get(newBusinessroleModule2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, BusinessroleModule> businessroleModules = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(businessroleModules.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		BusinessroleModule newBusinessroleModule = addBusinessroleModule();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBusinessroleModule.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, BusinessroleModule> businessroleModules = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, businessroleModules.size());
		Assert.assertEquals(newBusinessroleModule,
			businessroleModules.get(newBusinessroleModule.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, BusinessroleModule> businessroleModules = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(businessroleModules.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		BusinessroleModule newBusinessroleModule = addBusinessroleModule();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBusinessroleModule.getPrimaryKey());

		Map<Serializable, BusinessroleModule> businessroleModules = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, businessroleModules.size());
		Assert.assertEquals(newBusinessroleModule,
			businessroleModules.get(newBusinessroleModule.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = BusinessroleModuleLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<BusinessroleModule>() {
				@Override
				public void performAction(BusinessroleModule businessroleModule) {
					Assert.assertNotNull(businessroleModule);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		BusinessroleModule newBusinessroleModule = addBusinessroleModule();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BusinessroleModule.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("businessroleModuleSid",
				newBusinessroleModule.getBusinessroleModuleSid()));

		List<BusinessroleModule> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		BusinessroleModule existingBusinessroleModule = result.get(0);

		Assert.assertEquals(existingBusinessroleModule, newBusinessroleModule);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BusinessroleModule.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("businessroleModuleSid",
				RandomTestUtil.nextInt()));

		List<BusinessroleModule> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		BusinessroleModule newBusinessroleModule = addBusinessroleModule();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BusinessroleModule.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"businessroleModuleSid"));

		Object newBusinessroleModuleSid = newBusinessroleModule.getBusinessroleModuleSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("businessroleModuleSid",
				new Object[] { newBusinessroleModuleSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingBusinessroleModuleSid = result.get(0);

		Assert.assertEquals(existingBusinessroleModuleSid,
			newBusinessroleModuleSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BusinessroleModule.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"businessroleModuleSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("businessroleModuleSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected BusinessroleModule addBusinessroleModule()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		BusinessroleModule businessroleModule = _persistence.create(pk);

		businessroleModule.setCreatedBy(RandomTestUtil.nextInt());

		businessroleModule.setBusinessroleMasterSid(RandomTestUtil.nextInt());

		businessroleModule.setAddFlag(RandomTestUtil.randomString());

		businessroleModule.setViewFlag(RandomTestUtil.randomString());

		businessroleModule.setModifiedBy(RandomTestUtil.nextInt());

		businessroleModule.setCreatedDate(RandomTestUtil.nextDate());

		businessroleModule.setSubmodulePropertyId(RandomTestUtil.nextInt());

		businessroleModule.setEditFlag(RandomTestUtil.randomString());

		businessroleModule.setVersionNo(RandomTestUtil.nextInt());

		businessroleModule.setAccessModule(RandomTestUtil.randomString());

		businessroleModule.setModifiedDate(RandomTestUtil.nextDate());

		_businessroleModules.add(_persistence.update(businessroleModule));

		return businessroleModule;
	}

	private List<BusinessroleModule> _businessroleModules = new ArrayList<BusinessroleModule>();
	private BusinessroleModulePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}