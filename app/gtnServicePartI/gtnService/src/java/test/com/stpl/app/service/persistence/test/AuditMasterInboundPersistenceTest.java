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

import com.stpl.app.exception.NoSuchAuditMasterInboundException;
import com.stpl.app.model.AuditMasterInbound;
import com.stpl.app.service.AuditMasterInboundLocalServiceUtil;
import com.stpl.app.service.persistence.AuditMasterInboundPersistence;
import com.stpl.app.service.persistence.AuditMasterInboundUtil;

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
public class AuditMasterInboundPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = AuditMasterInboundUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AuditMasterInbound> iterator = _auditMasterInbounds.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AuditMasterInbound auditMasterInbound = _persistence.create(pk);

		Assert.assertNotNull(auditMasterInbound);

		Assert.assertEquals(auditMasterInbound.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AuditMasterInbound newAuditMasterInbound = addAuditMasterInbound();

		_persistence.remove(newAuditMasterInbound);

		AuditMasterInbound existingAuditMasterInbound = _persistence.fetchByPrimaryKey(newAuditMasterInbound.getPrimaryKey());

		Assert.assertNull(existingAuditMasterInbound);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAuditMasterInbound();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AuditMasterInbound newAuditMasterInbound = _persistence.create(pk);

		newAuditMasterInbound.setReceivedRecordAmountAttr(RandomTestUtil.randomString());

		newAuditMasterInbound.setModifiedBy(RandomTestUtil.nextInt());

		newAuditMasterInbound.setCreatedDate(RandomTestUtil.nextDate());

		newAuditMasterInbound.setInterfaceRunEndDate(RandomTestUtil.nextDate());

		newAuditMasterInbound.setApplicationProcess(RandomTestUtil.randomString());

		newAuditMasterInbound.setDiscrepancyAmount(RandomTestUtil.nextDouble());

		newAuditMasterInbound.setBatchId(RandomTestUtil.randomString());

		newAuditMasterInbound.setFileName(RandomTestUtil.randomString());

		newAuditMasterInbound.setSentRecordAmountAttribute(RandomTestUtil.randomString());

		newAuditMasterInbound.setStatus(RandomTestUtil.randomString());

		newAuditMasterInbound.setReceivedRecordAmount(RandomTestUtil.nextDouble());

		newAuditMasterInbound.setValidRecordAmount(RandomTestUtil.nextDouble());

		newAuditMasterInbound.setInvalidRecordCount(RandomTestUtil.nextInt());

		newAuditMasterInbound.setReceivedRecordCount(RandomTestUtil.nextInt());

		newAuditMasterInbound.setCreatedBy(RandomTestUtil.nextInt());

		newAuditMasterInbound.setChangeCount(RandomTestUtil.nextInt());

		newAuditMasterInbound.setUnprocessedRecords(RandomTestUtil.randomString());

		newAuditMasterInbound.setDeleteCount(RandomTestUtil.nextInt());

		newAuditMasterInbound.setModifiedDate(RandomTestUtil.nextDate());

		newAuditMasterInbound.setSentRecordAmount(RandomTestUtil.nextDouble());

		newAuditMasterInbound.setSentRecordCount(RandomTestUtil.nextInt());

		newAuditMasterInbound.setAddCount(RandomTestUtil.nextInt());

		newAuditMasterInbound.setSource(RandomTestUtil.randomString());

		newAuditMasterInbound.setInvalidRecordAmount(RandomTestUtil.nextDouble());

		newAuditMasterInbound.setInterfaceRunStartDate(RandomTestUtil.nextDate());

		_auditMasterInbounds.add(_persistence.update(newAuditMasterInbound));

		AuditMasterInbound existingAuditMasterInbound = _persistence.findByPrimaryKey(newAuditMasterInbound.getPrimaryKey());

		Assert.assertEquals(existingAuditMasterInbound.getReceivedRecordAmountAttr(),
			newAuditMasterInbound.getReceivedRecordAmountAttr());
		Assert.assertEquals(existingAuditMasterInbound.getModifiedBy(),
			newAuditMasterInbound.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAuditMasterInbound.getCreatedDate()),
			Time.getShortTimestamp(newAuditMasterInbound.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAuditMasterInbound.getInterfaceRunEndDate()),
			Time.getShortTimestamp(
				newAuditMasterInbound.getInterfaceRunEndDate()));
		Assert.assertEquals(existingAuditMasterInbound.getApplicationProcess(),
			newAuditMasterInbound.getApplicationProcess());
		AssertUtils.assertEquals(existingAuditMasterInbound.getDiscrepancyAmount(),
			newAuditMasterInbound.getDiscrepancyAmount());
		Assert.assertEquals(existingAuditMasterInbound.getBatchId(),
			newAuditMasterInbound.getBatchId());
		Assert.assertEquals(existingAuditMasterInbound.getFileName(),
			newAuditMasterInbound.getFileName());
		Assert.assertEquals(existingAuditMasterInbound.getSentRecordAmountAttribute(),
			newAuditMasterInbound.getSentRecordAmountAttribute());
		Assert.assertEquals(existingAuditMasterInbound.getStatus(),
			newAuditMasterInbound.getStatus());
		AssertUtils.assertEquals(existingAuditMasterInbound.getReceivedRecordAmount(),
			newAuditMasterInbound.getReceivedRecordAmount());
		AssertUtils.assertEquals(existingAuditMasterInbound.getValidRecordAmount(),
			newAuditMasterInbound.getValidRecordAmount());
		Assert.assertEquals(existingAuditMasterInbound.getInvalidRecordCount(),
			newAuditMasterInbound.getInvalidRecordCount());
		Assert.assertEquals(existingAuditMasterInbound.getReceivedRecordCount(),
			newAuditMasterInbound.getReceivedRecordCount());
		Assert.assertEquals(existingAuditMasterInbound.getCreatedBy(),
			newAuditMasterInbound.getCreatedBy());
		Assert.assertEquals(existingAuditMasterInbound.getChangeCount(),
			newAuditMasterInbound.getChangeCount());
		Assert.assertEquals(existingAuditMasterInbound.getUnprocessedRecords(),
			newAuditMasterInbound.getUnprocessedRecords());
		Assert.assertEquals(existingAuditMasterInbound.getDeleteCount(),
			newAuditMasterInbound.getDeleteCount());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAuditMasterInbound.getModifiedDate()),
			Time.getShortTimestamp(newAuditMasterInbound.getModifiedDate()));
		Assert.assertEquals(existingAuditMasterInbound.getAuditInboundSid(),
			newAuditMasterInbound.getAuditInboundSid());
		AssertUtils.assertEquals(existingAuditMasterInbound.getSentRecordAmount(),
			newAuditMasterInbound.getSentRecordAmount());
		Assert.assertEquals(existingAuditMasterInbound.getSentRecordCount(),
			newAuditMasterInbound.getSentRecordCount());
		Assert.assertEquals(existingAuditMasterInbound.getAddCount(),
			newAuditMasterInbound.getAddCount());
		Assert.assertEquals(existingAuditMasterInbound.getSource(),
			newAuditMasterInbound.getSource());
		AssertUtils.assertEquals(existingAuditMasterInbound.getInvalidRecordAmount(),
			newAuditMasterInbound.getInvalidRecordAmount());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAuditMasterInbound.getInterfaceRunStartDate()),
			Time.getShortTimestamp(
				newAuditMasterInbound.getInterfaceRunStartDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AuditMasterInbound newAuditMasterInbound = addAuditMasterInbound();

		AuditMasterInbound existingAuditMasterInbound = _persistence.findByPrimaryKey(newAuditMasterInbound.getPrimaryKey());

		Assert.assertEquals(existingAuditMasterInbound, newAuditMasterInbound);
	}

	@Test(expected = NoSuchAuditMasterInboundException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AuditMasterInbound> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AUDIT_MASTER_INBOUND",
			"receivedRecordAmountAttr", true, "modifiedBy", true,
			"createdDate", true, "interfaceRunEndDate", true,
			"applicationProcess", true, "discrepancyAmount", true, "batchId",
			true, "fileName", true, "sentRecordAmountAttribute", true,
			"status", true, "receivedRecordAmount", true, "validRecordAmount",
			true, "invalidRecordCount", true, "receivedRecordCount", true,
			"createdBy", true, "changeCount", true, "unprocessedRecords", true,
			"deleteCount", true, "modifiedDate", true, "auditInboundSid", true,
			"sentRecordAmount", true, "sentRecordCount", true, "addCount",
			true, "source", true, "invalidRecordAmount", true,
			"interfaceRunStartDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AuditMasterInbound newAuditMasterInbound = addAuditMasterInbound();

		AuditMasterInbound existingAuditMasterInbound = _persistence.fetchByPrimaryKey(newAuditMasterInbound.getPrimaryKey());

		Assert.assertEquals(existingAuditMasterInbound, newAuditMasterInbound);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AuditMasterInbound missingAuditMasterInbound = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAuditMasterInbound);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AuditMasterInbound newAuditMasterInbound1 = addAuditMasterInbound();
		AuditMasterInbound newAuditMasterInbound2 = addAuditMasterInbound();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAuditMasterInbound1.getPrimaryKey());
		primaryKeys.add(newAuditMasterInbound2.getPrimaryKey());

		Map<Serializable, AuditMasterInbound> auditMasterInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, auditMasterInbounds.size());
		Assert.assertEquals(newAuditMasterInbound1,
			auditMasterInbounds.get(newAuditMasterInbound1.getPrimaryKey()));
		Assert.assertEquals(newAuditMasterInbound2,
			auditMasterInbounds.get(newAuditMasterInbound2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AuditMasterInbound> auditMasterInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(auditMasterInbounds.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AuditMasterInbound newAuditMasterInbound = addAuditMasterInbound();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAuditMasterInbound.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AuditMasterInbound> auditMasterInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, auditMasterInbounds.size());
		Assert.assertEquals(newAuditMasterInbound,
			auditMasterInbounds.get(newAuditMasterInbound.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AuditMasterInbound> auditMasterInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(auditMasterInbounds.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AuditMasterInbound newAuditMasterInbound = addAuditMasterInbound();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAuditMasterInbound.getPrimaryKey());

		Map<Serializable, AuditMasterInbound> auditMasterInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, auditMasterInbounds.size());
		Assert.assertEquals(newAuditMasterInbound,
			auditMasterInbounds.get(newAuditMasterInbound.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AuditMasterInboundLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AuditMasterInbound>() {
				@Override
				public void performAction(AuditMasterInbound auditMasterInbound) {
					Assert.assertNotNull(auditMasterInbound);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AuditMasterInbound newAuditMasterInbound = addAuditMasterInbound();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AuditMasterInbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("auditInboundSid",
				newAuditMasterInbound.getAuditInboundSid()));

		List<AuditMasterInbound> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AuditMasterInbound existingAuditMasterInbound = result.get(0);

		Assert.assertEquals(existingAuditMasterInbound, newAuditMasterInbound);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AuditMasterInbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("auditInboundSid",
				RandomTestUtil.nextInt()));

		List<AuditMasterInbound> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AuditMasterInbound newAuditMasterInbound = addAuditMasterInbound();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AuditMasterInbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"auditInboundSid"));

		Object newAuditInboundSid = newAuditMasterInbound.getAuditInboundSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("auditInboundSid",
				new Object[] { newAuditInboundSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAuditInboundSid = result.get(0);

		Assert.assertEquals(existingAuditInboundSid, newAuditInboundSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AuditMasterInbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"auditInboundSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("auditInboundSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AuditMasterInbound addAuditMasterInbound()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		AuditMasterInbound auditMasterInbound = _persistence.create(pk);

		auditMasterInbound.setReceivedRecordAmountAttr(RandomTestUtil.randomString());

		auditMasterInbound.setModifiedBy(RandomTestUtil.nextInt());

		auditMasterInbound.setCreatedDate(RandomTestUtil.nextDate());

		auditMasterInbound.setInterfaceRunEndDate(RandomTestUtil.nextDate());

		auditMasterInbound.setApplicationProcess(RandomTestUtil.randomString());

		auditMasterInbound.setDiscrepancyAmount(RandomTestUtil.nextDouble());

		auditMasterInbound.setBatchId(RandomTestUtil.randomString());

		auditMasterInbound.setFileName(RandomTestUtil.randomString());

		auditMasterInbound.setSentRecordAmountAttribute(RandomTestUtil.randomString());

		auditMasterInbound.setStatus(RandomTestUtil.randomString());

		auditMasterInbound.setReceivedRecordAmount(RandomTestUtil.nextDouble());

		auditMasterInbound.setValidRecordAmount(RandomTestUtil.nextDouble());

		auditMasterInbound.setInvalidRecordCount(RandomTestUtil.nextInt());

		auditMasterInbound.setReceivedRecordCount(RandomTestUtil.nextInt());

		auditMasterInbound.setCreatedBy(RandomTestUtil.nextInt());

		auditMasterInbound.setChangeCount(RandomTestUtil.nextInt());

		auditMasterInbound.setUnprocessedRecords(RandomTestUtil.randomString());

		auditMasterInbound.setDeleteCount(RandomTestUtil.nextInt());

		auditMasterInbound.setModifiedDate(RandomTestUtil.nextDate());

		auditMasterInbound.setSentRecordAmount(RandomTestUtil.nextDouble());

		auditMasterInbound.setSentRecordCount(RandomTestUtil.nextInt());

		auditMasterInbound.setAddCount(RandomTestUtil.nextInt());

		auditMasterInbound.setSource(RandomTestUtil.randomString());

		auditMasterInbound.setInvalidRecordAmount(RandomTestUtil.nextDouble());

		auditMasterInbound.setInterfaceRunStartDate(RandomTestUtil.nextDate());

		_auditMasterInbounds.add(_persistence.update(auditMasterInbound));

		return auditMasterInbound;
	}

	private List<AuditMasterInbound> _auditMasterInbounds = new ArrayList<AuditMasterInbound>();
	private AuditMasterInboundPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}