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

import com.stpl.app.exception.NoSuchCustomViewDetailsException;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.CustomViewDetailsPersistence;
import com.stpl.app.service.persistence.CustomViewDetailsUtil;

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
public class CustomViewDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CustomViewDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CustomViewDetails> iterator = _customViewDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomViewDetails customViewDetails = _persistence.create(pk);

		Assert.assertNotNull(customViewDetails);

		Assert.assertEquals(customViewDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CustomViewDetails newCustomViewDetails = addCustomViewDetails();

		_persistence.remove(newCustomViewDetails);

		CustomViewDetails existingCustomViewDetails = _persistence.fetchByPrimaryKey(newCustomViewDetails.getPrimaryKey());

		Assert.assertNull(existingCustomViewDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCustomViewDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomViewDetails newCustomViewDetails = _persistence.create(pk);

		newCustomViewDetails.setHierarchyId(RandomTestUtil.nextInt());

		newCustomViewDetails.setHierarchyIndicator(RandomTestUtil.randomString());

		newCustomViewDetails.setCustomViewMasterSid(RandomTestUtil.nextInt());

		newCustomViewDetails.setLevelNo(RandomTestUtil.nextInt());

		_customViewDetailses.add(_persistence.update(newCustomViewDetails));

		CustomViewDetails existingCustomViewDetails = _persistence.findByPrimaryKey(newCustomViewDetails.getPrimaryKey());

		Assert.assertEquals(existingCustomViewDetails.getHierarchyId(),
			newCustomViewDetails.getHierarchyId());
		Assert.assertEquals(existingCustomViewDetails.getHierarchyIndicator(),
			newCustomViewDetails.getHierarchyIndicator());
		Assert.assertEquals(existingCustomViewDetails.getCustomViewMasterSid(),
			newCustomViewDetails.getCustomViewMasterSid());
		Assert.assertEquals(existingCustomViewDetails.getCustomViewDetailsSid(),
			newCustomViewDetails.getCustomViewDetailsSid());
		Assert.assertEquals(existingCustomViewDetails.getLevelNo(),
			newCustomViewDetails.getLevelNo());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CustomViewDetails newCustomViewDetails = addCustomViewDetails();

		CustomViewDetails existingCustomViewDetails = _persistence.findByPrimaryKey(newCustomViewDetails.getPrimaryKey());

		Assert.assertEquals(existingCustomViewDetails, newCustomViewDetails);
	}

	@Test(expected = NoSuchCustomViewDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CustomViewDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CUSTOM_VIEW_DETAILS",
			"hierarchyId", true, "hierarchyIndicator", true,
			"customViewMasterSid", true, "customViewDetailsSid", true,
			"levelNo", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CustomViewDetails newCustomViewDetails = addCustomViewDetails();

		CustomViewDetails existingCustomViewDetails = _persistence.fetchByPrimaryKey(newCustomViewDetails.getPrimaryKey());

		Assert.assertEquals(existingCustomViewDetails, newCustomViewDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomViewDetails missingCustomViewDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCustomViewDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CustomViewDetails newCustomViewDetails1 = addCustomViewDetails();
		CustomViewDetails newCustomViewDetails2 = addCustomViewDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomViewDetails1.getPrimaryKey());
		primaryKeys.add(newCustomViewDetails2.getPrimaryKey());

		Map<Serializable, CustomViewDetails> customViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, customViewDetailses.size());
		Assert.assertEquals(newCustomViewDetails1,
			customViewDetailses.get(newCustomViewDetails1.getPrimaryKey()));
		Assert.assertEquals(newCustomViewDetails2,
			customViewDetailses.get(newCustomViewDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CustomViewDetails> customViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(customViewDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CustomViewDetails newCustomViewDetails = addCustomViewDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomViewDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CustomViewDetails> customViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, customViewDetailses.size());
		Assert.assertEquals(newCustomViewDetails,
			customViewDetailses.get(newCustomViewDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CustomViewDetails> customViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(customViewDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CustomViewDetails newCustomViewDetails = addCustomViewDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomViewDetails.getPrimaryKey());

		Map<Serializable, CustomViewDetails> customViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, customViewDetailses.size());
		Assert.assertEquals(newCustomViewDetails,
			customViewDetailses.get(newCustomViewDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CustomViewDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CustomViewDetails>() {
				@Override
				public void performAction(CustomViewDetails customViewDetails) {
					Assert.assertNotNull(customViewDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CustomViewDetails newCustomViewDetails = addCustomViewDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customViewDetailsSid",
				newCustomViewDetails.getCustomViewDetailsSid()));

		List<CustomViewDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CustomViewDetails existingCustomViewDetails = result.get(0);

		Assert.assertEquals(existingCustomViewDetails, newCustomViewDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customViewDetailsSid",
				RandomTestUtil.nextInt()));

		List<CustomViewDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CustomViewDetails newCustomViewDetails = addCustomViewDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customViewDetailsSid"));

		Object newCustomViewDetailsSid = newCustomViewDetails.getCustomViewDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("customViewDetailsSid",
				new Object[] { newCustomViewDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCustomViewDetailsSid = result.get(0);

		Assert.assertEquals(existingCustomViewDetailsSid,
			newCustomViewDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customViewDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("customViewDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CustomViewDetails addCustomViewDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomViewDetails customViewDetails = _persistence.create(pk);

		customViewDetails.setHierarchyId(RandomTestUtil.nextInt());

		customViewDetails.setHierarchyIndicator(RandomTestUtil.randomString());

		customViewDetails.setCustomViewMasterSid(RandomTestUtil.nextInt());

		customViewDetails.setLevelNo(RandomTestUtil.nextInt());

		_customViewDetailses.add(_persistence.update(customViewDetails));

		return customViewDetails;
	}

	private List<CustomViewDetails> _customViewDetailses = new ArrayList<CustomViewDetails>();
	private CustomViewDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}