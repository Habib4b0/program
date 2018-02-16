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

import com.stpl.app.exception.NoSuchVwAccrualMasterException;
import com.stpl.app.model.VwAccrualMaster;
import com.stpl.app.service.VwAccrualMasterLocalServiceUtil;
import com.stpl.app.service.persistence.VwAccrualMasterPersistence;
import com.stpl.app.service.persistence.VwAccrualMasterUtil;

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
public class VwAccrualMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = VwAccrualMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwAccrualMaster> iterator = _vwAccrualMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwAccrualMaster vwAccrualMaster = _persistence.create(pk);

		Assert.assertNotNull(vwAccrualMaster);

		Assert.assertEquals(vwAccrualMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwAccrualMaster newVwAccrualMaster = addVwAccrualMaster();

		_persistence.remove(newVwAccrualMaster);

		VwAccrualMaster existingVwAccrualMaster = _persistence.fetchByPrimaryKey(newVwAccrualMaster.getPrimaryKey());

		Assert.assertNull(existingVwAccrualMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwAccrualMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwAccrualMaster newVwAccrualMaster = _persistence.create(pk);

		newVwAccrualMaster.setItemQualifier(RandomTestUtil.randomString());

		newVwAccrualMaster.setBusinessUnitQualifier(RandomTestUtil.randomString());

		newVwAccrualMaster.setItemNo(RandomTestUtil.randomString());

		newVwAccrualMaster.setPostingIndicator(RandomTestUtil.randomString());

		newVwAccrualMaster.setCreatedDate(RandomTestUtil.nextDate());

		newVwAccrualMaster.setCostCenter(RandomTestUtil.randomString());

		newVwAccrualMaster.setSubLedger(RandomTestUtil.randomString());

		newVwAccrualMaster.setAccrualPeriodEd(RandomTestUtil.nextDate());

		newVwAccrualMaster.setAccrualId(RandomTestUtil.randomString());

		newVwAccrualMaster.setCompanyQualifier(RandomTestUtil.randomString());

		newVwAccrualMaster.setContractNo(RandomTestUtil.randomString());

		newVwAccrualMaster.setBatchId(RandomTestUtil.randomString());

		newVwAccrualMaster.setItemName(RandomTestUtil.randomString());

		newVwAccrualMaster.setBrandId(RandomTestUtil.randomString());

		newVwAccrualMaster.setPostingDate(RandomTestUtil.nextDate());

		newVwAccrualMaster.setBusinessUnitName(RandomTestUtil.randomString());

		newVwAccrualMaster.setSalesId(RandomTestUtil.randomString());

		newVwAccrualMaster.setAmount(RandomTestUtil.nextDouble());

		newVwAccrualMaster.setBusinessUnitNo(RandomTestUtil.randomString());

		newVwAccrualMaster.setSubLedgerType(RandomTestUtil.randomString());

		newVwAccrualMaster.setDocumentType(RandomTestUtil.randomString());

		newVwAccrualMaster.setAccuralType(RandomTestUtil.randomString());

		newVwAccrualMaster.setCreatedBy(RandomTestUtil.nextInt());

		newVwAccrualMaster.setProgramNo(RandomTestUtil.randomString());

		newVwAccrualMaster.setCustomerId(RandomTestUtil.randomString());

		newVwAccrualMaster.setItemId(RandomTestUtil.randomString());

		newVwAccrualMaster.setContractId(RandomTestUtil.randomString());

		newVwAccrualMaster.setContractName(RandomTestUtil.randomString());

		newVwAccrualMaster.setGlAccount(RandomTestUtil.randomString());

		newVwAccrualMaster.setGlDate(RandomTestUtil.nextDate());

		newVwAccrualMaster.setBusinessUnitId(RandomTestUtil.randomString());

		newVwAccrualMaster.setProgramType(RandomTestUtil.randomString());

		newVwAccrualMaster.setCustomerName(RandomTestUtil.randomString());

		newVwAccrualMaster.setCustomerNo(RandomTestUtil.randomString());

		newVwAccrualMaster.setSource(RandomTestUtil.randomString());

		newVwAccrualMaster.setAccrualPeriodSd(RandomTestUtil.nextDate());

		_vwAccrualMasters.add(_persistence.update(newVwAccrualMaster));

		VwAccrualMaster existingVwAccrualMaster = _persistence.findByPrimaryKey(newVwAccrualMaster.getPrimaryKey());

		Assert.assertEquals(existingVwAccrualMaster.getItemQualifier(),
			newVwAccrualMaster.getItemQualifier());
		Assert.assertEquals(existingVwAccrualMaster.getBusinessUnitQualifier(),
			newVwAccrualMaster.getBusinessUnitQualifier());
		Assert.assertEquals(existingVwAccrualMaster.getItemNo(),
			newVwAccrualMaster.getItemNo());
		Assert.assertEquals(existingVwAccrualMaster.getPostingIndicator(),
			newVwAccrualMaster.getPostingIndicator());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwAccrualMaster.getCreatedDate()),
			Time.getShortTimestamp(newVwAccrualMaster.getCreatedDate()));
		Assert.assertEquals(existingVwAccrualMaster.getCostCenter(),
			newVwAccrualMaster.getCostCenter());
		Assert.assertEquals(existingVwAccrualMaster.getSubLedger(),
			newVwAccrualMaster.getSubLedger());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwAccrualMaster.getAccrualPeriodEd()),
			Time.getShortTimestamp(newVwAccrualMaster.getAccrualPeriodEd()));
		Assert.assertEquals(existingVwAccrualMaster.getAccrualId(),
			newVwAccrualMaster.getAccrualId());
		Assert.assertEquals(existingVwAccrualMaster.getCompanyQualifier(),
			newVwAccrualMaster.getCompanyQualifier());
		Assert.assertEquals(existingVwAccrualMaster.getContractNo(),
			newVwAccrualMaster.getContractNo());
		Assert.assertEquals(existingVwAccrualMaster.getBatchId(),
			newVwAccrualMaster.getBatchId());
		Assert.assertEquals(existingVwAccrualMaster.getItemName(),
			newVwAccrualMaster.getItemName());
		Assert.assertEquals(existingVwAccrualMaster.getBrandId(),
			newVwAccrualMaster.getBrandId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwAccrualMaster.getPostingDate()),
			Time.getShortTimestamp(newVwAccrualMaster.getPostingDate()));
		Assert.assertEquals(existingVwAccrualMaster.getBusinessUnitName(),
			newVwAccrualMaster.getBusinessUnitName());
		Assert.assertEquals(existingVwAccrualMaster.getSalesId(),
			newVwAccrualMaster.getSalesId());
		AssertUtils.assertEquals(existingVwAccrualMaster.getAmount(),
			newVwAccrualMaster.getAmount());
		Assert.assertEquals(existingVwAccrualMaster.getBusinessUnitNo(),
			newVwAccrualMaster.getBusinessUnitNo());
		Assert.assertEquals(existingVwAccrualMaster.getSubLedgerType(),
			newVwAccrualMaster.getSubLedgerType());
		Assert.assertEquals(existingVwAccrualMaster.getDocumentType(),
			newVwAccrualMaster.getDocumentType());
		Assert.assertEquals(existingVwAccrualMaster.getAccuralType(),
			newVwAccrualMaster.getAccuralType());
		Assert.assertEquals(existingVwAccrualMaster.getCreatedBy(),
			newVwAccrualMaster.getCreatedBy());
		Assert.assertEquals(existingVwAccrualMaster.getProgramNo(),
			newVwAccrualMaster.getProgramNo());
		Assert.assertEquals(existingVwAccrualMaster.getCustomerId(),
			newVwAccrualMaster.getCustomerId());
		Assert.assertEquals(existingVwAccrualMaster.getItemId(),
			newVwAccrualMaster.getItemId());
		Assert.assertEquals(existingVwAccrualMaster.getAccrualMasterSid(),
			newVwAccrualMaster.getAccrualMasterSid());
		Assert.assertEquals(existingVwAccrualMaster.getContractId(),
			newVwAccrualMaster.getContractId());
		Assert.assertEquals(existingVwAccrualMaster.getContractName(),
			newVwAccrualMaster.getContractName());
		Assert.assertEquals(existingVwAccrualMaster.getGlAccount(),
			newVwAccrualMaster.getGlAccount());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwAccrualMaster.getGlDate()),
			Time.getShortTimestamp(newVwAccrualMaster.getGlDate()));
		Assert.assertEquals(existingVwAccrualMaster.getBusinessUnitId(),
			newVwAccrualMaster.getBusinessUnitId());
		Assert.assertEquals(existingVwAccrualMaster.getProgramType(),
			newVwAccrualMaster.getProgramType());
		Assert.assertEquals(existingVwAccrualMaster.getCustomerName(),
			newVwAccrualMaster.getCustomerName());
		Assert.assertEquals(existingVwAccrualMaster.getCustomerNo(),
			newVwAccrualMaster.getCustomerNo());
		Assert.assertEquals(existingVwAccrualMaster.getSource(),
			newVwAccrualMaster.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwAccrualMaster.getAccrualPeriodSd()),
			Time.getShortTimestamp(newVwAccrualMaster.getAccrualPeriodSd()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwAccrualMaster newVwAccrualMaster = addVwAccrualMaster();

		VwAccrualMaster existingVwAccrualMaster = _persistence.findByPrimaryKey(newVwAccrualMaster.getPrimaryKey());

		Assert.assertEquals(existingVwAccrualMaster, newVwAccrualMaster);
	}

	@Test(expected = NoSuchVwAccrualMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwAccrualMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("vw_ACCRUAL_MASTER",
			"itemQualifier", true, "businessUnitQualifier", true, "itemNo",
			true, "postingIndicator", true, "createdDate", true, "costCenter",
			true, "subLedger", true, "accrualPeriodEd", true, "accrualId",
			true, "companyQualifier", true, "contractNo", true, "batchId",
			true, "itemName", true, "brandId", true, "postingDate", true,
			"businessUnitName", true, "salesId", true, "amount", true,
			"businessUnitNo", true, "subLedgerType", true, "documentType",
			true, "accuralType", true, "createdBy", true, "programNo", true,
			"customerId", true, "itemId", true, "accrualMasterSid", true,
			"contractId", true, "contractName", true, "glAccount", true,
			"glDate", true, "businessUnitId", true, "programType", true,
			"customerName", true, "customerNo", true, "source", true,
			"accrualPeriodSd", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwAccrualMaster newVwAccrualMaster = addVwAccrualMaster();

		VwAccrualMaster existingVwAccrualMaster = _persistence.fetchByPrimaryKey(newVwAccrualMaster.getPrimaryKey());

		Assert.assertEquals(existingVwAccrualMaster, newVwAccrualMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwAccrualMaster missingVwAccrualMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwAccrualMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwAccrualMaster newVwAccrualMaster1 = addVwAccrualMaster();
		VwAccrualMaster newVwAccrualMaster2 = addVwAccrualMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwAccrualMaster1.getPrimaryKey());
		primaryKeys.add(newVwAccrualMaster2.getPrimaryKey());

		Map<Serializable, VwAccrualMaster> vwAccrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwAccrualMasters.size());
		Assert.assertEquals(newVwAccrualMaster1,
			vwAccrualMasters.get(newVwAccrualMaster1.getPrimaryKey()));
		Assert.assertEquals(newVwAccrualMaster2,
			vwAccrualMasters.get(newVwAccrualMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwAccrualMaster> vwAccrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwAccrualMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwAccrualMaster newVwAccrualMaster = addVwAccrualMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwAccrualMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwAccrualMaster> vwAccrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwAccrualMasters.size());
		Assert.assertEquals(newVwAccrualMaster,
			vwAccrualMasters.get(newVwAccrualMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwAccrualMaster> vwAccrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwAccrualMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwAccrualMaster newVwAccrualMaster = addVwAccrualMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwAccrualMaster.getPrimaryKey());

		Map<Serializable, VwAccrualMaster> vwAccrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwAccrualMasters.size());
		Assert.assertEquals(newVwAccrualMaster,
			vwAccrualMasters.get(newVwAccrualMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwAccrualMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwAccrualMaster>() {
				@Override
				public void performAction(VwAccrualMaster vwAccrualMaster) {
					Assert.assertNotNull(vwAccrualMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwAccrualMaster newVwAccrualMaster = addVwAccrualMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwAccrualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accrualMasterSid",
				newVwAccrualMaster.getAccrualMasterSid()));

		List<VwAccrualMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwAccrualMaster existingVwAccrualMaster = result.get(0);

		Assert.assertEquals(existingVwAccrualMaster, newVwAccrualMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwAccrualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accrualMasterSid",
				RandomTestUtil.nextInt()));

		List<VwAccrualMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwAccrualMaster newVwAccrualMaster = addVwAccrualMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwAccrualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accrualMasterSid"));

		Object newAccrualMasterSid = newVwAccrualMaster.getAccrualMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("accrualMasterSid",
				new Object[] { newAccrualMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccrualMasterSid = result.get(0);

		Assert.assertEquals(existingAccrualMasterSid, newAccrualMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwAccrualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accrualMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("accrualMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwAccrualMaster addVwAccrualMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwAccrualMaster vwAccrualMaster = _persistence.create(pk);

		vwAccrualMaster.setItemQualifier(RandomTestUtil.randomString());

		vwAccrualMaster.setBusinessUnitQualifier(RandomTestUtil.randomString());

		vwAccrualMaster.setItemNo(RandomTestUtil.randomString());

		vwAccrualMaster.setPostingIndicator(RandomTestUtil.randomString());

		vwAccrualMaster.setCreatedDate(RandomTestUtil.nextDate());

		vwAccrualMaster.setCostCenter(RandomTestUtil.randomString());

		vwAccrualMaster.setSubLedger(RandomTestUtil.randomString());

		vwAccrualMaster.setAccrualPeriodEd(RandomTestUtil.nextDate());

		vwAccrualMaster.setAccrualId(RandomTestUtil.randomString());

		vwAccrualMaster.setCompanyQualifier(RandomTestUtil.randomString());

		vwAccrualMaster.setContractNo(RandomTestUtil.randomString());

		vwAccrualMaster.setBatchId(RandomTestUtil.randomString());

		vwAccrualMaster.setItemName(RandomTestUtil.randomString());

		vwAccrualMaster.setBrandId(RandomTestUtil.randomString());

		vwAccrualMaster.setPostingDate(RandomTestUtil.nextDate());

		vwAccrualMaster.setBusinessUnitName(RandomTestUtil.randomString());

		vwAccrualMaster.setSalesId(RandomTestUtil.randomString());

		vwAccrualMaster.setAmount(RandomTestUtil.nextDouble());

		vwAccrualMaster.setBusinessUnitNo(RandomTestUtil.randomString());

		vwAccrualMaster.setSubLedgerType(RandomTestUtil.randomString());

		vwAccrualMaster.setDocumentType(RandomTestUtil.randomString());

		vwAccrualMaster.setAccuralType(RandomTestUtil.randomString());

		vwAccrualMaster.setCreatedBy(RandomTestUtil.nextInt());

		vwAccrualMaster.setProgramNo(RandomTestUtil.randomString());

		vwAccrualMaster.setCustomerId(RandomTestUtil.randomString());

		vwAccrualMaster.setItemId(RandomTestUtil.randomString());

		vwAccrualMaster.setContractId(RandomTestUtil.randomString());

		vwAccrualMaster.setContractName(RandomTestUtil.randomString());

		vwAccrualMaster.setGlAccount(RandomTestUtil.randomString());

		vwAccrualMaster.setGlDate(RandomTestUtil.nextDate());

		vwAccrualMaster.setBusinessUnitId(RandomTestUtil.randomString());

		vwAccrualMaster.setProgramType(RandomTestUtil.randomString());

		vwAccrualMaster.setCustomerName(RandomTestUtil.randomString());

		vwAccrualMaster.setCustomerNo(RandomTestUtil.randomString());

		vwAccrualMaster.setSource(RandomTestUtil.randomString());

		vwAccrualMaster.setAccrualPeriodSd(RandomTestUtil.nextDate());

		_vwAccrualMasters.add(_persistence.update(vwAccrualMaster));

		return vwAccrualMaster;
	}

	private List<VwAccrualMaster> _vwAccrualMasters = new ArrayList<VwAccrualMaster>();
	private VwAccrualMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}