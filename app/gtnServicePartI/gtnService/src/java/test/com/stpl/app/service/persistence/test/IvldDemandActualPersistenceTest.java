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

import com.stpl.app.exception.NoSuchIvldDemandActualException;
import com.stpl.app.model.IvldDemandActual;
import com.stpl.app.service.IvldDemandActualLocalServiceUtil;
import com.stpl.app.service.persistence.IvldDemandActualPersistence;
import com.stpl.app.service.persistence.IvldDemandActualUtil;

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
public class IvldDemandActualPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldDemandActualUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldDemandActual> iterator = _ivldDemandActuals.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldDemandActual ivldDemandActual = _persistence.create(pk);

		Assert.assertNotNull(ivldDemandActual);

		Assert.assertEquals(ivldDemandActual.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldDemandActual newIvldDemandActual = addIvldDemandActual();

		_persistence.remove(newIvldDemandActual);

		IvldDemandActual existingIvldDemandActual = _persistence.fetchByPrimaryKey(newIvldDemandActual.getPrimaryKey());

		Assert.assertNull(existingIvldDemandActual);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldDemandActual();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldDemandActual newIvldDemandActual = _persistence.create(pk);

		newIvldDemandActual.setForecastYear(RandomTestUtil.randomString());

		newIvldDemandActual.setGrossUnits(RandomTestUtil.randomString());

		newIvldDemandActual.setTotalDemandUnits(RandomTestUtil.randomString());

		newIvldDemandActual.setItemId(RandomTestUtil.randomString());

		newIvldDemandActual.setModifiedDate(RandomTestUtil.nextDate());

		newIvldDemandActual.setOrganizationKey(RandomTestUtil.randomString());

		newIvldDemandActual.setSource(RandomTestUtil.randomString());

		newIvldDemandActual.setMarketShareRatio(RandomTestUtil.randomString());

		newIvldDemandActual.setCreatedBy(RandomTestUtil.randomString());

		newIvldDemandActual.setCreatedDate(RandomTestUtil.nextDate());

		newIvldDemandActual.setDemandActualInterfaceId(RandomTestUtil.randomString());

		newIvldDemandActual.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldDemandActual.setItemIdentifier(RandomTestUtil.randomString());

		newIvldDemandActual.setErrorCode(RandomTestUtil.randomString());

		newIvldDemandActual.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldDemandActual.setMarketShareUnits(RandomTestUtil.randomString());

		newIvldDemandActual.setModifiedBy(RandomTestUtil.randomString());

		newIvldDemandActual.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldDemandActual.setReasonForFailure(RandomTestUtil.randomString());

		newIvldDemandActual.setCountry(RandomTestUtil.randomString());

		newIvldDemandActual.setForecastType(RandomTestUtil.randomString());

		newIvldDemandActual.setBrandId(RandomTestUtil.randomString());

		newIvldDemandActual.setGrossPrice(RandomTestUtil.randomString());

		newIvldDemandActual.setGrossAmount(RandomTestUtil.randomString());

		newIvldDemandActual.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldDemandActual.setBatchId(RandomTestUtil.randomString());

		newIvldDemandActual.setForecastMonth(RandomTestUtil.randomString());

		newIvldDemandActual.setErrorField(RandomTestUtil.randomString());

		newIvldDemandActual.setNetSalesPrice(RandomTestUtil.randomString());

		newIvldDemandActual.setNetSalesAmount(RandomTestUtil.randomString());

		newIvldDemandActual.setSegment(RandomTestUtil.randomString());

		newIvldDemandActual.setTotalDemandAmount(RandomTestUtil.randomString());

		newIvldDemandActual.setMarketSizeUnits(RandomTestUtil.randomString());

		newIvldDemandActual.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldDemandActuals.add(_persistence.update(newIvldDemandActual));

		IvldDemandActual existingIvldDemandActual = _persistence.findByPrimaryKey(newIvldDemandActual.getPrimaryKey());

		Assert.assertEquals(existingIvldDemandActual.getForecastYear(),
			newIvldDemandActual.getForecastYear());
		Assert.assertEquals(existingIvldDemandActual.getGrossUnits(),
			newIvldDemandActual.getGrossUnits());
		Assert.assertEquals(existingIvldDemandActual.getTotalDemandUnits(),
			newIvldDemandActual.getTotalDemandUnits());
		Assert.assertEquals(existingIvldDemandActual.getItemId(),
			newIvldDemandActual.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldDemandActual.getModifiedDate()),
			Time.getShortTimestamp(newIvldDemandActual.getModifiedDate()));
		Assert.assertEquals(existingIvldDemandActual.getOrganizationKey(),
			newIvldDemandActual.getOrganizationKey());
		Assert.assertEquals(existingIvldDemandActual.getSource(),
			newIvldDemandActual.getSource());
		Assert.assertEquals(existingIvldDemandActual.getMarketShareRatio(),
			newIvldDemandActual.getMarketShareRatio());
		Assert.assertEquals(existingIvldDemandActual.getCreatedBy(),
			newIvldDemandActual.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldDemandActual.getCreatedDate()),
			Time.getShortTimestamp(newIvldDemandActual.getCreatedDate()));
		Assert.assertEquals(existingIvldDemandActual.getDemandActualInterfaceId(),
			newIvldDemandActual.getDemandActualInterfaceId());
		Assert.assertEquals(existingIvldDemandActual.getAddChgDelIndicator(),
			newIvldDemandActual.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldDemandActual.getItemIdentifier(),
			newIvldDemandActual.getItemIdentifier());
		Assert.assertEquals(existingIvldDemandActual.getErrorCode(),
			newIvldDemandActual.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldDemandActual.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldDemandActual.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldDemandActual.getMarketShareUnits(),
			newIvldDemandActual.getMarketShareUnits());
		Assert.assertEquals(existingIvldDemandActual.getModifiedBy(),
			newIvldDemandActual.getModifiedBy());
		Assert.assertEquals(existingIvldDemandActual.getReprocessedFlag(),
			newIvldDemandActual.getReprocessedFlag());
		Assert.assertEquals(existingIvldDemandActual.getReasonForFailure(),
			newIvldDemandActual.getReasonForFailure());
		Assert.assertEquals(existingIvldDemandActual.getCountry(),
			newIvldDemandActual.getCountry());
		Assert.assertEquals(existingIvldDemandActual.getForecastType(),
			newIvldDemandActual.getForecastType());
		Assert.assertEquals(existingIvldDemandActual.getBrandId(),
			newIvldDemandActual.getBrandId());
		Assert.assertEquals(existingIvldDemandActual.getGrossPrice(),
			newIvldDemandActual.getGrossPrice());
		Assert.assertEquals(existingIvldDemandActual.getIvldDemandActualSid(),
			newIvldDemandActual.getIvldDemandActualSid());
		Assert.assertEquals(existingIvldDemandActual.getGrossAmount(),
			newIvldDemandActual.getGrossAmount());
		Assert.assertEquals(existingIvldDemandActual.getItemIdentifierCodeQualifier(),
			newIvldDemandActual.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldDemandActual.getBatchId(),
			newIvldDemandActual.getBatchId());
		Assert.assertEquals(existingIvldDemandActual.getForecastMonth(),
			newIvldDemandActual.getForecastMonth());
		Assert.assertEquals(existingIvldDemandActual.getErrorField(),
			newIvldDemandActual.getErrorField());
		Assert.assertEquals(existingIvldDemandActual.getNetSalesPrice(),
			newIvldDemandActual.getNetSalesPrice());
		Assert.assertEquals(existingIvldDemandActual.getNetSalesAmount(),
			newIvldDemandActual.getNetSalesAmount());
		Assert.assertEquals(existingIvldDemandActual.getSegment(),
			newIvldDemandActual.getSegment());
		Assert.assertEquals(existingIvldDemandActual.getTotalDemandAmount(),
			newIvldDemandActual.getTotalDemandAmount());
		Assert.assertEquals(existingIvldDemandActual.getMarketSizeUnits(),
			newIvldDemandActual.getMarketSizeUnits());
		Assert.assertEquals(existingIvldDemandActual.getCheckRecord(),
			newIvldDemandActual.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldDemandActual newIvldDemandActual = addIvldDemandActual();

		IvldDemandActual existingIvldDemandActual = _persistence.findByPrimaryKey(newIvldDemandActual.getPrimaryKey());

		Assert.assertEquals(existingIvldDemandActual, newIvldDemandActual);
	}

	@Test(expected = NoSuchIvldDemandActualException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldDemandActual> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_DEMAND_ACTUAL",
			"forecastYear", true, "grossUnits", true, "totalDemandUnits", true,
			"itemId", true, "modifiedDate", true, "organizationKey", true,
			"source", true, "marketShareRatio", true, "createdBy", true,
			"createdDate", true, "demandActualInterfaceId", true,
			"addChgDelIndicator", true, "itemIdentifier", true, "errorCode",
			true, "intfInsertedDate", true, "marketShareUnits", true,
			"modifiedBy", true, "reprocessedFlag", true, "reasonForFailure",
			true, "country", true, "forecastType", true, "brandId", true,
			"grossPrice", true, "ivldDemandActualSid", true, "grossAmount",
			true, "itemIdentifierCodeQualifier", true, "batchId", true,
			"forecastMonth", true, "errorField", true, "netSalesPrice", true,
			"netSalesAmount", true, "segment", true, "totalDemandAmount", true,
			"marketSizeUnits", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldDemandActual newIvldDemandActual = addIvldDemandActual();

		IvldDemandActual existingIvldDemandActual = _persistence.fetchByPrimaryKey(newIvldDemandActual.getPrimaryKey());

		Assert.assertEquals(existingIvldDemandActual, newIvldDemandActual);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldDemandActual missingIvldDemandActual = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldDemandActual);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldDemandActual newIvldDemandActual1 = addIvldDemandActual();
		IvldDemandActual newIvldDemandActual2 = addIvldDemandActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldDemandActual1.getPrimaryKey());
		primaryKeys.add(newIvldDemandActual2.getPrimaryKey());

		Map<Serializable, IvldDemandActual> ivldDemandActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldDemandActuals.size());
		Assert.assertEquals(newIvldDemandActual1,
			ivldDemandActuals.get(newIvldDemandActual1.getPrimaryKey()));
		Assert.assertEquals(newIvldDemandActual2,
			ivldDemandActuals.get(newIvldDemandActual2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldDemandActual> ivldDemandActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldDemandActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldDemandActual newIvldDemandActual = addIvldDemandActual();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldDemandActual.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldDemandActual> ivldDemandActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldDemandActuals.size());
		Assert.assertEquals(newIvldDemandActual,
			ivldDemandActuals.get(newIvldDemandActual.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldDemandActual> ivldDemandActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldDemandActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldDemandActual newIvldDemandActual = addIvldDemandActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldDemandActual.getPrimaryKey());

		Map<Serializable, IvldDemandActual> ivldDemandActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldDemandActuals.size());
		Assert.assertEquals(newIvldDemandActual,
			ivldDemandActuals.get(newIvldDemandActual.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldDemandActualLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldDemandActual>() {
				@Override
				public void performAction(IvldDemandActual ivldDemandActual) {
					Assert.assertNotNull(ivldDemandActual);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldDemandActual newIvldDemandActual = addIvldDemandActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldDemandActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldDemandActualSid",
				newIvldDemandActual.getIvldDemandActualSid()));

		List<IvldDemandActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldDemandActual existingIvldDemandActual = result.get(0);

		Assert.assertEquals(existingIvldDemandActual, newIvldDemandActual);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldDemandActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldDemandActualSid",
				RandomTestUtil.nextInt()));

		List<IvldDemandActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldDemandActual newIvldDemandActual = addIvldDemandActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldDemandActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldDemandActualSid"));

		Object newIvldDemandActualSid = newIvldDemandActual.getIvldDemandActualSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldDemandActualSid",
				new Object[] { newIvldDemandActualSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldDemandActualSid = result.get(0);

		Assert.assertEquals(existingIvldDemandActualSid, newIvldDemandActualSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldDemandActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldDemandActualSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldDemandActualSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldDemandActual addIvldDemandActual() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldDemandActual ivldDemandActual = _persistence.create(pk);

		ivldDemandActual.setForecastYear(RandomTestUtil.randomString());

		ivldDemandActual.setGrossUnits(RandomTestUtil.randomString());

		ivldDemandActual.setTotalDemandUnits(RandomTestUtil.randomString());

		ivldDemandActual.setItemId(RandomTestUtil.randomString());

		ivldDemandActual.setModifiedDate(RandomTestUtil.nextDate());

		ivldDemandActual.setOrganizationKey(RandomTestUtil.randomString());

		ivldDemandActual.setSource(RandomTestUtil.randomString());

		ivldDemandActual.setMarketShareRatio(RandomTestUtil.randomString());

		ivldDemandActual.setCreatedBy(RandomTestUtil.randomString());

		ivldDemandActual.setCreatedDate(RandomTestUtil.nextDate());

		ivldDemandActual.setDemandActualInterfaceId(RandomTestUtil.randomString());

		ivldDemandActual.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldDemandActual.setItemIdentifier(RandomTestUtil.randomString());

		ivldDemandActual.setErrorCode(RandomTestUtil.randomString());

		ivldDemandActual.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldDemandActual.setMarketShareUnits(RandomTestUtil.randomString());

		ivldDemandActual.setModifiedBy(RandomTestUtil.randomString());

		ivldDemandActual.setReprocessedFlag(RandomTestUtil.randomString());

		ivldDemandActual.setReasonForFailure(RandomTestUtil.randomString());

		ivldDemandActual.setCountry(RandomTestUtil.randomString());

		ivldDemandActual.setForecastType(RandomTestUtil.randomString());

		ivldDemandActual.setBrandId(RandomTestUtil.randomString());

		ivldDemandActual.setGrossPrice(RandomTestUtil.randomString());

		ivldDemandActual.setGrossAmount(RandomTestUtil.randomString());

		ivldDemandActual.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldDemandActual.setBatchId(RandomTestUtil.randomString());

		ivldDemandActual.setForecastMonth(RandomTestUtil.randomString());

		ivldDemandActual.setErrorField(RandomTestUtil.randomString());

		ivldDemandActual.setNetSalesPrice(RandomTestUtil.randomString());

		ivldDemandActual.setNetSalesAmount(RandomTestUtil.randomString());

		ivldDemandActual.setSegment(RandomTestUtil.randomString());

		ivldDemandActual.setTotalDemandAmount(RandomTestUtil.randomString());

		ivldDemandActual.setMarketSizeUnits(RandomTestUtil.randomString());

		ivldDemandActual.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldDemandActuals.add(_persistence.update(ivldDemandActual));

		return ivldDemandActual;
	}

	private List<IvldDemandActual> _ivldDemandActuals = new ArrayList<IvldDemandActual>();
	private IvldDemandActualPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}