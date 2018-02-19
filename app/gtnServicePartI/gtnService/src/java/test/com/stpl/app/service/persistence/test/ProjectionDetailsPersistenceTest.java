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

import com.stpl.app.exception.NoSuchProjectionDetailsException;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ProjectionDetailsPersistence;
import com.stpl.app.service.persistence.ProjectionDetailsUtil;

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
public class ProjectionDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ProjectionDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ProjectionDetails> iterator = _projectionDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionDetails projectionDetails = _persistence.create(pk);

		Assert.assertNotNull(projectionDetails);

		Assert.assertEquals(projectionDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ProjectionDetails newProjectionDetails = addProjectionDetails();

		_persistence.remove(newProjectionDetails);

		ProjectionDetails existingProjectionDetails = _persistence.fetchByPrimaryKey(newProjectionDetails.getPrimaryKey());

		Assert.assertNull(existingProjectionDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProjectionDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionDetails newProjectionDetails = _persistence.create(pk);

		newProjectionDetails.setCcpDetailsSid(RandomTestUtil.nextInt());

		newProjectionDetails.setProjectionMasterSid(RandomTestUtil.nextInt());

		_projectionDetailses.add(_persistence.update(newProjectionDetails));

		ProjectionDetails existingProjectionDetails = _persistence.findByPrimaryKey(newProjectionDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionDetails.getProjectionDetailsSid(),
			newProjectionDetails.getProjectionDetailsSid());
		Assert.assertEquals(existingProjectionDetails.getCcpDetailsSid(),
			newProjectionDetails.getCcpDetailsSid());
		Assert.assertEquals(existingProjectionDetails.getProjectionMasterSid(),
			newProjectionDetails.getProjectionMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ProjectionDetails newProjectionDetails = addProjectionDetails();

		ProjectionDetails existingProjectionDetails = _persistence.findByPrimaryKey(newProjectionDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionDetails, newProjectionDetails);
	}

	@Test(expected = NoSuchProjectionDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ProjectionDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PROJECTION_DETAILS",
			"projectionDetailsSid", true, "ccpDetailsSid", true,
			"projectionMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ProjectionDetails newProjectionDetails = addProjectionDetails();

		ProjectionDetails existingProjectionDetails = _persistence.fetchByPrimaryKey(newProjectionDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionDetails, newProjectionDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionDetails missingProjectionDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProjectionDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ProjectionDetails newProjectionDetails1 = addProjectionDetails();
		ProjectionDetails newProjectionDetails2 = addProjectionDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionDetails1.getPrimaryKey());
		primaryKeys.add(newProjectionDetails2.getPrimaryKey());

		Map<Serializable, ProjectionDetails> projectionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, projectionDetailses.size());
		Assert.assertEquals(newProjectionDetails1,
			projectionDetailses.get(newProjectionDetails1.getPrimaryKey()));
		Assert.assertEquals(newProjectionDetails2,
			projectionDetailses.get(newProjectionDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ProjectionDetails> projectionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ProjectionDetails newProjectionDetails = addProjectionDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ProjectionDetails> projectionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionDetailses.size());
		Assert.assertEquals(newProjectionDetails,
			projectionDetailses.get(newProjectionDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ProjectionDetails> projectionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ProjectionDetails newProjectionDetails = addProjectionDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionDetails.getPrimaryKey());

		Map<Serializable, ProjectionDetails> projectionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionDetailses.size());
		Assert.assertEquals(newProjectionDetails,
			projectionDetailses.get(newProjectionDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProjectionDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProjectionDetails>() {
				@Override
				public void performAction(ProjectionDetails projectionDetails) {
					Assert.assertNotNull(projectionDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ProjectionDetails newProjectionDetails = addProjectionDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				newProjectionDetails.getProjectionDetailsSid()));

		List<ProjectionDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ProjectionDetails existingProjectionDetails = result.get(0);

		Assert.assertEquals(existingProjectionDetails, newProjectionDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<ProjectionDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ProjectionDetails newProjectionDetails = addProjectionDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		Object newProjectionDetailsSid = newProjectionDetails.getProjectionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { newProjectionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionDetailsSid = result.get(0);

		Assert.assertEquals(existingProjectionDetailsSid,
			newProjectionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ProjectionDetails addProjectionDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionDetails projectionDetails = _persistence.create(pk);

		projectionDetails.setCcpDetailsSid(RandomTestUtil.nextInt());

		projectionDetails.setProjectionMasterSid(RandomTestUtil.nextInt());

		_projectionDetailses.add(_persistence.update(projectionDetails));

		return projectionDetails;
	}

	private List<ProjectionDetails> _projectionDetailses = new ArrayList<ProjectionDetails>();
	private ProjectionDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}