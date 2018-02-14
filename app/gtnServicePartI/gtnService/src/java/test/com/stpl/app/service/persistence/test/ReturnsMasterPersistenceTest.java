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

import com.stpl.app.exception.NoSuchReturnsMasterException;
import com.stpl.app.model.ReturnsMaster;
import com.stpl.app.service.ReturnsMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ReturnsMasterPersistence;
import com.stpl.app.service.persistence.ReturnsMasterUtil;

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
public class ReturnsMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ReturnsMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ReturnsMaster> iterator = _returnsMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ReturnsMaster returnsMaster = _persistence.create(pk);

		Assert.assertNotNull(returnsMaster);

		Assert.assertEquals(returnsMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ReturnsMaster newReturnsMaster = addReturnsMaster();

		_persistence.remove(newReturnsMaster);

		ReturnsMaster existingReturnsMaster = _persistence.fetchByPrimaryKey(newReturnsMaster.getPrimaryKey());

		Assert.assertNull(existingReturnsMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addReturnsMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ReturnsMaster newReturnsMaster = _persistence.create(pk);

		newReturnsMaster.setAdjValueAtOrigAsp(RandomTestUtil.randomString());

		newReturnsMaster.setItemMasterSid(RandomTestUtil.nextInt());

		newReturnsMaster.setFirstReturn(RandomTestUtil.nextDate());

		newReturnsMaster.setAsp(RandomTestUtil.randomString());

		newReturnsMaster.setMaxExpiredMonthPlusCutoff(RandomTestUtil.nextDate());

		newReturnsMaster.setPosEstimatedReturnUnits(RandomTestUtil.randomString());

		newReturnsMaster.setOrigSaleMonthCutOff(RandomTestUtil.nextDate());

		newReturnsMaster.setCalcUsed(RandomTestUtil.randomString());

		newReturnsMaster.setModifiedDate(RandomTestUtil.nextDate());

		newReturnsMaster.setBrandMasterSid(RandomTestUtil.nextInt());

		newReturnsMaster.setLastReturn(RandomTestUtil.nextDate());

		newReturnsMaster.setExpectedReturnUnits(RandomTestUtil.randomString());

		newReturnsMaster.setCreatedDate(RandomTestUtil.nextDate());

		newReturnsMaster.setCreatedBy(RandomTestUtil.randomString());

		newReturnsMaster.setSource(RandomTestUtil.randomString());

		newReturnsMaster.setVersion(RandomTestUtil.randomString());

		newReturnsMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		newReturnsMaster.setWeightedAvgMonths(RandomTestUtil.randomString());

		newReturnsMaster.setModifiedBy(RandomTestUtil.randomString());

		newReturnsMaster.setInboundStatus(RandomTestUtil.randomString());

		newReturnsMaster.setPct25th(RandomTestUtil.randomString());

		newReturnsMaster.setLoadDate(RandomTestUtil.nextDate());

		newReturnsMaster.setMaxExpiredMonth(RandomTestUtil.nextDate());

		newReturnsMaster.setActualReturnRate(RandomTestUtil.randomString());

		newReturnsMaster.setRreserveId(RandomTestUtil.randomString());

		newReturnsMaster.setReturnComplete(RandomTestUtil.randomString());

		newReturnsMaster.setExpectedReturnRate(RandomTestUtil.randomString());

		newReturnsMaster.setPct50th(RandomTestUtil.randomString());

		newReturnsMaster.setWithin50qrtile(RandomTestUtil.randomString());

		newReturnsMaster.setCumReturnUnits(RandomTestUtil.randomString());

		newReturnsMaster.setOrigSaleMonth(RandomTestUtil.nextDate());

		newReturnsMaster.setDescription(RandomTestUtil.randomString());

		newReturnsMaster.setSku(RandomTestUtil.randomString());

		newReturnsMaster.setUpperLimit(RandomTestUtil.randomString());

		newReturnsMaster.setLowerLimit(RandomTestUtil.randomString());

		newReturnsMaster.setValueAtOrigAsp(RandomTestUtil.randomString());

		newReturnsMaster.setAdjEstimatedReturnUnits(RandomTestUtil.randomString());

		newReturnsMaster.setPct75th(RandomTestUtil.randomString());

		newReturnsMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newReturnsMaster.setPastExpiration(RandomTestUtil.randomString());

		newReturnsMaster.setBatchId(RandomTestUtil.randomString());

		newReturnsMaster.setMaximum(RandomTestUtil.randomString());

		newReturnsMaster.setOrigSaleUnits(RandomTestUtil.randomString());

		newReturnsMaster.setBrand(RandomTestUtil.randomString());

		newReturnsMaster.setOrigSaleDollars(RandomTestUtil.randomString());

		_returnsMasters.add(_persistence.update(newReturnsMaster));

		ReturnsMaster existingReturnsMaster = _persistence.findByPrimaryKey(newReturnsMaster.getPrimaryKey());

		Assert.assertEquals(existingReturnsMaster.getAdjValueAtOrigAsp(),
			newReturnsMaster.getAdjValueAtOrigAsp());
		Assert.assertEquals(existingReturnsMaster.getItemMasterSid(),
			newReturnsMaster.getItemMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getFirstReturn()),
			Time.getShortTimestamp(newReturnsMaster.getFirstReturn()));
		Assert.assertEquals(existingReturnsMaster.getAsp(),
			newReturnsMaster.getAsp());
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getMaxExpiredMonthPlusCutoff()),
			Time.getShortTimestamp(
				newReturnsMaster.getMaxExpiredMonthPlusCutoff()));
		Assert.assertEquals(existingReturnsMaster.getPosEstimatedReturnUnits(),
			newReturnsMaster.getPosEstimatedReturnUnits());
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getOrigSaleMonthCutOff()),
			Time.getShortTimestamp(newReturnsMaster.getOrigSaleMonthCutOff()));
		Assert.assertEquals(existingReturnsMaster.getCalcUsed(),
			newReturnsMaster.getCalcUsed());
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getModifiedDate()),
			Time.getShortTimestamp(newReturnsMaster.getModifiedDate()));
		Assert.assertEquals(existingReturnsMaster.getBrandMasterSid(),
			newReturnsMaster.getBrandMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getLastReturn()),
			Time.getShortTimestamp(newReturnsMaster.getLastReturn()));
		Assert.assertEquals(existingReturnsMaster.getExpectedReturnUnits(),
			newReturnsMaster.getExpectedReturnUnits());
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getCreatedDate()),
			Time.getShortTimestamp(newReturnsMaster.getCreatedDate()));
		Assert.assertEquals(existingReturnsMaster.getCreatedBy(),
			newReturnsMaster.getCreatedBy());
		Assert.assertEquals(existingReturnsMaster.getSource(),
			newReturnsMaster.getSource());
		Assert.assertEquals(existingReturnsMaster.getVersion(),
			newReturnsMaster.getVersion());
		Assert.assertEquals(existingReturnsMaster.getAddChgDelIndicator(),
			newReturnsMaster.getAddChgDelIndicator());
		Assert.assertEquals(existingReturnsMaster.getReturnsMasterSid(),
			newReturnsMaster.getReturnsMasterSid());
		Assert.assertEquals(existingReturnsMaster.getWeightedAvgMonths(),
			newReturnsMaster.getWeightedAvgMonths());
		Assert.assertEquals(existingReturnsMaster.getModifiedBy(),
			newReturnsMaster.getModifiedBy());
		Assert.assertEquals(existingReturnsMaster.getInboundStatus(),
			newReturnsMaster.getInboundStatus());
		Assert.assertEquals(existingReturnsMaster.getPct25th(),
			newReturnsMaster.getPct25th());
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getLoadDate()),
			Time.getShortTimestamp(newReturnsMaster.getLoadDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getMaxExpiredMonth()),
			Time.getShortTimestamp(newReturnsMaster.getMaxExpiredMonth()));
		Assert.assertEquals(existingReturnsMaster.getActualReturnRate(),
			newReturnsMaster.getActualReturnRate());
		Assert.assertEquals(existingReturnsMaster.getRreserveId(),
			newReturnsMaster.getRreserveId());
		Assert.assertEquals(existingReturnsMaster.getReturnComplete(),
			newReturnsMaster.getReturnComplete());
		Assert.assertEquals(existingReturnsMaster.getExpectedReturnRate(),
			newReturnsMaster.getExpectedReturnRate());
		Assert.assertEquals(existingReturnsMaster.getPct50th(),
			newReturnsMaster.getPct50th());
		Assert.assertEquals(existingReturnsMaster.getWithin50qrtile(),
			newReturnsMaster.getWithin50qrtile());
		Assert.assertEquals(existingReturnsMaster.getCumReturnUnits(),
			newReturnsMaster.getCumReturnUnits());
		Assert.assertEquals(Time.getShortTimestamp(
				existingReturnsMaster.getOrigSaleMonth()),
			Time.getShortTimestamp(newReturnsMaster.getOrigSaleMonth()));
		Assert.assertEquals(existingReturnsMaster.getDescription(),
			newReturnsMaster.getDescription());
		Assert.assertEquals(existingReturnsMaster.getSku(),
			newReturnsMaster.getSku());
		Assert.assertEquals(existingReturnsMaster.getUpperLimit(),
			newReturnsMaster.getUpperLimit());
		Assert.assertEquals(existingReturnsMaster.getLowerLimit(),
			newReturnsMaster.getLowerLimit());
		Assert.assertEquals(existingReturnsMaster.getValueAtOrigAsp(),
			newReturnsMaster.getValueAtOrigAsp());
		Assert.assertEquals(existingReturnsMaster.getAdjEstimatedReturnUnits(),
			newReturnsMaster.getAdjEstimatedReturnUnits());
		Assert.assertEquals(existingReturnsMaster.getPct75th(),
			newReturnsMaster.getPct75th());
		Assert.assertEquals(existingReturnsMaster.getRecordLockStatus(),
			newReturnsMaster.getRecordLockStatus());
		Assert.assertEquals(existingReturnsMaster.getPastExpiration(),
			newReturnsMaster.getPastExpiration());
		Assert.assertEquals(existingReturnsMaster.getBatchId(),
			newReturnsMaster.getBatchId());
		Assert.assertEquals(existingReturnsMaster.getMaximum(),
			newReturnsMaster.getMaximum());
		Assert.assertEquals(existingReturnsMaster.getOrigSaleUnits(),
			newReturnsMaster.getOrigSaleUnits());
		Assert.assertEquals(existingReturnsMaster.getBrand(),
			newReturnsMaster.getBrand());
		Assert.assertEquals(existingReturnsMaster.getOrigSaleDollars(),
			newReturnsMaster.getOrigSaleDollars());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ReturnsMaster newReturnsMaster = addReturnsMaster();

		ReturnsMaster existingReturnsMaster = _persistence.findByPrimaryKey(newReturnsMaster.getPrimaryKey());

		Assert.assertEquals(existingReturnsMaster, newReturnsMaster);
	}

	@Test(expected = NoSuchReturnsMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ReturnsMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RETURNS_MASTER",
			"adjValueAtOrigAsp", true, "itemMasterSid", true, "firstReturn",
			true, "asp", true, "maxExpiredMonthPlusCutoff", true,
			"posEstimatedReturnUnits", true, "origSaleMonthCutOff", true,
			"calcUsed", true, "modifiedDate", true, "brandMasterSid", true,
			"lastReturn", true, "expectedReturnUnits", true, "createdDate",
			true, "createdBy", true, "source", true, "version", true,
			"addChgDelIndicator", true, "returnsMasterSid", true,
			"weightedAvgMonths", true, "modifiedBy", true, "inboundStatus",
			true, "pct25th", true, "loadDate", true, "maxExpiredMonth", true,
			"actualReturnRate", true, "rreserveId", true, "returnComplete",
			true, "expectedReturnRate", true, "pct50th", true,
			"within50qrtile", true, "cumReturnUnits", true, "origSaleMonth",
			true, "description", true, "sku", true, "upperLimit", true,
			"lowerLimit", true, "valueAtOrigAsp", true,
			"adjEstimatedReturnUnits", true, "pct75th", true,
			"recordLockStatus", true, "pastExpiration", true, "batchId", true,
			"maximum", true, "origSaleUnits", true, "brand", true,
			"origSaleDollars", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ReturnsMaster newReturnsMaster = addReturnsMaster();

		ReturnsMaster existingReturnsMaster = _persistence.fetchByPrimaryKey(newReturnsMaster.getPrimaryKey());

		Assert.assertEquals(existingReturnsMaster, newReturnsMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ReturnsMaster missingReturnsMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingReturnsMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ReturnsMaster newReturnsMaster1 = addReturnsMaster();
		ReturnsMaster newReturnsMaster2 = addReturnsMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newReturnsMaster1.getPrimaryKey());
		primaryKeys.add(newReturnsMaster2.getPrimaryKey());

		Map<Serializable, ReturnsMaster> returnsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, returnsMasters.size());
		Assert.assertEquals(newReturnsMaster1,
			returnsMasters.get(newReturnsMaster1.getPrimaryKey()));
		Assert.assertEquals(newReturnsMaster2,
			returnsMasters.get(newReturnsMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ReturnsMaster> returnsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(returnsMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ReturnsMaster newReturnsMaster = addReturnsMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newReturnsMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ReturnsMaster> returnsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, returnsMasters.size());
		Assert.assertEquals(newReturnsMaster,
			returnsMasters.get(newReturnsMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ReturnsMaster> returnsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(returnsMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ReturnsMaster newReturnsMaster = addReturnsMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newReturnsMaster.getPrimaryKey());

		Map<Serializable, ReturnsMaster> returnsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, returnsMasters.size());
		Assert.assertEquals(newReturnsMaster,
			returnsMasters.get(newReturnsMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ReturnsMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ReturnsMaster>() {
				@Override
				public void performAction(ReturnsMaster returnsMaster) {
					Assert.assertNotNull(returnsMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ReturnsMaster newReturnsMaster = addReturnsMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReturnsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("returnsMasterSid",
				newReturnsMaster.getReturnsMasterSid()));

		List<ReturnsMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ReturnsMaster existingReturnsMaster = result.get(0);

		Assert.assertEquals(existingReturnsMaster, newReturnsMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReturnsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("returnsMasterSid",
				RandomTestUtil.nextInt()));

		List<ReturnsMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ReturnsMaster newReturnsMaster = addReturnsMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReturnsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"returnsMasterSid"));

		Object newReturnsMasterSid = newReturnsMaster.getReturnsMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("returnsMasterSid",
				new Object[] { newReturnsMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingReturnsMasterSid = result.get(0);

		Assert.assertEquals(existingReturnsMasterSid, newReturnsMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ReturnsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"returnsMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("returnsMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ReturnsMaster addReturnsMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ReturnsMaster returnsMaster = _persistence.create(pk);

		returnsMaster.setAdjValueAtOrigAsp(RandomTestUtil.randomString());

		returnsMaster.setItemMasterSid(RandomTestUtil.nextInt());

		returnsMaster.setFirstReturn(RandomTestUtil.nextDate());

		returnsMaster.setAsp(RandomTestUtil.randomString());

		returnsMaster.setMaxExpiredMonthPlusCutoff(RandomTestUtil.nextDate());

		returnsMaster.setPosEstimatedReturnUnits(RandomTestUtil.randomString());

		returnsMaster.setOrigSaleMonthCutOff(RandomTestUtil.nextDate());

		returnsMaster.setCalcUsed(RandomTestUtil.randomString());

		returnsMaster.setModifiedDate(RandomTestUtil.nextDate());

		returnsMaster.setBrandMasterSid(RandomTestUtil.nextInt());

		returnsMaster.setLastReturn(RandomTestUtil.nextDate());

		returnsMaster.setExpectedReturnUnits(RandomTestUtil.randomString());

		returnsMaster.setCreatedDate(RandomTestUtil.nextDate());

		returnsMaster.setCreatedBy(RandomTestUtil.randomString());

		returnsMaster.setSource(RandomTestUtil.randomString());

		returnsMaster.setVersion(RandomTestUtil.randomString());

		returnsMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		returnsMaster.setWeightedAvgMonths(RandomTestUtil.randomString());

		returnsMaster.setModifiedBy(RandomTestUtil.randomString());

		returnsMaster.setInboundStatus(RandomTestUtil.randomString());

		returnsMaster.setPct25th(RandomTestUtil.randomString());

		returnsMaster.setLoadDate(RandomTestUtil.nextDate());

		returnsMaster.setMaxExpiredMonth(RandomTestUtil.nextDate());

		returnsMaster.setActualReturnRate(RandomTestUtil.randomString());

		returnsMaster.setRreserveId(RandomTestUtil.randomString());

		returnsMaster.setReturnComplete(RandomTestUtil.randomString());

		returnsMaster.setExpectedReturnRate(RandomTestUtil.randomString());

		returnsMaster.setPct50th(RandomTestUtil.randomString());

		returnsMaster.setWithin50qrtile(RandomTestUtil.randomString());

		returnsMaster.setCumReturnUnits(RandomTestUtil.randomString());

		returnsMaster.setOrigSaleMonth(RandomTestUtil.nextDate());

		returnsMaster.setDescription(RandomTestUtil.randomString());

		returnsMaster.setSku(RandomTestUtil.randomString());

		returnsMaster.setUpperLimit(RandomTestUtil.randomString());

		returnsMaster.setLowerLimit(RandomTestUtil.randomString());

		returnsMaster.setValueAtOrigAsp(RandomTestUtil.randomString());

		returnsMaster.setAdjEstimatedReturnUnits(RandomTestUtil.randomString());

		returnsMaster.setPct75th(RandomTestUtil.randomString());

		returnsMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		returnsMaster.setPastExpiration(RandomTestUtil.randomString());

		returnsMaster.setBatchId(RandomTestUtil.randomString());

		returnsMaster.setMaximum(RandomTestUtil.randomString());

		returnsMaster.setOrigSaleUnits(RandomTestUtil.randomString());

		returnsMaster.setBrand(RandomTestUtil.randomString());

		returnsMaster.setOrigSaleDollars(RandomTestUtil.randomString());

		_returnsMasters.add(_persistence.update(returnsMaster));

		return returnsMaster;
	}

	private List<ReturnsMaster> _returnsMasters = new ArrayList<ReturnsMaster>();
	private ReturnsMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}