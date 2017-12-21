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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchCffCustomViewDetailsException;
import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.service.CffCustomViewDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffCustomViewDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.CffCustomViewDetailsUtil;

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
public class CffCustomViewDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffCustomViewDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffCustomViewDetails> iterator = _cffCustomViewDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustomViewDetails cffCustomViewDetails = _persistence.create(pk);

		Assert.assertNotNull(cffCustomViewDetails);

		Assert.assertEquals(cffCustomViewDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffCustomViewDetails newCffCustomViewDetails = addCffCustomViewDetails();

		_persistence.remove(newCffCustomViewDetails);

		CffCustomViewDetails existingCffCustomViewDetails = _persistence.fetchByPrimaryKey(newCffCustomViewDetails.getPrimaryKey());

		Assert.assertNull(existingCffCustomViewDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffCustomViewDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustomViewDetails newCffCustomViewDetails = _persistence.create(pk);

		newCffCustomViewDetails.setHierarchyId(RandomTestUtil.nextInt());

		newCffCustomViewDetails.setHierarchyIndicator(RandomTestUtil.randomString());

		newCffCustomViewDetails.setLevelNo(RandomTestUtil.nextInt());

		newCffCustomViewDetails.setCffCustomViewMasterSid(RandomTestUtil.nextInt());

		_cffCustomViewDetailses.add(_persistence.update(newCffCustomViewDetails));

		CffCustomViewDetails existingCffCustomViewDetails = _persistence.findByPrimaryKey(newCffCustomViewDetails.getPrimaryKey());

		Assert.assertEquals(existingCffCustomViewDetails.getHierarchyId(),
			newCffCustomViewDetails.getHierarchyId());
		Assert.assertEquals(existingCffCustomViewDetails.getHierarchyIndicator(),
			newCffCustomViewDetails.getHierarchyIndicator());
		Assert.assertEquals(existingCffCustomViewDetails.getCffCustomViewDetailsSid(),
			newCffCustomViewDetails.getCffCustomViewDetailsSid());
		Assert.assertEquals(existingCffCustomViewDetails.getLevelNo(),
			newCffCustomViewDetails.getLevelNo());
		Assert.assertEquals(existingCffCustomViewDetails.getCffCustomViewMasterSid(),
			newCffCustomViewDetails.getCffCustomViewMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffCustomViewDetails newCffCustomViewDetails = addCffCustomViewDetails();

		CffCustomViewDetails existingCffCustomViewDetails = _persistence.findByPrimaryKey(newCffCustomViewDetails.getPrimaryKey());

		Assert.assertEquals(existingCffCustomViewDetails,
			newCffCustomViewDetails);
	}

	@Test(expected = NoSuchCffCustomViewDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffCustomViewDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_CUSTOM_VIEW_DETAILS",
			"hierarchyId", true, "hierarchyIndicator", true,
			"cffCustomViewDetailsSid", true, "levelNo", true,
			"cffCustomViewMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffCustomViewDetails newCffCustomViewDetails = addCffCustomViewDetails();

		CffCustomViewDetails existingCffCustomViewDetails = _persistence.fetchByPrimaryKey(newCffCustomViewDetails.getPrimaryKey());

		Assert.assertEquals(existingCffCustomViewDetails,
			newCffCustomViewDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustomViewDetails missingCffCustomViewDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffCustomViewDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffCustomViewDetails newCffCustomViewDetails1 = addCffCustomViewDetails();
		CffCustomViewDetails newCffCustomViewDetails2 = addCffCustomViewDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustomViewDetails1.getPrimaryKey());
		primaryKeys.add(newCffCustomViewDetails2.getPrimaryKey());

		Map<Serializable, CffCustomViewDetails> cffCustomViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffCustomViewDetailses.size());
		Assert.assertEquals(newCffCustomViewDetails1,
			cffCustomViewDetailses.get(newCffCustomViewDetails1.getPrimaryKey()));
		Assert.assertEquals(newCffCustomViewDetails2,
			cffCustomViewDetailses.get(newCffCustomViewDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffCustomViewDetails> cffCustomViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffCustomViewDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffCustomViewDetails newCffCustomViewDetails = addCffCustomViewDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustomViewDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffCustomViewDetails> cffCustomViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffCustomViewDetailses.size());
		Assert.assertEquals(newCffCustomViewDetails,
			cffCustomViewDetailses.get(newCffCustomViewDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffCustomViewDetails> cffCustomViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffCustomViewDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffCustomViewDetails newCffCustomViewDetails = addCffCustomViewDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustomViewDetails.getPrimaryKey());

		Map<Serializable, CffCustomViewDetails> cffCustomViewDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffCustomViewDetailses.size());
		Assert.assertEquals(newCffCustomViewDetails,
			cffCustomViewDetailses.get(newCffCustomViewDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffCustomViewDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffCustomViewDetails>() {
				@Override
				public void performAction(
					CffCustomViewDetails cffCustomViewDetails) {
					Assert.assertNotNull(cffCustomViewDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffCustomViewDetails newCffCustomViewDetails = addCffCustomViewDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustomViewDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffCustomViewDetailsSid",
				newCffCustomViewDetails.getCffCustomViewDetailsSid()));

		List<CffCustomViewDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffCustomViewDetails existingCffCustomViewDetails = result.get(0);

		Assert.assertEquals(existingCffCustomViewDetails,
			newCffCustomViewDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustomViewDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffCustomViewDetailsSid",
				RandomTestUtil.nextInt()));

		List<CffCustomViewDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffCustomViewDetails newCffCustomViewDetails = addCffCustomViewDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustomViewDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffCustomViewDetailsSid"));

		Object newCffCustomViewDetailsSid = newCffCustomViewDetails.getCffCustomViewDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffCustomViewDetailsSid",
				new Object[] { newCffCustomViewDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffCustomViewDetailsSid = result.get(0);

		Assert.assertEquals(existingCffCustomViewDetailsSid,
			newCffCustomViewDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustomViewDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffCustomViewDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffCustomViewDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffCustomViewDetails addCffCustomViewDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustomViewDetails cffCustomViewDetails = _persistence.create(pk);

		cffCustomViewDetails.setHierarchyId(RandomTestUtil.nextInt());

		cffCustomViewDetails.setHierarchyIndicator(RandomTestUtil.randomString());

		cffCustomViewDetails.setLevelNo(RandomTestUtil.nextInt());

		cffCustomViewDetails.setCffCustomViewMasterSid(RandomTestUtil.nextInt());

		_cffCustomViewDetailses.add(_persistence.update(cffCustomViewDetails));

		return cffCustomViewDetails;
	}

	private List<CffCustomViewDetails> _cffCustomViewDetailses = new ArrayList<CffCustomViewDetails>();
	private CffCustomViewDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}