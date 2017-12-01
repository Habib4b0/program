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

import com.stpl.app.exception.NoSuchIvldFormulaDetailsException;
import com.stpl.app.model.IvldFormulaDetails;
import com.stpl.app.service.IvldFormulaDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.IvldFormulaDetailsPersistence;
import com.stpl.app.service.persistence.IvldFormulaDetailsUtil;

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
public class IvldFormulaDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldFormulaDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldFormulaDetails> iterator = _ivldFormulaDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldFormulaDetails ivldFormulaDetails = _persistence.create(pk);

		Assert.assertNotNull(ivldFormulaDetails);

		Assert.assertEquals(ivldFormulaDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldFormulaDetails newIvldFormulaDetails = addIvldFormulaDetails();

		_persistence.remove(newIvldFormulaDetails);

		IvldFormulaDetails existingIvldFormulaDetails = _persistence.fetchByPrimaryKey(newIvldFormulaDetails.getPrimaryKey());

		Assert.assertNull(existingIvldFormulaDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldFormulaDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldFormulaDetails newIvldFormulaDetails = _persistence.create(pk);

		newIvldFormulaDetails.setEndDate(RandomTestUtil.randomString());

		newIvldFormulaDetails.setRebatePercent1(RandomTestUtil.randomString());

		newIvldFormulaDetails.setItemId(RandomTestUtil.randomString());

		newIvldFormulaDetails.setRebatePercent2(RandomTestUtil.randomString());

		newIvldFormulaDetails.setFormulaDesc(RandomTestUtil.randomString());

		newIvldFormulaDetails.setModifiedDate(RandomTestUtil.nextDate());

		newIvldFormulaDetails.setRebatePercent3(RandomTestUtil.randomString());

		newIvldFormulaDetails.setCreatedBy(RandomTestUtil.randomString());

		newIvldFormulaDetails.setCreatedDate(RandomTestUtil.nextDate());

		newIvldFormulaDetails.setSource(RandomTestUtil.randomString());

		newIvldFormulaDetails.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldFormulaDetails.setErrorCode(RandomTestUtil.randomString());

		newIvldFormulaDetails.setFormulaId(RandomTestUtil.randomString());

		newIvldFormulaDetails.setModifiedBy(RandomTestUtil.randomString());

		newIvldFormulaDetails.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldFormulaDetails.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldFormulaDetails.setFormulaDetailsIntfid(RandomTestUtil.randomString());

		newIvldFormulaDetails.setReasonForFailure(RandomTestUtil.randomString());

		newIvldFormulaDetails.setContractPrice1(RandomTestUtil.randomString());

		newIvldFormulaDetails.setCompanyStringId(RandomTestUtil.randomString());

		newIvldFormulaDetails.setContractPrice2(RandomTestUtil.randomString());

		newIvldFormulaDetails.setFormulaNo(RandomTestUtil.randomString());

		newIvldFormulaDetails.setStartDate(RandomTestUtil.randomString());

		newIvldFormulaDetails.setBatchId(RandomTestUtil.randomString());

		newIvldFormulaDetails.setErrorField(RandomTestUtil.randomString());

		newIvldFormulaDetails.setContractPrice3(RandomTestUtil.randomString());

		newIvldFormulaDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldFormulaDetailses.add(_persistence.update(newIvldFormulaDetails));

		IvldFormulaDetails existingIvldFormulaDetails = _persistence.findByPrimaryKey(newIvldFormulaDetails.getPrimaryKey());

		Assert.assertEquals(existingIvldFormulaDetails.getEndDate(),
			newIvldFormulaDetails.getEndDate());
		Assert.assertEquals(existingIvldFormulaDetails.getRebatePercent1(),
			newIvldFormulaDetails.getRebatePercent1());
		Assert.assertEquals(existingIvldFormulaDetails.getItemId(),
			newIvldFormulaDetails.getItemId());
		Assert.assertEquals(existingIvldFormulaDetails.getRebatePercent2(),
			newIvldFormulaDetails.getRebatePercent2());
		Assert.assertEquals(existingIvldFormulaDetails.getFormulaDesc(),
			newIvldFormulaDetails.getFormulaDesc());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldFormulaDetails.getModifiedDate()),
			Time.getShortTimestamp(newIvldFormulaDetails.getModifiedDate()));
		Assert.assertEquals(existingIvldFormulaDetails.getRebatePercent3(),
			newIvldFormulaDetails.getRebatePercent3());
		Assert.assertEquals(existingIvldFormulaDetails.getCreatedBy(),
			newIvldFormulaDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldFormulaDetails.getCreatedDate()),
			Time.getShortTimestamp(newIvldFormulaDetails.getCreatedDate()));
		Assert.assertEquals(existingIvldFormulaDetails.getSource(),
			newIvldFormulaDetails.getSource());
		Assert.assertEquals(existingIvldFormulaDetails.getAddChgDelIndicator(),
			newIvldFormulaDetails.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldFormulaDetails.getErrorCode(),
			newIvldFormulaDetails.getErrorCode());
		Assert.assertEquals(existingIvldFormulaDetails.getFormulaId(),
			newIvldFormulaDetails.getFormulaId());
		Assert.assertEquals(existingIvldFormulaDetails.getModifiedBy(),
			newIvldFormulaDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldFormulaDetails.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldFormulaDetails.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldFormulaDetails.getReprocessedFlag(),
			newIvldFormulaDetails.getReprocessedFlag());
		Assert.assertEquals(existingIvldFormulaDetails.getFormulaDetailsIntfid(),
			newIvldFormulaDetails.getFormulaDetailsIntfid());
		Assert.assertEquals(existingIvldFormulaDetails.getReasonForFailure(),
			newIvldFormulaDetails.getReasonForFailure());
		Assert.assertEquals(existingIvldFormulaDetails.getContractPrice1(),
			newIvldFormulaDetails.getContractPrice1());
		Assert.assertEquals(existingIvldFormulaDetails.getCompanyStringId(),
			newIvldFormulaDetails.getCompanyStringId());
		Assert.assertEquals(existingIvldFormulaDetails.getContractPrice2(),
			newIvldFormulaDetails.getContractPrice2());
		Assert.assertEquals(existingIvldFormulaDetails.getFormulaNo(),
			newIvldFormulaDetails.getFormulaNo());
		Assert.assertEquals(existingIvldFormulaDetails.getStartDate(),
			newIvldFormulaDetails.getStartDate());
		Assert.assertEquals(existingIvldFormulaDetails.getBatchId(),
			newIvldFormulaDetails.getBatchId());
		Assert.assertEquals(existingIvldFormulaDetails.getErrorField(),
			newIvldFormulaDetails.getErrorField());
		Assert.assertEquals(existingIvldFormulaDetails.getContractPrice3(),
			newIvldFormulaDetails.getContractPrice3());
		Assert.assertEquals(existingIvldFormulaDetails.getIvldFormulaDetailsSid(),
			newIvldFormulaDetails.getIvldFormulaDetailsSid());
		Assert.assertEquals(existingIvldFormulaDetails.getCheckRecord(),
			newIvldFormulaDetails.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldFormulaDetails newIvldFormulaDetails = addIvldFormulaDetails();

		IvldFormulaDetails existingIvldFormulaDetails = _persistence.findByPrimaryKey(newIvldFormulaDetails.getPrimaryKey());

		Assert.assertEquals(existingIvldFormulaDetails, newIvldFormulaDetails);
	}

	@Test(expected = NoSuchIvldFormulaDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldFormulaDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_FORMULA_DETAILS",
			"endDate", true, "rebatePercent1", true, "itemId", true,
			"rebatePercent2", true, "formulaDesc", true, "modifiedDate", true,
			"rebatePercent3", true, "createdBy", true, "createdDate", true,
			"source", true, "addChgDelIndicator", true, "errorCode", true,
			"formulaId", true, "modifiedBy", true, "intfInsertedDate", true,
			"reprocessedFlag", true, "formulaDetailsIntfid", true,
			"reasonForFailure", true, "contractPrice1", true,
			"companyStringId", true, "contractPrice2", true, "formulaNo", true,
			"startDate", true, "batchId", true, "errorField", true,
			"contractPrice3", true, "ivldFormulaDetailsSid", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldFormulaDetails newIvldFormulaDetails = addIvldFormulaDetails();

		IvldFormulaDetails existingIvldFormulaDetails = _persistence.fetchByPrimaryKey(newIvldFormulaDetails.getPrimaryKey());

		Assert.assertEquals(existingIvldFormulaDetails, newIvldFormulaDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldFormulaDetails missingIvldFormulaDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldFormulaDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldFormulaDetails newIvldFormulaDetails1 = addIvldFormulaDetails();
		IvldFormulaDetails newIvldFormulaDetails2 = addIvldFormulaDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldFormulaDetails1.getPrimaryKey());
		primaryKeys.add(newIvldFormulaDetails2.getPrimaryKey());

		Map<Serializable, IvldFormulaDetails> ivldFormulaDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldFormulaDetailses.size());
		Assert.assertEquals(newIvldFormulaDetails1,
			ivldFormulaDetailses.get(newIvldFormulaDetails1.getPrimaryKey()));
		Assert.assertEquals(newIvldFormulaDetails2,
			ivldFormulaDetailses.get(newIvldFormulaDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldFormulaDetails> ivldFormulaDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldFormulaDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldFormulaDetails newIvldFormulaDetails = addIvldFormulaDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldFormulaDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldFormulaDetails> ivldFormulaDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldFormulaDetailses.size());
		Assert.assertEquals(newIvldFormulaDetails,
			ivldFormulaDetailses.get(newIvldFormulaDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldFormulaDetails> ivldFormulaDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldFormulaDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldFormulaDetails newIvldFormulaDetails = addIvldFormulaDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldFormulaDetails.getPrimaryKey());

		Map<Serializable, IvldFormulaDetails> ivldFormulaDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldFormulaDetailses.size());
		Assert.assertEquals(newIvldFormulaDetails,
			ivldFormulaDetailses.get(newIvldFormulaDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldFormulaDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldFormulaDetails>() {
				@Override
				public void performAction(IvldFormulaDetails ivldFormulaDetails) {
					Assert.assertNotNull(ivldFormulaDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldFormulaDetails newIvldFormulaDetails = addIvldFormulaDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldFormulaDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldFormulaDetailsSid",
				newIvldFormulaDetails.getIvldFormulaDetailsSid()));

		List<IvldFormulaDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldFormulaDetails existingIvldFormulaDetails = result.get(0);

		Assert.assertEquals(existingIvldFormulaDetails, newIvldFormulaDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldFormulaDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldFormulaDetailsSid",
				RandomTestUtil.nextInt()));

		List<IvldFormulaDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldFormulaDetails newIvldFormulaDetails = addIvldFormulaDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldFormulaDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldFormulaDetailsSid"));

		Object newIvldFormulaDetailsSid = newIvldFormulaDetails.getIvldFormulaDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldFormulaDetailsSid",
				new Object[] { newIvldFormulaDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldFormulaDetailsSid = result.get(0);

		Assert.assertEquals(existingIvldFormulaDetailsSid,
			newIvldFormulaDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldFormulaDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldFormulaDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldFormulaDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldFormulaDetails addIvldFormulaDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldFormulaDetails ivldFormulaDetails = _persistence.create(pk);

		ivldFormulaDetails.setEndDate(RandomTestUtil.randomString());

		ivldFormulaDetails.setRebatePercent1(RandomTestUtil.randomString());

		ivldFormulaDetails.setItemId(RandomTestUtil.randomString());

		ivldFormulaDetails.setRebatePercent2(RandomTestUtil.randomString());

		ivldFormulaDetails.setFormulaDesc(RandomTestUtil.randomString());

		ivldFormulaDetails.setModifiedDate(RandomTestUtil.nextDate());

		ivldFormulaDetails.setRebatePercent3(RandomTestUtil.randomString());

		ivldFormulaDetails.setCreatedBy(RandomTestUtil.randomString());

		ivldFormulaDetails.setCreatedDate(RandomTestUtil.nextDate());

		ivldFormulaDetails.setSource(RandomTestUtil.randomString());

		ivldFormulaDetails.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldFormulaDetails.setErrorCode(RandomTestUtil.randomString());

		ivldFormulaDetails.setFormulaId(RandomTestUtil.randomString());

		ivldFormulaDetails.setModifiedBy(RandomTestUtil.randomString());

		ivldFormulaDetails.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldFormulaDetails.setReprocessedFlag(RandomTestUtil.randomString());

		ivldFormulaDetails.setFormulaDetailsIntfid(RandomTestUtil.randomString());

		ivldFormulaDetails.setReasonForFailure(RandomTestUtil.randomString());

		ivldFormulaDetails.setContractPrice1(RandomTestUtil.randomString());

		ivldFormulaDetails.setCompanyStringId(RandomTestUtil.randomString());

		ivldFormulaDetails.setContractPrice2(RandomTestUtil.randomString());

		ivldFormulaDetails.setFormulaNo(RandomTestUtil.randomString());

		ivldFormulaDetails.setStartDate(RandomTestUtil.randomString());

		ivldFormulaDetails.setBatchId(RandomTestUtil.randomString());

		ivldFormulaDetails.setErrorField(RandomTestUtil.randomString());

		ivldFormulaDetails.setContractPrice3(RandomTestUtil.randomString());

		ivldFormulaDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldFormulaDetailses.add(_persistence.update(ivldFormulaDetails));

		return ivldFormulaDetails;
	}

	private List<IvldFormulaDetails> _ivldFormulaDetailses = new ArrayList<IvldFormulaDetails>();
	private IvldFormulaDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}