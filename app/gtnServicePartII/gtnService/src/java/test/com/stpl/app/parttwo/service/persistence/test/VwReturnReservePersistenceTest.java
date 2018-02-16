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

import com.stpl.app.parttwo.exception.NoSuchVwReturnReserveException;
import com.stpl.app.parttwo.model.VwReturnReserve;
import com.stpl.app.parttwo.service.VwReturnReserveLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwReturnReservePersistence;
import com.stpl.app.parttwo.service.persistence.VwReturnReserveUtil;

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
public class VwReturnReservePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwReturnReserveUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwReturnReserve> iterator = _vwReturnReserves.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwReturnReserve vwReturnReserve = _persistence.create(pk);

		Assert.assertNotNull(vwReturnReserve);

		Assert.assertEquals(vwReturnReserve.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwReturnReserve newVwReturnReserve = addVwReturnReserve();

		_persistence.remove(newVwReturnReserve);

		VwReturnReserve existingVwReturnReserve = _persistence.fetchByPrimaryKey(newVwReturnReserve.getPrimaryKey());

		Assert.assertNull(existingVwReturnReserve);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwReturnReserve();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwReturnReserve newVwReturnReserve = _persistence.create(pk);

		newVwReturnReserve.setItemMasterSid(RandomTestUtil.nextInt());

		newVwReturnReserve.setCompanyName(RandomTestUtil.randomString());

		newVwReturnReserve.setProject(RandomTestUtil.randomString());

		newVwReturnReserve.setYear(RandomTestUtil.randomString());

		newVwReturnReserve.setItemId(RandomTestUtil.randomString());

		newVwReturnReserve.setBrandName(RandomTestUtil.randomString());

		newVwReturnReserve.setModifiedDate(RandomTestUtil.nextDate());

		newVwReturnReserve.setBrandMasterSid(RandomTestUtil.nextInt());

		newVwReturnReserve.setAccount(RandomTestUtil.randomString());

		newVwReturnReserve.setReturnReserveIntfId(RandomTestUtil.nextInt());

		newVwReturnReserve.setGlCompanyMasterSid(RandomTestUtil.nextInt());

		newVwReturnReserve.setSource(RandomTestUtil.randomString());

		newVwReturnReserve.setCreatedDate(RandomTestUtil.nextDate());

		newVwReturnReserve.setCreatedBy(RandomTestUtil.randomString());

		newVwReturnReserve.setBusinessUnit(RandomTestUtil.randomString());

		newVwReturnReserve.setBusinessUnitName(RandomTestUtil.randomString());

		newVwReturnReserve.setBuCompanyMasterSid(RandomTestUtil.nextInt());

		newVwReturnReserve.setInboundStatus(RandomTestUtil.randomString());

		newVwReturnReserve.setModifiedBy(RandomTestUtil.randomString());

		newVwReturnReserve.setItemNo(RandomTestUtil.randomString());

		newVwReturnReserve.setMonth(RandomTestUtil.randomString());

		newVwReturnReserve.setUdc6(RandomTestUtil.randomString());

		newVwReturnReserve.setUdc5(RandomTestUtil.randomString());

		newVwReturnReserve.setUdc4(RandomTestUtil.randomString());

		newVwReturnReserve.setUdc1(RandomTestUtil.randomString());

		newVwReturnReserve.setUnits(RandomTestUtil.randomString());

		newVwReturnReserve.setUdc2(RandomTestUtil.randomString());

		newVwReturnReserve.setUdc3(RandomTestUtil.randomString());

		newVwReturnReserve.setCountry(RandomTestUtil.randomString());

		newVwReturnReserve.setCompanyIdString(RandomTestUtil.randomString());

		newVwReturnReserve.setCostCenter(RandomTestUtil.randomString());

		newVwReturnReserve.setGlCompany(RandomTestUtil.randomString());

		newVwReturnReserve.setBrandId(RandomTestUtil.randomString());

		newVwReturnReserve.setFuture1(RandomTestUtil.randomString());

		newVwReturnReserve.setFuture2(RandomTestUtil.randomString());

		newVwReturnReserve.setAmount(RandomTestUtil.randomString());

		newVwReturnReserve.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newVwReturnReserve.setDivision(RandomTestUtil.randomString());

		newVwReturnReserve.setCompanyNo(RandomTestUtil.randomString());

		newVwReturnReserve.setBatchId(RandomTestUtil.randomString());

		newVwReturnReserve.setItemName(RandomTestUtil.randomString());

		_vwReturnReserves.add(_persistence.update(newVwReturnReserve));

		VwReturnReserve existingVwReturnReserve = _persistence.findByPrimaryKey(newVwReturnReserve.getPrimaryKey());

		Assert.assertEquals(existingVwReturnReserve.getItemMasterSid(),
			newVwReturnReserve.getItemMasterSid());
		Assert.assertEquals(existingVwReturnReserve.getCompanyName(),
			newVwReturnReserve.getCompanyName());
		Assert.assertEquals(existingVwReturnReserve.getProject(),
			newVwReturnReserve.getProject());
		Assert.assertEquals(existingVwReturnReserve.getYear(),
			newVwReturnReserve.getYear());
		Assert.assertEquals(existingVwReturnReserve.getItemId(),
			newVwReturnReserve.getItemId());
		Assert.assertEquals(existingVwReturnReserve.getBrandName(),
			newVwReturnReserve.getBrandName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwReturnReserve.getModifiedDate()),
			Time.getShortTimestamp(newVwReturnReserve.getModifiedDate()));
		Assert.assertEquals(existingVwReturnReserve.getBrandMasterSid(),
			newVwReturnReserve.getBrandMasterSid());
		Assert.assertEquals(existingVwReturnReserve.getAccount(),
			newVwReturnReserve.getAccount());
		Assert.assertEquals(existingVwReturnReserve.getReturnReserveIntfId(),
			newVwReturnReserve.getReturnReserveIntfId());
		Assert.assertEquals(existingVwReturnReserve.getGlCompanyMasterSid(),
			newVwReturnReserve.getGlCompanyMasterSid());
		Assert.assertEquals(existingVwReturnReserve.getSource(),
			newVwReturnReserve.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwReturnReserve.getCreatedDate()),
			Time.getShortTimestamp(newVwReturnReserve.getCreatedDate()));
		Assert.assertEquals(existingVwReturnReserve.getCreatedBy(),
			newVwReturnReserve.getCreatedBy());
		Assert.assertEquals(existingVwReturnReserve.getBusinessUnit(),
			newVwReturnReserve.getBusinessUnit());
		Assert.assertEquals(existingVwReturnReserve.getBusinessUnitName(),
			newVwReturnReserve.getBusinessUnitName());
		Assert.assertEquals(existingVwReturnReserve.getBuCompanyMasterSid(),
			newVwReturnReserve.getBuCompanyMasterSid());
		Assert.assertEquals(existingVwReturnReserve.getInboundStatus(),
			newVwReturnReserve.getInboundStatus());
		Assert.assertEquals(existingVwReturnReserve.getModifiedBy(),
			newVwReturnReserve.getModifiedBy());
		Assert.assertEquals(existingVwReturnReserve.getItemNo(),
			newVwReturnReserve.getItemNo());
		Assert.assertEquals(existingVwReturnReserve.getMonth(),
			newVwReturnReserve.getMonth());
		Assert.assertEquals(existingVwReturnReserve.getUdc6(),
			newVwReturnReserve.getUdc6());
		Assert.assertEquals(existingVwReturnReserve.getUdc5(),
			newVwReturnReserve.getUdc5());
		Assert.assertEquals(existingVwReturnReserve.getUdc4(),
			newVwReturnReserve.getUdc4());
		Assert.assertEquals(existingVwReturnReserve.getUdc1(),
			newVwReturnReserve.getUdc1());
		Assert.assertEquals(existingVwReturnReserve.getUnits(),
			newVwReturnReserve.getUnits());
		Assert.assertEquals(existingVwReturnReserve.getUdc2(),
			newVwReturnReserve.getUdc2());
		Assert.assertEquals(existingVwReturnReserve.getUdc3(),
			newVwReturnReserve.getUdc3());
		Assert.assertEquals(existingVwReturnReserve.getCountry(),
			newVwReturnReserve.getCountry());
		Assert.assertEquals(existingVwReturnReserve.getCompanyIdString(),
			newVwReturnReserve.getCompanyIdString());
		Assert.assertEquals(existingVwReturnReserve.getCostCenter(),
			newVwReturnReserve.getCostCenter());
		Assert.assertEquals(existingVwReturnReserve.getGlCompany(),
			newVwReturnReserve.getGlCompany());
		Assert.assertEquals(existingVwReturnReserve.getBrandId(),
			newVwReturnReserve.getBrandId());
		Assert.assertEquals(existingVwReturnReserve.getFuture1(),
			newVwReturnReserve.getFuture1());
		Assert.assertEquals(existingVwReturnReserve.getFuture2(),
			newVwReturnReserve.getFuture2());
		Assert.assertEquals(existingVwReturnReserve.getAmount(),
			newVwReturnReserve.getAmount());
		Assert.assertEquals(existingVwReturnReserve.getRecordLockStatus(),
			newVwReturnReserve.getRecordLockStatus());
		Assert.assertEquals(existingVwReturnReserve.getDivision(),
			newVwReturnReserve.getDivision());
		Assert.assertEquals(existingVwReturnReserve.getReturnReserveSid(),
			newVwReturnReserve.getReturnReserveSid());
		Assert.assertEquals(existingVwReturnReserve.getCompanyNo(),
			newVwReturnReserve.getCompanyNo());
		Assert.assertEquals(existingVwReturnReserve.getBatchId(),
			newVwReturnReserve.getBatchId());
		Assert.assertEquals(existingVwReturnReserve.getItemName(),
			newVwReturnReserve.getItemName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwReturnReserve newVwReturnReserve = addVwReturnReserve();

		VwReturnReserve existingVwReturnReserve = _persistence.findByPrimaryKey(newVwReturnReserve.getPrimaryKey());

		Assert.assertEquals(existingVwReturnReserve, newVwReturnReserve);
	}

	@Test(expected = NoSuchVwReturnReserveException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwReturnReserve> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_RETURN_RESERVE",
			"itemMasterSid", true, "companyName", true, "project", true,
			"year", true, "itemId", true, "brandName", true, "modifiedDate",
			true, "brandMasterSid", true, "account", true,
			"returnReserveIntfId", true, "glCompanyMasterSid", true, "source",
			true, "createdDate", true, "createdBy", true, "businessUnit", true,
			"businessUnitName", true, "buCompanyMasterSid", true,
			"inboundStatus", true, "modifiedBy", true, "itemNo", true, "month",
			true, "udc6", true, "udc5", true, "udc4", true, "udc1", true,
			"units", true, "udc2", true, "udc3", true, "country", true,
			"companyIdString", true, "costCenter", true, "glCompany", true,
			"brandId", true, "future1", true, "future2", true, "amount", true,
			"recordLockStatus", true, "division", true, "returnReserveSid",
			true, "companyNo", true, "batchId", true, "itemName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwReturnReserve newVwReturnReserve = addVwReturnReserve();

		VwReturnReserve existingVwReturnReserve = _persistence.fetchByPrimaryKey(newVwReturnReserve.getPrimaryKey());

		Assert.assertEquals(existingVwReturnReserve, newVwReturnReserve);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwReturnReserve missingVwReturnReserve = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwReturnReserve);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwReturnReserve newVwReturnReserve1 = addVwReturnReserve();
		VwReturnReserve newVwReturnReserve2 = addVwReturnReserve();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwReturnReserve1.getPrimaryKey());
		primaryKeys.add(newVwReturnReserve2.getPrimaryKey());

		Map<Serializable, VwReturnReserve> vwReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwReturnReserves.size());
		Assert.assertEquals(newVwReturnReserve1,
			vwReturnReserves.get(newVwReturnReserve1.getPrimaryKey()));
		Assert.assertEquals(newVwReturnReserve2,
			vwReturnReserves.get(newVwReturnReserve2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwReturnReserve> vwReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwReturnReserves.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwReturnReserve newVwReturnReserve = addVwReturnReserve();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwReturnReserve.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwReturnReserve> vwReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwReturnReserves.size());
		Assert.assertEquals(newVwReturnReserve,
			vwReturnReserves.get(newVwReturnReserve.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwReturnReserve> vwReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwReturnReserves.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwReturnReserve newVwReturnReserve = addVwReturnReserve();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwReturnReserve.getPrimaryKey());

		Map<Serializable, VwReturnReserve> vwReturnReserves = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwReturnReserves.size());
		Assert.assertEquals(newVwReturnReserve,
			vwReturnReserves.get(newVwReturnReserve.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwReturnReserveLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwReturnReserve>() {
				@Override
				public void performAction(VwReturnReserve vwReturnReserve) {
					Assert.assertNotNull(vwReturnReserve);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwReturnReserve newVwReturnReserve = addVwReturnReserve();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwReturnReserve.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("returnReserveSid",
				newVwReturnReserve.getReturnReserveSid()));

		List<VwReturnReserve> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwReturnReserve existingVwReturnReserve = result.get(0);

		Assert.assertEquals(existingVwReturnReserve, newVwReturnReserve);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwReturnReserve.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("returnReserveSid",
				RandomTestUtil.nextInt()));

		List<VwReturnReserve> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwReturnReserve newVwReturnReserve = addVwReturnReserve();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwReturnReserve.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"returnReserveSid"));

		Object newReturnReserveSid = newVwReturnReserve.getReturnReserveSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("returnReserveSid",
				new Object[] { newReturnReserveSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingReturnReserveSid = result.get(0);

		Assert.assertEquals(existingReturnReserveSid, newReturnReserveSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwReturnReserve.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"returnReserveSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("returnReserveSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwReturnReserve addVwReturnReserve() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwReturnReserve vwReturnReserve = _persistence.create(pk);

		vwReturnReserve.setItemMasterSid(RandomTestUtil.nextInt());

		vwReturnReserve.setCompanyName(RandomTestUtil.randomString());

		vwReturnReserve.setProject(RandomTestUtil.randomString());

		vwReturnReserve.setYear(RandomTestUtil.randomString());

		vwReturnReserve.setItemId(RandomTestUtil.randomString());

		vwReturnReserve.setBrandName(RandomTestUtil.randomString());

		vwReturnReserve.setModifiedDate(RandomTestUtil.nextDate());

		vwReturnReserve.setBrandMasterSid(RandomTestUtil.nextInt());

		vwReturnReserve.setAccount(RandomTestUtil.randomString());

		vwReturnReserve.setReturnReserveIntfId(RandomTestUtil.nextInt());

		vwReturnReserve.setGlCompanyMasterSid(RandomTestUtil.nextInt());

		vwReturnReserve.setSource(RandomTestUtil.randomString());

		vwReturnReserve.setCreatedDate(RandomTestUtil.nextDate());

		vwReturnReserve.setCreatedBy(RandomTestUtil.randomString());

		vwReturnReserve.setBusinessUnit(RandomTestUtil.randomString());

		vwReturnReserve.setBusinessUnitName(RandomTestUtil.randomString());

		vwReturnReserve.setBuCompanyMasterSid(RandomTestUtil.nextInt());

		vwReturnReserve.setInboundStatus(RandomTestUtil.randomString());

		vwReturnReserve.setModifiedBy(RandomTestUtil.randomString());

		vwReturnReserve.setItemNo(RandomTestUtil.randomString());

		vwReturnReserve.setMonth(RandomTestUtil.randomString());

		vwReturnReserve.setUdc6(RandomTestUtil.randomString());

		vwReturnReserve.setUdc5(RandomTestUtil.randomString());

		vwReturnReserve.setUdc4(RandomTestUtil.randomString());

		vwReturnReserve.setUdc1(RandomTestUtil.randomString());

		vwReturnReserve.setUnits(RandomTestUtil.randomString());

		vwReturnReserve.setUdc2(RandomTestUtil.randomString());

		vwReturnReserve.setUdc3(RandomTestUtil.randomString());

		vwReturnReserve.setCountry(RandomTestUtil.randomString());

		vwReturnReserve.setCompanyIdString(RandomTestUtil.randomString());

		vwReturnReserve.setCostCenter(RandomTestUtil.randomString());

		vwReturnReserve.setGlCompany(RandomTestUtil.randomString());

		vwReturnReserve.setBrandId(RandomTestUtil.randomString());

		vwReturnReserve.setFuture1(RandomTestUtil.randomString());

		vwReturnReserve.setFuture2(RandomTestUtil.randomString());

		vwReturnReserve.setAmount(RandomTestUtil.randomString());

		vwReturnReserve.setRecordLockStatus(RandomTestUtil.randomBoolean());

		vwReturnReserve.setDivision(RandomTestUtil.randomString());

		vwReturnReserve.setCompanyNo(RandomTestUtil.randomString());

		vwReturnReserve.setBatchId(RandomTestUtil.randomString());

		vwReturnReserve.setItemName(RandomTestUtil.randomString());

		_vwReturnReserves.add(_persistence.update(vwReturnReserve));

		return vwReturnReserve;
	}

	private List<VwReturnReserve> _vwReturnReserves = new ArrayList<VwReturnReserve>();
	private VwReturnReservePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}