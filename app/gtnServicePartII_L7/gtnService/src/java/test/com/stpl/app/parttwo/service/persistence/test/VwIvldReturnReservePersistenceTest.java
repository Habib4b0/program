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

import com.stpl.app.parttwo.exception.NoSuchVwIvldReturnReserveException;
import com.stpl.app.parttwo.model.VwIvldReturnReserve;
import com.stpl.app.parttwo.service.VwIvldReturnReserveLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwIvldReturnReservePersistence;
import com.stpl.app.parttwo.service.persistence.VwIvldReturnReserveUtil;

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
public class VwIvldReturnReservePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwIvldReturnReserveUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwIvldReturnReserve> iterator = _vwIvldReturnReserves.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldReturnReserve vwIvldReturnReserve = _persistence.create(pk);

		Assert.assertNotNull(vwIvldReturnReserve);

		Assert.assertEquals(vwIvldReturnReserve.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwIvldReturnReserve newVwIvldReturnReserve = addVwIvldReturnReserve();

		_persistence.remove(newVwIvldReturnReserve);

		VwIvldReturnReserve existingVwIvldReturnReserve = _persistence.fetchByPrimaryKey(newVwIvldReturnReserve.getPrimaryKey());

		Assert.assertNull(existingVwIvldReturnReserve);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwIvldReturnReserve();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldReturnReserve newVwIvldReturnReserve = _persistence.create(pk);

		newVwIvldReturnReserve.setCompanyName(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setYear(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setProject(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setItemId(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setBrandName(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setModifiedDate(RandomTestUtil.nextDate());

		newVwIvldReturnReserve.setAccount(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setReturnReserveIntfId(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setSource(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setCreatedDate(RandomTestUtil.nextDate());

		newVwIvldReturnReserve.setCreatedBy(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setBusinessUnit(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setBusinessUnitName(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setErrorCode(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setIntfInsertedDate(RandomTestUtil.nextDate());

		newVwIvldReturnReserve.setModifiedBy(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setItemNo(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setMonth(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setReprocessedFlag(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setUdc6(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setUdc5(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setUdc4(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setUdc1(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setUnits(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setUdc2(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setUdc3(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setReasonForFailure(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setCountry(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setCompanyId(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setCostCenter(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setGlCompany(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setBrandId(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setFuture1(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setFuture2(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setAmount(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setDivision(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setCompanyNo(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setBatchId(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setItemName(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setErrorField(RandomTestUtil.randomString());

		newVwIvldReturnReserve.setCheckRecord(RandomTestUtil.randomBoolean());

		_vwIvldReturnReserves.add(_persistence.update(newVwIvldReturnReserve));

		VwIvldReturnReserve existingVwIvldReturnReserve = _persistence.findByPrimaryKey(newVwIvldReturnReserve.getPrimaryKey());

		Assert.assertEquals(existingVwIvldReturnReserve.getIvldReturnReserveSid(),
			newVwIvldReturnReserve.getIvldReturnReserveSid());
		Assert.assertEquals(existingVwIvldReturnReserve.getCompanyName(),
			newVwIvldReturnReserve.getCompanyName());
		Assert.assertEquals(existingVwIvldReturnReserve.getYear(),
			newVwIvldReturnReserve.getYear());
		Assert.assertEquals(existingVwIvldReturnReserve.getProject(),
			newVwIvldReturnReserve.getProject());
		Assert.assertEquals(existingVwIvldReturnReserve.getItemId(),
			newVwIvldReturnReserve.getItemId());
		Assert.assertEquals(existingVwIvldReturnReserve.getBrandName(),
			newVwIvldReturnReserve.getBrandName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwIvldReturnReserve.getModifiedDate()),
			Time.getShortTimestamp(newVwIvldReturnReserve.getModifiedDate()));
		Assert.assertEquals(existingVwIvldReturnReserve.getAccount(),
			newVwIvldReturnReserve.getAccount());
		Assert.assertEquals(existingVwIvldReturnReserve.getReturnReserveIntfId(),
			newVwIvldReturnReserve.getReturnReserveIntfId());
		Assert.assertEquals(existingVwIvldReturnReserve.getSource(),
			newVwIvldReturnReserve.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwIvldReturnReserve.getCreatedDate()),
			Time.getShortTimestamp(newVwIvldReturnReserve.getCreatedDate()));
		Assert.assertEquals(existingVwIvldReturnReserve.getCreatedBy(),
			newVwIvldReturnReserve.getCreatedBy());
		Assert.assertEquals(existingVwIvldReturnReserve.getBusinessUnit(),
			newVwIvldReturnReserve.getBusinessUnit());
		Assert.assertEquals(existingVwIvldReturnReserve.getBusinessUnitName(),
			newVwIvldReturnReserve.getBusinessUnitName());
		Assert.assertEquals(existingVwIvldReturnReserve.getAddChgDelIndicator(),
			newVwIvldReturnReserve.getAddChgDelIndicator());
		Assert.assertEquals(existingVwIvldReturnReserve.getErrorCode(),
			newVwIvldReturnReserve.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwIvldReturnReserve.getIntfInsertedDate()),
			Time.getShortTimestamp(newVwIvldReturnReserve.getIntfInsertedDate()));
		Assert.assertEquals(existingVwIvldReturnReserve.getModifiedBy(),
			newVwIvldReturnReserve.getModifiedBy());
		Assert.assertEquals(existingVwIvldReturnReserve.getItemNo(),
			newVwIvldReturnReserve.getItemNo());
		Assert.assertEquals(existingVwIvldReturnReserve.getMonth(),
			newVwIvldReturnReserve.getMonth());
		Assert.assertEquals(existingVwIvldReturnReserve.getReprocessedFlag(),
			newVwIvldReturnReserve.getReprocessedFlag());
		Assert.assertEquals(existingVwIvldReturnReserve.getUdc6(),
			newVwIvldReturnReserve.getUdc6());
		Assert.assertEquals(existingVwIvldReturnReserve.getUdc5(),
			newVwIvldReturnReserve.getUdc5());
		Assert.assertEquals(existingVwIvldReturnReserve.getUdc4(),
			newVwIvldReturnReserve.getUdc4());
		Assert.assertEquals(existingVwIvldReturnReserve.getUdc1(),
			newVwIvldReturnReserve.getUdc1());
		Assert.assertEquals(existingVwIvldReturnReserve.getUnits(),
			newVwIvldReturnReserve.getUnits());
		Assert.assertEquals(existingVwIvldReturnReserve.getUdc2(),
			newVwIvldReturnReserve.getUdc2());
		Assert.assertEquals(existingVwIvldReturnReserve.getUdc3(),
			newVwIvldReturnReserve.getUdc3());
		Assert.assertEquals(existingVwIvldReturnReserve.getReasonForFailure(),
			newVwIvldReturnReserve.getReasonForFailure());
		Assert.assertEquals(existingVwIvldReturnReserve.getCountry(),
			newVwIvldReturnReserve.getCountry());
		Assert.assertEquals(existingVwIvldReturnReserve.getCompanyId(),
			newVwIvldReturnReserve.getCompanyId());
		Assert.assertEquals(existingVwIvldReturnReserve.getCostCenter(),
			newVwIvldReturnReserve.getCostCenter());
		Assert.assertEquals(existingVwIvldReturnReserve.getGlCompany(),
			newVwIvldReturnReserve.getGlCompany());
		Assert.assertEquals(existingVwIvldReturnReserve.getBrandId(),
			newVwIvldReturnReserve.getBrandId());
		Assert.assertEquals(existingVwIvldReturnReserve.getFuture1(),
			newVwIvldReturnReserve.getFuture1());
		Assert.assertEquals(existingVwIvldReturnReserve.getFuture2(),
			newVwIvldReturnReserve.getFuture2());
		Assert.assertEquals(existingVwIvldReturnReserve.getAmount(),
			newVwIvldReturnReserve.getAmount());
		Assert.assertEquals(existingVwIvldReturnReserve.getDivision(),
			newVwIvldReturnReserve.getDivision());
		Assert.assertEquals(existingVwIvldReturnReserve.getCompanyNo(),
			newVwIvldReturnReserve.getCompanyNo());
		Assert.assertEquals(existingVwIvldReturnReserve.getBatchId(),
			newVwIvldReturnReserve.getBatchId());
		Assert.assertEquals(existingVwIvldReturnReserve.getItemName(),
			newVwIvldReturnReserve.getItemName());
		Assert.assertEquals(existingVwIvldReturnReserve.getErrorField(),
			newVwIvldReturnReserve.getErrorField());
		Assert.assertEquals(existingVwIvldReturnReserve.getCheckRecord(),
			newVwIvldReturnReserve.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwIvldReturnReserve newVwIvldReturnReserve = addVwIvldReturnReserve();

		VwIvldReturnReserve existingVwIvldReturnReserve = _persistence.findByPrimaryKey(newVwIvldReturnReserve.getPrimaryKey());

		Assert.assertEquals(existingVwIvldReturnReserve, newVwIvldReturnReserve);
	}

	@Test(expected = NoSuchVwIvldReturnReserveException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwIvldReturnReserve> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_IVLD_RETURN_RESERVE",
			"ivldReturnReserveSid", true, "companyName", true, "year", true,
			"project", true, "itemId", true, "brandName", true, "modifiedDate",
			true, "account", true, "returnReserveIntfId", true, "source", true,
			"createdDate", true, "createdBy", true, "businessUnit", true,
			"businessUnitName", true, "addChgDelIndicator", true, "errorCode",
			true, "intfInsertedDate", true, "modifiedBy", true, "itemNo", true,
			"month", true, "reprocessedFlag", true, "udc6", true, "udc5", true,
			"udc4", true, "udc1", true, "units", true, "udc2", true, "udc3",
			true, "reasonForFailure", true, "country", true, "companyId", true,
			"costCenter", true, "glCompany", true, "brandId", true, "future1",
			true, "future2", true, "amount", true, "division", true,
			"companyNo", true, "batchId", true, "itemName", true, "errorField",
			true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwIvldReturnReserve newVwIvldReturnReserve = addVwIvldReturnReserve();

		VwIvldReturnReserve existingVwIvldReturnReserve = _persistence.fetchByPrimaryKey(newVwIvldReturnReserve.getPrimaryKey());

		Assert.assertEquals(existingVwIvldReturnReserve, newVwIvldReturnReserve);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldReturnReserve missingVwIvldReturnReserve = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwIvldReturnReserve);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwIvldReturnReserve newVwIvldReturnReserve1 = addVwIvldReturnReserve();
		VwIvldReturnReserve newVwIvldReturnReserve2 = addVwIvldReturnReserve();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldReturnReserve1.getPrimaryKey());
		primaryKeys.add(newVwIvldReturnReserve2.getPrimaryKey());

		Map<Serializable, VwIvldReturnReserve> vwIvldReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwIvldReturnReserves.size());
		Assert.assertEquals(newVwIvldReturnReserve1,
			vwIvldReturnReserves.get(newVwIvldReturnReserve1.getPrimaryKey()));
		Assert.assertEquals(newVwIvldReturnReserve2,
			vwIvldReturnReserves.get(newVwIvldReturnReserve2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwIvldReturnReserve> vwIvldReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwIvldReturnReserves.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwIvldReturnReserve newVwIvldReturnReserve = addVwIvldReturnReserve();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldReturnReserve.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwIvldReturnReserve> vwIvldReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwIvldReturnReserves.size());
		Assert.assertEquals(newVwIvldReturnReserve,
			vwIvldReturnReserves.get(newVwIvldReturnReserve.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwIvldReturnReserve> vwIvldReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwIvldReturnReserves.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwIvldReturnReserve newVwIvldReturnReserve = addVwIvldReturnReserve();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldReturnReserve.getPrimaryKey());

		Map<Serializable, VwIvldReturnReserve> vwIvldReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwIvldReturnReserves.size());
		Assert.assertEquals(newVwIvldReturnReserve,
			vwIvldReturnReserves.get(newVwIvldReturnReserve.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwIvldReturnReserveLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwIvldReturnReserve>() {
				@Override
				public void performAction(
					VwIvldReturnReserve vwIvldReturnReserve) {
					Assert.assertNotNull(vwIvldReturnReserve);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwIvldReturnReserve newVwIvldReturnReserve = addVwIvldReturnReserve();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldReturnReserve.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldReturnReserveSid",
				newVwIvldReturnReserve.getIvldReturnReserveSid()));

		List<VwIvldReturnReserve> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwIvldReturnReserve existingVwIvldReturnReserve = result.get(0);

		Assert.assertEquals(existingVwIvldReturnReserve, newVwIvldReturnReserve);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldReturnReserve.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldReturnReserveSid",
				RandomTestUtil.nextInt()));

		List<VwIvldReturnReserve> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwIvldReturnReserve newVwIvldReturnReserve = addVwIvldReturnReserve();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldReturnReserve.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldReturnReserveSid"));

		Object newIvldReturnReserveSid = newVwIvldReturnReserve.getIvldReturnReserveSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldReturnReserveSid",
				new Object[] { newIvldReturnReserveSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldReturnReserveSid = result.get(0);

		Assert.assertEquals(existingIvldReturnReserveSid,
			newIvldReturnReserveSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldReturnReserve.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldReturnReserveSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldReturnReserveSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwIvldReturnReserve addVwIvldReturnReserve()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldReturnReserve vwIvldReturnReserve = _persistence.create(pk);

		vwIvldReturnReserve.setCompanyName(RandomTestUtil.randomString());

		vwIvldReturnReserve.setYear(RandomTestUtil.randomString());

		vwIvldReturnReserve.setProject(RandomTestUtil.randomString());

		vwIvldReturnReserve.setItemId(RandomTestUtil.randomString());

		vwIvldReturnReserve.setBrandName(RandomTestUtil.randomString());

		vwIvldReturnReserve.setModifiedDate(RandomTestUtil.nextDate());

		vwIvldReturnReserve.setAccount(RandomTestUtil.randomString());

		vwIvldReturnReserve.setReturnReserveIntfId(RandomTestUtil.randomString());

		vwIvldReturnReserve.setSource(RandomTestUtil.randomString());

		vwIvldReturnReserve.setCreatedDate(RandomTestUtil.nextDate());

		vwIvldReturnReserve.setCreatedBy(RandomTestUtil.randomString());

		vwIvldReturnReserve.setBusinessUnit(RandomTestUtil.randomString());

		vwIvldReturnReserve.setBusinessUnitName(RandomTestUtil.randomString());

		vwIvldReturnReserve.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwIvldReturnReserve.setErrorCode(RandomTestUtil.randomString());

		vwIvldReturnReserve.setIntfInsertedDate(RandomTestUtil.nextDate());

		vwIvldReturnReserve.setModifiedBy(RandomTestUtil.randomString());

		vwIvldReturnReserve.setItemNo(RandomTestUtil.randomString());

		vwIvldReturnReserve.setMonth(RandomTestUtil.randomString());

		vwIvldReturnReserve.setReprocessedFlag(RandomTestUtil.randomString());

		vwIvldReturnReserve.setUdc6(RandomTestUtil.randomString());

		vwIvldReturnReserve.setUdc5(RandomTestUtil.randomString());

		vwIvldReturnReserve.setUdc4(RandomTestUtil.randomString());

		vwIvldReturnReserve.setUdc1(RandomTestUtil.randomString());

		vwIvldReturnReserve.setUnits(RandomTestUtil.randomString());

		vwIvldReturnReserve.setUdc2(RandomTestUtil.randomString());

		vwIvldReturnReserve.setUdc3(RandomTestUtil.randomString());

		vwIvldReturnReserve.setReasonForFailure(RandomTestUtil.randomString());

		vwIvldReturnReserve.setCountry(RandomTestUtil.randomString());

		vwIvldReturnReserve.setCompanyId(RandomTestUtil.randomString());

		vwIvldReturnReserve.setCostCenter(RandomTestUtil.randomString());

		vwIvldReturnReserve.setGlCompany(RandomTestUtil.randomString());

		vwIvldReturnReserve.setBrandId(RandomTestUtil.randomString());

		vwIvldReturnReserve.setFuture1(RandomTestUtil.randomString());

		vwIvldReturnReserve.setFuture2(RandomTestUtil.randomString());

		vwIvldReturnReserve.setAmount(RandomTestUtil.randomString());

		vwIvldReturnReserve.setDivision(RandomTestUtil.randomString());

		vwIvldReturnReserve.setCompanyNo(RandomTestUtil.randomString());

		vwIvldReturnReserve.setBatchId(RandomTestUtil.randomString());

		vwIvldReturnReserve.setItemName(RandomTestUtil.randomString());

		vwIvldReturnReserve.setErrorField(RandomTestUtil.randomString());

		vwIvldReturnReserve.setCheckRecord(RandomTestUtil.randomBoolean());

		_vwIvldReturnReserves.add(_persistence.update(vwIvldReturnReserve));

		return vwIvldReturnReserve;
	}

	private List<VwIvldReturnReserve> _vwIvldReturnReserves = new ArrayList<VwIvldReturnReserve>();
	private VwIvldReturnReservePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}