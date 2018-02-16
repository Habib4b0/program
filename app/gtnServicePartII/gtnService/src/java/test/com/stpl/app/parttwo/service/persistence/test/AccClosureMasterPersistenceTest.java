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

import com.stpl.app.parttwo.exception.NoSuchAccClosureMasterException;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.AccClosureMasterPersistence;
import com.stpl.app.parttwo.service.persistence.AccClosureMasterUtil;

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
public class AccClosureMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = AccClosureMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AccClosureMaster> iterator = _accClosureMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureMaster accClosureMaster = _persistence.create(pk);

		Assert.assertNotNull(accClosureMaster);

		Assert.assertEquals(accClosureMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AccClosureMaster newAccClosureMaster = addAccClosureMaster();

		_persistence.remove(newAccClosureMaster);

		AccClosureMaster existingAccClosureMaster = _persistence.fetchByPrimaryKey(newAccClosureMaster.getPrimaryKey());

		Assert.assertNull(existingAccClosureMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAccClosureMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureMaster newAccClosureMaster = _persistence.create(pk);

		newAccClosureMaster.setSaveFlag(RandomTestUtil.randomBoolean());

		newAccClosureMaster.setAccountNo(RandomTestUtil.randomString());

		newAccClosureMaster.setToDate(RandomTestUtil.nextDate());

		newAccClosureMaster.setItemMasterSid(RandomTestUtil.nextInt());

		newAccClosureMaster.setDescription(RandomTestUtil.randomString());

		newAccClosureMaster.setReportName(RandomTestUtil.randomString());

		newAccClosureMaster.setRsType(RandomTestUtil.nextInt());

		newAccClosureMaster.setProductIdentifier(RandomTestUtil.randomString());

		newAccClosureMaster.setModifiedDate(RandomTestUtil.nextDate());

		newAccClosureMaster.setWorkflowStatus(RandomTestUtil.nextInt());

		newAccClosureMaster.setModuleType(RandomTestUtil.randomString());

		newAccClosureMaster.setFromDate(RandomTestUtil.nextDate());

		newAccClosureMaster.setContractType(RandomTestUtil.nextInt());

		newAccClosureMaster.setGlCompanyMasterSid(RandomTestUtil.nextInt());

		newAccClosureMaster.setCreatedDate(RandomTestUtil.nextDate());

		newAccClosureMaster.setCreatedBy(RandomTestUtil.nextInt());

		newAccClosureMaster.setContractMasterSid(RandomTestUtil.nextInt());

		newAccClosureMaster.setAccrualPeriod(RandomTestUtil.randomString());

		newAccClosureMaster.setCompanyGroupSid(RandomTestUtil.randomString());

		newAccClosureMaster.setRsCategory(RandomTestUtil.nextInt());

		newAccClosureMaster.setAdjustmentType(RandomTestUtil.nextInt());

		newAccClosureMaster.setModifiedBy(RandomTestUtil.nextInt());

		newAccClosureMaster.setItemGroupSid(RandomTestUtil.randomString());

		newAccClosureMaster.setRebateProgramType(RandomTestUtil.nextInt());

		_accClosureMasters.add(_persistence.update(newAccClosureMaster));

		AccClosureMaster existingAccClosureMaster = _persistence.findByPrimaryKey(newAccClosureMaster.getPrimaryKey());

		Assert.assertEquals(existingAccClosureMaster.getSaveFlag(),
			newAccClosureMaster.getSaveFlag());
		Assert.assertEquals(existingAccClosureMaster.getAccountNo(),
			newAccClosureMaster.getAccountNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccClosureMaster.getToDate()),
			Time.getShortTimestamp(newAccClosureMaster.getToDate()));
		Assert.assertEquals(existingAccClosureMaster.getItemMasterSid(),
			newAccClosureMaster.getItemMasterSid());
		Assert.assertEquals(existingAccClosureMaster.getDescription(),
			newAccClosureMaster.getDescription());
		Assert.assertEquals(existingAccClosureMaster.getReportName(),
			newAccClosureMaster.getReportName());
		Assert.assertEquals(existingAccClosureMaster.getRsType(),
			newAccClosureMaster.getRsType());
		Assert.assertEquals(existingAccClosureMaster.getProductIdentifier(),
			newAccClosureMaster.getProductIdentifier());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccClosureMaster.getModifiedDate()),
			Time.getShortTimestamp(newAccClosureMaster.getModifiedDate()));
		Assert.assertEquals(existingAccClosureMaster.getWorkflowStatus(),
			newAccClosureMaster.getWorkflowStatus());
		Assert.assertEquals(existingAccClosureMaster.getModuleType(),
			newAccClosureMaster.getModuleType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccClosureMaster.getFromDate()),
			Time.getShortTimestamp(newAccClosureMaster.getFromDate()));
		Assert.assertEquals(existingAccClosureMaster.getContractType(),
			newAccClosureMaster.getContractType());
		Assert.assertEquals(existingAccClosureMaster.getGlCompanyMasterSid(),
			newAccClosureMaster.getGlCompanyMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccClosureMaster.getCreatedDate()),
			Time.getShortTimestamp(newAccClosureMaster.getCreatedDate()));
		Assert.assertEquals(existingAccClosureMaster.getCreatedBy(),
			newAccClosureMaster.getCreatedBy());
		Assert.assertEquals(existingAccClosureMaster.getContractMasterSid(),
			newAccClosureMaster.getContractMasterSid());
		Assert.assertEquals(existingAccClosureMaster.getAccrualPeriod(),
			newAccClosureMaster.getAccrualPeriod());
		Assert.assertEquals(existingAccClosureMaster.getCompanyGroupSid(),
			newAccClosureMaster.getCompanyGroupSid());
		Assert.assertEquals(existingAccClosureMaster.getAccClosureMasterSid(),
			newAccClosureMaster.getAccClosureMasterSid());
		Assert.assertEquals(existingAccClosureMaster.getRsCategory(),
			newAccClosureMaster.getRsCategory());
		Assert.assertEquals(existingAccClosureMaster.getAdjustmentType(),
			newAccClosureMaster.getAdjustmentType());
		Assert.assertEquals(existingAccClosureMaster.getModifiedBy(),
			newAccClosureMaster.getModifiedBy());
		Assert.assertEquals(existingAccClosureMaster.getItemGroupSid(),
			newAccClosureMaster.getItemGroupSid());
		Assert.assertEquals(existingAccClosureMaster.getRebateProgramType(),
			newAccClosureMaster.getRebateProgramType());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AccClosureMaster newAccClosureMaster = addAccClosureMaster();

		AccClosureMaster existingAccClosureMaster = _persistence.findByPrimaryKey(newAccClosureMaster.getPrimaryKey());

		Assert.assertEquals(existingAccClosureMaster, newAccClosureMaster);
	}

	@Test(expected = NoSuchAccClosureMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AccClosureMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Acc_Closure_Master",
			"saveFlag", true, "accountNo", true, "toDate", true,
			"itemMasterSid", true, "description", true, "reportName", true,
			"rsType", true, "productIdentifier", true, "modifiedDate", true,
			"workflowStatus", true, "moduleType", true, "fromDate", true,
			"contractType", true, "glCompanyMasterSid", true, "createdDate",
			true, "createdBy", true, "contractMasterSid", true,
			"accrualPeriod", true, "companyGroupSid", true,
			"accClosureMasterSid", true, "rsCategory", true, "adjustmentType",
			true, "modifiedBy", true, "itemGroupSid", true,
			"rebateProgramType", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AccClosureMaster newAccClosureMaster = addAccClosureMaster();

		AccClosureMaster existingAccClosureMaster = _persistence.fetchByPrimaryKey(newAccClosureMaster.getPrimaryKey());

		Assert.assertEquals(existingAccClosureMaster, newAccClosureMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureMaster missingAccClosureMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAccClosureMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AccClosureMaster newAccClosureMaster1 = addAccClosureMaster();
		AccClosureMaster newAccClosureMaster2 = addAccClosureMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureMaster1.getPrimaryKey());
		primaryKeys.add(newAccClosureMaster2.getPrimaryKey());

		Map<Serializable, AccClosureMaster> accClosureMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, accClosureMasters.size());
		Assert.assertEquals(newAccClosureMaster1,
			accClosureMasters.get(newAccClosureMaster1.getPrimaryKey()));
		Assert.assertEquals(newAccClosureMaster2,
			accClosureMasters.get(newAccClosureMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AccClosureMaster> accClosureMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accClosureMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AccClosureMaster newAccClosureMaster = addAccClosureMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AccClosureMaster> accClosureMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accClosureMasters.size());
		Assert.assertEquals(newAccClosureMaster,
			accClosureMasters.get(newAccClosureMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AccClosureMaster> accClosureMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accClosureMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AccClosureMaster newAccClosureMaster = addAccClosureMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureMaster.getPrimaryKey());

		Map<Serializable, AccClosureMaster> accClosureMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accClosureMasters.size());
		Assert.assertEquals(newAccClosureMaster,
			accClosureMasters.get(newAccClosureMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AccClosureMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AccClosureMaster>() {
				@Override
				public void performAction(AccClosureMaster accClosureMaster) {
					Assert.assertNotNull(accClosureMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AccClosureMaster newAccClosureMaster = addAccClosureMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureMasterSid",
				newAccClosureMaster.getAccClosureMasterSid()));

		List<AccClosureMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AccClosureMaster existingAccClosureMaster = result.get(0);

		Assert.assertEquals(existingAccClosureMaster, newAccClosureMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureMasterSid",
				RandomTestUtil.nextInt()));

		List<AccClosureMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AccClosureMaster newAccClosureMaster = addAccClosureMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureMasterSid"));

		Object newAccClosureMasterSid = newAccClosureMaster.getAccClosureMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureMasterSid",
				new Object[] { newAccClosureMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccClosureMasterSid = result.get(0);

		Assert.assertEquals(existingAccClosureMasterSid, newAccClosureMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AccClosureMaster addAccClosureMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureMaster accClosureMaster = _persistence.create(pk);

		accClosureMaster.setSaveFlag(RandomTestUtil.randomBoolean());

		accClosureMaster.setAccountNo(RandomTestUtil.randomString());

		accClosureMaster.setToDate(RandomTestUtil.nextDate());

		accClosureMaster.setItemMasterSid(RandomTestUtil.nextInt());

		accClosureMaster.setDescription(RandomTestUtil.randomString());

		accClosureMaster.setReportName(RandomTestUtil.randomString());

		accClosureMaster.setRsType(RandomTestUtil.nextInt());

		accClosureMaster.setProductIdentifier(RandomTestUtil.randomString());

		accClosureMaster.setModifiedDate(RandomTestUtil.nextDate());

		accClosureMaster.setWorkflowStatus(RandomTestUtil.nextInt());

		accClosureMaster.setModuleType(RandomTestUtil.randomString());

		accClosureMaster.setFromDate(RandomTestUtil.nextDate());

		accClosureMaster.setContractType(RandomTestUtil.nextInt());

		accClosureMaster.setGlCompanyMasterSid(RandomTestUtil.nextInt());

		accClosureMaster.setCreatedDate(RandomTestUtil.nextDate());

		accClosureMaster.setCreatedBy(RandomTestUtil.nextInt());

		accClosureMaster.setContractMasterSid(RandomTestUtil.nextInt());

		accClosureMaster.setAccrualPeriod(RandomTestUtil.randomString());

		accClosureMaster.setCompanyGroupSid(RandomTestUtil.randomString());

		accClosureMaster.setRsCategory(RandomTestUtil.nextInt());

		accClosureMaster.setAdjustmentType(RandomTestUtil.nextInt());

		accClosureMaster.setModifiedBy(RandomTestUtil.nextInt());

		accClosureMaster.setItemGroupSid(RandomTestUtil.randomString());

		accClosureMaster.setRebateProgramType(RandomTestUtil.nextInt());

		_accClosureMasters.add(_persistence.update(accClosureMaster));

		return accClosureMaster;
	}

	private List<AccClosureMaster> _accClosureMasters = new ArrayList<AccClosureMaster>();
	private AccClosureMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}