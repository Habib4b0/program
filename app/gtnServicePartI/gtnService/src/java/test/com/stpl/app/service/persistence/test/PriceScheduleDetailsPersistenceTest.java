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
import com.liferay.portal.kernel.test.AssertUtils;
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

import com.stpl.app.exception.NoSuchPriceScheduleDetailsException;
import com.stpl.app.model.PriceScheduleDetails;
import com.stpl.app.service.PriceScheduleDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.PriceScheduleDetailsPersistence;
import com.stpl.app.service.persistence.PriceScheduleDetailsUtil;

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
public class PriceScheduleDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PriceScheduleDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PriceScheduleDetails> iterator = _priceScheduleDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PriceScheduleDetails priceScheduleDetails = _persistence.create(pk);

		Assert.assertNotNull(priceScheduleDetails);

		Assert.assertEquals(priceScheduleDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PriceScheduleDetails newPriceScheduleDetails = addPriceScheduleDetails();

		_persistence.remove(newPriceScheduleDetails);

		PriceScheduleDetails existingPriceScheduleDetails = _persistence.fetchByPrimaryKey(newPriceScheduleDetails.getPrimaryKey());

		Assert.assertNull(existingPriceScheduleDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPriceScheduleDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PriceScheduleDetails newPriceScheduleDetails = _persistence.create(pk);

		newPriceScheduleDetails.setPrice(RandomTestUtil.nextDouble());

		newPriceScheduleDetails.setCompanyFamilyplanSystemId(RandomTestUtil.nextInt());

		newPriceScheduleDetails.setItemSystemId(RandomTestUtil.nextInt());

		newPriceScheduleDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		newPriceScheduleDetails.setBasePrice(RandomTestUtil.nextDouble());

		newPriceScheduleDetails.setRevisionDate(RandomTestUtil.nextDate());

		newPriceScheduleDetails.setModifiedDate(RandomTestUtil.nextDate());

		newPriceScheduleDetails.setPriceScheduleSystemId(RandomTestUtil.nextInt());

		newPriceScheduleDetails.setItemFamilyplanSystemId(RandomTestUtil.nextInt());

		newPriceScheduleDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		newPriceScheduleDetails.setCreatedBy(RandomTestUtil.randomString());

		newPriceScheduleDetails.setCreatedDate(RandomTestUtil.nextDate());

		newPriceScheduleDetails.setSuggestedPrice(RandomTestUtil.nextDouble());

		newPriceScheduleDetails.setInboundStatus(RandomTestUtil.randomString());

		newPriceScheduleDetails.setModifiedBy(RandomTestUtil.randomString());

		newPriceScheduleDetails.setContractPrice(RandomTestUtil.nextDouble());

		newPriceScheduleDetails.setPriceToleranceType(RandomTestUtil.randomString());

		newPriceScheduleDetails.setMemberFamilyplanSystemId(RandomTestUtil.nextInt());

		newPriceScheduleDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		newPriceScheduleDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		newPriceScheduleDetails.setPriceToleranceFrequency(RandomTestUtil.randomString());

		newPriceScheduleDetails.setAttachedDate(RandomTestUtil.nextDate());

		newPriceScheduleDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		newPriceScheduleDetails.setRecordLockStatus(RandomTestUtil.randomString());

		newPriceScheduleDetails.setPricePlanId(RandomTestUtil.randomString());

		newPriceScheduleDetails.setPriceType(RandomTestUtil.randomString());

		newPriceScheduleDetails.setBatchId(RandomTestUtil.randomString());

		newPriceScheduleDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		newPriceScheduleDetails.setPriceRevision(RandomTestUtil.nextDouble());

		_priceScheduleDetailses.add(_persistence.update(newPriceScheduleDetails));

		PriceScheduleDetails existingPriceScheduleDetails = _persistence.findByPrimaryKey(newPriceScheduleDetails.getPrimaryKey());

		AssertUtils.assertEquals(existingPriceScheduleDetails.getPrice(),
			newPriceScheduleDetails.getPrice());
		Assert.assertEquals(existingPriceScheduleDetails.getPsDetailsSystemId(),
			newPriceScheduleDetails.getPsDetailsSystemId());
		Assert.assertEquals(existingPriceScheduleDetails.getCompanyFamilyplanSystemId(),
			newPriceScheduleDetails.getCompanyFamilyplanSystemId());
		Assert.assertEquals(existingPriceScheduleDetails.getItemSystemId(),
			newPriceScheduleDetails.getItemSystemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleDetails.getPriceProtectionStartDate()),
			Time.getShortTimestamp(
				newPriceScheduleDetails.getPriceProtectionStartDate()));
		AssertUtils.assertEquals(existingPriceScheduleDetails.getBasePrice(),
			newPriceScheduleDetails.getBasePrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleDetails.getRevisionDate()),
			Time.getShortTimestamp(newPriceScheduleDetails.getRevisionDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleDetails.getModifiedDate()),
			Time.getShortTimestamp(newPriceScheduleDetails.getModifiedDate()));
		Assert.assertEquals(existingPriceScheduleDetails.getPriceScheduleSystemId(),
			newPriceScheduleDetails.getPriceScheduleSystemId());
		Assert.assertEquals(existingPriceScheduleDetails.getItemFamilyplanSystemId(),
			newPriceScheduleDetails.getItemFamilyplanSystemId());
		AssertUtils.assertEquals(existingPriceScheduleDetails.getPriceTolerance(),
			newPriceScheduleDetails.getPriceTolerance());
		Assert.assertEquals(existingPriceScheduleDetails.getCreatedBy(),
			newPriceScheduleDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleDetails.getCreatedDate()),
			Time.getShortTimestamp(newPriceScheduleDetails.getCreatedDate()));
		AssertUtils.assertEquals(existingPriceScheduleDetails.getSuggestedPrice(),
			newPriceScheduleDetails.getSuggestedPrice());
		Assert.assertEquals(existingPriceScheduleDetails.getInboundStatus(),
			newPriceScheduleDetails.getInboundStatus());
		Assert.assertEquals(existingPriceScheduleDetails.getModifiedBy(),
			newPriceScheduleDetails.getModifiedBy());
		AssertUtils.assertEquals(existingPriceScheduleDetails.getContractPrice(),
			newPriceScheduleDetails.getContractPrice());
		Assert.assertEquals(existingPriceScheduleDetails.getPriceToleranceType(),
			newPriceScheduleDetails.getPriceToleranceType());
		Assert.assertEquals(existingPriceScheduleDetails.getMemberFamilyplanSystemId(),
			newPriceScheduleDetails.getMemberFamilyplanSystemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleDetails.getContractPriceEndDate()),
			Time.getShortTimestamp(
				newPriceScheduleDetails.getContractPriceEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleDetails.getContractPriceStartDate()),
			Time.getShortTimestamp(
				newPriceScheduleDetails.getContractPriceStartDate()));
		Assert.assertEquals(existingPriceScheduleDetails.getPriceToleranceFrequency(),
			newPriceScheduleDetails.getPriceToleranceFrequency());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleDetails.getAttachedDate()),
			Time.getShortTimestamp(newPriceScheduleDetails.getAttachedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleDetails.getPriceProtectionEndDate()),
			Time.getShortTimestamp(
				newPriceScheduleDetails.getPriceProtectionEndDate()));
		Assert.assertEquals(existingPriceScheduleDetails.getRecordLockStatus(),
			newPriceScheduleDetails.getRecordLockStatus());
		Assert.assertEquals(existingPriceScheduleDetails.getPricePlanId(),
			newPriceScheduleDetails.getPricePlanId());
		Assert.assertEquals(existingPriceScheduleDetails.getPriceType(),
			newPriceScheduleDetails.getPriceType());
		Assert.assertEquals(existingPriceScheduleDetails.getBatchId(),
			newPriceScheduleDetails.getBatchId());
		Assert.assertEquals(existingPriceScheduleDetails.getPriceToleranceInterval(),
			newPriceScheduleDetails.getPriceToleranceInterval());
		AssertUtils.assertEquals(existingPriceScheduleDetails.getPriceRevision(),
			newPriceScheduleDetails.getPriceRevision());
	}

	@Test
	public void testCountByPriceScheduleMasterDetails()
		throws Exception {
		_persistence.countByPriceScheduleMasterDetails(RandomTestUtil.nextInt());

		_persistence.countByPriceScheduleMasterDetails(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PriceScheduleDetails newPriceScheduleDetails = addPriceScheduleDetails();

		PriceScheduleDetails existingPriceScheduleDetails = _persistence.findByPrimaryKey(newPriceScheduleDetails.getPrimaryKey());

		Assert.assertEquals(existingPriceScheduleDetails,
			newPriceScheduleDetails);
	}

	@Test(expected = NoSuchPriceScheduleDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<PriceScheduleDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PRICE_SCHEDULE_DETAILS",
			"price", true, "psDetailsSystemId", true,
			"companyFamilyplanSystemId", true, "itemSystemId", true,
			"priceProtectionStartDate", true, "basePrice", true,
			"revisionDate", true, "modifiedDate", true,
			"priceScheduleSystemId", true, "itemFamilyplanSystemId", true,
			"priceTolerance", true, "createdBy", true, "createdDate", true,
			"suggestedPrice", true, "inboundStatus", true, "modifiedBy", true,
			"contractPrice", true, "priceToleranceType", true,
			"memberFamilyplanSystemId", true, "contractPriceEndDate", true,
			"contractPriceStartDate", true, "priceToleranceFrequency", true,
			"attachedDate", true, "priceProtectionEndDate", true,
			"recordLockStatus", true, "pricePlanId", true, "priceType", true,
			"batchId", true, "priceToleranceInterval", true, "priceRevision",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PriceScheduleDetails newPriceScheduleDetails = addPriceScheduleDetails();

		PriceScheduleDetails existingPriceScheduleDetails = _persistence.fetchByPrimaryKey(newPriceScheduleDetails.getPrimaryKey());

		Assert.assertEquals(existingPriceScheduleDetails,
			newPriceScheduleDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PriceScheduleDetails missingPriceScheduleDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPriceScheduleDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PriceScheduleDetails newPriceScheduleDetails1 = addPriceScheduleDetails();
		PriceScheduleDetails newPriceScheduleDetails2 = addPriceScheduleDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPriceScheduleDetails1.getPrimaryKey());
		primaryKeys.add(newPriceScheduleDetails2.getPrimaryKey());

		Map<Serializable, PriceScheduleDetails> priceScheduleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, priceScheduleDetailses.size());
		Assert.assertEquals(newPriceScheduleDetails1,
			priceScheduleDetailses.get(newPriceScheduleDetails1.getPrimaryKey()));
		Assert.assertEquals(newPriceScheduleDetails2,
			priceScheduleDetailses.get(newPriceScheduleDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PriceScheduleDetails> priceScheduleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(priceScheduleDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PriceScheduleDetails newPriceScheduleDetails = addPriceScheduleDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPriceScheduleDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PriceScheduleDetails> priceScheduleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, priceScheduleDetailses.size());
		Assert.assertEquals(newPriceScheduleDetails,
			priceScheduleDetailses.get(newPriceScheduleDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PriceScheduleDetails> priceScheduleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(priceScheduleDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PriceScheduleDetails newPriceScheduleDetails = addPriceScheduleDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPriceScheduleDetails.getPrimaryKey());

		Map<Serializable, PriceScheduleDetails> priceScheduleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, priceScheduleDetailses.size());
		Assert.assertEquals(newPriceScheduleDetails,
			priceScheduleDetailses.get(newPriceScheduleDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PriceScheduleDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PriceScheduleDetails>() {
				@Override
				public void performAction(
					PriceScheduleDetails priceScheduleDetails) {
					Assert.assertNotNull(priceScheduleDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PriceScheduleDetails newPriceScheduleDetails = addPriceScheduleDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PriceScheduleDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psDetailsSystemId",
				newPriceScheduleDetails.getPsDetailsSystemId()));

		List<PriceScheduleDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PriceScheduleDetails existingPriceScheduleDetails = result.get(0);

		Assert.assertEquals(existingPriceScheduleDetails,
			newPriceScheduleDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PriceScheduleDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psDetailsSystemId",
				RandomTestUtil.nextInt()));

		List<PriceScheduleDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PriceScheduleDetails newPriceScheduleDetails = addPriceScheduleDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PriceScheduleDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"psDetailsSystemId"));

		Object newPsDetailsSystemId = newPriceScheduleDetails.getPsDetailsSystemId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("psDetailsSystemId",
				new Object[] { newPsDetailsSystemId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPsDetailsSystemId = result.get(0);

		Assert.assertEquals(existingPsDetailsSystemId, newPsDetailsSystemId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PriceScheduleDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"psDetailsSystemId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("psDetailsSystemId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PriceScheduleDetails addPriceScheduleDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		PriceScheduleDetails priceScheduleDetails = _persistence.create(pk);

		priceScheduleDetails.setPrice(RandomTestUtil.nextDouble());

		priceScheduleDetails.setCompanyFamilyplanSystemId(RandomTestUtil.nextInt());

		priceScheduleDetails.setItemSystemId(RandomTestUtil.nextInt());

		priceScheduleDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		priceScheduleDetails.setBasePrice(RandomTestUtil.nextDouble());

		priceScheduleDetails.setRevisionDate(RandomTestUtil.nextDate());

		priceScheduleDetails.setModifiedDate(RandomTestUtil.nextDate());

		priceScheduleDetails.setPriceScheduleSystemId(RandomTestUtil.nextInt());

		priceScheduleDetails.setItemFamilyplanSystemId(RandomTestUtil.nextInt());

		priceScheduleDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		priceScheduleDetails.setCreatedBy(RandomTestUtil.randomString());

		priceScheduleDetails.setCreatedDate(RandomTestUtil.nextDate());

		priceScheduleDetails.setSuggestedPrice(RandomTestUtil.nextDouble());

		priceScheduleDetails.setInboundStatus(RandomTestUtil.randomString());

		priceScheduleDetails.setModifiedBy(RandomTestUtil.randomString());

		priceScheduleDetails.setContractPrice(RandomTestUtil.nextDouble());

		priceScheduleDetails.setPriceToleranceType(RandomTestUtil.randomString());

		priceScheduleDetails.setMemberFamilyplanSystemId(RandomTestUtil.nextInt());

		priceScheduleDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		priceScheduleDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		priceScheduleDetails.setPriceToleranceFrequency(RandomTestUtil.randomString());

		priceScheduleDetails.setAttachedDate(RandomTestUtil.nextDate());

		priceScheduleDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		priceScheduleDetails.setRecordLockStatus(RandomTestUtil.randomString());

		priceScheduleDetails.setPricePlanId(RandomTestUtil.randomString());

		priceScheduleDetails.setPriceType(RandomTestUtil.randomString());

		priceScheduleDetails.setBatchId(RandomTestUtil.randomString());

		priceScheduleDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		priceScheduleDetails.setPriceRevision(RandomTestUtil.nextDouble());

		_priceScheduleDetailses.add(_persistence.update(priceScheduleDetails));

		return priceScheduleDetails;
	}

	private List<PriceScheduleDetails> _priceScheduleDetailses = new ArrayList<PriceScheduleDetails>();
	private PriceScheduleDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}