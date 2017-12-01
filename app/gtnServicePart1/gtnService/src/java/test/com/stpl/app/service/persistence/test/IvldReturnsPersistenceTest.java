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

import com.stpl.app.exception.NoSuchIvldReturnsException;
import com.stpl.app.model.IvldReturns;
import com.stpl.app.service.IvldReturnsLocalServiceUtil;
import com.stpl.app.service.persistence.IvldReturnsPersistence;
import com.stpl.app.service.persistence.IvldReturnsUtil;

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
public class IvldReturnsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldReturnsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldReturns> iterator = _ivldReturnses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldReturns ivldReturns = _persistence.create(pk);

		Assert.assertNotNull(ivldReturns);

		Assert.assertEquals(ivldReturns.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldReturns newIvldReturns = addIvldReturns();

		_persistence.remove(newIvldReturns);

		IvldReturns existingIvldReturns = _persistence.fetchByPrimaryKey(newIvldReturns.getPrimaryKey());

		Assert.assertNull(existingIvldReturns);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldReturns();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldReturns newIvldReturns = _persistence.create(pk);

		newIvldReturns.setAdjValueAtOrigAsp(RandomTestUtil.randomString());

		newIvldReturns.setFirstReturn(RandomTestUtil.randomString());

		newIvldReturns.setAsp(RandomTestUtil.randomString());

		newIvldReturns.setMaxExpiredMonthPlusCutoff(RandomTestUtil.randomString());

		newIvldReturns.setPosEstimatedReturnUnits(RandomTestUtil.randomString());

		newIvldReturns.setOrigSaleMonthCutOff(RandomTestUtil.randomString());

		newIvldReturns.setCalcUsed(RandomTestUtil.randomString());

		newIvldReturns.setModifiedDate(RandomTestUtil.nextDate());

		newIvldReturns.setLastReturn(RandomTestUtil.randomString());

		newIvldReturns.setExpectedReturnUnits(RandomTestUtil.randomString());

		newIvldReturns.setCreatedDate(RandomTestUtil.nextDate());

		newIvldReturns.setCreatedBy(RandomTestUtil.randomString());

		newIvldReturns.setSource(RandomTestUtil.randomString());

		newIvldReturns.setVersion(RandomTestUtil.randomString());

		newIvldReturns.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldReturns.setWeightedAvgMonths(RandomTestUtil.randomString());

		newIvldReturns.setErrorCode(RandomTestUtil.randomString());

		newIvldReturns.setModifiedBy(RandomTestUtil.randomString());

		newIvldReturns.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldReturns.setPct25th(RandomTestUtil.randomString());

		newIvldReturns.setLoadDate(RandomTestUtil.randomString());

		newIvldReturns.setMaxExpiredMonth(RandomTestUtil.randomString());

		newIvldReturns.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldReturns.setActualReturnRate(RandomTestUtil.randomString());

		newIvldReturns.setRreserveId(RandomTestUtil.randomString());

		newIvldReturns.setReturnComplete(RandomTestUtil.randomString());

		newIvldReturns.setExpectedReturnRate(RandomTestUtil.randomString());

		newIvldReturns.setPct50th(RandomTestUtil.randomString());

		newIvldReturns.setWithin50qrtile(RandomTestUtil.randomString());

		newIvldReturns.setRreserveInterfaceId(RandomTestUtil.nextInt());

		newIvldReturns.setCumReturnUnits(RandomTestUtil.randomString());

		newIvldReturns.setReasonForFailure(RandomTestUtil.randomString());

		newIvldReturns.setOrigSaleMonth(RandomTestUtil.randomString());

		newIvldReturns.setDescription(RandomTestUtil.randomString());

		newIvldReturns.setSku(RandomTestUtil.randomString());

		newIvldReturns.setUpperLimit(RandomTestUtil.randomString());

		newIvldReturns.setLowerLimit(RandomTestUtil.randomString());

		newIvldReturns.setValueAtOrigAsp(RandomTestUtil.randomString());

		newIvldReturns.setAdjEstimatedReturnUnits(RandomTestUtil.randomString());

		newIvldReturns.setPct75th(RandomTestUtil.randomString());

		newIvldReturns.setPastExpiration(RandomTestUtil.randomString());

		newIvldReturns.setBatchId(RandomTestUtil.randomString());

		newIvldReturns.setMaximum(RandomTestUtil.randomString());

		newIvldReturns.setOrigSaleUnits(RandomTestUtil.randomString());

		newIvldReturns.setErrorField(RandomTestUtil.randomString());

		newIvldReturns.setBrand(RandomTestUtil.randomString());

		newIvldReturns.setOrigSaleDollars(RandomTestUtil.randomString());

		newIvldReturns.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldReturnses.add(_persistence.update(newIvldReturns));

		IvldReturns existingIvldReturns = _persistence.findByPrimaryKey(newIvldReturns.getPrimaryKey());

		Assert.assertEquals(existingIvldReturns.getAdjValueAtOrigAsp(),
			newIvldReturns.getAdjValueAtOrigAsp());
		Assert.assertEquals(existingIvldReturns.getFirstReturn(),
			newIvldReturns.getFirstReturn());
		Assert.assertEquals(existingIvldReturns.getAsp(),
			newIvldReturns.getAsp());
		Assert.assertEquals(existingIvldReturns.getMaxExpiredMonthPlusCutoff(),
			newIvldReturns.getMaxExpiredMonthPlusCutoff());
		Assert.assertEquals(existingIvldReturns.getPosEstimatedReturnUnits(),
			newIvldReturns.getPosEstimatedReturnUnits());
		Assert.assertEquals(existingIvldReturns.getOrigSaleMonthCutOff(),
			newIvldReturns.getOrigSaleMonthCutOff());
		Assert.assertEquals(existingIvldReturns.getCalcUsed(),
			newIvldReturns.getCalcUsed());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldReturns.getModifiedDate()),
			Time.getShortTimestamp(newIvldReturns.getModifiedDate()));
		Assert.assertEquals(existingIvldReturns.getLastReturn(),
			newIvldReturns.getLastReturn());
		Assert.assertEquals(existingIvldReturns.getExpectedReturnUnits(),
			newIvldReturns.getExpectedReturnUnits());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldReturns.getCreatedDate()),
			Time.getShortTimestamp(newIvldReturns.getCreatedDate()));
		Assert.assertEquals(existingIvldReturns.getCreatedBy(),
			newIvldReturns.getCreatedBy());
		Assert.assertEquals(existingIvldReturns.getSource(),
			newIvldReturns.getSource());
		Assert.assertEquals(existingIvldReturns.getVersion(),
			newIvldReturns.getVersion());
		Assert.assertEquals(existingIvldReturns.getAddChgDelIndicator(),
			newIvldReturns.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldReturns.getWeightedAvgMonths(),
			newIvldReturns.getWeightedAvgMonths());
		Assert.assertEquals(existingIvldReturns.getErrorCode(),
			newIvldReturns.getErrorCode());
		Assert.assertEquals(existingIvldReturns.getModifiedBy(),
			newIvldReturns.getModifiedBy());
		Assert.assertEquals(existingIvldReturns.getIvldReturnsSid(),
			newIvldReturns.getIvldReturnsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldReturns.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldReturns.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldReturns.getPct25th(),
			newIvldReturns.getPct25th());
		Assert.assertEquals(existingIvldReturns.getLoadDate(),
			newIvldReturns.getLoadDate());
		Assert.assertEquals(existingIvldReturns.getMaxExpiredMonth(),
			newIvldReturns.getMaxExpiredMonth());
		Assert.assertEquals(existingIvldReturns.getReprocessedFlag(),
			newIvldReturns.getReprocessedFlag());
		Assert.assertEquals(existingIvldReturns.getActualReturnRate(),
			newIvldReturns.getActualReturnRate());
		Assert.assertEquals(existingIvldReturns.getRreserveId(),
			newIvldReturns.getRreserveId());
		Assert.assertEquals(existingIvldReturns.getReturnComplete(),
			newIvldReturns.getReturnComplete());
		Assert.assertEquals(existingIvldReturns.getExpectedReturnRate(),
			newIvldReturns.getExpectedReturnRate());
		Assert.assertEquals(existingIvldReturns.getPct50th(),
			newIvldReturns.getPct50th());
		Assert.assertEquals(existingIvldReturns.getWithin50qrtile(),
			newIvldReturns.getWithin50qrtile());
		Assert.assertEquals(existingIvldReturns.getRreserveInterfaceId(),
			newIvldReturns.getRreserveInterfaceId());
		Assert.assertEquals(existingIvldReturns.getCumReturnUnits(),
			newIvldReturns.getCumReturnUnits());
		Assert.assertEquals(existingIvldReturns.getReasonForFailure(),
			newIvldReturns.getReasonForFailure());
		Assert.assertEquals(existingIvldReturns.getOrigSaleMonth(),
			newIvldReturns.getOrigSaleMonth());
		Assert.assertEquals(existingIvldReturns.getDescription(),
			newIvldReturns.getDescription());
		Assert.assertEquals(existingIvldReturns.getSku(),
			newIvldReturns.getSku());
		Assert.assertEquals(existingIvldReturns.getUpperLimit(),
			newIvldReturns.getUpperLimit());
		Assert.assertEquals(existingIvldReturns.getLowerLimit(),
			newIvldReturns.getLowerLimit());
		Assert.assertEquals(existingIvldReturns.getValueAtOrigAsp(),
			newIvldReturns.getValueAtOrigAsp());
		Assert.assertEquals(existingIvldReturns.getAdjEstimatedReturnUnits(),
			newIvldReturns.getAdjEstimatedReturnUnits());
		Assert.assertEquals(existingIvldReturns.getPct75th(),
			newIvldReturns.getPct75th());
		Assert.assertEquals(existingIvldReturns.getPastExpiration(),
			newIvldReturns.getPastExpiration());
		Assert.assertEquals(existingIvldReturns.getBatchId(),
			newIvldReturns.getBatchId());
		Assert.assertEquals(existingIvldReturns.getMaximum(),
			newIvldReturns.getMaximum());
		Assert.assertEquals(existingIvldReturns.getOrigSaleUnits(),
			newIvldReturns.getOrigSaleUnits());
		Assert.assertEquals(existingIvldReturns.getErrorField(),
			newIvldReturns.getErrorField());
		Assert.assertEquals(existingIvldReturns.getBrand(),
			newIvldReturns.getBrand());
		Assert.assertEquals(existingIvldReturns.getOrigSaleDollars(),
			newIvldReturns.getOrigSaleDollars());
		Assert.assertEquals(existingIvldReturns.getCheckRecord(),
			newIvldReturns.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldReturns newIvldReturns = addIvldReturns();

		IvldReturns existingIvldReturns = _persistence.findByPrimaryKey(newIvldReturns.getPrimaryKey());

		Assert.assertEquals(existingIvldReturns, newIvldReturns);
	}

	@Test(expected = NoSuchIvldReturnsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldReturns> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_RETURNS",
			"adjValueAtOrigAsp", true, "firstReturn", true, "asp", true,
			"maxExpiredMonthPlusCutoff", true, "posEstimatedReturnUnits", true,
			"origSaleMonthCutOff", true, "calcUsed", true, "modifiedDate",
			true, "lastReturn", true, "expectedReturnUnits", true,
			"createdDate", true, "createdBy", true, "source", true, "version",
			true, "addChgDelIndicator", true, "weightedAvgMonths", true,
			"errorCode", true, "modifiedBy", true, "ivldReturnsSid", true,
			"intfInsertedDate", true, "pct25th", true, "loadDate", true,
			"maxExpiredMonth", true, "reprocessedFlag", true,
			"actualReturnRate", true, "rreserveId", true, "returnComplete",
			true, "expectedReturnRate", true, "pct50th", true,
			"within50qrtile", true, "rreserveInterfaceId", true,
			"cumReturnUnits", true, "reasonForFailure", true, "origSaleMonth",
			true, "description", true, "sku", true, "upperLimit", true,
			"lowerLimit", true, "valueAtOrigAsp", true,
			"adjEstimatedReturnUnits", true, "pct75th", true, "pastExpiration",
			true, "batchId", true, "maximum", true, "origSaleUnits", true,
			"errorField", true, "brand", true, "origSaleDollars", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldReturns newIvldReturns = addIvldReturns();

		IvldReturns existingIvldReturns = _persistence.fetchByPrimaryKey(newIvldReturns.getPrimaryKey());

		Assert.assertEquals(existingIvldReturns, newIvldReturns);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldReturns missingIvldReturns = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldReturns);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldReturns newIvldReturns1 = addIvldReturns();
		IvldReturns newIvldReturns2 = addIvldReturns();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldReturns1.getPrimaryKey());
		primaryKeys.add(newIvldReturns2.getPrimaryKey());

		Map<Serializable, IvldReturns> ivldReturnses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldReturnses.size());
		Assert.assertEquals(newIvldReturns1,
			ivldReturnses.get(newIvldReturns1.getPrimaryKey()));
		Assert.assertEquals(newIvldReturns2,
			ivldReturnses.get(newIvldReturns2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldReturns> ivldReturnses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldReturnses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldReturns newIvldReturns = addIvldReturns();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldReturns.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldReturns> ivldReturnses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldReturnses.size());
		Assert.assertEquals(newIvldReturns,
			ivldReturnses.get(newIvldReturns.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldReturns> ivldReturnses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldReturnses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldReturns newIvldReturns = addIvldReturns();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldReturns.getPrimaryKey());

		Map<Serializable, IvldReturns> ivldReturnses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldReturnses.size());
		Assert.assertEquals(newIvldReturns,
			ivldReturnses.get(newIvldReturns.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldReturnsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldReturns>() {
				@Override
				public void performAction(IvldReturns ivldReturns) {
					Assert.assertNotNull(ivldReturns);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldReturns newIvldReturns = addIvldReturns();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldReturns.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldReturnsSid",
				newIvldReturns.getIvldReturnsSid()));

		List<IvldReturns> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldReturns existingIvldReturns = result.get(0);

		Assert.assertEquals(existingIvldReturns, newIvldReturns);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldReturns.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldReturnsSid",
				RandomTestUtil.nextInt()));

		List<IvldReturns> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldReturns newIvldReturns = addIvldReturns();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldReturns.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldReturnsSid"));

		Object newIvldReturnsSid = newIvldReturns.getIvldReturnsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldReturnsSid",
				new Object[] { newIvldReturnsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldReturnsSid = result.get(0);

		Assert.assertEquals(existingIvldReturnsSid, newIvldReturnsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldReturns.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldReturnsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldReturnsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldReturns addIvldReturns() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldReturns ivldReturns = _persistence.create(pk);

		ivldReturns.setAdjValueAtOrigAsp(RandomTestUtil.randomString());

		ivldReturns.setFirstReturn(RandomTestUtil.randomString());

		ivldReturns.setAsp(RandomTestUtil.randomString());

		ivldReturns.setMaxExpiredMonthPlusCutoff(RandomTestUtil.randomString());

		ivldReturns.setPosEstimatedReturnUnits(RandomTestUtil.randomString());

		ivldReturns.setOrigSaleMonthCutOff(RandomTestUtil.randomString());

		ivldReturns.setCalcUsed(RandomTestUtil.randomString());

		ivldReturns.setModifiedDate(RandomTestUtil.nextDate());

		ivldReturns.setLastReturn(RandomTestUtil.randomString());

		ivldReturns.setExpectedReturnUnits(RandomTestUtil.randomString());

		ivldReturns.setCreatedDate(RandomTestUtil.nextDate());

		ivldReturns.setCreatedBy(RandomTestUtil.randomString());

		ivldReturns.setSource(RandomTestUtil.randomString());

		ivldReturns.setVersion(RandomTestUtil.randomString());

		ivldReturns.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldReturns.setWeightedAvgMonths(RandomTestUtil.randomString());

		ivldReturns.setErrorCode(RandomTestUtil.randomString());

		ivldReturns.setModifiedBy(RandomTestUtil.randomString());

		ivldReturns.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldReturns.setPct25th(RandomTestUtil.randomString());

		ivldReturns.setLoadDate(RandomTestUtil.randomString());

		ivldReturns.setMaxExpiredMonth(RandomTestUtil.randomString());

		ivldReturns.setReprocessedFlag(RandomTestUtil.randomString());

		ivldReturns.setActualReturnRate(RandomTestUtil.randomString());

		ivldReturns.setRreserveId(RandomTestUtil.randomString());

		ivldReturns.setReturnComplete(RandomTestUtil.randomString());

		ivldReturns.setExpectedReturnRate(RandomTestUtil.randomString());

		ivldReturns.setPct50th(RandomTestUtil.randomString());

		ivldReturns.setWithin50qrtile(RandomTestUtil.randomString());

		ivldReturns.setRreserveInterfaceId(RandomTestUtil.nextInt());

		ivldReturns.setCumReturnUnits(RandomTestUtil.randomString());

		ivldReturns.setReasonForFailure(RandomTestUtil.randomString());

		ivldReturns.setOrigSaleMonth(RandomTestUtil.randomString());

		ivldReturns.setDescription(RandomTestUtil.randomString());

		ivldReturns.setSku(RandomTestUtil.randomString());

		ivldReturns.setUpperLimit(RandomTestUtil.randomString());

		ivldReturns.setLowerLimit(RandomTestUtil.randomString());

		ivldReturns.setValueAtOrigAsp(RandomTestUtil.randomString());

		ivldReturns.setAdjEstimatedReturnUnits(RandomTestUtil.randomString());

		ivldReturns.setPct75th(RandomTestUtil.randomString());

		ivldReturns.setPastExpiration(RandomTestUtil.randomString());

		ivldReturns.setBatchId(RandomTestUtil.randomString());

		ivldReturns.setMaximum(RandomTestUtil.randomString());

		ivldReturns.setOrigSaleUnits(RandomTestUtil.randomString());

		ivldReturns.setErrorField(RandomTestUtil.randomString());

		ivldReturns.setBrand(RandomTestUtil.randomString());

		ivldReturns.setOrigSaleDollars(RandomTestUtil.randomString());

		ivldReturns.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldReturnses.add(_persistence.update(ivldReturns));

		return ivldReturns;
	}

	private List<IvldReturns> _ivldReturnses = new ArrayList<IvldReturns>();
	private IvldReturnsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}