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

import com.stpl.app.exception.NoSuchProjectionCustDetailsException;
import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.service.ProjectionCustDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ProjectionCustDetailsPersistence;
import com.stpl.app.service.persistence.ProjectionCustDetailsUtil;

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
public class ProjectionCustDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ProjectionCustDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ProjectionCustDetails> iterator = _projectionCustDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionCustDetails projectionCustDetails = _persistence.create(pk);

		Assert.assertNotNull(projectionCustDetails);

		Assert.assertEquals(projectionCustDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ProjectionCustDetails newProjectionCustDetails = addProjectionCustDetails();

		_persistence.remove(newProjectionCustDetails);

		ProjectionCustDetails existingProjectionCustDetails = _persistence.fetchByPrimaryKey(newProjectionCustDetails.getPrimaryKey());

		Assert.assertNull(existingProjectionCustDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProjectionCustDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionCustDetails newProjectionCustDetails = _persistence.create(pk);

		newProjectionCustDetails.setContractName(RandomTestUtil.randomString());

		newProjectionCustDetails.setCustomerName(RandomTestUtil.randomString());

		newProjectionCustDetails.setCostCenter(RandomTestUtil.randomString());

		newProjectionCustDetails.setCustomerAlias(RandomTestUtil.randomString());

		newProjectionCustDetails.setSubLedgerCode(RandomTestUtil.randomString());

		newProjectionCustDetails.setProjectionId(RandomTestUtil.nextInt());

		newProjectionCustDetails.setMarketType(RandomTestUtil.randomString());

		newProjectionCustDetails.setContractNo(RandomTestUtil.randomString());

		_projectionCustDetailses.add(_persistence.update(
				newProjectionCustDetails));

		ProjectionCustDetails existingProjectionCustDetails = _persistence.findByPrimaryKey(newProjectionCustDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionCustDetails.getContractName(),
			newProjectionCustDetails.getContractName());
		Assert.assertEquals(existingProjectionCustDetails.getCustomerName(),
			newProjectionCustDetails.getCustomerName());
		Assert.assertEquals(existingProjectionCustDetails.getCustomerDetailsId(),
			newProjectionCustDetails.getCustomerDetailsId());
		Assert.assertEquals(existingProjectionCustDetails.getCostCenter(),
			newProjectionCustDetails.getCostCenter());
		Assert.assertEquals(existingProjectionCustDetails.getCustomerAlias(),
			newProjectionCustDetails.getCustomerAlias());
		Assert.assertEquals(existingProjectionCustDetails.getSubLedgerCode(),
			newProjectionCustDetails.getSubLedgerCode());
		Assert.assertEquals(existingProjectionCustDetails.getProjectionId(),
			newProjectionCustDetails.getProjectionId());
		Assert.assertEquals(existingProjectionCustDetails.getMarketType(),
			newProjectionCustDetails.getMarketType());
		Assert.assertEquals(existingProjectionCustDetails.getContractNo(),
			newProjectionCustDetails.getContractNo());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ProjectionCustDetails newProjectionCustDetails = addProjectionCustDetails();

		ProjectionCustDetails existingProjectionCustDetails = _persistence.findByPrimaryKey(newProjectionCustDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionCustDetails,
			newProjectionCustDetails);
	}

	@Test(expected = NoSuchProjectionCustDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ProjectionCustDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PROJECTION_CUST_DETAILS",
			"contractName", true, "customerName", true, "customerDetailsId",
			true, "costCenter", true, "customerAlias", true, "subLedgerCode",
			true, "projectionId", true, "marketType", true, "contractNo", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ProjectionCustDetails newProjectionCustDetails = addProjectionCustDetails();

		ProjectionCustDetails existingProjectionCustDetails = _persistence.fetchByPrimaryKey(newProjectionCustDetails.getPrimaryKey());

		Assert.assertEquals(existingProjectionCustDetails,
			newProjectionCustDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionCustDetails missingProjectionCustDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProjectionCustDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ProjectionCustDetails newProjectionCustDetails1 = addProjectionCustDetails();
		ProjectionCustDetails newProjectionCustDetails2 = addProjectionCustDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionCustDetails1.getPrimaryKey());
		primaryKeys.add(newProjectionCustDetails2.getPrimaryKey());

		Map<Serializable, ProjectionCustDetails> projectionCustDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, projectionCustDetailses.size());
		Assert.assertEquals(newProjectionCustDetails1,
			projectionCustDetailses.get(
				newProjectionCustDetails1.getPrimaryKey()));
		Assert.assertEquals(newProjectionCustDetails2,
			projectionCustDetailses.get(
				newProjectionCustDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ProjectionCustDetails> projectionCustDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionCustDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ProjectionCustDetails newProjectionCustDetails = addProjectionCustDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionCustDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ProjectionCustDetails> projectionCustDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionCustDetailses.size());
		Assert.assertEquals(newProjectionCustDetails,
			projectionCustDetailses.get(
				newProjectionCustDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ProjectionCustDetails> projectionCustDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionCustDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ProjectionCustDetails newProjectionCustDetails = addProjectionCustDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionCustDetails.getPrimaryKey());

		Map<Serializable, ProjectionCustDetails> projectionCustDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionCustDetailses.size());
		Assert.assertEquals(newProjectionCustDetails,
			projectionCustDetailses.get(
				newProjectionCustDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProjectionCustDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProjectionCustDetails>() {
				@Override
				public void performAction(
					ProjectionCustDetails projectionCustDetails) {
					Assert.assertNotNull(projectionCustDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ProjectionCustDetails newProjectionCustDetails = addProjectionCustDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customerDetailsId",
				newProjectionCustDetails.getCustomerDetailsId()));

		List<ProjectionCustDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ProjectionCustDetails existingProjectionCustDetails = result.get(0);

		Assert.assertEquals(existingProjectionCustDetails,
			newProjectionCustDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customerDetailsId",
				RandomTestUtil.nextInt()));

		List<ProjectionCustDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ProjectionCustDetails newProjectionCustDetails = addProjectionCustDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customerDetailsId"));

		Object newCustomerDetailsId = newProjectionCustDetails.getCustomerDetailsId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("customerDetailsId",
				new Object[] { newCustomerDetailsId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCustomerDetailsId = result.get(0);

		Assert.assertEquals(existingCustomerDetailsId, newCustomerDetailsId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customerDetailsId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("customerDetailsId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ProjectionCustDetails addProjectionCustDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionCustDetails projectionCustDetails = _persistence.create(pk);

		projectionCustDetails.setContractName(RandomTestUtil.randomString());

		projectionCustDetails.setCustomerName(RandomTestUtil.randomString());

		projectionCustDetails.setCostCenter(RandomTestUtil.randomString());

		projectionCustDetails.setCustomerAlias(RandomTestUtil.randomString());

		projectionCustDetails.setSubLedgerCode(RandomTestUtil.randomString());

		projectionCustDetails.setProjectionId(RandomTestUtil.nextInt());

		projectionCustDetails.setMarketType(RandomTestUtil.randomString());

		projectionCustDetails.setContractNo(RandomTestUtil.randomString());

		_projectionCustDetailses.add(_persistence.update(projectionCustDetails));

		return projectionCustDetails;
	}

	private List<ProjectionCustDetails> _projectionCustDetailses = new ArrayList<ProjectionCustDetails>();
	private ProjectionCustDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}