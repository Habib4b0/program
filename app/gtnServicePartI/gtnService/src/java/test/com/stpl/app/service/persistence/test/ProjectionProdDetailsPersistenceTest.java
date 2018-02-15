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

import com.stpl.app.exception.NoSuchProjectionProdDetailsException;
import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.service.ProjectionProdDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ProjectionProdDetailsPersistence;
import com.stpl.app.service.persistence.ProjectionProdDetailsUtil;

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
public class ProjectionProdDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ProjectionProdDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ProjectionProdDetails> iterator = _projectionProdDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionProdDetails projectionProdDetails = _persistence.create(pk);

		Assert.assertNotNull(projectionProdDetails);

		Assert.assertEquals(projectionProdDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ProjectionProdDetails newProjectionProdDetails = addProjectionProdDetails();

		_persistence.remove(newProjectionProdDetails);

		ProjectionProdDetails existingProjectionProdDetails = _persistence.fetchByPrimaryKey(newProjectionProdDetails.getPrimaryKey());

		Assert.assertNull(existingProjectionProdDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProjectionProdDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionProdDetails newProjectionProdDetails = _persistence.create(pk);

		newProjectionProdDetails.setProductName(RandomTestUtil.randomString());

		newProjectionProdDetails.setCostCenter(RandomTestUtil.randomString());

		newProjectionProdDetails.setProductNo(RandomTestUtil.randomString());

		newProjectionProdDetails.setSubLedgerCode(RandomTestUtil.randomString());

		newProjectionProdDetails.setBrandName(RandomTestUtil.randomString());

		newProjectionProdDetails.setProjectionId(RandomTestUtil.nextInt());

		_projectionProdDetailses.add(_persistence.update(
				newProjectionProdDetails));

		ProjectionProdDetails existingProjectionProdDetails = _persistence.findByPrimaryKey(newProjectionProdDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionProdDetails.getProductName(),
			newProjectionProdDetails.getProductName());
		Assert.assertEquals(existingProjectionProdDetails.getCostCenter(),
			newProjectionProdDetails.getCostCenter());
		Assert.assertEquals(existingProjectionProdDetails.getProductNo(),
			newProjectionProdDetails.getProductNo());
		Assert.assertEquals(existingProjectionProdDetails.getSubLedgerCode(),
			newProjectionProdDetails.getSubLedgerCode());
		Assert.assertEquals(existingProjectionProdDetails.getProductDetailsId(),
			newProjectionProdDetails.getProductDetailsId());
		Assert.assertEquals(existingProjectionProdDetails.getBrandName(),
			newProjectionProdDetails.getBrandName());
		Assert.assertEquals(existingProjectionProdDetails.getProjectionId(),
			newProjectionProdDetails.getProjectionId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ProjectionProdDetails newProjectionProdDetails = addProjectionProdDetails();

		ProjectionProdDetails existingProjectionProdDetails = _persistence.findByPrimaryKey(newProjectionProdDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionProdDetails,
			newProjectionProdDetails);
	}

	@Test(expected = NoSuchProjectionProdDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ProjectionProdDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PROJECTION_PROD_DETAILS",
			"productName", true, "costCenter", true, "productNo", true,
			"subLedgerCode", true, "productDetailsId", true, "brandName", true,
			"projectionId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ProjectionProdDetails newProjectionProdDetails = addProjectionProdDetails();

		ProjectionProdDetails existingProjectionProdDetails = _persistence.fetchByPrimaryKey(newProjectionProdDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionProdDetails,
			newProjectionProdDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionProdDetails missingProjectionProdDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProjectionProdDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ProjectionProdDetails newProjectionProdDetails1 = addProjectionProdDetails();
		ProjectionProdDetails newProjectionProdDetails2 = addProjectionProdDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionProdDetails1.getPrimaryKey());
		primaryKeys.add(newProjectionProdDetails2.getPrimaryKey());

		Map<Serializable, ProjectionProdDetails> projectionProdDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, projectionProdDetailses.size());
		Assert.assertEquals(newProjectionProdDetails1,
			projectionProdDetailses.get(
				newProjectionProdDetails1.getPrimaryKey()));
		Assert.assertEquals(newProjectionProdDetails2,
			projectionProdDetailses.get(
				newProjectionProdDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ProjectionProdDetails> projectionProdDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionProdDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ProjectionProdDetails newProjectionProdDetails = addProjectionProdDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionProdDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ProjectionProdDetails> projectionProdDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionProdDetailses.size());
		Assert.assertEquals(newProjectionProdDetails,
			projectionProdDetailses.get(
				newProjectionProdDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ProjectionProdDetails> projectionProdDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionProdDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ProjectionProdDetails newProjectionProdDetails = addProjectionProdDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionProdDetails.getPrimaryKey());

		Map<Serializable, ProjectionProdDetails> projectionProdDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionProdDetailses.size());
		Assert.assertEquals(newProjectionProdDetails,
			projectionProdDetailses.get(
				newProjectionProdDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProjectionProdDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProjectionProdDetails>() {
				@Override
				public void performAction(
					ProjectionProdDetails projectionProdDetails) {
					Assert.assertNotNull(projectionProdDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ProjectionProdDetails newProjectionProdDetails = addProjectionProdDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("productDetailsId",
				newProjectionProdDetails.getProductDetailsId()));

		List<ProjectionProdDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ProjectionProdDetails existingProjectionProdDetails = result.get(0);

		Assert.assertEquals(existingProjectionProdDetails,
			newProjectionProdDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("productDetailsId",
				RandomTestUtil.nextInt()));

		List<ProjectionProdDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ProjectionProdDetails newProjectionProdDetails = addProjectionProdDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"productDetailsId"));

		Object newProductDetailsId = newProjectionProdDetails.getProductDetailsId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("productDetailsId",
				new Object[] { newProductDetailsId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProductDetailsId = result.get(0);

		Assert.assertEquals(existingProductDetailsId, newProductDetailsId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"productDetailsId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("productDetailsId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ProjectionProdDetails addProjectionProdDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionProdDetails projectionProdDetails = _persistence.create(pk);

		projectionProdDetails.setProductName(RandomTestUtil.randomString());

		projectionProdDetails.setCostCenter(RandomTestUtil.randomString());

		projectionProdDetails.setProductNo(RandomTestUtil.randomString());

		projectionProdDetails.setSubLedgerCode(RandomTestUtil.randomString());

		projectionProdDetails.setBrandName(RandomTestUtil.randomString());

		projectionProdDetails.setProjectionId(RandomTestUtil.nextInt());

		_projectionProdDetailses.add(_persistence.update(projectionProdDetails));

		return projectionProdDetails;
	}

	private List<ProjectionProdDetails> _projectionProdDetailses = new ArrayList<ProjectionProdDetails>();
	private ProjectionProdDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}