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

import com.stpl.app.exception.NoSuchProjectionNameConfigException;
import com.stpl.app.model.ProjectionNameConfig;
import com.stpl.app.service.ProjectionNameConfigLocalServiceUtil;
import com.stpl.app.service.persistence.ProjectionNameConfigPersistence;
import com.stpl.app.service.persistence.ProjectionNameConfigUtil;

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
public class ProjectionNameConfigPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ProjectionNameConfigUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ProjectionNameConfig> iterator = _projectionNameConfigs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionNameConfig projectionNameConfig = _persistence.create(pk);

		Assert.assertNotNull(projectionNameConfig);

		Assert.assertEquals(projectionNameConfig.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ProjectionNameConfig newProjectionNameConfig = addProjectionNameConfig();

		_persistence.remove(newProjectionNameConfig);

		ProjectionNameConfig existingProjectionNameConfig = _persistence.fetchByPrimaryKey(newProjectionNameConfig.getPrimaryKey());

		Assert.assertNull(existingProjectionNameConfig);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProjectionNameConfig();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionNameConfig newProjectionNameConfig = _persistence.create(pk);

		newProjectionNameConfig.setCreatedDate(RandomTestUtil.nextDate());

		newProjectionNameConfig.setCreatedBy(RandomTestUtil.nextInt());

		newProjectionNameConfig.setBusinessProcessType(RandomTestUtil.randomString());

		newProjectionNameConfig.setVersionNo(RandomTestUtil.nextInt());

		newProjectionNameConfig.setModifiedBy(RandomTestUtil.nextInt());

		newProjectionNameConfig.setModifiedDate(RandomTestUtil.nextDate());

		newProjectionNameConfig.setSelectedAttributes(RandomTestUtil.randomString());

		_projectionNameConfigs.add(_persistence.update(newProjectionNameConfig));

		ProjectionNameConfig existingProjectionNameConfig = _persistence.findByPrimaryKey(newProjectionNameConfig.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingProjectionNameConfig.getCreatedDate()),
			Time.getShortTimestamp(newProjectionNameConfig.getCreatedDate()));
		Assert.assertEquals(existingProjectionNameConfig.getCreatedBy(),
			newProjectionNameConfig.getCreatedBy());
		Assert.assertEquals(existingProjectionNameConfig.getBusinessProcessType(),
			newProjectionNameConfig.getBusinessProcessType());
		Assert.assertEquals(existingProjectionNameConfig.getVersionNo(),
			newProjectionNameConfig.getVersionNo());
		Assert.assertEquals(existingProjectionNameConfig.getModifiedBy(),
			newProjectionNameConfig.getModifiedBy());
		Assert.assertEquals(existingProjectionNameConfig.getProjectionNameConfigSid(),
			newProjectionNameConfig.getProjectionNameConfigSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingProjectionNameConfig.getModifiedDate()),
			Time.getShortTimestamp(newProjectionNameConfig.getModifiedDate()));
		Assert.assertEquals(existingProjectionNameConfig.getSelectedAttributes(),
			newProjectionNameConfig.getSelectedAttributes());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ProjectionNameConfig newProjectionNameConfig = addProjectionNameConfig();

		ProjectionNameConfig existingProjectionNameConfig = _persistence.findByPrimaryKey(newProjectionNameConfig.getPrimaryKey());

		Assert.assertEquals(existingProjectionNameConfig,
			newProjectionNameConfig);
	}

	@Test(expected = NoSuchProjectionNameConfigException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ProjectionNameConfig> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PROJECTION_NAME_CONFIG",
			"createdDate", true, "createdBy", true, "businessProcessType",
			true, "versionNo", true, "modifiedBy", true,
			"projectionNameConfigSid", true, "modifiedDate", true,
			"selectedAttributes", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ProjectionNameConfig newProjectionNameConfig = addProjectionNameConfig();

		ProjectionNameConfig existingProjectionNameConfig = _persistence.fetchByPrimaryKey(newProjectionNameConfig.getPrimaryKey());

		Assert.assertEquals(existingProjectionNameConfig,
			newProjectionNameConfig);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionNameConfig missingProjectionNameConfig = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProjectionNameConfig);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ProjectionNameConfig newProjectionNameConfig1 = addProjectionNameConfig();
		ProjectionNameConfig newProjectionNameConfig2 = addProjectionNameConfig();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionNameConfig1.getPrimaryKey());
		primaryKeys.add(newProjectionNameConfig2.getPrimaryKey());

		Map<Serializable, ProjectionNameConfig> projectionNameConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, projectionNameConfigs.size());
		Assert.assertEquals(newProjectionNameConfig1,
			projectionNameConfigs.get(newProjectionNameConfig1.getPrimaryKey()));
		Assert.assertEquals(newProjectionNameConfig2,
			projectionNameConfigs.get(newProjectionNameConfig2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ProjectionNameConfig> projectionNameConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionNameConfigs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ProjectionNameConfig newProjectionNameConfig = addProjectionNameConfig();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionNameConfig.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ProjectionNameConfig> projectionNameConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionNameConfigs.size());
		Assert.assertEquals(newProjectionNameConfig,
			projectionNameConfigs.get(newProjectionNameConfig.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ProjectionNameConfig> projectionNameConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionNameConfigs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ProjectionNameConfig newProjectionNameConfig = addProjectionNameConfig();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionNameConfig.getPrimaryKey());

		Map<Serializable, ProjectionNameConfig> projectionNameConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionNameConfigs.size());
		Assert.assertEquals(newProjectionNameConfig,
			projectionNameConfigs.get(newProjectionNameConfig.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProjectionNameConfigLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProjectionNameConfig>() {
				@Override
				public void performAction(
					ProjectionNameConfig projectionNameConfig) {
					Assert.assertNotNull(projectionNameConfig);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ProjectionNameConfig newProjectionNameConfig = addProjectionNameConfig();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionNameConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionNameConfigSid",
				newProjectionNameConfig.getProjectionNameConfigSid()));

		List<ProjectionNameConfig> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ProjectionNameConfig existingProjectionNameConfig = result.get(0);

		Assert.assertEquals(existingProjectionNameConfig,
			newProjectionNameConfig);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionNameConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionNameConfigSid",
				RandomTestUtil.nextInt()));

		List<ProjectionNameConfig> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ProjectionNameConfig newProjectionNameConfig = addProjectionNameConfig();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionNameConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionNameConfigSid"));

		Object newProjectionNameConfigSid = newProjectionNameConfig.getProjectionNameConfigSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionNameConfigSid",
				new Object[] { newProjectionNameConfigSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionNameConfigSid = result.get(0);

		Assert.assertEquals(existingProjectionNameConfigSid,
			newProjectionNameConfigSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionNameConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionNameConfigSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionNameConfigSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ProjectionNameConfig addProjectionNameConfig()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionNameConfig projectionNameConfig = _persistence.create(pk);

		projectionNameConfig.setCreatedDate(RandomTestUtil.nextDate());

		projectionNameConfig.setCreatedBy(RandomTestUtil.nextInt());

		projectionNameConfig.setBusinessProcessType(RandomTestUtil.randomString());

		projectionNameConfig.setVersionNo(RandomTestUtil.nextInt());

		projectionNameConfig.setModifiedBy(RandomTestUtil.nextInt());

		projectionNameConfig.setModifiedDate(RandomTestUtil.nextDate());

		projectionNameConfig.setSelectedAttributes(RandomTestUtil.randomString());

		_projectionNameConfigs.add(_persistence.update(projectionNameConfig));

		return projectionNameConfig;
	}

	private List<ProjectionNameConfig> _projectionNameConfigs = new ArrayList<ProjectionNameConfig>();
	private ProjectionNameConfigPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}