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

import com.stpl.app.exception.NoSuchDeductionGroupDetailsException;
import com.stpl.app.model.DeductionGroupDetails;
import com.stpl.app.service.DeductionGroupDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.DeductionGroupDetailsPersistence;
import com.stpl.app.service.persistence.DeductionGroupDetailsUtil;

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
public class DeductionGroupDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = DeductionGroupDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DeductionGroupDetails> iterator = _deductionGroupDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionGroupDetails deductionGroupDetails = _persistence.create(pk);

		Assert.assertNotNull(deductionGroupDetails);

		Assert.assertEquals(deductionGroupDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DeductionGroupDetails newDeductionGroupDetails = addDeductionGroupDetails();

		_persistence.remove(newDeductionGroupDetails);

		DeductionGroupDetails existingDeductionGroupDetails = _persistence.fetchByPrimaryKey(newDeductionGroupDetails.getPrimaryKey());

		Assert.assertNull(existingDeductionGroupDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDeductionGroupDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionGroupDetails newDeductionGroupDetails = _persistence.create(pk);

		newDeductionGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		newDeductionGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		newDeductionGroupDetails.setDeductionGroupSid(RandomTestUtil.nextInt());

		newDeductionGroupDetails.setVersionNo(RandomTestUtil.nextInt());

		newDeductionGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		newDeductionGroupDetails.setRsModelSid(RandomTestUtil.nextInt());

		newDeductionGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		_deductionGroupDetailses.add(_persistence.update(
				newDeductionGroupDetails));

		DeductionGroupDetails existingDeductionGroupDetails = _persistence.findByPrimaryKey(newDeductionGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionGroupDetails.getDeductionGroupDetailsSid(),
			newDeductionGroupDetails.getDeductionGroupDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDeductionGroupDetails.getCreatedDate()),
			Time.getShortTimestamp(newDeductionGroupDetails.getCreatedDate()));
		Assert.assertEquals(existingDeductionGroupDetails.getCreatedBy(),
			newDeductionGroupDetails.getCreatedBy());
		Assert.assertEquals(existingDeductionGroupDetails.getDeductionGroupSid(),
			newDeductionGroupDetails.getDeductionGroupSid());
		Assert.assertEquals(existingDeductionGroupDetails.getVersionNo(),
			newDeductionGroupDetails.getVersionNo());
		Assert.assertEquals(existingDeductionGroupDetails.getModifiedBy(),
			newDeductionGroupDetails.getModifiedBy());
		Assert.assertEquals(existingDeductionGroupDetails.getRsModelSid(),
			newDeductionGroupDetails.getRsModelSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDeductionGroupDetails.getModifiedDate()),
			Time.getShortTimestamp(newDeductionGroupDetails.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DeductionGroupDetails newDeductionGroupDetails = addDeductionGroupDetails();

		DeductionGroupDetails existingDeductionGroupDetails = _persistence.findByPrimaryKey(newDeductionGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionGroupDetails,
			newDeductionGroupDetails);
	}

	@Test(expected = NoSuchDeductionGroupDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DeductionGroupDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DEDUCTION_GROUP_DETAILS",
			"deductionGroupDetailsSid", true, "createdDate", true, "createdBy",
			true, "deductionGroupSid", true, "versionNo", true, "modifiedBy",
			true, "rsModelSid", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DeductionGroupDetails newDeductionGroupDetails = addDeductionGroupDetails();

		DeductionGroupDetails existingDeductionGroupDetails = _persistence.fetchByPrimaryKey(newDeductionGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionGroupDetails,
			newDeductionGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionGroupDetails missingDeductionGroupDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDeductionGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DeductionGroupDetails newDeductionGroupDetails1 = addDeductionGroupDetails();
		DeductionGroupDetails newDeductionGroupDetails2 = addDeductionGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionGroupDetails1.getPrimaryKey());
		primaryKeys.add(newDeductionGroupDetails2.getPrimaryKey());

		Map<Serializable, DeductionGroupDetails> deductionGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, deductionGroupDetailses.size());
		Assert.assertEquals(newDeductionGroupDetails1,
			deductionGroupDetailses.get(
				newDeductionGroupDetails1.getPrimaryKey()));
		Assert.assertEquals(newDeductionGroupDetails2,
			deductionGroupDetailses.get(
				newDeductionGroupDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DeductionGroupDetails> deductionGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DeductionGroupDetails newDeductionGroupDetails = addDeductionGroupDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionGroupDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DeductionGroupDetails> deductionGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionGroupDetailses.size());
		Assert.assertEquals(newDeductionGroupDetails,
			deductionGroupDetailses.get(
				newDeductionGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DeductionGroupDetails> deductionGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DeductionGroupDetails newDeductionGroupDetails = addDeductionGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionGroupDetails.getPrimaryKey());

		Map<Serializable, DeductionGroupDetails> deductionGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionGroupDetailses.size());
		Assert.assertEquals(newDeductionGroupDetails,
			deductionGroupDetailses.get(
				newDeductionGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DeductionGroupDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DeductionGroupDetails>() {
				@Override
				public void performAction(
					DeductionGroupDetails deductionGroupDetails) {
					Assert.assertNotNull(deductionGroupDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DeductionGroupDetails newDeductionGroupDetails = addDeductionGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"deductionGroupDetailsSid",
				newDeductionGroupDetails.getDeductionGroupDetailsSid()));

		List<DeductionGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DeductionGroupDetails existingDeductionGroupDetails = result.get(0);

		Assert.assertEquals(existingDeductionGroupDetails,
			newDeductionGroupDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"deductionGroupDetailsSid", RandomTestUtil.nextInt()));

		List<DeductionGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DeductionGroupDetails newDeductionGroupDetails = addDeductionGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"deductionGroupDetailsSid"));

		Object newDeductionGroupDetailsSid = newDeductionGroupDetails.getDeductionGroupDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"deductionGroupDetailsSid",
				new Object[] { newDeductionGroupDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDeductionGroupDetailsSid = result.get(0);

		Assert.assertEquals(existingDeductionGroupDetailsSid,
			newDeductionGroupDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"deductionGroupDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"deductionGroupDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DeductionGroupDetails addDeductionGroupDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionGroupDetails deductionGroupDetails = _persistence.create(pk);

		deductionGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		deductionGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		deductionGroupDetails.setDeductionGroupSid(RandomTestUtil.nextInt());

		deductionGroupDetails.setVersionNo(RandomTestUtil.nextInt());

		deductionGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		deductionGroupDetails.setRsModelSid(RandomTestUtil.nextInt());

		deductionGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		_deductionGroupDetailses.add(_persistence.update(deductionGroupDetails));

		return deductionGroupDetails;
	}

	private List<DeductionGroupDetails> _deductionGroupDetailses = new ArrayList<DeductionGroupDetails>();
	private DeductionGroupDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}