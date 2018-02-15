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

import com.stpl.app.exception.NoSuchModuleSubmoduleMasterException;
import com.stpl.app.model.ModuleSubmoduleMaster;
import com.stpl.app.service.ModuleSubmoduleMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ModuleSubmoduleMasterPersistence;
import com.stpl.app.service.persistence.ModuleSubmoduleMasterUtil;

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
public class ModuleSubmoduleMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ModuleSubmoduleMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ModuleSubmoduleMaster> iterator = _moduleSubmoduleMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ModuleSubmoduleMaster moduleSubmoduleMaster = _persistence.create(pk);

		Assert.assertNotNull(moduleSubmoduleMaster);

		Assert.assertEquals(moduleSubmoduleMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ModuleSubmoduleMaster newModuleSubmoduleMaster = addModuleSubmoduleMaster();

		_persistence.remove(newModuleSubmoduleMaster);

		ModuleSubmoduleMaster existingModuleSubmoduleMaster = _persistence.fetchByPrimaryKey(newModuleSubmoduleMaster.getPrimaryKey());

		Assert.assertNull(existingModuleSubmoduleMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addModuleSubmoduleMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ModuleSubmoduleMaster newModuleSubmoduleMaster = _persistence.create(pk);

		newModuleSubmoduleMaster.setCreatedDate(RandomTestUtil.nextDate());

		newModuleSubmoduleMaster.setCreatedBy(RandomTestUtil.nextInt());

		newModuleSubmoduleMaster.setCategory(RandomTestUtil.randomString());

		newModuleSubmoduleMaster.setSubmoduleName(RandomTestUtil.randomString());

		newModuleSubmoduleMaster.setModuleName(RandomTestUtil.randomString());

		newModuleSubmoduleMaster.setVersionNo(RandomTestUtil.nextInt());

		newModuleSubmoduleMaster.setModifiedBy(RandomTestUtil.nextInt());

		newModuleSubmoduleMaster.setModifiedDate(RandomTestUtil.nextDate());

		_moduleSubmoduleMasters.add(_persistence.update(
				newModuleSubmoduleMaster));

		ModuleSubmoduleMaster existingModuleSubmoduleMaster = _persistence.findByPrimaryKey(newModuleSubmoduleMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingModuleSubmoduleMaster.getCreatedDate()),
			Time.getShortTimestamp(newModuleSubmoduleMaster.getCreatedDate()));
		Assert.assertEquals(existingModuleSubmoduleMaster.getCreatedBy(),
			newModuleSubmoduleMaster.getCreatedBy());
		Assert.assertEquals(existingModuleSubmoduleMaster.getCategory(),
			newModuleSubmoduleMaster.getCategory());
		Assert.assertEquals(existingModuleSubmoduleMaster.getModuleSubmoduleSid(),
			newModuleSubmoduleMaster.getModuleSubmoduleSid());
		Assert.assertEquals(existingModuleSubmoduleMaster.getSubmoduleName(),
			newModuleSubmoduleMaster.getSubmoduleName());
		Assert.assertEquals(existingModuleSubmoduleMaster.getModuleName(),
			newModuleSubmoduleMaster.getModuleName());
		Assert.assertEquals(existingModuleSubmoduleMaster.getVersionNo(),
			newModuleSubmoduleMaster.getVersionNo());
		Assert.assertEquals(existingModuleSubmoduleMaster.getModifiedBy(),
			newModuleSubmoduleMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingModuleSubmoduleMaster.getModifiedDate()),
			Time.getShortTimestamp(newModuleSubmoduleMaster.getModifiedDate()));
	}

	@Test
	public void testCountByModuleName() throws Exception {
		_persistence.countByModuleName(StringPool.BLANK);

		_persistence.countByModuleName(StringPool.NULL);

		_persistence.countByModuleName((String)null);
	}

	@Test
	public void testCountBySubmoduleName() throws Exception {
		_persistence.countBySubmoduleName(StringPool.BLANK);

		_persistence.countBySubmoduleName(StringPool.NULL);

		_persistence.countBySubmoduleName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ModuleSubmoduleMaster newModuleSubmoduleMaster = addModuleSubmoduleMaster();

		ModuleSubmoduleMaster existingModuleSubmoduleMaster = _persistence.findByPrimaryKey(newModuleSubmoduleMaster.getPrimaryKey());

		Assert.assertEquals(existingModuleSubmoduleMaster,
			newModuleSubmoduleMaster);
	}

	@Test(expected = NoSuchModuleSubmoduleMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ModuleSubmoduleMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MODULE_SUBMODULE_MASTER",
			"createdDate", true, "createdBy", true, "category", true,
			"moduleSubmoduleSid", true, "submoduleName", true, "moduleName",
			true, "versionNo", true, "modifiedBy", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ModuleSubmoduleMaster newModuleSubmoduleMaster = addModuleSubmoduleMaster();

		ModuleSubmoduleMaster existingModuleSubmoduleMaster = _persistence.fetchByPrimaryKey(newModuleSubmoduleMaster.getPrimaryKey());

		Assert.assertEquals(existingModuleSubmoduleMaster,
			newModuleSubmoduleMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ModuleSubmoduleMaster missingModuleSubmoduleMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingModuleSubmoduleMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ModuleSubmoduleMaster newModuleSubmoduleMaster1 = addModuleSubmoduleMaster();
		ModuleSubmoduleMaster newModuleSubmoduleMaster2 = addModuleSubmoduleMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newModuleSubmoduleMaster1.getPrimaryKey());
		primaryKeys.add(newModuleSubmoduleMaster2.getPrimaryKey());

		Map<Serializable, ModuleSubmoduleMaster> moduleSubmoduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, moduleSubmoduleMasters.size());
		Assert.assertEquals(newModuleSubmoduleMaster1,
			moduleSubmoduleMasters.get(
				newModuleSubmoduleMaster1.getPrimaryKey()));
		Assert.assertEquals(newModuleSubmoduleMaster2,
			moduleSubmoduleMasters.get(
				newModuleSubmoduleMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ModuleSubmoduleMaster> moduleSubmoduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(moduleSubmoduleMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ModuleSubmoduleMaster newModuleSubmoduleMaster = addModuleSubmoduleMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newModuleSubmoduleMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ModuleSubmoduleMaster> moduleSubmoduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, moduleSubmoduleMasters.size());
		Assert.assertEquals(newModuleSubmoduleMaster,
			moduleSubmoduleMasters.get(newModuleSubmoduleMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ModuleSubmoduleMaster> moduleSubmoduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(moduleSubmoduleMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ModuleSubmoduleMaster newModuleSubmoduleMaster = addModuleSubmoduleMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newModuleSubmoduleMaster.getPrimaryKey());

		Map<Serializable, ModuleSubmoduleMaster> moduleSubmoduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, moduleSubmoduleMasters.size());
		Assert.assertEquals(newModuleSubmoduleMaster,
			moduleSubmoduleMasters.get(newModuleSubmoduleMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ModuleSubmoduleMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ModuleSubmoduleMaster>() {
				@Override
				public void performAction(
					ModuleSubmoduleMaster moduleSubmoduleMaster) {
					Assert.assertNotNull(moduleSubmoduleMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ModuleSubmoduleMaster newModuleSubmoduleMaster = addModuleSubmoduleMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ModuleSubmoduleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("moduleSubmoduleSid",
				newModuleSubmoduleMaster.getModuleSubmoduleSid()));

		List<ModuleSubmoduleMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ModuleSubmoduleMaster existingModuleSubmoduleMaster = result.get(0);

		Assert.assertEquals(existingModuleSubmoduleMaster,
			newModuleSubmoduleMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ModuleSubmoduleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("moduleSubmoduleSid",
				RandomTestUtil.nextInt()));

		List<ModuleSubmoduleMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ModuleSubmoduleMaster newModuleSubmoduleMaster = addModuleSubmoduleMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ModuleSubmoduleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"moduleSubmoduleSid"));

		Object newModuleSubmoduleSid = newModuleSubmoduleMaster.getModuleSubmoduleSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("moduleSubmoduleSid",
				new Object[] { newModuleSubmoduleSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingModuleSubmoduleSid = result.get(0);

		Assert.assertEquals(existingModuleSubmoduleSid, newModuleSubmoduleSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ModuleSubmoduleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"moduleSubmoduleSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("moduleSubmoduleSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ModuleSubmoduleMaster addModuleSubmoduleMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ModuleSubmoduleMaster moduleSubmoduleMaster = _persistence.create(pk);

		moduleSubmoduleMaster.setCreatedDate(RandomTestUtil.nextDate());

		moduleSubmoduleMaster.setCreatedBy(RandomTestUtil.nextInt());

		moduleSubmoduleMaster.setCategory(RandomTestUtil.randomString());

		moduleSubmoduleMaster.setSubmoduleName(RandomTestUtil.randomString());

		moduleSubmoduleMaster.setModuleName(RandomTestUtil.randomString());

		moduleSubmoduleMaster.setVersionNo(RandomTestUtil.nextInt());

		moduleSubmoduleMaster.setModifiedBy(RandomTestUtil.nextInt());

		moduleSubmoduleMaster.setModifiedDate(RandomTestUtil.nextDate());

		_moduleSubmoduleMasters.add(_persistence.update(moduleSubmoduleMaster));

		return moduleSubmoduleMaster;
	}

	private List<ModuleSubmoduleMaster> _moduleSubmoduleMasters = new ArrayList<ModuleSubmoduleMaster>();
	private ModuleSubmoduleMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}