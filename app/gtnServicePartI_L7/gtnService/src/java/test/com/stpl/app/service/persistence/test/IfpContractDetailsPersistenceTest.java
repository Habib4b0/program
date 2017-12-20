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

import com.stpl.app.exception.NoSuchIfpContractDetailsException;
import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.IfpContractDetailsPersistence;
import com.stpl.app.service.persistence.IfpContractDetailsUtil;

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
public class IfpContractDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IfpContractDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IfpContractDetails> iterator = _ifpContractDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpContractDetails ifpContractDetails = _persistence.create(pk);

		Assert.assertNotNull(ifpContractDetails);

		Assert.assertEquals(ifpContractDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IfpContractDetails newIfpContractDetails = addIfpContractDetails();

		_persistence.remove(newIfpContractDetails);

		IfpContractDetails existingIfpContractDetails = _persistence.fetchByPrimaryKey(newIfpContractDetails.getPrimaryKey());

		Assert.assertNull(existingIfpContractDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIfpContractDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpContractDetails newIfpContractDetails = _persistence.create(pk);

		newIfpContractDetails.setItemStatus(RandomTestUtil.nextInt());

		newIfpContractDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newIfpContractDetails.setIfpContractAttachedDate(RandomTestUtil.nextDate());

		newIfpContractDetails.setItemEndDate(RandomTestUtil.nextDate());

		newIfpContractDetails.setTotalVolumeCommitment(RandomTestUtil.randomString());

		newIfpContractDetails.setTotalDollarCommitment(RandomTestUtil.randomString());

		newIfpContractDetails.setIfpContractAttachedStatus(RandomTestUtil.nextInt());

		newIfpContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		newIfpContractDetails.setTotalMarketshareCommitment(RandomTestUtil.randomString());

		newIfpContractDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newIfpContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		newIfpContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		newIfpContractDetails.setSource(RandomTestUtil.randomString());

		newIfpContractDetails.setItemStartDate(RandomTestUtil.nextDate());

		newIfpContractDetails.setBatchId(RandomTestUtil.randomString());

		newIfpContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		newIfpContractDetails.setInboundStatus(RandomTestUtil.randomString());

		newIfpContractDetails.setIfpContractSid(RandomTestUtil.nextInt());

		newIfpContractDetails.setCommitmentPeriod(RandomTestUtil.randomString());

		_ifpContractDetailses.add(_persistence.update(newIfpContractDetails));

		IfpContractDetails existingIfpContractDetails = _persistence.findByPrimaryKey(newIfpContractDetails.getPrimaryKey());

		Assert.assertEquals(existingIfpContractDetails.getItemStatus(),
			newIfpContractDetails.getItemStatus());
		Assert.assertEquals(existingIfpContractDetails.getItemMasterSid(),
			newIfpContractDetails.getItemMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContractDetails.getIfpContractAttachedDate()),
			Time.getShortTimestamp(
				newIfpContractDetails.getIfpContractAttachedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContractDetails.getItemEndDate()),
			Time.getShortTimestamp(newIfpContractDetails.getItemEndDate()));
		Assert.assertEquals(existingIfpContractDetails.getTotalVolumeCommitment(),
			newIfpContractDetails.getTotalVolumeCommitment());
		Assert.assertEquals(existingIfpContractDetails.getTotalDollarCommitment(),
			newIfpContractDetails.getTotalDollarCommitment());
		Assert.assertEquals(existingIfpContractDetails.getIfpContractAttachedStatus(),
			newIfpContractDetails.getIfpContractAttachedStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContractDetails.getModifiedDate()),
			Time.getShortTimestamp(newIfpContractDetails.getModifiedDate()));
		Assert.assertEquals(existingIfpContractDetails.getTotalMarketshareCommitment(),
			newIfpContractDetails.getTotalMarketshareCommitment());
		Assert.assertEquals(existingIfpContractDetails.getRecordLockStatus(),
			newIfpContractDetails.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContractDetails.getCreatedDate()),
			Time.getShortTimestamp(newIfpContractDetails.getCreatedDate()));
		Assert.assertEquals(existingIfpContractDetails.getCreatedBy(),
			newIfpContractDetails.getCreatedBy());
		Assert.assertEquals(existingIfpContractDetails.getSource(),
			newIfpContractDetails.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContractDetails.getItemStartDate()),
			Time.getShortTimestamp(newIfpContractDetails.getItemStartDate()));
		Assert.assertEquals(existingIfpContractDetails.getBatchId(),
			newIfpContractDetails.getBatchId());
		Assert.assertEquals(existingIfpContractDetails.getIfpContractDetailsSid(),
			newIfpContractDetails.getIfpContractDetailsSid());
		Assert.assertEquals(existingIfpContractDetails.getModifiedBy(),
			newIfpContractDetails.getModifiedBy());
		Assert.assertEquals(existingIfpContractDetails.getInboundStatus(),
			newIfpContractDetails.getInboundStatus());
		Assert.assertEquals(existingIfpContractDetails.getIfpContractSid(),
			newIfpContractDetails.getIfpContractSid());
		Assert.assertEquals(existingIfpContractDetails.getCommitmentPeriod(),
			newIfpContractDetails.getCommitmentPeriod());
	}

	@Test
	public void testCountByIFPDetails() throws Exception {
		_persistence.countByIFPDetails(RandomTestUtil.nextInt(),
			RandomTestUtil.nextInt());

		_persistence.countByIFPDetails(0, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IfpContractDetails newIfpContractDetails = addIfpContractDetails();

		IfpContractDetails existingIfpContractDetails = _persistence.findByPrimaryKey(newIfpContractDetails.getPrimaryKey());

		Assert.assertEquals(existingIfpContractDetails, newIfpContractDetails);
	}

	@Test(expected = NoSuchIfpContractDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IfpContractDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IFP_CONTRACT_DETAILS",
			"itemStatus", true, "itemMasterSid", true,
			"ifpContractAttachedDate", true, "itemEndDate", true,
			"totalVolumeCommitment", true, "totalDollarCommitment", true,
			"ifpContractAttachedStatus", true, "modifiedDate", true,
			"totalMarketshareCommitment", true, "recordLockStatus", true,
			"createdDate", true, "createdBy", true, "source", true,
			"itemStartDate", true, "batchId", true, "ifpContractDetailsSid",
			true, "modifiedBy", true, "inboundStatus", true, "ifpContractSid",
			true, "commitmentPeriod", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IfpContractDetails newIfpContractDetails = addIfpContractDetails();

		IfpContractDetails existingIfpContractDetails = _persistence.fetchByPrimaryKey(newIfpContractDetails.getPrimaryKey());

		Assert.assertEquals(existingIfpContractDetails, newIfpContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpContractDetails missingIfpContractDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIfpContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IfpContractDetails newIfpContractDetails1 = addIfpContractDetails();
		IfpContractDetails newIfpContractDetails2 = addIfpContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpContractDetails1.getPrimaryKey());
		primaryKeys.add(newIfpContractDetails2.getPrimaryKey());

		Map<Serializable, IfpContractDetails> ifpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ifpContractDetailses.size());
		Assert.assertEquals(newIfpContractDetails1,
			ifpContractDetailses.get(newIfpContractDetails1.getPrimaryKey()));
		Assert.assertEquals(newIfpContractDetails2,
			ifpContractDetailses.get(newIfpContractDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IfpContractDetails> ifpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ifpContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IfpContractDetails newIfpContractDetails = addIfpContractDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpContractDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IfpContractDetails> ifpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ifpContractDetailses.size());
		Assert.assertEquals(newIfpContractDetails,
			ifpContractDetailses.get(newIfpContractDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IfpContractDetails> ifpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ifpContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IfpContractDetails newIfpContractDetails = addIfpContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpContractDetails.getPrimaryKey());

		Map<Serializable, IfpContractDetails> ifpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ifpContractDetailses.size());
		Assert.assertEquals(newIfpContractDetails,
			ifpContractDetailses.get(newIfpContractDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IfpContractDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IfpContractDetails>() {
				@Override
				public void performAction(IfpContractDetails ifpContractDetails) {
					Assert.assertNotNull(ifpContractDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IfpContractDetails newIfpContractDetails = addIfpContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ifpContractDetailsSid",
				newIfpContractDetails.getIfpContractDetailsSid()));

		List<IfpContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IfpContractDetails existingIfpContractDetails = result.get(0);

		Assert.assertEquals(existingIfpContractDetails, newIfpContractDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ifpContractDetailsSid",
				RandomTestUtil.nextInt()));

		List<IfpContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IfpContractDetails newIfpContractDetails = addIfpContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ifpContractDetailsSid"));

		Object newIfpContractDetailsSid = newIfpContractDetails.getIfpContractDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ifpContractDetailsSid",
				new Object[] { newIfpContractDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIfpContractDetailsSid = result.get(0);

		Assert.assertEquals(existingIfpContractDetailsSid,
			newIfpContractDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ifpContractDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ifpContractDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IfpContractDetails addIfpContractDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpContractDetails ifpContractDetails = _persistence.create(pk);

		ifpContractDetails.setItemStatus(RandomTestUtil.nextInt());

		ifpContractDetails.setItemMasterSid(RandomTestUtil.nextInt());

		ifpContractDetails.setIfpContractAttachedDate(RandomTestUtil.nextDate());

		ifpContractDetails.setItemEndDate(RandomTestUtil.nextDate());

		ifpContractDetails.setTotalVolumeCommitment(RandomTestUtil.randomString());

		ifpContractDetails.setTotalDollarCommitment(RandomTestUtil.randomString());

		ifpContractDetails.setIfpContractAttachedStatus(RandomTestUtil.nextInt());

		ifpContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		ifpContractDetails.setTotalMarketshareCommitment(RandomTestUtil.randomString());

		ifpContractDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		ifpContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		ifpContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		ifpContractDetails.setSource(RandomTestUtil.randomString());

		ifpContractDetails.setItemStartDate(RandomTestUtil.nextDate());

		ifpContractDetails.setBatchId(RandomTestUtil.randomString());

		ifpContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		ifpContractDetails.setInboundStatus(RandomTestUtil.randomString());

		ifpContractDetails.setIfpContractSid(RandomTestUtil.nextInt());

		ifpContractDetails.setCommitmentPeriod(RandomTestUtil.randomString());

		_ifpContractDetailses.add(_persistence.update(ifpContractDetails));

		return ifpContractDetails;
	}

	private List<IfpContractDetails> _ifpContractDetailses = new ArrayList<IfpContractDetails>();
	private IfpContractDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}