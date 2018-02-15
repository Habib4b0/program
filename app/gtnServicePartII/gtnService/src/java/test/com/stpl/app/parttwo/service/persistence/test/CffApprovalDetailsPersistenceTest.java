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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchCffApprovalDetailsException;
import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.service.CffApprovalDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffApprovalDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.CffApprovalDetailsUtil;

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
public class CffApprovalDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffApprovalDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffApprovalDetails> iterator = _cffApprovalDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffApprovalDetails cffApprovalDetails = _persistence.create(pk);

		Assert.assertNotNull(cffApprovalDetails);

		Assert.assertEquals(cffApprovalDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffApprovalDetails newCffApprovalDetails = addCffApprovalDetails();

		_persistence.remove(newCffApprovalDetails);

		CffApprovalDetails existingCffApprovalDetails = _persistence.fetchByPrimaryKey(newCffApprovalDetails.getPrimaryKey());

		Assert.assertNull(existingCffApprovalDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffApprovalDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffApprovalDetails newCffApprovalDetails = _persistence.create(pk);

		newCffApprovalDetails.setApprovalSequence(RandomTestUtil.nextInt());

		newCffApprovalDetails.setApprovedDate(RandomTestUtil.nextDate());

		newCffApprovalDetails.setApprovedBy(RandomTestUtil.nextInt());

		newCffApprovalDetails.setApprovalStatus(RandomTestUtil.nextInt());

		newCffApprovalDetails.setCffMasterSid(RandomTestUtil.nextInt());

		newCffApprovalDetails.setInboundStatus(RandomTestUtil.randomString());

		_cffApprovalDetailses.add(_persistence.update(newCffApprovalDetails));

		CffApprovalDetails existingCffApprovalDetails = _persistence.findByPrimaryKey(newCffApprovalDetails.getPrimaryKey());

		Assert.assertEquals(existingCffApprovalDetails.getApprovalSequence(),
			newCffApprovalDetails.getApprovalSequence());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCffApprovalDetails.getApprovedDate()),
			Time.getShortTimestamp(newCffApprovalDetails.getApprovedDate()));
		Assert.assertEquals(existingCffApprovalDetails.getApprovedBy(),
			newCffApprovalDetails.getApprovedBy());
		Assert.assertEquals(existingCffApprovalDetails.getApprovalStatus(),
			newCffApprovalDetails.getApprovalStatus());
		Assert.assertEquals(existingCffApprovalDetails.getCffMasterSid(),
			newCffApprovalDetails.getCffMasterSid());
		Assert.assertEquals(existingCffApprovalDetails.getInboundStatus(),
			newCffApprovalDetails.getInboundStatus());
		Assert.assertEquals(existingCffApprovalDetails.getCffApprovalDetailsSid(),
			newCffApprovalDetails.getCffApprovalDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffApprovalDetails newCffApprovalDetails = addCffApprovalDetails();

		CffApprovalDetails existingCffApprovalDetails = _persistence.findByPrimaryKey(newCffApprovalDetails.getPrimaryKey());

		Assert.assertEquals(existingCffApprovalDetails, newCffApprovalDetails);
	}

	@Test(expected = NoSuchCffApprovalDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffApprovalDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_APPROVAL_DETAILS",
			"approvalSequence", true, "approvedDate", true, "approvedBy", true,
			"approvalStatus", true, "cffMasterSid", true, "inboundStatus",
			true, "cffApprovalDetailsSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffApprovalDetails newCffApprovalDetails = addCffApprovalDetails();

		CffApprovalDetails existingCffApprovalDetails = _persistence.fetchByPrimaryKey(newCffApprovalDetails.getPrimaryKey());

		Assert.assertEquals(existingCffApprovalDetails, newCffApprovalDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffApprovalDetails missingCffApprovalDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffApprovalDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffApprovalDetails newCffApprovalDetails1 = addCffApprovalDetails();
		CffApprovalDetails newCffApprovalDetails2 = addCffApprovalDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffApprovalDetails1.getPrimaryKey());
		primaryKeys.add(newCffApprovalDetails2.getPrimaryKey());

		Map<Serializable, CffApprovalDetails> cffApprovalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffApprovalDetailses.size());
		Assert.assertEquals(newCffApprovalDetails1,
			cffApprovalDetailses.get(newCffApprovalDetails1.getPrimaryKey()));
		Assert.assertEquals(newCffApprovalDetails2,
			cffApprovalDetailses.get(newCffApprovalDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffApprovalDetails> cffApprovalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffApprovalDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffApprovalDetails newCffApprovalDetails = addCffApprovalDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffApprovalDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffApprovalDetails> cffApprovalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffApprovalDetailses.size());
		Assert.assertEquals(newCffApprovalDetails,
			cffApprovalDetailses.get(newCffApprovalDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffApprovalDetails> cffApprovalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffApprovalDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffApprovalDetails newCffApprovalDetails = addCffApprovalDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffApprovalDetails.getPrimaryKey());

		Map<Serializable, CffApprovalDetails> cffApprovalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffApprovalDetailses.size());
		Assert.assertEquals(newCffApprovalDetails,
			cffApprovalDetailses.get(newCffApprovalDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffApprovalDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffApprovalDetails>() {
				@Override
				public void performAction(CffApprovalDetails cffApprovalDetails) {
					Assert.assertNotNull(cffApprovalDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffApprovalDetails newCffApprovalDetails = addCffApprovalDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffApprovalDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffApprovalDetailsSid",
				newCffApprovalDetails.getCffApprovalDetailsSid()));

		List<CffApprovalDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffApprovalDetails existingCffApprovalDetails = result.get(0);

		Assert.assertEquals(existingCffApprovalDetails, newCffApprovalDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffApprovalDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffApprovalDetailsSid",
				RandomTestUtil.nextInt()));

		List<CffApprovalDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffApprovalDetails newCffApprovalDetails = addCffApprovalDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffApprovalDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffApprovalDetailsSid"));

		Object newCffApprovalDetailsSid = newCffApprovalDetails.getCffApprovalDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffApprovalDetailsSid",
				new Object[] { newCffApprovalDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffApprovalDetailsSid = result.get(0);

		Assert.assertEquals(existingCffApprovalDetailsSid,
			newCffApprovalDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffApprovalDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffApprovalDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffApprovalDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffApprovalDetails addCffApprovalDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffApprovalDetails cffApprovalDetails = _persistence.create(pk);

		cffApprovalDetails.setApprovalSequence(RandomTestUtil.nextInt());

		cffApprovalDetails.setApprovedDate(RandomTestUtil.nextDate());

		cffApprovalDetails.setApprovedBy(RandomTestUtil.nextInt());

		cffApprovalDetails.setApprovalStatus(RandomTestUtil.nextInt());

		cffApprovalDetails.setCffMasterSid(RandomTestUtil.nextInt());

		cffApprovalDetails.setInboundStatus(RandomTestUtil.randomString());

		_cffApprovalDetailses.add(_persistence.update(cffApprovalDetails));

		return cffApprovalDetails;
	}

	private List<CffApprovalDetails> _cffApprovalDetailses = new ArrayList<CffApprovalDetails>();
	private CffApprovalDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}