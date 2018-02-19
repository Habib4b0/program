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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchProjectionProdHierarchyException;
import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.app.service.ProjectionProdHierarchyLocalServiceUtil;
import com.stpl.app.service.persistence.ProjectionProdHierarchyPersistence;
import com.stpl.app.service.persistence.ProjectionProdHierarchyUtil;

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
public class ProjectionProdHierarchyPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ProjectionProdHierarchyUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ProjectionProdHierarchy> iterator = _projectionProdHierarchies.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionProdHierarchy projectionProdHierarchy = _persistence.create(pk);

		Assert.assertNotNull(projectionProdHierarchy);

		Assert.assertEquals(projectionProdHierarchy.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ProjectionProdHierarchy newProjectionProdHierarchy = addProjectionProdHierarchy();

		_persistence.remove(newProjectionProdHierarchy);

		ProjectionProdHierarchy existingProjectionProdHierarchy = _persistence.fetchByPrimaryKey(newProjectionProdHierarchy.getPrimaryKey());

		Assert.assertNull(existingProjectionProdHierarchy);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProjectionProdHierarchy();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionProdHierarchy newProjectionProdHierarchy = _persistence.create(pk);

		newProjectionProdHierarchy.setProjectionMasterSid(RandomTestUtil.nextInt());

		newProjectionProdHierarchy.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_projectionProdHierarchies.add(_persistence.update(
				newProjectionProdHierarchy));

		ProjectionProdHierarchy existingProjectionProdHierarchy = _persistence.findByPrimaryKey(newProjectionProdHierarchy.getPrimaryKey());

		Assert.assertEquals(existingProjectionProdHierarchy.getProjectionMasterSid(),
			newProjectionProdHierarchy.getProjectionMasterSid());
		Assert.assertEquals(existingProjectionProdHierarchy.getProjectionProdHierarchySid(),
			newProjectionProdHierarchy.getProjectionProdHierarchySid());
		Assert.assertEquals(existingProjectionProdHierarchy.getRelationshipLevelSid(),
			newProjectionProdHierarchy.getRelationshipLevelSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ProjectionProdHierarchy newProjectionProdHierarchy = addProjectionProdHierarchy();

		ProjectionProdHierarchy existingProjectionProdHierarchy = _persistence.findByPrimaryKey(newProjectionProdHierarchy.getPrimaryKey());

		Assert.assertEquals(existingProjectionProdHierarchy,
			newProjectionProdHierarchy);
	}

	@Test(expected = NoSuchProjectionProdHierarchyException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ProjectionProdHierarchy> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PROJECTION_PROD_HIERARCHY",
			"projectionMasterSid", true, "projectionProdHierarchySid", true,
			"relationshipLevelSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ProjectionProdHierarchy newProjectionProdHierarchy = addProjectionProdHierarchy();

		ProjectionProdHierarchy existingProjectionProdHierarchy = _persistence.fetchByPrimaryKey(newProjectionProdHierarchy.getPrimaryKey());

		Assert.assertEquals(existingProjectionProdHierarchy,
			newProjectionProdHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionProdHierarchy missingProjectionProdHierarchy = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProjectionProdHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ProjectionProdHierarchy newProjectionProdHierarchy1 = addProjectionProdHierarchy();
		ProjectionProdHierarchy newProjectionProdHierarchy2 = addProjectionProdHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionProdHierarchy1.getPrimaryKey());
		primaryKeys.add(newProjectionProdHierarchy2.getPrimaryKey());

		Map<Serializable, ProjectionProdHierarchy> projectionProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, projectionProdHierarchies.size());
		Assert.assertEquals(newProjectionProdHierarchy1,
			projectionProdHierarchies.get(
				newProjectionProdHierarchy1.getPrimaryKey()));
		Assert.assertEquals(newProjectionProdHierarchy2,
			projectionProdHierarchies.get(
				newProjectionProdHierarchy2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ProjectionProdHierarchy> projectionProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionProdHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ProjectionProdHierarchy newProjectionProdHierarchy = addProjectionProdHierarchy();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionProdHierarchy.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ProjectionProdHierarchy> projectionProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionProdHierarchies.size());
		Assert.assertEquals(newProjectionProdHierarchy,
			projectionProdHierarchies.get(
				newProjectionProdHierarchy.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ProjectionProdHierarchy> projectionProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionProdHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ProjectionProdHierarchy newProjectionProdHierarchy = addProjectionProdHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionProdHierarchy.getPrimaryKey());

		Map<Serializable, ProjectionProdHierarchy> projectionProdHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionProdHierarchies.size());
		Assert.assertEquals(newProjectionProdHierarchy,
			projectionProdHierarchies.get(
				newProjectionProdHierarchy.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProjectionProdHierarchyLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProjectionProdHierarchy>() {
				@Override
				public void performAction(
					ProjectionProdHierarchy projectionProdHierarchy) {
					Assert.assertNotNull(projectionProdHierarchy);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ProjectionProdHierarchy newProjectionProdHierarchy = addProjectionProdHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"projectionProdHierarchySid",
				newProjectionProdHierarchy.getProjectionProdHierarchySid()));

		List<ProjectionProdHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ProjectionProdHierarchy existingProjectionProdHierarchy = result.get(0);

		Assert.assertEquals(existingProjectionProdHierarchy,
			newProjectionProdHierarchy);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"projectionProdHierarchySid", RandomTestUtil.nextInt()));

		List<ProjectionProdHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ProjectionProdHierarchy newProjectionProdHierarchy = addProjectionProdHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionProdHierarchySid"));

		Object newProjectionProdHierarchySid = newProjectionProdHierarchy.getProjectionProdHierarchySid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"projectionProdHierarchySid",
				new Object[] { newProjectionProdHierarchySid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionProdHierarchySid = result.get(0);

		Assert.assertEquals(existingProjectionProdHierarchySid,
			newProjectionProdHierarchySid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionProdHierarchySid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"projectionProdHierarchySid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ProjectionProdHierarchy addProjectionProdHierarchy()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionProdHierarchy projectionProdHierarchy = _persistence.create(pk);

		projectionProdHierarchy.setProjectionMasterSid(RandomTestUtil.nextInt());

		projectionProdHierarchy.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_projectionProdHierarchies.add(_persistence.update(
				projectionProdHierarchy));

		return projectionProdHierarchy;
	}

	private List<ProjectionProdHierarchy> _projectionProdHierarchies = new ArrayList<ProjectionProdHierarchy>();
	private ProjectionProdHierarchyPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}