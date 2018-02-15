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

import com.stpl.app.exception.NoSuchModulePropertiesException;
import com.stpl.app.model.ModuleProperties;
import com.stpl.app.service.ModulePropertiesLocalServiceUtil;
import com.stpl.app.service.persistence.ModulePropertiesPersistence;
import com.stpl.app.service.persistence.ModulePropertiesUtil;

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
public class ModulePropertiesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ModulePropertiesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ModuleProperties> iterator = _modulePropertieses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ModuleProperties moduleProperties = _persistence.create(pk);

		Assert.assertNotNull(moduleProperties);

		Assert.assertEquals(moduleProperties.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ModuleProperties newModuleProperties = addModuleProperties();

		_persistence.remove(newModuleProperties);

		ModuleProperties existingModuleProperties = _persistence.fetchByPrimaryKey(newModuleProperties.getPrimaryKey());

		Assert.assertNull(existingModuleProperties);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addModuleProperties();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ModuleProperties newModuleProperties = _persistence.create(pk);

		newModuleProperties.setCreatedBy(RandomTestUtil.nextInt());

		newModuleProperties.setModuleName(RandomTestUtil.randomString());

		newModuleProperties.setModifiedBy(RandomTestUtil.nextInt());

		newModuleProperties.setCreatedDate(RandomTestUtil.nextDate());

		newModuleProperties.setNullFlag(RandomTestUtil.randomString());

		newModuleProperties.setVersionNo(RandomTestUtil.nextInt());

		newModuleProperties.setModuleSubmoduleSid(RandomTestUtil.nextInt());

		newModuleProperties.setCategoryName(RandomTestUtil.randomString());

		newModuleProperties.setPropertyName(RandomTestUtil.randomString());

		newModuleProperties.setDisplayName(RandomTestUtil.randomString());

		newModuleProperties.setModifiedDate(RandomTestUtil.nextDate());

		_modulePropertieses.add(_persistence.update(newModuleProperties));

		ModuleProperties existingModuleProperties = _persistence.findByPrimaryKey(newModuleProperties.getPrimaryKey());

		Assert.assertEquals(existingModuleProperties.getModulePropertySid(),
			newModuleProperties.getModulePropertySid());
		Assert.assertEquals(existingModuleProperties.getCreatedBy(),
			newModuleProperties.getCreatedBy());
		Assert.assertEquals(existingModuleProperties.getModuleName(),
			newModuleProperties.getModuleName());
		Assert.assertEquals(existingModuleProperties.getModifiedBy(),
			newModuleProperties.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingModuleProperties.getCreatedDate()),
			Time.getShortTimestamp(newModuleProperties.getCreatedDate()));
		Assert.assertEquals(existingModuleProperties.getNullFlag(),
			newModuleProperties.getNullFlag());
		Assert.assertEquals(existingModuleProperties.getVersionNo(),
			newModuleProperties.getVersionNo());
		Assert.assertEquals(existingModuleProperties.getModuleSubmoduleSid(),
			newModuleProperties.getModuleSubmoduleSid());
		Assert.assertEquals(existingModuleProperties.getCategoryName(),
			newModuleProperties.getCategoryName());
		Assert.assertEquals(existingModuleProperties.getPropertyName(),
			newModuleProperties.getPropertyName());
		Assert.assertEquals(existingModuleProperties.getDisplayName(),
			newModuleProperties.getDisplayName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingModuleProperties.getModifiedDate()),
			Time.getShortTimestamp(newModuleProperties.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ModuleProperties newModuleProperties = addModuleProperties();

		ModuleProperties existingModuleProperties = _persistence.findByPrimaryKey(newModuleProperties.getPrimaryKey());

		Assert.assertEquals(existingModuleProperties, newModuleProperties);
	}

	@Test(expected = NoSuchModulePropertiesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ModuleProperties> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MODULE_PROPERTIES",
			"modulePropertySid", true, "createdBy", true, "moduleName", true,
			"modifiedBy", true, "createdDate", true, "nullFlag", true,
			"versionNo", true, "moduleSubmoduleSid", true, "categoryName",
			true, "propertyName", true, "displayName", true, "modifiedDate",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ModuleProperties newModuleProperties = addModuleProperties();

		ModuleProperties existingModuleProperties = _persistence.fetchByPrimaryKey(newModuleProperties.getPrimaryKey());

		Assert.assertEquals(existingModuleProperties, newModuleProperties);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ModuleProperties missingModuleProperties = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingModuleProperties);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ModuleProperties newModuleProperties1 = addModuleProperties();
		ModuleProperties newModuleProperties2 = addModuleProperties();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newModuleProperties1.getPrimaryKey());
		primaryKeys.add(newModuleProperties2.getPrimaryKey());

		Map<Serializable, ModuleProperties> modulePropertieses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, modulePropertieses.size());
		Assert.assertEquals(newModuleProperties1,
			modulePropertieses.get(newModuleProperties1.getPrimaryKey()));
		Assert.assertEquals(newModuleProperties2,
			modulePropertieses.get(newModuleProperties2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ModuleProperties> modulePropertieses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(modulePropertieses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ModuleProperties newModuleProperties = addModuleProperties();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newModuleProperties.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ModuleProperties> modulePropertieses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, modulePropertieses.size());
		Assert.assertEquals(newModuleProperties,
			modulePropertieses.get(newModuleProperties.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ModuleProperties> modulePropertieses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(modulePropertieses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ModuleProperties newModuleProperties = addModuleProperties();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newModuleProperties.getPrimaryKey());

		Map<Serializable, ModuleProperties> modulePropertieses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, modulePropertieses.size());
		Assert.assertEquals(newModuleProperties,
			modulePropertieses.get(newModuleProperties.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ModulePropertiesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ModuleProperties>() {
				@Override
				public void performAction(ModuleProperties moduleProperties) {
					Assert.assertNotNull(moduleProperties);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ModuleProperties newModuleProperties = addModuleProperties();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ModuleProperties.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("modulePropertySid",
				newModuleProperties.getModulePropertySid()));

		List<ModuleProperties> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ModuleProperties existingModuleProperties = result.get(0);

		Assert.assertEquals(existingModuleProperties, newModuleProperties);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ModuleProperties.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("modulePropertySid",
				RandomTestUtil.nextInt()));

		List<ModuleProperties> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ModuleProperties newModuleProperties = addModuleProperties();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ModuleProperties.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"modulePropertySid"));

		Object newModulePropertySid = newModuleProperties.getModulePropertySid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("modulePropertySid",
				new Object[] { newModulePropertySid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingModulePropertySid = result.get(0);

		Assert.assertEquals(existingModulePropertySid, newModulePropertySid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ModuleProperties.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"modulePropertySid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("modulePropertySid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ModuleProperties addModuleProperties() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ModuleProperties moduleProperties = _persistence.create(pk);

		moduleProperties.setCreatedBy(RandomTestUtil.nextInt());

		moduleProperties.setModuleName(RandomTestUtil.randomString());

		moduleProperties.setModifiedBy(RandomTestUtil.nextInt());

		moduleProperties.setCreatedDate(RandomTestUtil.nextDate());

		moduleProperties.setNullFlag(RandomTestUtil.randomString());

		moduleProperties.setVersionNo(RandomTestUtil.nextInt());

		moduleProperties.setModuleSubmoduleSid(RandomTestUtil.nextInt());

		moduleProperties.setCategoryName(RandomTestUtil.randomString());

		moduleProperties.setPropertyName(RandomTestUtil.randomString());

		moduleProperties.setDisplayName(RandomTestUtil.randomString());

		moduleProperties.setModifiedDate(RandomTestUtil.nextDate());

		_modulePropertieses.add(_persistence.update(moduleProperties));

		return moduleProperties;
	}

	private List<ModuleProperties> _modulePropertieses = new ArrayList<ModuleProperties>();
	private ModulePropertiesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}