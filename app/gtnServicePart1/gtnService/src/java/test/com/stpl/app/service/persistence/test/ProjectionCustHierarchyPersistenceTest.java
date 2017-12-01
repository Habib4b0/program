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

import com.stpl.app.exception.NoSuchProjectionCustHierarchyException;
import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;
import com.stpl.app.service.persistence.ProjectionCustHierarchyPersistence;
import com.stpl.app.service.persistence.ProjectionCustHierarchyUtil;

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
public class ProjectionCustHierarchyPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ProjectionCustHierarchyUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ProjectionCustHierarchy> iterator = _projectionCustHierarchies.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionCustHierarchy projectionCustHierarchy = _persistence.create(pk);

		Assert.assertNotNull(projectionCustHierarchy);

		Assert.assertEquals(projectionCustHierarchy.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ProjectionCustHierarchy newProjectionCustHierarchy = addProjectionCustHierarchy();

		_persistence.remove(newProjectionCustHierarchy);

		ProjectionCustHierarchy existingProjectionCustHierarchy = _persistence.fetchByPrimaryKey(newProjectionCustHierarchy.getPrimaryKey());

		Assert.assertNull(existingProjectionCustHierarchy);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProjectionCustHierarchy();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionCustHierarchy newProjectionCustHierarchy = _persistence.create(pk);

		newProjectionCustHierarchy.setProjectionMasterSid(RandomTestUtil.nextInt());

		newProjectionCustHierarchy.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_projectionCustHierarchies.add(_persistence.update(
				newProjectionCustHierarchy));

		ProjectionCustHierarchy existingProjectionCustHierarchy = _persistence.findByPrimaryKey(newProjectionCustHierarchy.getPrimaryKey());

		Assert.assertEquals(existingProjectionCustHierarchy.getProjectionMasterSid(),
			newProjectionCustHierarchy.getProjectionMasterSid());
		Assert.assertEquals(existingProjectionCustHierarchy.getProjectionCustHierarchySid(),
			newProjectionCustHierarchy.getProjectionCustHierarchySid());
		Assert.assertEquals(existingProjectionCustHierarchy.getRelationshipLevelSid(),
			newProjectionCustHierarchy.getRelationshipLevelSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ProjectionCustHierarchy newProjectionCustHierarchy = addProjectionCustHierarchy();

		ProjectionCustHierarchy existingProjectionCustHierarchy = _persistence.findByPrimaryKey(newProjectionCustHierarchy.getPrimaryKey());

		Assert.assertEquals(existingProjectionCustHierarchy,
			newProjectionCustHierarchy);
	}

	@Test(expected = NoSuchProjectionCustHierarchyException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ProjectionCustHierarchy> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PROJECTION_CUST_HIERARCHY",
			"projectionMasterSid", true, "projectionCustHierarchySid", true,
			"relationshipLevelSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ProjectionCustHierarchy newProjectionCustHierarchy = addProjectionCustHierarchy();

		ProjectionCustHierarchy existingProjectionCustHierarchy = _persistence.fetchByPrimaryKey(newProjectionCustHierarchy.getPrimaryKey());

		Assert.assertEquals(existingProjectionCustHierarchy,
			newProjectionCustHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionCustHierarchy missingProjectionCustHierarchy = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProjectionCustHierarchy);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ProjectionCustHierarchy newProjectionCustHierarchy1 = addProjectionCustHierarchy();
		ProjectionCustHierarchy newProjectionCustHierarchy2 = addProjectionCustHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionCustHierarchy1.getPrimaryKey());
		primaryKeys.add(newProjectionCustHierarchy2.getPrimaryKey());

		Map<Serializable, ProjectionCustHierarchy> projectionCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, projectionCustHierarchies.size());
		Assert.assertEquals(newProjectionCustHierarchy1,
			projectionCustHierarchies.get(
				newProjectionCustHierarchy1.getPrimaryKey()));
		Assert.assertEquals(newProjectionCustHierarchy2,
			projectionCustHierarchies.get(
				newProjectionCustHierarchy2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ProjectionCustHierarchy> projectionCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionCustHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ProjectionCustHierarchy newProjectionCustHierarchy = addProjectionCustHierarchy();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionCustHierarchy.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ProjectionCustHierarchy> projectionCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionCustHierarchies.size());
		Assert.assertEquals(newProjectionCustHierarchy,
			projectionCustHierarchies.get(
				newProjectionCustHierarchy.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ProjectionCustHierarchy> projectionCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionCustHierarchies.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ProjectionCustHierarchy newProjectionCustHierarchy = addProjectionCustHierarchy();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionCustHierarchy.getPrimaryKey());

		Map<Serializable, ProjectionCustHierarchy> projectionCustHierarchies = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionCustHierarchies.size());
		Assert.assertEquals(newProjectionCustHierarchy,
			projectionCustHierarchies.get(
				newProjectionCustHierarchy.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProjectionCustHierarchyLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProjectionCustHierarchy>() {
				@Override
				public void performAction(
					ProjectionCustHierarchy projectionCustHierarchy) {
					Assert.assertNotNull(projectionCustHierarchy);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ProjectionCustHierarchy newProjectionCustHierarchy = addProjectionCustHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"projectionCustHierarchySid",
				newProjectionCustHierarchy.getProjectionCustHierarchySid()));

		List<ProjectionCustHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ProjectionCustHierarchy existingProjectionCustHierarchy = result.get(0);

		Assert.assertEquals(existingProjectionCustHierarchy,
			newProjectionCustHierarchy);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"projectionCustHierarchySid", RandomTestUtil.nextInt()));

		List<ProjectionCustHierarchy> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ProjectionCustHierarchy newProjectionCustHierarchy = addProjectionCustHierarchy();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionCustHierarchySid"));

		Object newProjectionCustHierarchySid = newProjectionCustHierarchy.getProjectionCustHierarchySid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"projectionCustHierarchySid",
				new Object[] { newProjectionCustHierarchySid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionCustHierarchySid = result.get(0);

		Assert.assertEquals(existingProjectionCustHierarchySid,
			newProjectionCustHierarchySid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionCustHierarchySid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"projectionCustHierarchySid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ProjectionCustHierarchy addProjectionCustHierarchy()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionCustHierarchy projectionCustHierarchy = _persistence.create(pk);

		projectionCustHierarchy.setProjectionMasterSid(RandomTestUtil.nextInt());

		projectionCustHierarchy.setRelationshipLevelSid(RandomTestUtil.nextInt());

		_projectionCustHierarchies.add(_persistence.update(
				projectionCustHierarchy));

		return projectionCustHierarchy;
	}

	private List<ProjectionCustHierarchy> _projectionCustHierarchies = new ArrayList<ProjectionCustHierarchy>();
	private ProjectionCustHierarchyPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}