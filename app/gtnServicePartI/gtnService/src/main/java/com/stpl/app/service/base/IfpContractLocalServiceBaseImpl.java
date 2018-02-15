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

package com.stpl.app.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.model.IfpContract;
import com.stpl.app.service.IfpContractLocalService;
import com.stpl.app.service.persistence.AccrualDetailsPersistence;
import com.stpl.app.service.persistence.AccrualMasterPersistence;
import com.stpl.app.service.persistence.ActualsMasterPersistence;
import com.stpl.app.service.persistence.AdditionalNotesPersistence;
import com.stpl.app.service.persistence.AuditMasterInboundPersistence;
import com.stpl.app.service.persistence.AverageShelfLifeMasterPersistence;
import com.stpl.app.service.persistence.BestPriceMasterPersistence;
import com.stpl.app.service.persistence.BrandMasterPersistence;
import com.stpl.app.service.persistence.BusinessroleMasterPersistence;
import com.stpl.app.service.persistence.BusinessroleModulePersistence;
import com.stpl.app.service.persistence.CcpDetailsPersistence;
import com.stpl.app.service.persistence.CcpMapPersistence;
import com.stpl.app.service.persistence.CdrDetailsPersistence;
import com.stpl.app.service.persistence.CdrModelPersistence;
import com.stpl.app.service.persistence.CfpContractDetailsPersistence;
import com.stpl.app.service.persistence.CfpContractPersistence;
import com.stpl.app.service.persistence.CfpDetailsPersistence;
import com.stpl.app.service.persistence.CfpModelPersistence;
import com.stpl.app.service.persistence.ChActualDiscountPersistence;
import com.stpl.app.service.persistence.ChActualSalesPersistence;
import com.stpl.app.service.persistence.ChAssumptionsPersistence;
import com.stpl.app.service.persistence.ChDiscountProjMasterPersistence;
import com.stpl.app.service.persistence.ChProjectionDiscountPersistence;
import com.stpl.app.service.persistence.ChProjectionSelectionPersistence;
import com.stpl.app.service.persistence.ChSalesProjectionMasterPersistence;
import com.stpl.app.service.persistence.ChSalesProjectionPersistence;
import com.stpl.app.service.persistence.CompanyGroupDetailsPersistence;
import com.stpl.app.service.persistence.CompanyGroupPersistence;
import com.stpl.app.service.persistence.CompanyIdentifierPersistence;
import com.stpl.app.service.persistence.CompanyMasterPersistence;
import com.stpl.app.service.persistence.CompanyParentDetailsPersistence;
import com.stpl.app.service.persistence.CompanyQualifierPersistence;
import com.stpl.app.service.persistence.CompanyTradeClassPersistence;
import com.stpl.app.service.persistence.ContractAliasMasterPersistence;
import com.stpl.app.service.persistence.ContractMasterPersistence;
import com.stpl.app.service.persistence.CpiIndexMasterPersistence;
import com.stpl.app.service.persistence.CustomViewDetailsPersistence;
import com.stpl.app.service.persistence.CustomViewMasterPersistence;
import com.stpl.app.service.persistence.DeductionCalendarDetailsPersistence;
import com.stpl.app.service.persistence.DeductionCalendarMasterPersistence;
import com.stpl.app.service.persistence.DeductionDetailsPersistence;
import com.stpl.app.service.persistence.DeductionGroupDetailsPersistence;
import com.stpl.app.service.persistence.DeductionGroupPersistence;
import com.stpl.app.service.persistence.DemandForecastPersistence;
import com.stpl.app.service.persistence.DocDetailsPersistence;
import com.stpl.app.service.persistence.FcpActualsPersistence;
import com.stpl.app.service.persistence.FcpProjPersistence;
import com.stpl.app.service.persistence.FederalNewNdcPersistence;
import com.stpl.app.service.persistence.FileManagementPersistence;
import com.stpl.app.service.persistence.ForecastConfigPersistence;
import com.stpl.app.service.persistence.ForecastingFormulaPersistence;
import com.stpl.app.service.persistence.ForecastingMasterPersistence;
import com.stpl.app.service.persistence.ForecastingViewMasterPersistence;
import com.stpl.app.service.persistence.FormulaDetailsMasterPersistence;
import com.stpl.app.service.persistence.GcmCompanyDetailsPersistence;
import com.stpl.app.service.persistence.GcmCompanyLinkPersistence;
import com.stpl.app.service.persistence.GcmContractDetailsPersistence;
import com.stpl.app.service.persistence.GcmGlobalDetailsPersistence;
import com.stpl.app.service.persistence.GcmItemDetailsPersistence;
import com.stpl.app.service.persistence.GlBalanceMasterPersistence;
import com.stpl.app.service.persistence.GlCostCenterMasterPersistence;
import com.stpl.app.service.persistence.HelperTableFinder;
import com.stpl.app.service.persistence.HelperTablePersistence;
import com.stpl.app.service.persistence.HierarchyDefinitionPersistence;
import com.stpl.app.service.persistence.HierarchyLevelDefinitionPersistence;
import com.stpl.app.service.persistence.HierarchyLevelValuesPersistence;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsPersistence;
import com.stpl.app.service.persistence.HistCompanyGroupPersistence;
import com.stpl.app.service.persistence.HistHierarchyDefinitionPersistence;
import com.stpl.app.service.persistence.HistHierarchyLevelDefnPersistence;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesPersistence;
import com.stpl.app.service.persistence.HistItemGroupDetailsPersistence;
import com.stpl.app.service.persistence.HistItemGroupPersistence;
import com.stpl.app.service.persistence.HistRelationshipBuilderPersistence;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnPersistence;
import com.stpl.app.service.persistence.HistWorkflowMasterPersistence;
import com.stpl.app.service.persistence.IfpContractDetailsPersistence;
import com.stpl.app.service.persistence.IfpContractPersistence;
import com.stpl.app.service.persistence.IfpDetailsPersistence;
import com.stpl.app.service.persistence.IfpModelPersistence;
import com.stpl.app.service.persistence.ImtdCfpDetailsPersistence;
import com.stpl.app.service.persistence.ImtdDeductionDetailsPersistence;
import com.stpl.app.service.persistence.ImtdIfpDetailsPersistence;
import com.stpl.app.service.persistence.ImtdItemPriceRebateDetailsPersistence;
import com.stpl.app.service.persistence.ImtdLevelValuesPersistence;
import com.stpl.app.service.persistence.ImtdPsDetailsPersistence;
import com.stpl.app.service.persistence.ImtdRsContractDetailsFrPersistence;
import com.stpl.app.service.persistence.ImtdRsDetailsFrPersistence;
import com.stpl.app.service.persistence.ImtdRsDetailsPersistence;
import com.stpl.app.service.persistence.ImtdSalesBasisDetailsPersistence;
import com.stpl.app.service.persistence.InventoryWdActualMasPersistence;
import com.stpl.app.service.persistence.InventoryWdProjMasPersistence;
import com.stpl.app.service.persistence.ItemGroupDetailsPersistence;
import com.stpl.app.service.persistence.ItemGroupPersistence;
import com.stpl.app.service.persistence.ItemHierarchyDefMasterPersistence;
import com.stpl.app.service.persistence.ItemHierarchyMasterPersistence;
import com.stpl.app.service.persistence.ItemIdentifierPersistence;
import com.stpl.app.service.persistence.ItemMasterPersistence;
import com.stpl.app.service.persistence.ItemPricingPersistence;
import com.stpl.app.service.persistence.ItemPricingQualifierPersistence;
import com.stpl.app.service.persistence.ItemQualifierPersistence;
import com.stpl.app.service.persistence.IvldActualMasterPersistence;
import com.stpl.app.service.persistence.IvldAverageShelfLifePersistence;
import com.stpl.app.service.persistence.IvldBestPricePersistence;
import com.stpl.app.service.persistence.IvldCpiPersistence;
import com.stpl.app.service.persistence.IvldDemandActualPersistence;
import com.stpl.app.service.persistence.IvldDemandForecastPersistence;
import com.stpl.app.service.persistence.IvldForecastSalesPersistence;
import com.stpl.app.service.persistence.IvldFormulaDetailsPersistence;
import com.stpl.app.service.persistence.IvldGlBalancePersistence;
import com.stpl.app.service.persistence.IvldGlCostCenterPersistence;
import com.stpl.app.service.persistence.IvldInventoryWdActualMasPersistence;
import com.stpl.app.service.persistence.IvldInventoryWdProjMasPersistence;
import com.stpl.app.service.persistence.IvldItemHierarchyDefinitionPersistence;
import com.stpl.app.service.persistence.IvldItemHierarchyPersistence;
import com.stpl.app.service.persistence.IvldLotMasterPersistence;
import com.stpl.app.service.persistence.IvldMasterDataAttributePersistence;
import com.stpl.app.service.persistence.IvldReturnsPersistence;
import com.stpl.app.service.persistence.IvldSalesMasterPersistence;
import com.stpl.app.service.persistence.LotMasterPersistence;
import com.stpl.app.service.persistence.MAssumptionsPersistence;
import com.stpl.app.service.persistence.MParityLookupPersistence;
import com.stpl.app.service.persistence.MProjectionSelectionPersistence;
import com.stpl.app.service.persistence.MSalesProjectionMasterPersistence;
import com.stpl.app.service.persistence.MSupplementalDiscActualsPersistence;
import com.stpl.app.service.persistence.MSupplementalDiscMasterPersistence;
import com.stpl.app.service.persistence.MSupplementalDiscProjPersistence;
import com.stpl.app.service.persistence.MailNotificationMasterPersistence;
import com.stpl.app.service.persistence.MasterDataAttributePersistence;
import com.stpl.app.service.persistence.MasterDataFilesPersistence;
import com.stpl.app.service.persistence.MedicaidNewNdcPersistence;
import com.stpl.app.service.persistence.MedicaidUraActualsPersistence;
import com.stpl.app.service.persistence.MedicaidUraProjPersistence;
import com.stpl.app.service.persistence.ModulePropertiesPersistence;
import com.stpl.app.service.persistence.ModuleSubmoduleMasterPersistence;
import com.stpl.app.service.persistence.NaProjDetailsPersistence;
import com.stpl.app.service.persistence.NaProjMasterPersistence;
import com.stpl.app.service.persistence.NaProjectionSelectionPersistence;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPersistence;
import com.stpl.app.service.persistence.NationalAssumptionsPersistence;
import com.stpl.app.service.persistence.NationalAssumptionsProjPersistence;
import com.stpl.app.service.persistence.NetSalesFormulaMasterPersistence;
import com.stpl.app.service.persistence.NmActualDiscountPersistence;
import com.stpl.app.service.persistence.NmActualPpaPersistence;
import com.stpl.app.service.persistence.NmDiscountProjMasterPersistence;
import com.stpl.app.service.persistence.NmDiscountProjectionPersistence;
import com.stpl.app.service.persistence.NmPpaProjectionMasterPersistence;
import com.stpl.app.service.persistence.NmPpaProjectionPersistence;
import com.stpl.app.service.persistence.NmProjectionSelectionPersistence;
import com.stpl.app.service.persistence.NmSalesProjectionMasterPersistence;
import com.stpl.app.service.persistence.NmSalesProjectionPersistence;
import com.stpl.app.service.persistence.PeriodPersistence;
import com.stpl.app.service.persistence.PhsActualsPersistence;
import com.stpl.app.service.persistence.PhsProjPersistence;
import com.stpl.app.service.persistence.PriceScheduleDetailsPersistence;
import com.stpl.app.service.persistence.PriceScheduleMasterPersistence;
import com.stpl.app.service.persistence.ProjectionCustDetailsPersistence;
import com.stpl.app.service.persistence.ProjectionCustHierarchyPersistence;
import com.stpl.app.service.persistence.ProjectionDetailsPersistence;
import com.stpl.app.service.persistence.ProjectionMasterPersistence;
import com.stpl.app.service.persistence.ProjectionNameConfigPersistence;
import com.stpl.app.service.persistence.ProjectionProdDetailsPersistence;
import com.stpl.app.service.persistence.ProjectionProdHierarchyPersistence;
import com.stpl.app.service.persistence.PsContractDetailsPersistence;
import com.stpl.app.service.persistence.PsContractPersistence;
import com.stpl.app.service.persistence.PsDetailsPersistence;
import com.stpl.app.service.persistence.PsModelPersistence;
import com.stpl.app.service.persistence.RebatePlanMasterPersistence;
import com.stpl.app.service.persistence.RebatePlanTierPersistence;
import com.stpl.app.service.persistence.RebateTierFormulaPersistence;
import com.stpl.app.service.persistence.RelationshipBuilderPersistence;
import com.stpl.app.service.persistence.RelationshipLevelDefinitionPersistence;
import com.stpl.app.service.persistence.ReturnsMasterPersistence;
import com.stpl.app.service.persistence.RsContractDetailsFrPersistence;
import com.stpl.app.service.persistence.RsContractDetailsPersistence;
import com.stpl.app.service.persistence.RsContractPersistence;
import com.stpl.app.service.persistence.RsDetailsFrPersistence;
import com.stpl.app.service.persistence.RsDetailsPersistence;
import com.stpl.app.service.persistence.RsModelPersistence;
import com.stpl.app.service.persistence.SalesBasisDetailsPersistence;
import com.stpl.app.service.persistence.SalesMasterPersistence;
import com.stpl.app.service.persistence.StChActualDiscountPersistence;
import com.stpl.app.service.persistence.StChAssumptionsPersistence;
import com.stpl.app.service.persistence.StChDiscountProjMasterPersistence;
import com.stpl.app.service.persistence.StChProjectionDiscountPersistence;
import com.stpl.app.service.persistence.StChSalesProjectionMasterPersistence;
import com.stpl.app.service.persistence.StDeductionCalendarDetailsPersistence;
import com.stpl.app.service.persistence.StFederalNewNdcPersistence;
import com.stpl.app.service.persistence.StMAssumptionsPersistence;
import com.stpl.app.service.persistence.StMSupplementalDiscActualsPersistence;
import com.stpl.app.service.persistence.StMSupplementalDiscMasterPersistence;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPersistence;
import com.stpl.app.service.persistence.StMedicaidNewNdcPersistence;
import com.stpl.app.service.persistence.StNationalAssumptionsPersistence;
import com.stpl.app.service.persistence.StNewNdcPersistence;
import com.stpl.app.service.persistence.StNmActualDiscountPersistence;
import com.stpl.app.service.persistence.StNmActualPpaPersistence;
import com.stpl.app.service.persistence.StNmAssumptionsPersistence;
import com.stpl.app.service.persistence.StNmDiscountProjMasterPersistence;
import com.stpl.app.service.persistence.StNmDiscountProjectionPersistence;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterPersistence;
import com.stpl.app.service.persistence.StNmPpaProjectionPersistence;
import com.stpl.app.service.persistence.StSelectionTablePersistence;
import com.stpl.app.service.persistence.TransactionModuleDetailsPersistence;
import com.stpl.app.service.persistence.TransactionModuleMasterPersistence;
import com.stpl.app.service.persistence.UdcsPersistence;
import com.stpl.app.service.persistence.UsergroupBusinessrolePersistence;
import com.stpl.app.service.persistence.UsergroupDomainMasterPersistence;
import com.stpl.app.service.persistence.VwAccrualMasterPersistence;
import com.stpl.app.service.persistence.VwDemandForecastActualPersistence;
import com.stpl.app.service.persistence.VwInventoryWdActualProjMasPersistence;
import com.stpl.app.service.persistence.VwIvldDemandForecastActualPersistence;
import com.stpl.app.service.persistence.VwIvldInventoryWdActualProjMasPersistence;
import com.stpl.app.service.persistence.VwUserTablesPersistence;
import com.stpl.app.service.persistence.WfMailConfigPersistence;
import com.stpl.app.service.persistence.WorkflowMasterPersistence;
import com.stpl.app.service.persistence.WorkflowProcessInfoPersistence;
import com.stpl.app.service.persistence.WorkflowProfilePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the ifp contract local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.stpl.app.service.impl.IfpContractLocalServiceImpl}.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.impl.IfpContractLocalServiceImpl
 * @see com.stpl.app.service.IfpContractLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class IfpContractLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements IfpContractLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.stpl.app.service.IfpContractLocalServiceUtil} to access the ifp contract local service.
	 */

	/**
	 * Adds the ifp contract to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ifpContract the ifp contract
	 * @return the ifp contract that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public IfpContract addIfpContract(IfpContract ifpContract) {
		ifpContract.setNew(true);

		return ifpContractPersistence.update(ifpContract);
	}

	/**
	 * Creates a new ifp contract with the primary key. Does not add the ifp contract to the database.
	 *
	 * @param ifpContractSid the primary key for the new ifp contract
	 * @return the new ifp contract
	 */
	@Override
	public IfpContract createIfpContract(int ifpContractSid) {
		return ifpContractPersistence.create(ifpContractSid);
	}

	/**
	 * Deletes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ifpContractSid the primary key of the ifp contract
	 * @return the ifp contract that was removed
	 * @throws PortalException if a ifp contract with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public IfpContract deleteIfpContract(int ifpContractSid)
		throws PortalException {
		return ifpContractPersistence.remove(ifpContractSid);
	}

	/**
	 * Deletes the ifp contract from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ifpContract the ifp contract
	 * @return the ifp contract that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public IfpContract deleteIfpContract(IfpContract ifpContract) {
		return ifpContractPersistence.remove(ifpContract);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(IfpContract.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ifpContractPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return ifpContractPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return ifpContractPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return ifpContractPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return ifpContractPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public IfpContract fetchIfpContract(int ifpContractSid) {
		return ifpContractPersistence.fetchByPrimaryKey(ifpContractSid);
	}

	/**
	 * Returns the ifp contract with the primary key.
	 *
	 * @param ifpContractSid the primary key of the ifp contract
	 * @return the ifp contract
	 * @throws PortalException if a ifp contract with the primary key could not be found
	 */
	@Override
	public IfpContract getIfpContract(int ifpContractSid)
		throws PortalException {
		return ifpContractPersistence.findByPrimaryKey(ifpContractSid);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ifpContractLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(IfpContract.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ifpContractSid");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(ifpContractLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(IfpContract.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"ifpContractSid");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(ifpContractLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(IfpContract.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ifpContractSid");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return ifpContractLocalService.deleteIfpContract((IfpContract)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return ifpContractPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the ifp contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp contracts
	 * @param end the upper bound of the range of ifp contracts (not inclusive)
	 * @return the range of ifp contracts
	 */
	@Override
	public List<IfpContract> getIfpContracts(int start, int end) {
		return ifpContractPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of ifp contracts.
	 *
	 * @return the number of ifp contracts
	 */
	@Override
	public int getIfpContractsCount() {
		return ifpContractPersistence.countAll();
	}

	/**
	 * Updates the ifp contract in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ifpContract the ifp contract
	 * @return the ifp contract that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public IfpContract updateIfpContract(IfpContract ifpContract) {
		return ifpContractPersistence.update(ifpContract);
	}

	/**
	 * Returns the accrual details local service.
	 *
	 * @return the accrual details local service
	 */
	public com.stpl.app.service.AccrualDetailsLocalService getAccrualDetailsLocalService() {
		return accrualDetailsLocalService;
	}

	/**
	 * Sets the accrual details local service.
	 *
	 * @param accrualDetailsLocalService the accrual details local service
	 */
	public void setAccrualDetailsLocalService(
		com.stpl.app.service.AccrualDetailsLocalService accrualDetailsLocalService) {
		this.accrualDetailsLocalService = accrualDetailsLocalService;
	}

	/**
	 * Returns the accrual details persistence.
	 *
	 * @return the accrual details persistence
	 */
	public AccrualDetailsPersistence getAccrualDetailsPersistence() {
		return accrualDetailsPersistence;
	}

	/**
	 * Sets the accrual details persistence.
	 *
	 * @param accrualDetailsPersistence the accrual details persistence
	 */
	public void setAccrualDetailsPersistence(
		AccrualDetailsPersistence accrualDetailsPersistence) {
		this.accrualDetailsPersistence = accrualDetailsPersistence;
	}

	/**
	 * Returns the accrual master local service.
	 *
	 * @return the accrual master local service
	 */
	public com.stpl.app.service.AccrualMasterLocalService getAccrualMasterLocalService() {
		return accrualMasterLocalService;
	}

	/**
	 * Sets the accrual master local service.
	 *
	 * @param accrualMasterLocalService the accrual master local service
	 */
	public void setAccrualMasterLocalService(
		com.stpl.app.service.AccrualMasterLocalService accrualMasterLocalService) {
		this.accrualMasterLocalService = accrualMasterLocalService;
	}

	/**
	 * Returns the accrual master persistence.
	 *
	 * @return the accrual master persistence
	 */
	public AccrualMasterPersistence getAccrualMasterPersistence() {
		return accrualMasterPersistence;
	}

	/**
	 * Sets the accrual master persistence.
	 *
	 * @param accrualMasterPersistence the accrual master persistence
	 */
	public void setAccrualMasterPersistence(
		AccrualMasterPersistence accrualMasterPersistence) {
		this.accrualMasterPersistence = accrualMasterPersistence;
	}

	/**
	 * Returns the actuals master local service.
	 *
	 * @return the actuals master local service
	 */
	public com.stpl.app.service.ActualsMasterLocalService getActualsMasterLocalService() {
		return actualsMasterLocalService;
	}

	/**
	 * Sets the actuals master local service.
	 *
	 * @param actualsMasterLocalService the actuals master local service
	 */
	public void setActualsMasterLocalService(
		com.stpl.app.service.ActualsMasterLocalService actualsMasterLocalService) {
		this.actualsMasterLocalService = actualsMasterLocalService;
	}

	/**
	 * Returns the actuals master persistence.
	 *
	 * @return the actuals master persistence
	 */
	public ActualsMasterPersistence getActualsMasterPersistence() {
		return actualsMasterPersistence;
	}

	/**
	 * Sets the actuals master persistence.
	 *
	 * @param actualsMasterPersistence the actuals master persistence
	 */
	public void setActualsMasterPersistence(
		ActualsMasterPersistence actualsMasterPersistence) {
		this.actualsMasterPersistence = actualsMasterPersistence;
	}

	/**
	 * Returns the additional notes local service.
	 *
	 * @return the additional notes local service
	 */
	public com.stpl.app.service.AdditionalNotesLocalService getAdditionalNotesLocalService() {
		return additionalNotesLocalService;
	}

	/**
	 * Sets the additional notes local service.
	 *
	 * @param additionalNotesLocalService the additional notes local service
	 */
	public void setAdditionalNotesLocalService(
		com.stpl.app.service.AdditionalNotesLocalService additionalNotesLocalService) {
		this.additionalNotesLocalService = additionalNotesLocalService;
	}

	/**
	 * Returns the additional notes persistence.
	 *
	 * @return the additional notes persistence
	 */
	public AdditionalNotesPersistence getAdditionalNotesPersistence() {
		return additionalNotesPersistence;
	}

	/**
	 * Sets the additional notes persistence.
	 *
	 * @param additionalNotesPersistence the additional notes persistence
	 */
	public void setAdditionalNotesPersistence(
		AdditionalNotesPersistence additionalNotesPersistence) {
		this.additionalNotesPersistence = additionalNotesPersistence;
	}

	/**
	 * Returns the audit master inbound local service.
	 *
	 * @return the audit master inbound local service
	 */
	public com.stpl.app.service.AuditMasterInboundLocalService getAuditMasterInboundLocalService() {
		return auditMasterInboundLocalService;
	}

	/**
	 * Sets the audit master inbound local service.
	 *
	 * @param auditMasterInboundLocalService the audit master inbound local service
	 */
	public void setAuditMasterInboundLocalService(
		com.stpl.app.service.AuditMasterInboundLocalService auditMasterInboundLocalService) {
		this.auditMasterInboundLocalService = auditMasterInboundLocalService;
	}

	/**
	 * Returns the audit master inbound persistence.
	 *
	 * @return the audit master inbound persistence
	 */
	public AuditMasterInboundPersistence getAuditMasterInboundPersistence() {
		return auditMasterInboundPersistence;
	}

	/**
	 * Sets the audit master inbound persistence.
	 *
	 * @param auditMasterInboundPersistence the audit master inbound persistence
	 */
	public void setAuditMasterInboundPersistence(
		AuditMasterInboundPersistence auditMasterInboundPersistence) {
		this.auditMasterInboundPersistence = auditMasterInboundPersistence;
	}

	/**
	 * Returns the average shelf life master local service.
	 *
	 * @return the average shelf life master local service
	 */
	public com.stpl.app.service.AverageShelfLifeMasterLocalService getAverageShelfLifeMasterLocalService() {
		return averageShelfLifeMasterLocalService;
	}

	/**
	 * Sets the average shelf life master local service.
	 *
	 * @param averageShelfLifeMasterLocalService the average shelf life master local service
	 */
	public void setAverageShelfLifeMasterLocalService(
		com.stpl.app.service.AverageShelfLifeMasterLocalService averageShelfLifeMasterLocalService) {
		this.averageShelfLifeMasterLocalService = averageShelfLifeMasterLocalService;
	}

	/**
	 * Returns the average shelf life master persistence.
	 *
	 * @return the average shelf life master persistence
	 */
	public AverageShelfLifeMasterPersistence getAverageShelfLifeMasterPersistence() {
		return averageShelfLifeMasterPersistence;
	}

	/**
	 * Sets the average shelf life master persistence.
	 *
	 * @param averageShelfLifeMasterPersistence the average shelf life master persistence
	 */
	public void setAverageShelfLifeMasterPersistence(
		AverageShelfLifeMasterPersistence averageShelfLifeMasterPersistence) {
		this.averageShelfLifeMasterPersistence = averageShelfLifeMasterPersistence;
	}

	/**
	 * Returns the best price master local service.
	 *
	 * @return the best price master local service
	 */
	public com.stpl.app.service.BestPriceMasterLocalService getBestPriceMasterLocalService() {
		return bestPriceMasterLocalService;
	}

	/**
	 * Sets the best price master local service.
	 *
	 * @param bestPriceMasterLocalService the best price master local service
	 */
	public void setBestPriceMasterLocalService(
		com.stpl.app.service.BestPriceMasterLocalService bestPriceMasterLocalService) {
		this.bestPriceMasterLocalService = bestPriceMasterLocalService;
	}

	/**
	 * Returns the best price master persistence.
	 *
	 * @return the best price master persistence
	 */
	public BestPriceMasterPersistence getBestPriceMasterPersistence() {
		return bestPriceMasterPersistence;
	}

	/**
	 * Sets the best price master persistence.
	 *
	 * @param bestPriceMasterPersistence the best price master persistence
	 */
	public void setBestPriceMasterPersistence(
		BestPriceMasterPersistence bestPriceMasterPersistence) {
		this.bestPriceMasterPersistence = bestPriceMasterPersistence;
	}

	/**
	 * Returns the brand master local service.
	 *
	 * @return the brand master local service
	 */
	public com.stpl.app.service.BrandMasterLocalService getBrandMasterLocalService() {
		return brandMasterLocalService;
	}

	/**
	 * Sets the brand master local service.
	 *
	 * @param brandMasterLocalService the brand master local service
	 */
	public void setBrandMasterLocalService(
		com.stpl.app.service.BrandMasterLocalService brandMasterLocalService) {
		this.brandMasterLocalService = brandMasterLocalService;
	}

	/**
	 * Returns the brand master persistence.
	 *
	 * @return the brand master persistence
	 */
	public BrandMasterPersistence getBrandMasterPersistence() {
		return brandMasterPersistence;
	}

	/**
	 * Sets the brand master persistence.
	 *
	 * @param brandMasterPersistence the brand master persistence
	 */
	public void setBrandMasterPersistence(
		BrandMasterPersistence brandMasterPersistence) {
		this.brandMasterPersistence = brandMasterPersistence;
	}

	/**
	 * Returns the businessrole master local service.
	 *
	 * @return the businessrole master local service
	 */
	public com.stpl.app.service.BusinessroleMasterLocalService getBusinessroleMasterLocalService() {
		return businessroleMasterLocalService;
	}

	/**
	 * Sets the businessrole master local service.
	 *
	 * @param businessroleMasterLocalService the businessrole master local service
	 */
	public void setBusinessroleMasterLocalService(
		com.stpl.app.service.BusinessroleMasterLocalService businessroleMasterLocalService) {
		this.businessroleMasterLocalService = businessroleMasterLocalService;
	}

	/**
	 * Returns the businessrole master persistence.
	 *
	 * @return the businessrole master persistence
	 */
	public BusinessroleMasterPersistence getBusinessroleMasterPersistence() {
		return businessroleMasterPersistence;
	}

	/**
	 * Sets the businessrole master persistence.
	 *
	 * @param businessroleMasterPersistence the businessrole master persistence
	 */
	public void setBusinessroleMasterPersistence(
		BusinessroleMasterPersistence businessroleMasterPersistence) {
		this.businessroleMasterPersistence = businessroleMasterPersistence;
	}

	/**
	 * Returns the businessrole module local service.
	 *
	 * @return the businessrole module local service
	 */
	public com.stpl.app.service.BusinessroleModuleLocalService getBusinessroleModuleLocalService() {
		return businessroleModuleLocalService;
	}

	/**
	 * Sets the businessrole module local service.
	 *
	 * @param businessroleModuleLocalService the businessrole module local service
	 */
	public void setBusinessroleModuleLocalService(
		com.stpl.app.service.BusinessroleModuleLocalService businessroleModuleLocalService) {
		this.businessroleModuleLocalService = businessroleModuleLocalService;
	}

	/**
	 * Returns the businessrole module persistence.
	 *
	 * @return the businessrole module persistence
	 */
	public BusinessroleModulePersistence getBusinessroleModulePersistence() {
		return businessroleModulePersistence;
	}

	/**
	 * Sets the businessrole module persistence.
	 *
	 * @param businessroleModulePersistence the businessrole module persistence
	 */
	public void setBusinessroleModulePersistence(
		BusinessroleModulePersistence businessroleModulePersistence) {
		this.businessroleModulePersistence = businessroleModulePersistence;
	}

	/**
	 * Returns the ccp details local service.
	 *
	 * @return the ccp details local service
	 */
	public com.stpl.app.service.CcpDetailsLocalService getCcpDetailsLocalService() {
		return ccpDetailsLocalService;
	}

	/**
	 * Sets the ccp details local service.
	 *
	 * @param ccpDetailsLocalService the ccp details local service
	 */
	public void setCcpDetailsLocalService(
		com.stpl.app.service.CcpDetailsLocalService ccpDetailsLocalService) {
		this.ccpDetailsLocalService = ccpDetailsLocalService;
	}

	/**
	 * Returns the ccp details persistence.
	 *
	 * @return the ccp details persistence
	 */
	public CcpDetailsPersistence getCcpDetailsPersistence() {
		return ccpDetailsPersistence;
	}

	/**
	 * Sets the ccp details persistence.
	 *
	 * @param ccpDetailsPersistence the ccp details persistence
	 */
	public void setCcpDetailsPersistence(
		CcpDetailsPersistence ccpDetailsPersistence) {
		this.ccpDetailsPersistence = ccpDetailsPersistence;
	}

	/**
	 * Returns the ccp map local service.
	 *
	 * @return the ccp map local service
	 */
	public com.stpl.app.service.CcpMapLocalService getCcpMapLocalService() {
		return ccpMapLocalService;
	}

	/**
	 * Sets the ccp map local service.
	 *
	 * @param ccpMapLocalService the ccp map local service
	 */
	public void setCcpMapLocalService(
		com.stpl.app.service.CcpMapLocalService ccpMapLocalService) {
		this.ccpMapLocalService = ccpMapLocalService;
	}

	/**
	 * Returns the ccp map persistence.
	 *
	 * @return the ccp map persistence
	 */
	public CcpMapPersistence getCcpMapPersistence() {
		return ccpMapPersistence;
	}

	/**
	 * Sets the ccp map persistence.
	 *
	 * @param ccpMapPersistence the ccp map persistence
	 */
	public void setCcpMapPersistence(CcpMapPersistence ccpMapPersistence) {
		this.ccpMapPersistence = ccpMapPersistence;
	}

	/**
	 * Returns the cdr details local service.
	 *
	 * @return the cdr details local service
	 */
	public com.stpl.app.service.CdrDetailsLocalService getCdrDetailsLocalService() {
		return cdrDetailsLocalService;
	}

	/**
	 * Sets the cdr details local service.
	 *
	 * @param cdrDetailsLocalService the cdr details local service
	 */
	public void setCdrDetailsLocalService(
		com.stpl.app.service.CdrDetailsLocalService cdrDetailsLocalService) {
		this.cdrDetailsLocalService = cdrDetailsLocalService;
	}

	/**
	 * Returns the cdr details persistence.
	 *
	 * @return the cdr details persistence
	 */
	public CdrDetailsPersistence getCdrDetailsPersistence() {
		return cdrDetailsPersistence;
	}

	/**
	 * Sets the cdr details persistence.
	 *
	 * @param cdrDetailsPersistence the cdr details persistence
	 */
	public void setCdrDetailsPersistence(
		CdrDetailsPersistence cdrDetailsPersistence) {
		this.cdrDetailsPersistence = cdrDetailsPersistence;
	}

	/**
	 * Returns the cdr model local service.
	 *
	 * @return the cdr model local service
	 */
	public com.stpl.app.service.CdrModelLocalService getCdrModelLocalService() {
		return cdrModelLocalService;
	}

	/**
	 * Sets the cdr model local service.
	 *
	 * @param cdrModelLocalService the cdr model local service
	 */
	public void setCdrModelLocalService(
		com.stpl.app.service.CdrModelLocalService cdrModelLocalService) {
		this.cdrModelLocalService = cdrModelLocalService;
	}

	/**
	 * Returns the cdr model persistence.
	 *
	 * @return the cdr model persistence
	 */
	public CdrModelPersistence getCdrModelPersistence() {
		return cdrModelPersistence;
	}

	/**
	 * Sets the cdr model persistence.
	 *
	 * @param cdrModelPersistence the cdr model persistence
	 */
	public void setCdrModelPersistence(CdrModelPersistence cdrModelPersistence) {
		this.cdrModelPersistence = cdrModelPersistence;
	}

	/**
	 * Returns the cfp contract local service.
	 *
	 * @return the cfp contract local service
	 */
	public com.stpl.app.service.CfpContractLocalService getCfpContractLocalService() {
		return cfpContractLocalService;
	}

	/**
	 * Sets the cfp contract local service.
	 *
	 * @param cfpContractLocalService the cfp contract local service
	 */
	public void setCfpContractLocalService(
		com.stpl.app.service.CfpContractLocalService cfpContractLocalService) {
		this.cfpContractLocalService = cfpContractLocalService;
	}

	/**
	 * Returns the cfp contract persistence.
	 *
	 * @return the cfp contract persistence
	 */
	public CfpContractPersistence getCfpContractPersistence() {
		return cfpContractPersistence;
	}

	/**
	 * Sets the cfp contract persistence.
	 *
	 * @param cfpContractPersistence the cfp contract persistence
	 */
	public void setCfpContractPersistence(
		CfpContractPersistence cfpContractPersistence) {
		this.cfpContractPersistence = cfpContractPersistence;
	}

	/**
	 * Returns the cfp contract details local service.
	 *
	 * @return the cfp contract details local service
	 */
	public com.stpl.app.service.CfpContractDetailsLocalService getCfpContractDetailsLocalService() {
		return cfpContractDetailsLocalService;
	}

	/**
	 * Sets the cfp contract details local service.
	 *
	 * @param cfpContractDetailsLocalService the cfp contract details local service
	 */
	public void setCfpContractDetailsLocalService(
		com.stpl.app.service.CfpContractDetailsLocalService cfpContractDetailsLocalService) {
		this.cfpContractDetailsLocalService = cfpContractDetailsLocalService;
	}

	/**
	 * Returns the cfp contract details persistence.
	 *
	 * @return the cfp contract details persistence
	 */
	public CfpContractDetailsPersistence getCfpContractDetailsPersistence() {
		return cfpContractDetailsPersistence;
	}

	/**
	 * Sets the cfp contract details persistence.
	 *
	 * @param cfpContractDetailsPersistence the cfp contract details persistence
	 */
	public void setCfpContractDetailsPersistence(
		CfpContractDetailsPersistence cfpContractDetailsPersistence) {
		this.cfpContractDetailsPersistence = cfpContractDetailsPersistence;
	}

	/**
	 * Returns the cfp details local service.
	 *
	 * @return the cfp details local service
	 */
	public com.stpl.app.service.CfpDetailsLocalService getCfpDetailsLocalService() {
		return cfpDetailsLocalService;
	}

	/**
	 * Sets the cfp details local service.
	 *
	 * @param cfpDetailsLocalService the cfp details local service
	 */
	public void setCfpDetailsLocalService(
		com.stpl.app.service.CfpDetailsLocalService cfpDetailsLocalService) {
		this.cfpDetailsLocalService = cfpDetailsLocalService;
	}

	/**
	 * Returns the cfp details persistence.
	 *
	 * @return the cfp details persistence
	 */
	public CfpDetailsPersistence getCfpDetailsPersistence() {
		return cfpDetailsPersistence;
	}

	/**
	 * Sets the cfp details persistence.
	 *
	 * @param cfpDetailsPersistence the cfp details persistence
	 */
	public void setCfpDetailsPersistence(
		CfpDetailsPersistence cfpDetailsPersistence) {
		this.cfpDetailsPersistence = cfpDetailsPersistence;
	}

	/**
	 * Returns the cfp model local service.
	 *
	 * @return the cfp model local service
	 */
	public com.stpl.app.service.CfpModelLocalService getCfpModelLocalService() {
		return cfpModelLocalService;
	}

	/**
	 * Sets the cfp model local service.
	 *
	 * @param cfpModelLocalService the cfp model local service
	 */
	public void setCfpModelLocalService(
		com.stpl.app.service.CfpModelLocalService cfpModelLocalService) {
		this.cfpModelLocalService = cfpModelLocalService;
	}

	/**
	 * Returns the cfp model persistence.
	 *
	 * @return the cfp model persistence
	 */
	public CfpModelPersistence getCfpModelPersistence() {
		return cfpModelPersistence;
	}

	/**
	 * Sets the cfp model persistence.
	 *
	 * @param cfpModelPersistence the cfp model persistence
	 */
	public void setCfpModelPersistence(CfpModelPersistence cfpModelPersistence) {
		this.cfpModelPersistence = cfpModelPersistence;
	}

	/**
	 * Returns the ch actual discount local service.
	 *
	 * @return the ch actual discount local service
	 */
	public com.stpl.app.service.ChActualDiscountLocalService getChActualDiscountLocalService() {
		return chActualDiscountLocalService;
	}

	/**
	 * Sets the ch actual discount local service.
	 *
	 * @param chActualDiscountLocalService the ch actual discount local service
	 */
	public void setChActualDiscountLocalService(
		com.stpl.app.service.ChActualDiscountLocalService chActualDiscountLocalService) {
		this.chActualDiscountLocalService = chActualDiscountLocalService;
	}

	/**
	 * Returns the ch actual discount persistence.
	 *
	 * @return the ch actual discount persistence
	 */
	public ChActualDiscountPersistence getChActualDiscountPersistence() {
		return chActualDiscountPersistence;
	}

	/**
	 * Sets the ch actual discount persistence.
	 *
	 * @param chActualDiscountPersistence the ch actual discount persistence
	 */
	public void setChActualDiscountPersistence(
		ChActualDiscountPersistence chActualDiscountPersistence) {
		this.chActualDiscountPersistence = chActualDiscountPersistence;
	}

	/**
	 * Returns the ch actual sales local service.
	 *
	 * @return the ch actual sales local service
	 */
	public com.stpl.app.service.ChActualSalesLocalService getChActualSalesLocalService() {
		return chActualSalesLocalService;
	}

	/**
	 * Sets the ch actual sales local service.
	 *
	 * @param chActualSalesLocalService the ch actual sales local service
	 */
	public void setChActualSalesLocalService(
		com.stpl.app.service.ChActualSalesLocalService chActualSalesLocalService) {
		this.chActualSalesLocalService = chActualSalesLocalService;
	}

	/**
	 * Returns the ch actual sales persistence.
	 *
	 * @return the ch actual sales persistence
	 */
	public ChActualSalesPersistence getChActualSalesPersistence() {
		return chActualSalesPersistence;
	}

	/**
	 * Sets the ch actual sales persistence.
	 *
	 * @param chActualSalesPersistence the ch actual sales persistence
	 */
	public void setChActualSalesPersistence(
		ChActualSalesPersistence chActualSalesPersistence) {
		this.chActualSalesPersistence = chActualSalesPersistence;
	}

	/**
	 * Returns the ch assumptions local service.
	 *
	 * @return the ch assumptions local service
	 */
	public com.stpl.app.service.ChAssumptionsLocalService getChAssumptionsLocalService() {
		return chAssumptionsLocalService;
	}

	/**
	 * Sets the ch assumptions local service.
	 *
	 * @param chAssumptionsLocalService the ch assumptions local service
	 */
	public void setChAssumptionsLocalService(
		com.stpl.app.service.ChAssumptionsLocalService chAssumptionsLocalService) {
		this.chAssumptionsLocalService = chAssumptionsLocalService;
	}

	/**
	 * Returns the ch assumptions persistence.
	 *
	 * @return the ch assumptions persistence
	 */
	public ChAssumptionsPersistence getChAssumptionsPersistence() {
		return chAssumptionsPersistence;
	}

	/**
	 * Sets the ch assumptions persistence.
	 *
	 * @param chAssumptionsPersistence the ch assumptions persistence
	 */
	public void setChAssumptionsPersistence(
		ChAssumptionsPersistence chAssumptionsPersistence) {
		this.chAssumptionsPersistence = chAssumptionsPersistence;
	}

	/**
	 * Returns the ch discount proj master local service.
	 *
	 * @return the ch discount proj master local service
	 */
	public com.stpl.app.service.ChDiscountProjMasterLocalService getChDiscountProjMasterLocalService() {
		return chDiscountProjMasterLocalService;
	}

	/**
	 * Sets the ch discount proj master local service.
	 *
	 * @param chDiscountProjMasterLocalService the ch discount proj master local service
	 */
	public void setChDiscountProjMasterLocalService(
		com.stpl.app.service.ChDiscountProjMasterLocalService chDiscountProjMasterLocalService) {
		this.chDiscountProjMasterLocalService = chDiscountProjMasterLocalService;
	}

	/**
	 * Returns the ch discount proj master persistence.
	 *
	 * @return the ch discount proj master persistence
	 */
	public ChDiscountProjMasterPersistence getChDiscountProjMasterPersistence() {
		return chDiscountProjMasterPersistence;
	}

	/**
	 * Sets the ch discount proj master persistence.
	 *
	 * @param chDiscountProjMasterPersistence the ch discount proj master persistence
	 */
	public void setChDiscountProjMasterPersistence(
		ChDiscountProjMasterPersistence chDiscountProjMasterPersistence) {
		this.chDiscountProjMasterPersistence = chDiscountProjMasterPersistence;
	}

	/**
	 * Returns the ch projection discount local service.
	 *
	 * @return the ch projection discount local service
	 */
	public com.stpl.app.service.ChProjectionDiscountLocalService getChProjectionDiscountLocalService() {
		return chProjectionDiscountLocalService;
	}

	/**
	 * Sets the ch projection discount local service.
	 *
	 * @param chProjectionDiscountLocalService the ch projection discount local service
	 */
	public void setChProjectionDiscountLocalService(
		com.stpl.app.service.ChProjectionDiscountLocalService chProjectionDiscountLocalService) {
		this.chProjectionDiscountLocalService = chProjectionDiscountLocalService;
	}

	/**
	 * Returns the ch projection discount persistence.
	 *
	 * @return the ch projection discount persistence
	 */
	public ChProjectionDiscountPersistence getChProjectionDiscountPersistence() {
		return chProjectionDiscountPersistence;
	}

	/**
	 * Sets the ch projection discount persistence.
	 *
	 * @param chProjectionDiscountPersistence the ch projection discount persistence
	 */
	public void setChProjectionDiscountPersistence(
		ChProjectionDiscountPersistence chProjectionDiscountPersistence) {
		this.chProjectionDiscountPersistence = chProjectionDiscountPersistence;
	}

	/**
	 * Returns the ch projection selection local service.
	 *
	 * @return the ch projection selection local service
	 */
	public com.stpl.app.service.ChProjectionSelectionLocalService getChProjectionSelectionLocalService() {
		return chProjectionSelectionLocalService;
	}

	/**
	 * Sets the ch projection selection local service.
	 *
	 * @param chProjectionSelectionLocalService the ch projection selection local service
	 */
	public void setChProjectionSelectionLocalService(
		com.stpl.app.service.ChProjectionSelectionLocalService chProjectionSelectionLocalService) {
		this.chProjectionSelectionLocalService = chProjectionSelectionLocalService;
	}

	/**
	 * Returns the ch projection selection persistence.
	 *
	 * @return the ch projection selection persistence
	 */
	public ChProjectionSelectionPersistence getChProjectionSelectionPersistence() {
		return chProjectionSelectionPersistence;
	}

	/**
	 * Sets the ch projection selection persistence.
	 *
	 * @param chProjectionSelectionPersistence the ch projection selection persistence
	 */
	public void setChProjectionSelectionPersistence(
		ChProjectionSelectionPersistence chProjectionSelectionPersistence) {
		this.chProjectionSelectionPersistence = chProjectionSelectionPersistence;
	}

	/**
	 * Returns the ch sales projection local service.
	 *
	 * @return the ch sales projection local service
	 */
	public com.stpl.app.service.ChSalesProjectionLocalService getChSalesProjectionLocalService() {
		return chSalesProjectionLocalService;
	}

	/**
	 * Sets the ch sales projection local service.
	 *
	 * @param chSalesProjectionLocalService the ch sales projection local service
	 */
	public void setChSalesProjectionLocalService(
		com.stpl.app.service.ChSalesProjectionLocalService chSalesProjectionLocalService) {
		this.chSalesProjectionLocalService = chSalesProjectionLocalService;
	}

	/**
	 * Returns the ch sales projection persistence.
	 *
	 * @return the ch sales projection persistence
	 */
	public ChSalesProjectionPersistence getChSalesProjectionPersistence() {
		return chSalesProjectionPersistence;
	}

	/**
	 * Sets the ch sales projection persistence.
	 *
	 * @param chSalesProjectionPersistence the ch sales projection persistence
	 */
	public void setChSalesProjectionPersistence(
		ChSalesProjectionPersistence chSalesProjectionPersistence) {
		this.chSalesProjectionPersistence = chSalesProjectionPersistence;
	}

	/**
	 * Returns the ch sales projection master local service.
	 *
	 * @return the ch sales projection master local service
	 */
	public com.stpl.app.service.ChSalesProjectionMasterLocalService getChSalesProjectionMasterLocalService() {
		return chSalesProjectionMasterLocalService;
	}

	/**
	 * Sets the ch sales projection master local service.
	 *
	 * @param chSalesProjectionMasterLocalService the ch sales projection master local service
	 */
	public void setChSalesProjectionMasterLocalService(
		com.stpl.app.service.ChSalesProjectionMasterLocalService chSalesProjectionMasterLocalService) {
		this.chSalesProjectionMasterLocalService = chSalesProjectionMasterLocalService;
	}

	/**
	 * Returns the ch sales projection master persistence.
	 *
	 * @return the ch sales projection master persistence
	 */
	public ChSalesProjectionMasterPersistence getChSalesProjectionMasterPersistence() {
		return chSalesProjectionMasterPersistence;
	}

	/**
	 * Sets the ch sales projection master persistence.
	 *
	 * @param chSalesProjectionMasterPersistence the ch sales projection master persistence
	 */
	public void setChSalesProjectionMasterPersistence(
		ChSalesProjectionMasterPersistence chSalesProjectionMasterPersistence) {
		this.chSalesProjectionMasterPersistence = chSalesProjectionMasterPersistence;
	}

	/**
	 * Returns the company group local service.
	 *
	 * @return the company group local service
	 */
	public com.stpl.app.service.CompanyGroupLocalService getCompanyGroupLocalService() {
		return companyGroupLocalService;
	}

	/**
	 * Sets the company group local service.
	 *
	 * @param companyGroupLocalService the company group local service
	 */
	public void setCompanyGroupLocalService(
		com.stpl.app.service.CompanyGroupLocalService companyGroupLocalService) {
		this.companyGroupLocalService = companyGroupLocalService;
	}

	/**
	 * Returns the company group persistence.
	 *
	 * @return the company group persistence
	 */
	public CompanyGroupPersistence getCompanyGroupPersistence() {
		return companyGroupPersistence;
	}

	/**
	 * Sets the company group persistence.
	 *
	 * @param companyGroupPersistence the company group persistence
	 */
	public void setCompanyGroupPersistence(
		CompanyGroupPersistence companyGroupPersistence) {
		this.companyGroupPersistence = companyGroupPersistence;
	}

	/**
	 * Returns the company group details local service.
	 *
	 * @return the company group details local service
	 */
	public com.stpl.app.service.CompanyGroupDetailsLocalService getCompanyGroupDetailsLocalService() {
		return companyGroupDetailsLocalService;
	}

	/**
	 * Sets the company group details local service.
	 *
	 * @param companyGroupDetailsLocalService the company group details local service
	 */
	public void setCompanyGroupDetailsLocalService(
		com.stpl.app.service.CompanyGroupDetailsLocalService companyGroupDetailsLocalService) {
		this.companyGroupDetailsLocalService = companyGroupDetailsLocalService;
	}

	/**
	 * Returns the company group details persistence.
	 *
	 * @return the company group details persistence
	 */
	public CompanyGroupDetailsPersistence getCompanyGroupDetailsPersistence() {
		return companyGroupDetailsPersistence;
	}

	/**
	 * Sets the company group details persistence.
	 *
	 * @param companyGroupDetailsPersistence the company group details persistence
	 */
	public void setCompanyGroupDetailsPersistence(
		CompanyGroupDetailsPersistence companyGroupDetailsPersistence) {
		this.companyGroupDetailsPersistence = companyGroupDetailsPersistence;
	}

	/**
	 * Returns the company identifier local service.
	 *
	 * @return the company identifier local service
	 */
	public com.stpl.app.service.CompanyIdentifierLocalService getCompanyIdentifierLocalService() {
		return companyIdentifierLocalService;
	}

	/**
	 * Sets the company identifier local service.
	 *
	 * @param companyIdentifierLocalService the company identifier local service
	 */
	public void setCompanyIdentifierLocalService(
		com.stpl.app.service.CompanyIdentifierLocalService companyIdentifierLocalService) {
		this.companyIdentifierLocalService = companyIdentifierLocalService;
	}

	/**
	 * Returns the company identifier persistence.
	 *
	 * @return the company identifier persistence
	 */
	public CompanyIdentifierPersistence getCompanyIdentifierPersistence() {
		return companyIdentifierPersistence;
	}

	/**
	 * Sets the company identifier persistence.
	 *
	 * @param companyIdentifierPersistence the company identifier persistence
	 */
	public void setCompanyIdentifierPersistence(
		CompanyIdentifierPersistence companyIdentifierPersistence) {
		this.companyIdentifierPersistence = companyIdentifierPersistence;
	}

	/**
	 * Returns the company master local service.
	 *
	 * @return the company master local service
	 */
	public com.stpl.app.service.CompanyMasterLocalService getCompanyMasterLocalService() {
		return companyMasterLocalService;
	}

	/**
	 * Sets the company master local service.
	 *
	 * @param companyMasterLocalService the company master local service
	 */
	public void setCompanyMasterLocalService(
		com.stpl.app.service.CompanyMasterLocalService companyMasterLocalService) {
		this.companyMasterLocalService = companyMasterLocalService;
	}

	/**
	 * Returns the company master persistence.
	 *
	 * @return the company master persistence
	 */
	public CompanyMasterPersistence getCompanyMasterPersistence() {
		return companyMasterPersistence;
	}

	/**
	 * Sets the company master persistence.
	 *
	 * @param companyMasterPersistence the company master persistence
	 */
	public void setCompanyMasterPersistence(
		CompanyMasterPersistence companyMasterPersistence) {
		this.companyMasterPersistence = companyMasterPersistence;
	}

	/**
	 * Returns the company parent details local service.
	 *
	 * @return the company parent details local service
	 */
	public com.stpl.app.service.CompanyParentDetailsLocalService getCompanyParentDetailsLocalService() {
		return companyParentDetailsLocalService;
	}

	/**
	 * Sets the company parent details local service.
	 *
	 * @param companyParentDetailsLocalService the company parent details local service
	 */
	public void setCompanyParentDetailsLocalService(
		com.stpl.app.service.CompanyParentDetailsLocalService companyParentDetailsLocalService) {
		this.companyParentDetailsLocalService = companyParentDetailsLocalService;
	}

	/**
	 * Returns the company parent details persistence.
	 *
	 * @return the company parent details persistence
	 */
	public CompanyParentDetailsPersistence getCompanyParentDetailsPersistence() {
		return companyParentDetailsPersistence;
	}

	/**
	 * Sets the company parent details persistence.
	 *
	 * @param companyParentDetailsPersistence the company parent details persistence
	 */
	public void setCompanyParentDetailsPersistence(
		CompanyParentDetailsPersistence companyParentDetailsPersistence) {
		this.companyParentDetailsPersistence = companyParentDetailsPersistence;
	}

	/**
	 * Returns the company qualifier local service.
	 *
	 * @return the company qualifier local service
	 */
	public com.stpl.app.service.CompanyQualifierLocalService getCompanyQualifierLocalService() {
		return companyQualifierLocalService;
	}

	/**
	 * Sets the company qualifier local service.
	 *
	 * @param companyQualifierLocalService the company qualifier local service
	 */
	public void setCompanyQualifierLocalService(
		com.stpl.app.service.CompanyQualifierLocalService companyQualifierLocalService) {
		this.companyQualifierLocalService = companyQualifierLocalService;
	}

	/**
	 * Returns the company qualifier persistence.
	 *
	 * @return the company qualifier persistence
	 */
	public CompanyQualifierPersistence getCompanyQualifierPersistence() {
		return companyQualifierPersistence;
	}

	/**
	 * Sets the company qualifier persistence.
	 *
	 * @param companyQualifierPersistence the company qualifier persistence
	 */
	public void setCompanyQualifierPersistence(
		CompanyQualifierPersistence companyQualifierPersistence) {
		this.companyQualifierPersistence = companyQualifierPersistence;
	}

	/**
	 * Returns the company trade class local service.
	 *
	 * @return the company trade class local service
	 */
	public com.stpl.app.service.CompanyTradeClassLocalService getCompanyTradeClassLocalService() {
		return companyTradeClassLocalService;
	}

	/**
	 * Sets the company trade class local service.
	 *
	 * @param companyTradeClassLocalService the company trade class local service
	 */
	public void setCompanyTradeClassLocalService(
		com.stpl.app.service.CompanyTradeClassLocalService companyTradeClassLocalService) {
		this.companyTradeClassLocalService = companyTradeClassLocalService;
	}

	/**
	 * Returns the company trade class persistence.
	 *
	 * @return the company trade class persistence
	 */
	public CompanyTradeClassPersistence getCompanyTradeClassPersistence() {
		return companyTradeClassPersistence;
	}

	/**
	 * Sets the company trade class persistence.
	 *
	 * @param companyTradeClassPersistence the company trade class persistence
	 */
	public void setCompanyTradeClassPersistence(
		CompanyTradeClassPersistence companyTradeClassPersistence) {
		this.companyTradeClassPersistence = companyTradeClassPersistence;
	}

	/**
	 * Returns the contract alias master local service.
	 *
	 * @return the contract alias master local service
	 */
	public com.stpl.app.service.ContractAliasMasterLocalService getContractAliasMasterLocalService() {
		return contractAliasMasterLocalService;
	}

	/**
	 * Sets the contract alias master local service.
	 *
	 * @param contractAliasMasterLocalService the contract alias master local service
	 */
	public void setContractAliasMasterLocalService(
		com.stpl.app.service.ContractAliasMasterLocalService contractAliasMasterLocalService) {
		this.contractAliasMasterLocalService = contractAliasMasterLocalService;
	}

	/**
	 * Returns the contract alias master persistence.
	 *
	 * @return the contract alias master persistence
	 */
	public ContractAliasMasterPersistence getContractAliasMasterPersistence() {
		return contractAliasMasterPersistence;
	}

	/**
	 * Sets the contract alias master persistence.
	 *
	 * @param contractAliasMasterPersistence the contract alias master persistence
	 */
	public void setContractAliasMasterPersistence(
		ContractAliasMasterPersistence contractAliasMasterPersistence) {
		this.contractAliasMasterPersistence = contractAliasMasterPersistence;
	}

	/**
	 * Returns the contract master local service.
	 *
	 * @return the contract master local service
	 */
	public com.stpl.app.service.ContractMasterLocalService getContractMasterLocalService() {
		return contractMasterLocalService;
	}

	/**
	 * Sets the contract master local service.
	 *
	 * @param contractMasterLocalService the contract master local service
	 */
	public void setContractMasterLocalService(
		com.stpl.app.service.ContractMasterLocalService contractMasterLocalService) {
		this.contractMasterLocalService = contractMasterLocalService;
	}

	/**
	 * Returns the contract master persistence.
	 *
	 * @return the contract master persistence
	 */
	public ContractMasterPersistence getContractMasterPersistence() {
		return contractMasterPersistence;
	}

	/**
	 * Sets the contract master persistence.
	 *
	 * @param contractMasterPersistence the contract master persistence
	 */
	public void setContractMasterPersistence(
		ContractMasterPersistence contractMasterPersistence) {
		this.contractMasterPersistence = contractMasterPersistence;
	}

	/**
	 * Returns the cpi index master local service.
	 *
	 * @return the cpi index master local service
	 */
	public com.stpl.app.service.CpiIndexMasterLocalService getCpiIndexMasterLocalService() {
		return cpiIndexMasterLocalService;
	}

	/**
	 * Sets the cpi index master local service.
	 *
	 * @param cpiIndexMasterLocalService the cpi index master local service
	 */
	public void setCpiIndexMasterLocalService(
		com.stpl.app.service.CpiIndexMasterLocalService cpiIndexMasterLocalService) {
		this.cpiIndexMasterLocalService = cpiIndexMasterLocalService;
	}

	/**
	 * Returns the cpi index master persistence.
	 *
	 * @return the cpi index master persistence
	 */
	public CpiIndexMasterPersistence getCpiIndexMasterPersistence() {
		return cpiIndexMasterPersistence;
	}

	/**
	 * Sets the cpi index master persistence.
	 *
	 * @param cpiIndexMasterPersistence the cpi index master persistence
	 */
	public void setCpiIndexMasterPersistence(
		CpiIndexMasterPersistence cpiIndexMasterPersistence) {
		this.cpiIndexMasterPersistence = cpiIndexMasterPersistence;
	}

	/**
	 * Returns the custom view details local service.
	 *
	 * @return the custom view details local service
	 */
	public com.stpl.app.service.CustomViewDetailsLocalService getCustomViewDetailsLocalService() {
		return customViewDetailsLocalService;
	}

	/**
	 * Sets the custom view details local service.
	 *
	 * @param customViewDetailsLocalService the custom view details local service
	 */
	public void setCustomViewDetailsLocalService(
		com.stpl.app.service.CustomViewDetailsLocalService customViewDetailsLocalService) {
		this.customViewDetailsLocalService = customViewDetailsLocalService;
	}

	/**
	 * Returns the custom view details persistence.
	 *
	 * @return the custom view details persistence
	 */
	public CustomViewDetailsPersistence getCustomViewDetailsPersistence() {
		return customViewDetailsPersistence;
	}

	/**
	 * Sets the custom view details persistence.
	 *
	 * @param customViewDetailsPersistence the custom view details persistence
	 */
	public void setCustomViewDetailsPersistence(
		CustomViewDetailsPersistence customViewDetailsPersistence) {
		this.customViewDetailsPersistence = customViewDetailsPersistence;
	}

	/**
	 * Returns the custom view master local service.
	 *
	 * @return the custom view master local service
	 */
	public com.stpl.app.service.CustomViewMasterLocalService getCustomViewMasterLocalService() {
		return customViewMasterLocalService;
	}

	/**
	 * Sets the custom view master local service.
	 *
	 * @param customViewMasterLocalService the custom view master local service
	 */
	public void setCustomViewMasterLocalService(
		com.stpl.app.service.CustomViewMasterLocalService customViewMasterLocalService) {
		this.customViewMasterLocalService = customViewMasterLocalService;
	}

	/**
	 * Returns the custom view master persistence.
	 *
	 * @return the custom view master persistence
	 */
	public CustomViewMasterPersistence getCustomViewMasterPersistence() {
		return customViewMasterPersistence;
	}

	/**
	 * Sets the custom view master persistence.
	 *
	 * @param customViewMasterPersistence the custom view master persistence
	 */
	public void setCustomViewMasterPersistence(
		CustomViewMasterPersistence customViewMasterPersistence) {
		this.customViewMasterPersistence = customViewMasterPersistence;
	}

	/**
	 * Returns the deduction calendar details local service.
	 *
	 * @return the deduction calendar details local service
	 */
	public com.stpl.app.service.DeductionCalendarDetailsLocalService getDeductionCalendarDetailsLocalService() {
		return deductionCalendarDetailsLocalService;
	}

	/**
	 * Sets the deduction calendar details local service.
	 *
	 * @param deductionCalendarDetailsLocalService the deduction calendar details local service
	 */
	public void setDeductionCalendarDetailsLocalService(
		com.stpl.app.service.DeductionCalendarDetailsLocalService deductionCalendarDetailsLocalService) {
		this.deductionCalendarDetailsLocalService = deductionCalendarDetailsLocalService;
	}

	/**
	 * Returns the deduction calendar details persistence.
	 *
	 * @return the deduction calendar details persistence
	 */
	public DeductionCalendarDetailsPersistence getDeductionCalendarDetailsPersistence() {
		return deductionCalendarDetailsPersistence;
	}

	/**
	 * Sets the deduction calendar details persistence.
	 *
	 * @param deductionCalendarDetailsPersistence the deduction calendar details persistence
	 */
	public void setDeductionCalendarDetailsPersistence(
		DeductionCalendarDetailsPersistence deductionCalendarDetailsPersistence) {
		this.deductionCalendarDetailsPersistence = deductionCalendarDetailsPersistence;
	}

	/**
	 * Returns the deduction calendar master local service.
	 *
	 * @return the deduction calendar master local service
	 */
	public com.stpl.app.service.DeductionCalendarMasterLocalService getDeductionCalendarMasterLocalService() {
		return deductionCalendarMasterLocalService;
	}

	/**
	 * Sets the deduction calendar master local service.
	 *
	 * @param deductionCalendarMasterLocalService the deduction calendar master local service
	 */
	public void setDeductionCalendarMasterLocalService(
		com.stpl.app.service.DeductionCalendarMasterLocalService deductionCalendarMasterLocalService) {
		this.deductionCalendarMasterLocalService = deductionCalendarMasterLocalService;
	}

	/**
	 * Returns the deduction calendar master persistence.
	 *
	 * @return the deduction calendar master persistence
	 */
	public DeductionCalendarMasterPersistence getDeductionCalendarMasterPersistence() {
		return deductionCalendarMasterPersistence;
	}

	/**
	 * Sets the deduction calendar master persistence.
	 *
	 * @param deductionCalendarMasterPersistence the deduction calendar master persistence
	 */
	public void setDeductionCalendarMasterPersistence(
		DeductionCalendarMasterPersistence deductionCalendarMasterPersistence) {
		this.deductionCalendarMasterPersistence = deductionCalendarMasterPersistence;
	}

	/**
	 * Returns the deduction details local service.
	 *
	 * @return the deduction details local service
	 */
	public com.stpl.app.service.DeductionDetailsLocalService getDeductionDetailsLocalService() {
		return deductionDetailsLocalService;
	}

	/**
	 * Sets the deduction details local service.
	 *
	 * @param deductionDetailsLocalService the deduction details local service
	 */
	public void setDeductionDetailsLocalService(
		com.stpl.app.service.DeductionDetailsLocalService deductionDetailsLocalService) {
		this.deductionDetailsLocalService = deductionDetailsLocalService;
	}

	/**
	 * Returns the deduction details persistence.
	 *
	 * @return the deduction details persistence
	 */
	public DeductionDetailsPersistence getDeductionDetailsPersistence() {
		return deductionDetailsPersistence;
	}

	/**
	 * Sets the deduction details persistence.
	 *
	 * @param deductionDetailsPersistence the deduction details persistence
	 */
	public void setDeductionDetailsPersistence(
		DeductionDetailsPersistence deductionDetailsPersistence) {
		this.deductionDetailsPersistence = deductionDetailsPersistence;
	}

	/**
	 * Returns the deduction group local service.
	 *
	 * @return the deduction group local service
	 */
	public com.stpl.app.service.DeductionGroupLocalService getDeductionGroupLocalService() {
		return deductionGroupLocalService;
	}

	/**
	 * Sets the deduction group local service.
	 *
	 * @param deductionGroupLocalService the deduction group local service
	 */
	public void setDeductionGroupLocalService(
		com.stpl.app.service.DeductionGroupLocalService deductionGroupLocalService) {
		this.deductionGroupLocalService = deductionGroupLocalService;
	}

	/**
	 * Returns the deduction group persistence.
	 *
	 * @return the deduction group persistence
	 */
	public DeductionGroupPersistence getDeductionGroupPersistence() {
		return deductionGroupPersistence;
	}

	/**
	 * Sets the deduction group persistence.
	 *
	 * @param deductionGroupPersistence the deduction group persistence
	 */
	public void setDeductionGroupPersistence(
		DeductionGroupPersistence deductionGroupPersistence) {
		this.deductionGroupPersistence = deductionGroupPersistence;
	}

	/**
	 * Returns the deduction group details local service.
	 *
	 * @return the deduction group details local service
	 */
	public com.stpl.app.service.DeductionGroupDetailsLocalService getDeductionGroupDetailsLocalService() {
		return deductionGroupDetailsLocalService;
	}

	/**
	 * Sets the deduction group details local service.
	 *
	 * @param deductionGroupDetailsLocalService the deduction group details local service
	 */
	public void setDeductionGroupDetailsLocalService(
		com.stpl.app.service.DeductionGroupDetailsLocalService deductionGroupDetailsLocalService) {
		this.deductionGroupDetailsLocalService = deductionGroupDetailsLocalService;
	}

	/**
	 * Returns the deduction group details persistence.
	 *
	 * @return the deduction group details persistence
	 */
	public DeductionGroupDetailsPersistence getDeductionGroupDetailsPersistence() {
		return deductionGroupDetailsPersistence;
	}

	/**
	 * Sets the deduction group details persistence.
	 *
	 * @param deductionGroupDetailsPersistence the deduction group details persistence
	 */
	public void setDeductionGroupDetailsPersistence(
		DeductionGroupDetailsPersistence deductionGroupDetailsPersistence) {
		this.deductionGroupDetailsPersistence = deductionGroupDetailsPersistence;
	}

	/**
	 * Returns the demand forecast local service.
	 *
	 * @return the demand forecast local service
	 */
	public com.stpl.app.service.DemandForecastLocalService getDemandForecastLocalService() {
		return demandForecastLocalService;
	}

	/**
	 * Sets the demand forecast local service.
	 *
	 * @param demandForecastLocalService the demand forecast local service
	 */
	public void setDemandForecastLocalService(
		com.stpl.app.service.DemandForecastLocalService demandForecastLocalService) {
		this.demandForecastLocalService = demandForecastLocalService;
	}

	/**
	 * Returns the demand forecast persistence.
	 *
	 * @return the demand forecast persistence
	 */
	public DemandForecastPersistence getDemandForecastPersistence() {
		return demandForecastPersistence;
	}

	/**
	 * Sets the demand forecast persistence.
	 *
	 * @param demandForecastPersistence the demand forecast persistence
	 */
	public void setDemandForecastPersistence(
		DemandForecastPersistence demandForecastPersistence) {
		this.demandForecastPersistence = demandForecastPersistence;
	}

	/**
	 * Returns the doc details local service.
	 *
	 * @return the doc details local service
	 */
	public com.stpl.app.service.DocDetailsLocalService getDocDetailsLocalService() {
		return docDetailsLocalService;
	}

	/**
	 * Sets the doc details local service.
	 *
	 * @param docDetailsLocalService the doc details local service
	 */
	public void setDocDetailsLocalService(
		com.stpl.app.service.DocDetailsLocalService docDetailsLocalService) {
		this.docDetailsLocalService = docDetailsLocalService;
	}

	/**
	 * Returns the doc details persistence.
	 *
	 * @return the doc details persistence
	 */
	public DocDetailsPersistence getDocDetailsPersistence() {
		return docDetailsPersistence;
	}

	/**
	 * Sets the doc details persistence.
	 *
	 * @param docDetailsPersistence the doc details persistence
	 */
	public void setDocDetailsPersistence(
		DocDetailsPersistence docDetailsPersistence) {
		this.docDetailsPersistence = docDetailsPersistence;
	}

	/**
	 * Returns the fcp actuals local service.
	 *
	 * @return the fcp actuals local service
	 */
	public com.stpl.app.service.FcpActualsLocalService getFcpActualsLocalService() {
		return fcpActualsLocalService;
	}

	/**
	 * Sets the fcp actuals local service.
	 *
	 * @param fcpActualsLocalService the fcp actuals local service
	 */
	public void setFcpActualsLocalService(
		com.stpl.app.service.FcpActualsLocalService fcpActualsLocalService) {
		this.fcpActualsLocalService = fcpActualsLocalService;
	}

	/**
	 * Returns the fcp actuals persistence.
	 *
	 * @return the fcp actuals persistence
	 */
	public FcpActualsPersistence getFcpActualsPersistence() {
		return fcpActualsPersistence;
	}

	/**
	 * Sets the fcp actuals persistence.
	 *
	 * @param fcpActualsPersistence the fcp actuals persistence
	 */
	public void setFcpActualsPersistence(
		FcpActualsPersistence fcpActualsPersistence) {
		this.fcpActualsPersistence = fcpActualsPersistence;
	}

	/**
	 * Returns the fcp proj local service.
	 *
	 * @return the fcp proj local service
	 */
	public com.stpl.app.service.FcpProjLocalService getFcpProjLocalService() {
		return fcpProjLocalService;
	}

	/**
	 * Sets the fcp proj local service.
	 *
	 * @param fcpProjLocalService the fcp proj local service
	 */
	public void setFcpProjLocalService(
		com.stpl.app.service.FcpProjLocalService fcpProjLocalService) {
		this.fcpProjLocalService = fcpProjLocalService;
	}

	/**
	 * Returns the fcp proj persistence.
	 *
	 * @return the fcp proj persistence
	 */
	public FcpProjPersistence getFcpProjPersistence() {
		return fcpProjPersistence;
	}

	/**
	 * Sets the fcp proj persistence.
	 *
	 * @param fcpProjPersistence the fcp proj persistence
	 */
	public void setFcpProjPersistence(FcpProjPersistence fcpProjPersistence) {
		this.fcpProjPersistence = fcpProjPersistence;
	}

	/**
	 * Returns the federal new ndc local service.
	 *
	 * @return the federal new ndc local service
	 */
	public com.stpl.app.service.FederalNewNdcLocalService getFederalNewNdcLocalService() {
		return federalNewNdcLocalService;
	}

	/**
	 * Sets the federal new ndc local service.
	 *
	 * @param federalNewNdcLocalService the federal new ndc local service
	 */
	public void setFederalNewNdcLocalService(
		com.stpl.app.service.FederalNewNdcLocalService federalNewNdcLocalService) {
		this.federalNewNdcLocalService = federalNewNdcLocalService;
	}

	/**
	 * Returns the federal new ndc persistence.
	 *
	 * @return the federal new ndc persistence
	 */
	public FederalNewNdcPersistence getFederalNewNdcPersistence() {
		return federalNewNdcPersistence;
	}

	/**
	 * Sets the federal new ndc persistence.
	 *
	 * @param federalNewNdcPersistence the federal new ndc persistence
	 */
	public void setFederalNewNdcPersistence(
		FederalNewNdcPersistence federalNewNdcPersistence) {
		this.federalNewNdcPersistence = federalNewNdcPersistence;
	}

	/**
	 * Returns the file management local service.
	 *
	 * @return the file management local service
	 */
	public com.stpl.app.service.FileManagementLocalService getFileManagementLocalService() {
		return fileManagementLocalService;
	}

	/**
	 * Sets the file management local service.
	 *
	 * @param fileManagementLocalService the file management local service
	 */
	public void setFileManagementLocalService(
		com.stpl.app.service.FileManagementLocalService fileManagementLocalService) {
		this.fileManagementLocalService = fileManagementLocalService;
	}

	/**
	 * Returns the file management persistence.
	 *
	 * @return the file management persistence
	 */
	public FileManagementPersistence getFileManagementPersistence() {
		return fileManagementPersistence;
	}

	/**
	 * Sets the file management persistence.
	 *
	 * @param fileManagementPersistence the file management persistence
	 */
	public void setFileManagementPersistence(
		FileManagementPersistence fileManagementPersistence) {
		this.fileManagementPersistence = fileManagementPersistence;
	}

	/**
	 * Returns the forecast config local service.
	 *
	 * @return the forecast config local service
	 */
	public com.stpl.app.service.ForecastConfigLocalService getForecastConfigLocalService() {
		return forecastConfigLocalService;
	}

	/**
	 * Sets the forecast config local service.
	 *
	 * @param forecastConfigLocalService the forecast config local service
	 */
	public void setForecastConfigLocalService(
		com.stpl.app.service.ForecastConfigLocalService forecastConfigLocalService) {
		this.forecastConfigLocalService = forecastConfigLocalService;
	}

	/**
	 * Returns the forecast config persistence.
	 *
	 * @return the forecast config persistence
	 */
	public ForecastConfigPersistence getForecastConfigPersistence() {
		return forecastConfigPersistence;
	}

	/**
	 * Sets the forecast config persistence.
	 *
	 * @param forecastConfigPersistence the forecast config persistence
	 */
	public void setForecastConfigPersistence(
		ForecastConfigPersistence forecastConfigPersistence) {
		this.forecastConfigPersistence = forecastConfigPersistence;
	}

	/**
	 * Returns the forecasting formula local service.
	 *
	 * @return the forecasting formula local service
	 */
	public com.stpl.app.service.ForecastingFormulaLocalService getForecastingFormulaLocalService() {
		return forecastingFormulaLocalService;
	}

	/**
	 * Sets the forecasting formula local service.
	 *
	 * @param forecastingFormulaLocalService the forecasting formula local service
	 */
	public void setForecastingFormulaLocalService(
		com.stpl.app.service.ForecastingFormulaLocalService forecastingFormulaLocalService) {
		this.forecastingFormulaLocalService = forecastingFormulaLocalService;
	}

	/**
	 * Returns the forecasting formula persistence.
	 *
	 * @return the forecasting formula persistence
	 */
	public ForecastingFormulaPersistence getForecastingFormulaPersistence() {
		return forecastingFormulaPersistence;
	}

	/**
	 * Sets the forecasting formula persistence.
	 *
	 * @param forecastingFormulaPersistence the forecasting formula persistence
	 */
	public void setForecastingFormulaPersistence(
		ForecastingFormulaPersistence forecastingFormulaPersistence) {
		this.forecastingFormulaPersistence = forecastingFormulaPersistence;
	}

	/**
	 * Returns the forecasting master local service.
	 *
	 * @return the forecasting master local service
	 */
	public com.stpl.app.service.ForecastingMasterLocalService getForecastingMasterLocalService() {
		return forecastingMasterLocalService;
	}

	/**
	 * Sets the forecasting master local service.
	 *
	 * @param forecastingMasterLocalService the forecasting master local service
	 */
	public void setForecastingMasterLocalService(
		com.stpl.app.service.ForecastingMasterLocalService forecastingMasterLocalService) {
		this.forecastingMasterLocalService = forecastingMasterLocalService;
	}

	/**
	 * Returns the forecasting master persistence.
	 *
	 * @return the forecasting master persistence
	 */
	public ForecastingMasterPersistence getForecastingMasterPersistence() {
		return forecastingMasterPersistence;
	}

	/**
	 * Sets the forecasting master persistence.
	 *
	 * @param forecastingMasterPersistence the forecasting master persistence
	 */
	public void setForecastingMasterPersistence(
		ForecastingMasterPersistence forecastingMasterPersistence) {
		this.forecastingMasterPersistence = forecastingMasterPersistence;
	}

	/**
	 * Returns the forecasting view master local service.
	 *
	 * @return the forecasting view master local service
	 */
	public com.stpl.app.service.ForecastingViewMasterLocalService getForecastingViewMasterLocalService() {
		return forecastingViewMasterLocalService;
	}

	/**
	 * Sets the forecasting view master local service.
	 *
	 * @param forecastingViewMasterLocalService the forecasting view master local service
	 */
	public void setForecastingViewMasterLocalService(
		com.stpl.app.service.ForecastingViewMasterLocalService forecastingViewMasterLocalService) {
		this.forecastingViewMasterLocalService = forecastingViewMasterLocalService;
	}

	/**
	 * Returns the forecasting view master persistence.
	 *
	 * @return the forecasting view master persistence
	 */
	public ForecastingViewMasterPersistence getForecastingViewMasterPersistence() {
		return forecastingViewMasterPersistence;
	}

	/**
	 * Sets the forecasting view master persistence.
	 *
	 * @param forecastingViewMasterPersistence the forecasting view master persistence
	 */
	public void setForecastingViewMasterPersistence(
		ForecastingViewMasterPersistence forecastingViewMasterPersistence) {
		this.forecastingViewMasterPersistence = forecastingViewMasterPersistence;
	}

	/**
	 * Returns the formula details master local service.
	 *
	 * @return the formula details master local service
	 */
	public com.stpl.app.service.FormulaDetailsMasterLocalService getFormulaDetailsMasterLocalService() {
		return formulaDetailsMasterLocalService;
	}

	/**
	 * Sets the formula details master local service.
	 *
	 * @param formulaDetailsMasterLocalService the formula details master local service
	 */
	public void setFormulaDetailsMasterLocalService(
		com.stpl.app.service.FormulaDetailsMasterLocalService formulaDetailsMasterLocalService) {
		this.formulaDetailsMasterLocalService = formulaDetailsMasterLocalService;
	}

	/**
	 * Returns the formula details master persistence.
	 *
	 * @return the formula details master persistence
	 */
	public FormulaDetailsMasterPersistence getFormulaDetailsMasterPersistence() {
		return formulaDetailsMasterPersistence;
	}

	/**
	 * Sets the formula details master persistence.
	 *
	 * @param formulaDetailsMasterPersistence the formula details master persistence
	 */
	public void setFormulaDetailsMasterPersistence(
		FormulaDetailsMasterPersistence formulaDetailsMasterPersistence) {
		this.formulaDetailsMasterPersistence = formulaDetailsMasterPersistence;
	}

	/**
	 * Returns the gcm company details local service.
	 *
	 * @return the gcm company details local service
	 */
	public com.stpl.app.service.GcmCompanyDetailsLocalService getGcmCompanyDetailsLocalService() {
		return gcmCompanyDetailsLocalService;
	}

	/**
	 * Sets the gcm company details local service.
	 *
	 * @param gcmCompanyDetailsLocalService the gcm company details local service
	 */
	public void setGcmCompanyDetailsLocalService(
		com.stpl.app.service.GcmCompanyDetailsLocalService gcmCompanyDetailsLocalService) {
		this.gcmCompanyDetailsLocalService = gcmCompanyDetailsLocalService;
	}

	/**
	 * Returns the gcm company details persistence.
	 *
	 * @return the gcm company details persistence
	 */
	public GcmCompanyDetailsPersistence getGcmCompanyDetailsPersistence() {
		return gcmCompanyDetailsPersistence;
	}

	/**
	 * Sets the gcm company details persistence.
	 *
	 * @param gcmCompanyDetailsPersistence the gcm company details persistence
	 */
	public void setGcmCompanyDetailsPersistence(
		GcmCompanyDetailsPersistence gcmCompanyDetailsPersistence) {
		this.gcmCompanyDetailsPersistence = gcmCompanyDetailsPersistence;
	}

	/**
	 * Returns the gcm company link local service.
	 *
	 * @return the gcm company link local service
	 */
	public com.stpl.app.service.GcmCompanyLinkLocalService getGcmCompanyLinkLocalService() {
		return gcmCompanyLinkLocalService;
	}

	/**
	 * Sets the gcm company link local service.
	 *
	 * @param gcmCompanyLinkLocalService the gcm company link local service
	 */
	public void setGcmCompanyLinkLocalService(
		com.stpl.app.service.GcmCompanyLinkLocalService gcmCompanyLinkLocalService) {
		this.gcmCompanyLinkLocalService = gcmCompanyLinkLocalService;
	}

	/**
	 * Returns the gcm company link persistence.
	 *
	 * @return the gcm company link persistence
	 */
	public GcmCompanyLinkPersistence getGcmCompanyLinkPersistence() {
		return gcmCompanyLinkPersistence;
	}

	/**
	 * Sets the gcm company link persistence.
	 *
	 * @param gcmCompanyLinkPersistence the gcm company link persistence
	 */
	public void setGcmCompanyLinkPersistence(
		GcmCompanyLinkPersistence gcmCompanyLinkPersistence) {
		this.gcmCompanyLinkPersistence = gcmCompanyLinkPersistence;
	}

	/**
	 * Returns the gcm contract details local service.
	 *
	 * @return the gcm contract details local service
	 */
	public com.stpl.app.service.GcmContractDetailsLocalService getGcmContractDetailsLocalService() {
		return gcmContractDetailsLocalService;
	}

	/**
	 * Sets the gcm contract details local service.
	 *
	 * @param gcmContractDetailsLocalService the gcm contract details local service
	 */
	public void setGcmContractDetailsLocalService(
		com.stpl.app.service.GcmContractDetailsLocalService gcmContractDetailsLocalService) {
		this.gcmContractDetailsLocalService = gcmContractDetailsLocalService;
	}

	/**
	 * Returns the gcm contract details persistence.
	 *
	 * @return the gcm contract details persistence
	 */
	public GcmContractDetailsPersistence getGcmContractDetailsPersistence() {
		return gcmContractDetailsPersistence;
	}

	/**
	 * Sets the gcm contract details persistence.
	 *
	 * @param gcmContractDetailsPersistence the gcm contract details persistence
	 */
	public void setGcmContractDetailsPersistence(
		GcmContractDetailsPersistence gcmContractDetailsPersistence) {
		this.gcmContractDetailsPersistence = gcmContractDetailsPersistence;
	}

	/**
	 * Returns the gcm global details local service.
	 *
	 * @return the gcm global details local service
	 */
	public com.stpl.app.service.GcmGlobalDetailsLocalService getGcmGlobalDetailsLocalService() {
		return gcmGlobalDetailsLocalService;
	}

	/**
	 * Sets the gcm global details local service.
	 *
	 * @param gcmGlobalDetailsLocalService the gcm global details local service
	 */
	public void setGcmGlobalDetailsLocalService(
		com.stpl.app.service.GcmGlobalDetailsLocalService gcmGlobalDetailsLocalService) {
		this.gcmGlobalDetailsLocalService = gcmGlobalDetailsLocalService;
	}

	/**
	 * Returns the gcm global details persistence.
	 *
	 * @return the gcm global details persistence
	 */
	public GcmGlobalDetailsPersistence getGcmGlobalDetailsPersistence() {
		return gcmGlobalDetailsPersistence;
	}

	/**
	 * Sets the gcm global details persistence.
	 *
	 * @param gcmGlobalDetailsPersistence the gcm global details persistence
	 */
	public void setGcmGlobalDetailsPersistence(
		GcmGlobalDetailsPersistence gcmGlobalDetailsPersistence) {
		this.gcmGlobalDetailsPersistence = gcmGlobalDetailsPersistence;
	}

	/**
	 * Returns the gcm item details local service.
	 *
	 * @return the gcm item details local service
	 */
	public com.stpl.app.service.GcmItemDetailsLocalService getGcmItemDetailsLocalService() {
		return gcmItemDetailsLocalService;
	}

	/**
	 * Sets the gcm item details local service.
	 *
	 * @param gcmItemDetailsLocalService the gcm item details local service
	 */
	public void setGcmItemDetailsLocalService(
		com.stpl.app.service.GcmItemDetailsLocalService gcmItemDetailsLocalService) {
		this.gcmItemDetailsLocalService = gcmItemDetailsLocalService;
	}

	/**
	 * Returns the gcm item details persistence.
	 *
	 * @return the gcm item details persistence
	 */
	public GcmItemDetailsPersistence getGcmItemDetailsPersistence() {
		return gcmItemDetailsPersistence;
	}

	/**
	 * Sets the gcm item details persistence.
	 *
	 * @param gcmItemDetailsPersistence the gcm item details persistence
	 */
	public void setGcmItemDetailsPersistence(
		GcmItemDetailsPersistence gcmItemDetailsPersistence) {
		this.gcmItemDetailsPersistence = gcmItemDetailsPersistence;
	}

	/**
	 * Returns the gl balance master local service.
	 *
	 * @return the gl balance master local service
	 */
	public com.stpl.app.service.GlBalanceMasterLocalService getGlBalanceMasterLocalService() {
		return glBalanceMasterLocalService;
	}

	/**
	 * Sets the gl balance master local service.
	 *
	 * @param glBalanceMasterLocalService the gl balance master local service
	 */
	public void setGlBalanceMasterLocalService(
		com.stpl.app.service.GlBalanceMasterLocalService glBalanceMasterLocalService) {
		this.glBalanceMasterLocalService = glBalanceMasterLocalService;
	}

	/**
	 * Returns the gl balance master persistence.
	 *
	 * @return the gl balance master persistence
	 */
	public GlBalanceMasterPersistence getGlBalanceMasterPersistence() {
		return glBalanceMasterPersistence;
	}

	/**
	 * Sets the gl balance master persistence.
	 *
	 * @param glBalanceMasterPersistence the gl balance master persistence
	 */
	public void setGlBalanceMasterPersistence(
		GlBalanceMasterPersistence glBalanceMasterPersistence) {
		this.glBalanceMasterPersistence = glBalanceMasterPersistence;
	}

	/**
	 * Returns the gl cost center master local service.
	 *
	 * @return the gl cost center master local service
	 */
	public com.stpl.app.service.GlCostCenterMasterLocalService getGlCostCenterMasterLocalService() {
		return glCostCenterMasterLocalService;
	}

	/**
	 * Sets the gl cost center master local service.
	 *
	 * @param glCostCenterMasterLocalService the gl cost center master local service
	 */
	public void setGlCostCenterMasterLocalService(
		com.stpl.app.service.GlCostCenterMasterLocalService glCostCenterMasterLocalService) {
		this.glCostCenterMasterLocalService = glCostCenterMasterLocalService;
	}

	/**
	 * Returns the gl cost center master persistence.
	 *
	 * @return the gl cost center master persistence
	 */
	public GlCostCenterMasterPersistence getGlCostCenterMasterPersistence() {
		return glCostCenterMasterPersistence;
	}

	/**
	 * Sets the gl cost center master persistence.
	 *
	 * @param glCostCenterMasterPersistence the gl cost center master persistence
	 */
	public void setGlCostCenterMasterPersistence(
		GlCostCenterMasterPersistence glCostCenterMasterPersistence) {
		this.glCostCenterMasterPersistence = glCostCenterMasterPersistence;
	}

	/**
	 * Returns the helper table local service.
	 *
	 * @return the helper table local service
	 */
	public com.stpl.app.service.HelperTableLocalService getHelperTableLocalService() {
		return helperTableLocalService;
	}

	/**
	 * Sets the helper table local service.
	 *
	 * @param helperTableLocalService the helper table local service
	 */
	public void setHelperTableLocalService(
		com.stpl.app.service.HelperTableLocalService helperTableLocalService) {
		this.helperTableLocalService = helperTableLocalService;
	}

	/**
	 * Returns the helper table persistence.
	 *
	 * @return the helper table persistence
	 */
	public HelperTablePersistence getHelperTablePersistence() {
		return helperTablePersistence;
	}

	/**
	 * Sets the helper table persistence.
	 *
	 * @param helperTablePersistence the helper table persistence
	 */
	public void setHelperTablePersistence(
		HelperTablePersistence helperTablePersistence) {
		this.helperTablePersistence = helperTablePersistence;
	}

	/**
	 * Returns the helper table finder.
	 *
	 * @return the helper table finder
	 */
	public HelperTableFinder getHelperTableFinder() {
		return helperTableFinder;
	}

	/**
	 * Sets the helper table finder.
	 *
	 * @param helperTableFinder the helper table finder
	 */
	public void setHelperTableFinder(HelperTableFinder helperTableFinder) {
		this.helperTableFinder = helperTableFinder;
	}

	/**
	 * Returns the hierarchy definition local service.
	 *
	 * @return the hierarchy definition local service
	 */
	public com.stpl.app.service.HierarchyDefinitionLocalService getHierarchyDefinitionLocalService() {
		return hierarchyDefinitionLocalService;
	}

	/**
	 * Sets the hierarchy definition local service.
	 *
	 * @param hierarchyDefinitionLocalService the hierarchy definition local service
	 */
	public void setHierarchyDefinitionLocalService(
		com.stpl.app.service.HierarchyDefinitionLocalService hierarchyDefinitionLocalService) {
		this.hierarchyDefinitionLocalService = hierarchyDefinitionLocalService;
	}

	/**
	 * Returns the hierarchy definition persistence.
	 *
	 * @return the hierarchy definition persistence
	 */
	public HierarchyDefinitionPersistence getHierarchyDefinitionPersistence() {
		return hierarchyDefinitionPersistence;
	}

	/**
	 * Sets the hierarchy definition persistence.
	 *
	 * @param hierarchyDefinitionPersistence the hierarchy definition persistence
	 */
	public void setHierarchyDefinitionPersistence(
		HierarchyDefinitionPersistence hierarchyDefinitionPersistence) {
		this.hierarchyDefinitionPersistence = hierarchyDefinitionPersistence;
	}

	/**
	 * Returns the hierarchy level definition local service.
	 *
	 * @return the hierarchy level definition local service
	 */
	public com.stpl.app.service.HierarchyLevelDefinitionLocalService getHierarchyLevelDefinitionLocalService() {
		return hierarchyLevelDefinitionLocalService;
	}

	/**
	 * Sets the hierarchy level definition local service.
	 *
	 * @param hierarchyLevelDefinitionLocalService the hierarchy level definition local service
	 */
	public void setHierarchyLevelDefinitionLocalService(
		com.stpl.app.service.HierarchyLevelDefinitionLocalService hierarchyLevelDefinitionLocalService) {
		this.hierarchyLevelDefinitionLocalService = hierarchyLevelDefinitionLocalService;
	}

	/**
	 * Returns the hierarchy level definition persistence.
	 *
	 * @return the hierarchy level definition persistence
	 */
	public HierarchyLevelDefinitionPersistence getHierarchyLevelDefinitionPersistence() {
		return hierarchyLevelDefinitionPersistence;
	}

	/**
	 * Sets the hierarchy level definition persistence.
	 *
	 * @param hierarchyLevelDefinitionPersistence the hierarchy level definition persistence
	 */
	public void setHierarchyLevelDefinitionPersistence(
		HierarchyLevelDefinitionPersistence hierarchyLevelDefinitionPersistence) {
		this.hierarchyLevelDefinitionPersistence = hierarchyLevelDefinitionPersistence;
	}

	/**
	 * Returns the hierarchy level values local service.
	 *
	 * @return the hierarchy level values local service
	 */
	public com.stpl.app.service.HierarchyLevelValuesLocalService getHierarchyLevelValuesLocalService() {
		return hierarchyLevelValuesLocalService;
	}

	/**
	 * Sets the hierarchy level values local service.
	 *
	 * @param hierarchyLevelValuesLocalService the hierarchy level values local service
	 */
	public void setHierarchyLevelValuesLocalService(
		com.stpl.app.service.HierarchyLevelValuesLocalService hierarchyLevelValuesLocalService) {
		this.hierarchyLevelValuesLocalService = hierarchyLevelValuesLocalService;
	}

	/**
	 * Returns the hierarchy level values persistence.
	 *
	 * @return the hierarchy level values persistence
	 */
	public HierarchyLevelValuesPersistence getHierarchyLevelValuesPersistence() {
		return hierarchyLevelValuesPersistence;
	}

	/**
	 * Sets the hierarchy level values persistence.
	 *
	 * @param hierarchyLevelValuesPersistence the hierarchy level values persistence
	 */
	public void setHierarchyLevelValuesPersistence(
		HierarchyLevelValuesPersistence hierarchyLevelValuesPersistence) {
		this.hierarchyLevelValuesPersistence = hierarchyLevelValuesPersistence;
	}

	/**
	 * Returns the hist company group local service.
	 *
	 * @return the hist company group local service
	 */
	public com.stpl.app.service.HistCompanyGroupLocalService getHistCompanyGroupLocalService() {
		return histCompanyGroupLocalService;
	}

	/**
	 * Sets the hist company group local service.
	 *
	 * @param histCompanyGroupLocalService the hist company group local service
	 */
	public void setHistCompanyGroupLocalService(
		com.stpl.app.service.HistCompanyGroupLocalService histCompanyGroupLocalService) {
		this.histCompanyGroupLocalService = histCompanyGroupLocalService;
	}

	/**
	 * Returns the hist company group persistence.
	 *
	 * @return the hist company group persistence
	 */
	public HistCompanyGroupPersistence getHistCompanyGroupPersistence() {
		return histCompanyGroupPersistence;
	}

	/**
	 * Sets the hist company group persistence.
	 *
	 * @param histCompanyGroupPersistence the hist company group persistence
	 */
	public void setHistCompanyGroupPersistence(
		HistCompanyGroupPersistence histCompanyGroupPersistence) {
		this.histCompanyGroupPersistence = histCompanyGroupPersistence;
	}

	/**
	 * Returns the hist company group details local service.
	 *
	 * @return the hist company group details local service
	 */
	public com.stpl.app.service.HistCompanyGroupDetailsLocalService getHistCompanyGroupDetailsLocalService() {
		return histCompanyGroupDetailsLocalService;
	}

	/**
	 * Sets the hist company group details local service.
	 *
	 * @param histCompanyGroupDetailsLocalService the hist company group details local service
	 */
	public void setHistCompanyGroupDetailsLocalService(
		com.stpl.app.service.HistCompanyGroupDetailsLocalService histCompanyGroupDetailsLocalService) {
		this.histCompanyGroupDetailsLocalService = histCompanyGroupDetailsLocalService;
	}

	/**
	 * Returns the hist company group details persistence.
	 *
	 * @return the hist company group details persistence
	 */
	public HistCompanyGroupDetailsPersistence getHistCompanyGroupDetailsPersistence() {
		return histCompanyGroupDetailsPersistence;
	}

	/**
	 * Sets the hist company group details persistence.
	 *
	 * @param histCompanyGroupDetailsPersistence the hist company group details persistence
	 */
	public void setHistCompanyGroupDetailsPersistence(
		HistCompanyGroupDetailsPersistence histCompanyGroupDetailsPersistence) {
		this.histCompanyGroupDetailsPersistence = histCompanyGroupDetailsPersistence;
	}

	/**
	 * Returns the hist hierarchy definition local service.
	 *
	 * @return the hist hierarchy definition local service
	 */
	public com.stpl.app.service.HistHierarchyDefinitionLocalService getHistHierarchyDefinitionLocalService() {
		return histHierarchyDefinitionLocalService;
	}

	/**
	 * Sets the hist hierarchy definition local service.
	 *
	 * @param histHierarchyDefinitionLocalService the hist hierarchy definition local service
	 */
	public void setHistHierarchyDefinitionLocalService(
		com.stpl.app.service.HistHierarchyDefinitionLocalService histHierarchyDefinitionLocalService) {
		this.histHierarchyDefinitionLocalService = histHierarchyDefinitionLocalService;
	}

	/**
	 * Returns the hist hierarchy definition persistence.
	 *
	 * @return the hist hierarchy definition persistence
	 */
	public HistHierarchyDefinitionPersistence getHistHierarchyDefinitionPersistence() {
		return histHierarchyDefinitionPersistence;
	}

	/**
	 * Sets the hist hierarchy definition persistence.
	 *
	 * @param histHierarchyDefinitionPersistence the hist hierarchy definition persistence
	 */
	public void setHistHierarchyDefinitionPersistence(
		HistHierarchyDefinitionPersistence histHierarchyDefinitionPersistence) {
		this.histHierarchyDefinitionPersistence = histHierarchyDefinitionPersistence;
	}

	/**
	 * Returns the hist hierarchy level defn local service.
	 *
	 * @return the hist hierarchy level defn local service
	 */
	public com.stpl.app.service.HistHierarchyLevelDefnLocalService getHistHierarchyLevelDefnLocalService() {
		return histHierarchyLevelDefnLocalService;
	}

	/**
	 * Sets the hist hierarchy level defn local service.
	 *
	 * @param histHierarchyLevelDefnLocalService the hist hierarchy level defn local service
	 */
	public void setHistHierarchyLevelDefnLocalService(
		com.stpl.app.service.HistHierarchyLevelDefnLocalService histHierarchyLevelDefnLocalService) {
		this.histHierarchyLevelDefnLocalService = histHierarchyLevelDefnLocalService;
	}

	/**
	 * Returns the hist hierarchy level defn persistence.
	 *
	 * @return the hist hierarchy level defn persistence
	 */
	public HistHierarchyLevelDefnPersistence getHistHierarchyLevelDefnPersistence() {
		return histHierarchyLevelDefnPersistence;
	}

	/**
	 * Sets the hist hierarchy level defn persistence.
	 *
	 * @param histHierarchyLevelDefnPersistence the hist hierarchy level defn persistence
	 */
	public void setHistHierarchyLevelDefnPersistence(
		HistHierarchyLevelDefnPersistence histHierarchyLevelDefnPersistence) {
		this.histHierarchyLevelDefnPersistence = histHierarchyLevelDefnPersistence;
	}

	/**
	 * Returns the hist hierarchy level values local service.
	 *
	 * @return the hist hierarchy level values local service
	 */
	public com.stpl.app.service.HistHierarchyLevelValuesLocalService getHistHierarchyLevelValuesLocalService() {
		return histHierarchyLevelValuesLocalService;
	}

	/**
	 * Sets the hist hierarchy level values local service.
	 *
	 * @param histHierarchyLevelValuesLocalService the hist hierarchy level values local service
	 */
	public void setHistHierarchyLevelValuesLocalService(
		com.stpl.app.service.HistHierarchyLevelValuesLocalService histHierarchyLevelValuesLocalService) {
		this.histHierarchyLevelValuesLocalService = histHierarchyLevelValuesLocalService;
	}

	/**
	 * Returns the hist hierarchy level values persistence.
	 *
	 * @return the hist hierarchy level values persistence
	 */
	public HistHierarchyLevelValuesPersistence getHistHierarchyLevelValuesPersistence() {
		return histHierarchyLevelValuesPersistence;
	}

	/**
	 * Sets the hist hierarchy level values persistence.
	 *
	 * @param histHierarchyLevelValuesPersistence the hist hierarchy level values persistence
	 */
	public void setHistHierarchyLevelValuesPersistence(
		HistHierarchyLevelValuesPersistence histHierarchyLevelValuesPersistence) {
		this.histHierarchyLevelValuesPersistence = histHierarchyLevelValuesPersistence;
	}

	/**
	 * Returns the hist item group local service.
	 *
	 * @return the hist item group local service
	 */
	public com.stpl.app.service.HistItemGroupLocalService getHistItemGroupLocalService() {
		return histItemGroupLocalService;
	}

	/**
	 * Sets the hist item group local service.
	 *
	 * @param histItemGroupLocalService the hist item group local service
	 */
	public void setHistItemGroupLocalService(
		com.stpl.app.service.HistItemGroupLocalService histItemGroupLocalService) {
		this.histItemGroupLocalService = histItemGroupLocalService;
	}

	/**
	 * Returns the hist item group persistence.
	 *
	 * @return the hist item group persistence
	 */
	public HistItemGroupPersistence getHistItemGroupPersistence() {
		return histItemGroupPersistence;
	}

	/**
	 * Sets the hist item group persistence.
	 *
	 * @param histItemGroupPersistence the hist item group persistence
	 */
	public void setHistItemGroupPersistence(
		HistItemGroupPersistence histItemGroupPersistence) {
		this.histItemGroupPersistence = histItemGroupPersistence;
	}

	/**
	 * Returns the hist item group details local service.
	 *
	 * @return the hist item group details local service
	 */
	public com.stpl.app.service.HistItemGroupDetailsLocalService getHistItemGroupDetailsLocalService() {
		return histItemGroupDetailsLocalService;
	}

	/**
	 * Sets the hist item group details local service.
	 *
	 * @param histItemGroupDetailsLocalService the hist item group details local service
	 */
	public void setHistItemGroupDetailsLocalService(
		com.stpl.app.service.HistItemGroupDetailsLocalService histItemGroupDetailsLocalService) {
		this.histItemGroupDetailsLocalService = histItemGroupDetailsLocalService;
	}

	/**
	 * Returns the hist item group details persistence.
	 *
	 * @return the hist item group details persistence
	 */
	public HistItemGroupDetailsPersistence getHistItemGroupDetailsPersistence() {
		return histItemGroupDetailsPersistence;
	}

	/**
	 * Sets the hist item group details persistence.
	 *
	 * @param histItemGroupDetailsPersistence the hist item group details persistence
	 */
	public void setHistItemGroupDetailsPersistence(
		HistItemGroupDetailsPersistence histItemGroupDetailsPersistence) {
		this.histItemGroupDetailsPersistence = histItemGroupDetailsPersistence;
	}

	/**
	 * Returns the hist relationship builder local service.
	 *
	 * @return the hist relationship builder local service
	 */
	public com.stpl.app.service.HistRelationshipBuilderLocalService getHistRelationshipBuilderLocalService() {
		return histRelationshipBuilderLocalService;
	}

	/**
	 * Sets the hist relationship builder local service.
	 *
	 * @param histRelationshipBuilderLocalService the hist relationship builder local service
	 */
	public void setHistRelationshipBuilderLocalService(
		com.stpl.app.service.HistRelationshipBuilderLocalService histRelationshipBuilderLocalService) {
		this.histRelationshipBuilderLocalService = histRelationshipBuilderLocalService;
	}

	/**
	 * Returns the hist relationship builder persistence.
	 *
	 * @return the hist relationship builder persistence
	 */
	public HistRelationshipBuilderPersistence getHistRelationshipBuilderPersistence() {
		return histRelationshipBuilderPersistence;
	}

	/**
	 * Sets the hist relationship builder persistence.
	 *
	 * @param histRelationshipBuilderPersistence the hist relationship builder persistence
	 */
	public void setHistRelationshipBuilderPersistence(
		HistRelationshipBuilderPersistence histRelationshipBuilderPersistence) {
		this.histRelationshipBuilderPersistence = histRelationshipBuilderPersistence;
	}

	/**
	 * Returns the hist relationship level defn local service.
	 *
	 * @return the hist relationship level defn local service
	 */
	public com.stpl.app.service.HistRelationshipLevelDefnLocalService getHistRelationshipLevelDefnLocalService() {
		return histRelationshipLevelDefnLocalService;
	}

	/**
	 * Sets the hist relationship level defn local service.
	 *
	 * @param histRelationshipLevelDefnLocalService the hist relationship level defn local service
	 */
	public void setHistRelationshipLevelDefnLocalService(
		com.stpl.app.service.HistRelationshipLevelDefnLocalService histRelationshipLevelDefnLocalService) {
		this.histRelationshipLevelDefnLocalService = histRelationshipLevelDefnLocalService;
	}

	/**
	 * Returns the hist relationship level defn persistence.
	 *
	 * @return the hist relationship level defn persistence
	 */
	public HistRelationshipLevelDefnPersistence getHistRelationshipLevelDefnPersistence() {
		return histRelationshipLevelDefnPersistence;
	}

	/**
	 * Sets the hist relationship level defn persistence.
	 *
	 * @param histRelationshipLevelDefnPersistence the hist relationship level defn persistence
	 */
	public void setHistRelationshipLevelDefnPersistence(
		HistRelationshipLevelDefnPersistence histRelationshipLevelDefnPersistence) {
		this.histRelationshipLevelDefnPersistence = histRelationshipLevelDefnPersistence;
	}

	/**
	 * Returns the hist workflow master local service.
	 *
	 * @return the hist workflow master local service
	 */
	public com.stpl.app.service.HistWorkflowMasterLocalService getHistWorkflowMasterLocalService() {
		return histWorkflowMasterLocalService;
	}

	/**
	 * Sets the hist workflow master local service.
	 *
	 * @param histWorkflowMasterLocalService the hist workflow master local service
	 */
	public void setHistWorkflowMasterLocalService(
		com.stpl.app.service.HistWorkflowMasterLocalService histWorkflowMasterLocalService) {
		this.histWorkflowMasterLocalService = histWorkflowMasterLocalService;
	}

	/**
	 * Returns the hist workflow master persistence.
	 *
	 * @return the hist workflow master persistence
	 */
	public HistWorkflowMasterPersistence getHistWorkflowMasterPersistence() {
		return histWorkflowMasterPersistence;
	}

	/**
	 * Sets the hist workflow master persistence.
	 *
	 * @param histWorkflowMasterPersistence the hist workflow master persistence
	 */
	public void setHistWorkflowMasterPersistence(
		HistWorkflowMasterPersistence histWorkflowMasterPersistence) {
		this.histWorkflowMasterPersistence = histWorkflowMasterPersistence;
	}

	/**
	 * Returns the ifp contract local service.
	 *
	 * @return the ifp contract local service
	 */
	public IfpContractLocalService getIfpContractLocalService() {
		return ifpContractLocalService;
	}

	/**
	 * Sets the ifp contract local service.
	 *
	 * @param ifpContractLocalService the ifp contract local service
	 */
	public void setIfpContractLocalService(
		IfpContractLocalService ifpContractLocalService) {
		this.ifpContractLocalService = ifpContractLocalService;
	}

	/**
	 * Returns the ifp contract persistence.
	 *
	 * @return the ifp contract persistence
	 */
	public IfpContractPersistence getIfpContractPersistence() {
		return ifpContractPersistence;
	}

	/**
	 * Sets the ifp contract persistence.
	 *
	 * @param ifpContractPersistence the ifp contract persistence
	 */
	public void setIfpContractPersistence(
		IfpContractPersistence ifpContractPersistence) {
		this.ifpContractPersistence = ifpContractPersistence;
	}

	/**
	 * Returns the ifp contract details local service.
	 *
	 * @return the ifp contract details local service
	 */
	public com.stpl.app.service.IfpContractDetailsLocalService getIfpContractDetailsLocalService() {
		return ifpContractDetailsLocalService;
	}

	/**
	 * Sets the ifp contract details local service.
	 *
	 * @param ifpContractDetailsLocalService the ifp contract details local service
	 */
	public void setIfpContractDetailsLocalService(
		com.stpl.app.service.IfpContractDetailsLocalService ifpContractDetailsLocalService) {
		this.ifpContractDetailsLocalService = ifpContractDetailsLocalService;
	}

	/**
	 * Returns the ifp contract details persistence.
	 *
	 * @return the ifp contract details persistence
	 */
	public IfpContractDetailsPersistence getIfpContractDetailsPersistence() {
		return ifpContractDetailsPersistence;
	}

	/**
	 * Sets the ifp contract details persistence.
	 *
	 * @param ifpContractDetailsPersistence the ifp contract details persistence
	 */
	public void setIfpContractDetailsPersistence(
		IfpContractDetailsPersistence ifpContractDetailsPersistence) {
		this.ifpContractDetailsPersistence = ifpContractDetailsPersistence;
	}

	/**
	 * Returns the ifp details local service.
	 *
	 * @return the ifp details local service
	 */
	public com.stpl.app.service.IfpDetailsLocalService getIfpDetailsLocalService() {
		return ifpDetailsLocalService;
	}

	/**
	 * Sets the ifp details local service.
	 *
	 * @param ifpDetailsLocalService the ifp details local service
	 */
	public void setIfpDetailsLocalService(
		com.stpl.app.service.IfpDetailsLocalService ifpDetailsLocalService) {
		this.ifpDetailsLocalService = ifpDetailsLocalService;
	}

	/**
	 * Returns the ifp details persistence.
	 *
	 * @return the ifp details persistence
	 */
	public IfpDetailsPersistence getIfpDetailsPersistence() {
		return ifpDetailsPersistence;
	}

	/**
	 * Sets the ifp details persistence.
	 *
	 * @param ifpDetailsPersistence the ifp details persistence
	 */
	public void setIfpDetailsPersistence(
		IfpDetailsPersistence ifpDetailsPersistence) {
		this.ifpDetailsPersistence = ifpDetailsPersistence;
	}

	/**
	 * Returns the ifp model local service.
	 *
	 * @return the ifp model local service
	 */
	public com.stpl.app.service.IfpModelLocalService getIfpModelLocalService() {
		return ifpModelLocalService;
	}

	/**
	 * Sets the ifp model local service.
	 *
	 * @param ifpModelLocalService the ifp model local service
	 */
	public void setIfpModelLocalService(
		com.stpl.app.service.IfpModelLocalService ifpModelLocalService) {
		this.ifpModelLocalService = ifpModelLocalService;
	}

	/**
	 * Returns the ifp model persistence.
	 *
	 * @return the ifp model persistence
	 */
	public IfpModelPersistence getIfpModelPersistence() {
		return ifpModelPersistence;
	}

	/**
	 * Sets the ifp model persistence.
	 *
	 * @param ifpModelPersistence the ifp model persistence
	 */
	public void setIfpModelPersistence(IfpModelPersistence ifpModelPersistence) {
		this.ifpModelPersistence = ifpModelPersistence;
	}

	/**
	 * Returns the imtd cfp details local service.
	 *
	 * @return the imtd cfp details local service
	 */
	public com.stpl.app.service.ImtdCfpDetailsLocalService getImtdCfpDetailsLocalService() {
		return imtdCfpDetailsLocalService;
	}

	/**
	 * Sets the imtd cfp details local service.
	 *
	 * @param imtdCfpDetailsLocalService the imtd cfp details local service
	 */
	public void setImtdCfpDetailsLocalService(
		com.stpl.app.service.ImtdCfpDetailsLocalService imtdCfpDetailsLocalService) {
		this.imtdCfpDetailsLocalService = imtdCfpDetailsLocalService;
	}

	/**
	 * Returns the imtd cfp details persistence.
	 *
	 * @return the imtd cfp details persistence
	 */
	public ImtdCfpDetailsPersistence getImtdCfpDetailsPersistence() {
		return imtdCfpDetailsPersistence;
	}

	/**
	 * Sets the imtd cfp details persistence.
	 *
	 * @param imtdCfpDetailsPersistence the imtd cfp details persistence
	 */
	public void setImtdCfpDetailsPersistence(
		ImtdCfpDetailsPersistence imtdCfpDetailsPersistence) {
		this.imtdCfpDetailsPersistence = imtdCfpDetailsPersistence;
	}

	/**
	 * Returns the imtd deduction details local service.
	 *
	 * @return the imtd deduction details local service
	 */
	public com.stpl.app.service.ImtdDeductionDetailsLocalService getImtdDeductionDetailsLocalService() {
		return imtdDeductionDetailsLocalService;
	}

	/**
	 * Sets the imtd deduction details local service.
	 *
	 * @param imtdDeductionDetailsLocalService the imtd deduction details local service
	 */
	public void setImtdDeductionDetailsLocalService(
		com.stpl.app.service.ImtdDeductionDetailsLocalService imtdDeductionDetailsLocalService) {
		this.imtdDeductionDetailsLocalService = imtdDeductionDetailsLocalService;
	}

	/**
	 * Returns the imtd deduction details persistence.
	 *
	 * @return the imtd deduction details persistence
	 */
	public ImtdDeductionDetailsPersistence getImtdDeductionDetailsPersistence() {
		return imtdDeductionDetailsPersistence;
	}

	/**
	 * Sets the imtd deduction details persistence.
	 *
	 * @param imtdDeductionDetailsPersistence the imtd deduction details persistence
	 */
	public void setImtdDeductionDetailsPersistence(
		ImtdDeductionDetailsPersistence imtdDeductionDetailsPersistence) {
		this.imtdDeductionDetailsPersistence = imtdDeductionDetailsPersistence;
	}

	/**
	 * Returns the imtd ifp details local service.
	 *
	 * @return the imtd ifp details local service
	 */
	public com.stpl.app.service.ImtdIfpDetailsLocalService getImtdIfpDetailsLocalService() {
		return imtdIfpDetailsLocalService;
	}

	/**
	 * Sets the imtd ifp details local service.
	 *
	 * @param imtdIfpDetailsLocalService the imtd ifp details local service
	 */
	public void setImtdIfpDetailsLocalService(
		com.stpl.app.service.ImtdIfpDetailsLocalService imtdIfpDetailsLocalService) {
		this.imtdIfpDetailsLocalService = imtdIfpDetailsLocalService;
	}

	/**
	 * Returns the imtd ifp details persistence.
	 *
	 * @return the imtd ifp details persistence
	 */
	public ImtdIfpDetailsPersistence getImtdIfpDetailsPersistence() {
		return imtdIfpDetailsPersistence;
	}

	/**
	 * Sets the imtd ifp details persistence.
	 *
	 * @param imtdIfpDetailsPersistence the imtd ifp details persistence
	 */
	public void setImtdIfpDetailsPersistence(
		ImtdIfpDetailsPersistence imtdIfpDetailsPersistence) {
		this.imtdIfpDetailsPersistence = imtdIfpDetailsPersistence;
	}

	/**
	 * Returns the imtd item price rebate details local service.
	 *
	 * @return the imtd item price rebate details local service
	 */
	public com.stpl.app.service.ImtdItemPriceRebateDetailsLocalService getImtdItemPriceRebateDetailsLocalService() {
		return imtdItemPriceRebateDetailsLocalService;
	}

	/**
	 * Sets the imtd item price rebate details local service.
	 *
	 * @param imtdItemPriceRebateDetailsLocalService the imtd item price rebate details local service
	 */
	public void setImtdItemPriceRebateDetailsLocalService(
		com.stpl.app.service.ImtdItemPriceRebateDetailsLocalService imtdItemPriceRebateDetailsLocalService) {
		this.imtdItemPriceRebateDetailsLocalService = imtdItemPriceRebateDetailsLocalService;
	}

	/**
	 * Returns the imtd item price rebate details persistence.
	 *
	 * @return the imtd item price rebate details persistence
	 */
	public ImtdItemPriceRebateDetailsPersistence getImtdItemPriceRebateDetailsPersistence() {
		return imtdItemPriceRebateDetailsPersistence;
	}

	/**
	 * Sets the imtd item price rebate details persistence.
	 *
	 * @param imtdItemPriceRebateDetailsPersistence the imtd item price rebate details persistence
	 */
	public void setImtdItemPriceRebateDetailsPersistence(
		ImtdItemPriceRebateDetailsPersistence imtdItemPriceRebateDetailsPersistence) {
		this.imtdItemPriceRebateDetailsPersistence = imtdItemPriceRebateDetailsPersistence;
	}

	/**
	 * Returns the imtd level values local service.
	 *
	 * @return the imtd level values local service
	 */
	public com.stpl.app.service.ImtdLevelValuesLocalService getImtdLevelValuesLocalService() {
		return imtdLevelValuesLocalService;
	}

	/**
	 * Sets the imtd level values local service.
	 *
	 * @param imtdLevelValuesLocalService the imtd level values local service
	 */
	public void setImtdLevelValuesLocalService(
		com.stpl.app.service.ImtdLevelValuesLocalService imtdLevelValuesLocalService) {
		this.imtdLevelValuesLocalService = imtdLevelValuesLocalService;
	}

	/**
	 * Returns the imtd level values persistence.
	 *
	 * @return the imtd level values persistence
	 */
	public ImtdLevelValuesPersistence getImtdLevelValuesPersistence() {
		return imtdLevelValuesPersistence;
	}

	/**
	 * Sets the imtd level values persistence.
	 *
	 * @param imtdLevelValuesPersistence the imtd level values persistence
	 */
	public void setImtdLevelValuesPersistence(
		ImtdLevelValuesPersistence imtdLevelValuesPersistence) {
		this.imtdLevelValuesPersistence = imtdLevelValuesPersistence;
	}

	/**
	 * Returns the imtd ps details local service.
	 *
	 * @return the imtd ps details local service
	 */
	public com.stpl.app.service.ImtdPsDetailsLocalService getImtdPsDetailsLocalService() {
		return imtdPsDetailsLocalService;
	}

	/**
	 * Sets the imtd ps details local service.
	 *
	 * @param imtdPsDetailsLocalService the imtd ps details local service
	 */
	public void setImtdPsDetailsLocalService(
		com.stpl.app.service.ImtdPsDetailsLocalService imtdPsDetailsLocalService) {
		this.imtdPsDetailsLocalService = imtdPsDetailsLocalService;
	}

	/**
	 * Returns the imtd ps details persistence.
	 *
	 * @return the imtd ps details persistence
	 */
	public ImtdPsDetailsPersistence getImtdPsDetailsPersistence() {
		return imtdPsDetailsPersistence;
	}

	/**
	 * Sets the imtd ps details persistence.
	 *
	 * @param imtdPsDetailsPersistence the imtd ps details persistence
	 */
	public void setImtdPsDetailsPersistence(
		ImtdPsDetailsPersistence imtdPsDetailsPersistence) {
		this.imtdPsDetailsPersistence = imtdPsDetailsPersistence;
	}

	/**
	 * Returns the imtd rs contract details fr local service.
	 *
	 * @return the imtd rs contract details fr local service
	 */
	public com.stpl.app.service.ImtdRsContractDetailsFrLocalService getImtdRsContractDetailsFrLocalService() {
		return imtdRsContractDetailsFrLocalService;
	}

	/**
	 * Sets the imtd rs contract details fr local service.
	 *
	 * @param imtdRsContractDetailsFrLocalService the imtd rs contract details fr local service
	 */
	public void setImtdRsContractDetailsFrLocalService(
		com.stpl.app.service.ImtdRsContractDetailsFrLocalService imtdRsContractDetailsFrLocalService) {
		this.imtdRsContractDetailsFrLocalService = imtdRsContractDetailsFrLocalService;
	}

	/**
	 * Returns the imtd rs contract details fr persistence.
	 *
	 * @return the imtd rs contract details fr persistence
	 */
	public ImtdRsContractDetailsFrPersistence getImtdRsContractDetailsFrPersistence() {
		return imtdRsContractDetailsFrPersistence;
	}

	/**
	 * Sets the imtd rs contract details fr persistence.
	 *
	 * @param imtdRsContractDetailsFrPersistence the imtd rs contract details fr persistence
	 */
	public void setImtdRsContractDetailsFrPersistence(
		ImtdRsContractDetailsFrPersistence imtdRsContractDetailsFrPersistence) {
		this.imtdRsContractDetailsFrPersistence = imtdRsContractDetailsFrPersistence;
	}

	/**
	 * Returns the imtd rs details local service.
	 *
	 * @return the imtd rs details local service
	 */
	public com.stpl.app.service.ImtdRsDetailsLocalService getImtdRsDetailsLocalService() {
		return imtdRsDetailsLocalService;
	}

	/**
	 * Sets the imtd rs details local service.
	 *
	 * @param imtdRsDetailsLocalService the imtd rs details local service
	 */
	public void setImtdRsDetailsLocalService(
		com.stpl.app.service.ImtdRsDetailsLocalService imtdRsDetailsLocalService) {
		this.imtdRsDetailsLocalService = imtdRsDetailsLocalService;
	}

	/**
	 * Returns the imtd rs details persistence.
	 *
	 * @return the imtd rs details persistence
	 */
	public ImtdRsDetailsPersistence getImtdRsDetailsPersistence() {
		return imtdRsDetailsPersistence;
	}

	/**
	 * Sets the imtd rs details persistence.
	 *
	 * @param imtdRsDetailsPersistence the imtd rs details persistence
	 */
	public void setImtdRsDetailsPersistence(
		ImtdRsDetailsPersistence imtdRsDetailsPersistence) {
		this.imtdRsDetailsPersistence = imtdRsDetailsPersistence;
	}

	/**
	 * Returns the imtd rs details fr local service.
	 *
	 * @return the imtd rs details fr local service
	 */
	public com.stpl.app.service.ImtdRsDetailsFrLocalService getImtdRsDetailsFrLocalService() {
		return imtdRsDetailsFrLocalService;
	}

	/**
	 * Sets the imtd rs details fr local service.
	 *
	 * @param imtdRsDetailsFrLocalService the imtd rs details fr local service
	 */
	public void setImtdRsDetailsFrLocalService(
		com.stpl.app.service.ImtdRsDetailsFrLocalService imtdRsDetailsFrLocalService) {
		this.imtdRsDetailsFrLocalService = imtdRsDetailsFrLocalService;
	}

	/**
	 * Returns the imtd rs details fr persistence.
	 *
	 * @return the imtd rs details fr persistence
	 */
	public ImtdRsDetailsFrPersistence getImtdRsDetailsFrPersistence() {
		return imtdRsDetailsFrPersistence;
	}

	/**
	 * Sets the imtd rs details fr persistence.
	 *
	 * @param imtdRsDetailsFrPersistence the imtd rs details fr persistence
	 */
	public void setImtdRsDetailsFrPersistence(
		ImtdRsDetailsFrPersistence imtdRsDetailsFrPersistence) {
		this.imtdRsDetailsFrPersistence = imtdRsDetailsFrPersistence;
	}

	/**
	 * Returns the imtd sales basis details local service.
	 *
	 * @return the imtd sales basis details local service
	 */
	public com.stpl.app.service.ImtdSalesBasisDetailsLocalService getImtdSalesBasisDetailsLocalService() {
		return imtdSalesBasisDetailsLocalService;
	}

	/**
	 * Sets the imtd sales basis details local service.
	 *
	 * @param imtdSalesBasisDetailsLocalService the imtd sales basis details local service
	 */
	public void setImtdSalesBasisDetailsLocalService(
		com.stpl.app.service.ImtdSalesBasisDetailsLocalService imtdSalesBasisDetailsLocalService) {
		this.imtdSalesBasisDetailsLocalService = imtdSalesBasisDetailsLocalService;
	}

	/**
	 * Returns the imtd sales basis details persistence.
	 *
	 * @return the imtd sales basis details persistence
	 */
	public ImtdSalesBasisDetailsPersistence getImtdSalesBasisDetailsPersistence() {
		return imtdSalesBasisDetailsPersistence;
	}

	/**
	 * Sets the imtd sales basis details persistence.
	 *
	 * @param imtdSalesBasisDetailsPersistence the imtd sales basis details persistence
	 */
	public void setImtdSalesBasisDetailsPersistence(
		ImtdSalesBasisDetailsPersistence imtdSalesBasisDetailsPersistence) {
		this.imtdSalesBasisDetailsPersistence = imtdSalesBasisDetailsPersistence;
	}

	/**
	 * Returns the inventory wd actual mas local service.
	 *
	 * @return the inventory wd actual mas local service
	 */
	public com.stpl.app.service.InventoryWdActualMasLocalService getInventoryWdActualMasLocalService() {
		return inventoryWdActualMasLocalService;
	}

	/**
	 * Sets the inventory wd actual mas local service.
	 *
	 * @param inventoryWdActualMasLocalService the inventory wd actual mas local service
	 */
	public void setInventoryWdActualMasLocalService(
		com.stpl.app.service.InventoryWdActualMasLocalService inventoryWdActualMasLocalService) {
		this.inventoryWdActualMasLocalService = inventoryWdActualMasLocalService;
	}

	/**
	 * Returns the inventory wd actual mas persistence.
	 *
	 * @return the inventory wd actual mas persistence
	 */
	public InventoryWdActualMasPersistence getInventoryWdActualMasPersistence() {
		return inventoryWdActualMasPersistence;
	}

	/**
	 * Sets the inventory wd actual mas persistence.
	 *
	 * @param inventoryWdActualMasPersistence the inventory wd actual mas persistence
	 */
	public void setInventoryWdActualMasPersistence(
		InventoryWdActualMasPersistence inventoryWdActualMasPersistence) {
		this.inventoryWdActualMasPersistence = inventoryWdActualMasPersistence;
	}

	/**
	 * Returns the inventory wd proj mas local service.
	 *
	 * @return the inventory wd proj mas local service
	 */
	public com.stpl.app.service.InventoryWdProjMasLocalService getInventoryWdProjMasLocalService() {
		return inventoryWdProjMasLocalService;
	}

	/**
	 * Sets the inventory wd proj mas local service.
	 *
	 * @param inventoryWdProjMasLocalService the inventory wd proj mas local service
	 */
	public void setInventoryWdProjMasLocalService(
		com.stpl.app.service.InventoryWdProjMasLocalService inventoryWdProjMasLocalService) {
		this.inventoryWdProjMasLocalService = inventoryWdProjMasLocalService;
	}

	/**
	 * Returns the inventory wd proj mas persistence.
	 *
	 * @return the inventory wd proj mas persistence
	 */
	public InventoryWdProjMasPersistence getInventoryWdProjMasPersistence() {
		return inventoryWdProjMasPersistence;
	}

	/**
	 * Sets the inventory wd proj mas persistence.
	 *
	 * @param inventoryWdProjMasPersistence the inventory wd proj mas persistence
	 */
	public void setInventoryWdProjMasPersistence(
		InventoryWdProjMasPersistence inventoryWdProjMasPersistence) {
		this.inventoryWdProjMasPersistence = inventoryWdProjMasPersistence;
	}

	/**
	 * Returns the item group local service.
	 *
	 * @return the item group local service
	 */
	public com.stpl.app.service.ItemGroupLocalService getItemGroupLocalService() {
		return itemGroupLocalService;
	}

	/**
	 * Sets the item group local service.
	 *
	 * @param itemGroupLocalService the item group local service
	 */
	public void setItemGroupLocalService(
		com.stpl.app.service.ItemGroupLocalService itemGroupLocalService) {
		this.itemGroupLocalService = itemGroupLocalService;
	}

	/**
	 * Returns the item group persistence.
	 *
	 * @return the item group persistence
	 */
	public ItemGroupPersistence getItemGroupPersistence() {
		return itemGroupPersistence;
	}

	/**
	 * Sets the item group persistence.
	 *
	 * @param itemGroupPersistence the item group persistence
	 */
	public void setItemGroupPersistence(
		ItemGroupPersistence itemGroupPersistence) {
		this.itemGroupPersistence = itemGroupPersistence;
	}

	/**
	 * Returns the item group details local service.
	 *
	 * @return the item group details local service
	 */
	public com.stpl.app.service.ItemGroupDetailsLocalService getItemGroupDetailsLocalService() {
		return itemGroupDetailsLocalService;
	}

	/**
	 * Sets the item group details local service.
	 *
	 * @param itemGroupDetailsLocalService the item group details local service
	 */
	public void setItemGroupDetailsLocalService(
		com.stpl.app.service.ItemGroupDetailsLocalService itemGroupDetailsLocalService) {
		this.itemGroupDetailsLocalService = itemGroupDetailsLocalService;
	}

	/**
	 * Returns the item group details persistence.
	 *
	 * @return the item group details persistence
	 */
	public ItemGroupDetailsPersistence getItemGroupDetailsPersistence() {
		return itemGroupDetailsPersistence;
	}

	/**
	 * Sets the item group details persistence.
	 *
	 * @param itemGroupDetailsPersistence the item group details persistence
	 */
	public void setItemGroupDetailsPersistence(
		ItemGroupDetailsPersistence itemGroupDetailsPersistence) {
		this.itemGroupDetailsPersistence = itemGroupDetailsPersistence;
	}

	/**
	 * Returns the item hierarchy def master local service.
	 *
	 * @return the item hierarchy def master local service
	 */
	public com.stpl.app.service.ItemHierarchyDefMasterLocalService getItemHierarchyDefMasterLocalService() {
		return itemHierarchyDefMasterLocalService;
	}

	/**
	 * Sets the item hierarchy def master local service.
	 *
	 * @param itemHierarchyDefMasterLocalService the item hierarchy def master local service
	 */
	public void setItemHierarchyDefMasterLocalService(
		com.stpl.app.service.ItemHierarchyDefMasterLocalService itemHierarchyDefMasterLocalService) {
		this.itemHierarchyDefMasterLocalService = itemHierarchyDefMasterLocalService;
	}

	/**
	 * Returns the item hierarchy def master persistence.
	 *
	 * @return the item hierarchy def master persistence
	 */
	public ItemHierarchyDefMasterPersistence getItemHierarchyDefMasterPersistence() {
		return itemHierarchyDefMasterPersistence;
	}

	/**
	 * Sets the item hierarchy def master persistence.
	 *
	 * @param itemHierarchyDefMasterPersistence the item hierarchy def master persistence
	 */
	public void setItemHierarchyDefMasterPersistence(
		ItemHierarchyDefMasterPersistence itemHierarchyDefMasterPersistence) {
		this.itemHierarchyDefMasterPersistence = itemHierarchyDefMasterPersistence;
	}

	/**
	 * Returns the item hierarchy master local service.
	 *
	 * @return the item hierarchy master local service
	 */
	public com.stpl.app.service.ItemHierarchyMasterLocalService getItemHierarchyMasterLocalService() {
		return itemHierarchyMasterLocalService;
	}

	/**
	 * Sets the item hierarchy master local service.
	 *
	 * @param itemHierarchyMasterLocalService the item hierarchy master local service
	 */
	public void setItemHierarchyMasterLocalService(
		com.stpl.app.service.ItemHierarchyMasterLocalService itemHierarchyMasterLocalService) {
		this.itemHierarchyMasterLocalService = itemHierarchyMasterLocalService;
	}

	/**
	 * Returns the item hierarchy master persistence.
	 *
	 * @return the item hierarchy master persistence
	 */
	public ItemHierarchyMasterPersistence getItemHierarchyMasterPersistence() {
		return itemHierarchyMasterPersistence;
	}

	/**
	 * Sets the item hierarchy master persistence.
	 *
	 * @param itemHierarchyMasterPersistence the item hierarchy master persistence
	 */
	public void setItemHierarchyMasterPersistence(
		ItemHierarchyMasterPersistence itemHierarchyMasterPersistence) {
		this.itemHierarchyMasterPersistence = itemHierarchyMasterPersistence;
	}

	/**
	 * Returns the item identifier local service.
	 *
	 * @return the item identifier local service
	 */
	public com.stpl.app.service.ItemIdentifierLocalService getItemIdentifierLocalService() {
		return itemIdentifierLocalService;
	}

	/**
	 * Sets the item identifier local service.
	 *
	 * @param itemIdentifierLocalService the item identifier local service
	 */
	public void setItemIdentifierLocalService(
		com.stpl.app.service.ItemIdentifierLocalService itemIdentifierLocalService) {
		this.itemIdentifierLocalService = itemIdentifierLocalService;
	}

	/**
	 * Returns the item identifier persistence.
	 *
	 * @return the item identifier persistence
	 */
	public ItemIdentifierPersistence getItemIdentifierPersistence() {
		return itemIdentifierPersistence;
	}

	/**
	 * Sets the item identifier persistence.
	 *
	 * @param itemIdentifierPersistence the item identifier persistence
	 */
	public void setItemIdentifierPersistence(
		ItemIdentifierPersistence itemIdentifierPersistence) {
		this.itemIdentifierPersistence = itemIdentifierPersistence;
	}

	/**
	 * Returns the item master local service.
	 *
	 * @return the item master local service
	 */
	public com.stpl.app.service.ItemMasterLocalService getItemMasterLocalService() {
		return itemMasterLocalService;
	}

	/**
	 * Sets the item master local service.
	 *
	 * @param itemMasterLocalService the item master local service
	 */
	public void setItemMasterLocalService(
		com.stpl.app.service.ItemMasterLocalService itemMasterLocalService) {
		this.itemMasterLocalService = itemMasterLocalService;
	}

	/**
	 * Returns the item master persistence.
	 *
	 * @return the item master persistence
	 */
	public ItemMasterPersistence getItemMasterPersistence() {
		return itemMasterPersistence;
	}

	/**
	 * Sets the item master persistence.
	 *
	 * @param itemMasterPersistence the item master persistence
	 */
	public void setItemMasterPersistence(
		ItemMasterPersistence itemMasterPersistence) {
		this.itemMasterPersistence = itemMasterPersistence;
	}

	/**
	 * Returns the item pricing local service.
	 *
	 * @return the item pricing local service
	 */
	public com.stpl.app.service.ItemPricingLocalService getItemPricingLocalService() {
		return itemPricingLocalService;
	}

	/**
	 * Sets the item pricing local service.
	 *
	 * @param itemPricingLocalService the item pricing local service
	 */
	public void setItemPricingLocalService(
		com.stpl.app.service.ItemPricingLocalService itemPricingLocalService) {
		this.itemPricingLocalService = itemPricingLocalService;
	}

	/**
	 * Returns the item pricing persistence.
	 *
	 * @return the item pricing persistence
	 */
	public ItemPricingPersistence getItemPricingPersistence() {
		return itemPricingPersistence;
	}

	/**
	 * Sets the item pricing persistence.
	 *
	 * @param itemPricingPersistence the item pricing persistence
	 */
	public void setItemPricingPersistence(
		ItemPricingPersistence itemPricingPersistence) {
		this.itemPricingPersistence = itemPricingPersistence;
	}

	/**
	 * Returns the item pricing qualifier local service.
	 *
	 * @return the item pricing qualifier local service
	 */
	public com.stpl.app.service.ItemPricingQualifierLocalService getItemPricingQualifierLocalService() {
		return itemPricingQualifierLocalService;
	}

	/**
	 * Sets the item pricing qualifier local service.
	 *
	 * @param itemPricingQualifierLocalService the item pricing qualifier local service
	 */
	public void setItemPricingQualifierLocalService(
		com.stpl.app.service.ItemPricingQualifierLocalService itemPricingQualifierLocalService) {
		this.itemPricingQualifierLocalService = itemPricingQualifierLocalService;
	}

	/**
	 * Returns the item pricing qualifier persistence.
	 *
	 * @return the item pricing qualifier persistence
	 */
	public ItemPricingQualifierPersistence getItemPricingQualifierPersistence() {
		return itemPricingQualifierPersistence;
	}

	/**
	 * Sets the item pricing qualifier persistence.
	 *
	 * @param itemPricingQualifierPersistence the item pricing qualifier persistence
	 */
	public void setItemPricingQualifierPersistence(
		ItemPricingQualifierPersistence itemPricingQualifierPersistence) {
		this.itemPricingQualifierPersistence = itemPricingQualifierPersistence;
	}

	/**
	 * Returns the item qualifier local service.
	 *
	 * @return the item qualifier local service
	 */
	public com.stpl.app.service.ItemQualifierLocalService getItemQualifierLocalService() {
		return itemQualifierLocalService;
	}

	/**
	 * Sets the item qualifier local service.
	 *
	 * @param itemQualifierLocalService the item qualifier local service
	 */
	public void setItemQualifierLocalService(
		com.stpl.app.service.ItemQualifierLocalService itemQualifierLocalService) {
		this.itemQualifierLocalService = itemQualifierLocalService;
	}

	/**
	 * Returns the item qualifier persistence.
	 *
	 * @return the item qualifier persistence
	 */
	public ItemQualifierPersistence getItemQualifierPersistence() {
		return itemQualifierPersistence;
	}

	/**
	 * Sets the item qualifier persistence.
	 *
	 * @param itemQualifierPersistence the item qualifier persistence
	 */
	public void setItemQualifierPersistence(
		ItemQualifierPersistence itemQualifierPersistence) {
		this.itemQualifierPersistence = itemQualifierPersistence;
	}

	/**
	 * Returns the ivld actual master local service.
	 *
	 * @return the ivld actual master local service
	 */
	public com.stpl.app.service.IvldActualMasterLocalService getIvldActualMasterLocalService() {
		return ivldActualMasterLocalService;
	}

	/**
	 * Sets the ivld actual master local service.
	 *
	 * @param ivldActualMasterLocalService the ivld actual master local service
	 */
	public void setIvldActualMasterLocalService(
		com.stpl.app.service.IvldActualMasterLocalService ivldActualMasterLocalService) {
		this.ivldActualMasterLocalService = ivldActualMasterLocalService;
	}

	/**
	 * Returns the ivld actual master persistence.
	 *
	 * @return the ivld actual master persistence
	 */
	public IvldActualMasterPersistence getIvldActualMasterPersistence() {
		return ivldActualMasterPersistence;
	}

	/**
	 * Sets the ivld actual master persistence.
	 *
	 * @param ivldActualMasterPersistence the ivld actual master persistence
	 */
	public void setIvldActualMasterPersistence(
		IvldActualMasterPersistence ivldActualMasterPersistence) {
		this.ivldActualMasterPersistence = ivldActualMasterPersistence;
	}

	/**
	 * Returns the ivld average shelf life local service.
	 *
	 * @return the ivld average shelf life local service
	 */
	public com.stpl.app.service.IvldAverageShelfLifeLocalService getIvldAverageShelfLifeLocalService() {
		return ivldAverageShelfLifeLocalService;
	}

	/**
	 * Sets the ivld average shelf life local service.
	 *
	 * @param ivldAverageShelfLifeLocalService the ivld average shelf life local service
	 */
	public void setIvldAverageShelfLifeLocalService(
		com.stpl.app.service.IvldAverageShelfLifeLocalService ivldAverageShelfLifeLocalService) {
		this.ivldAverageShelfLifeLocalService = ivldAverageShelfLifeLocalService;
	}

	/**
	 * Returns the ivld average shelf life persistence.
	 *
	 * @return the ivld average shelf life persistence
	 */
	public IvldAverageShelfLifePersistence getIvldAverageShelfLifePersistence() {
		return ivldAverageShelfLifePersistence;
	}

	/**
	 * Sets the ivld average shelf life persistence.
	 *
	 * @param ivldAverageShelfLifePersistence the ivld average shelf life persistence
	 */
	public void setIvldAverageShelfLifePersistence(
		IvldAverageShelfLifePersistence ivldAverageShelfLifePersistence) {
		this.ivldAverageShelfLifePersistence = ivldAverageShelfLifePersistence;
	}

	/**
	 * Returns the ivld best price local service.
	 *
	 * @return the ivld best price local service
	 */
	public com.stpl.app.service.IvldBestPriceLocalService getIvldBestPriceLocalService() {
		return ivldBestPriceLocalService;
	}

	/**
	 * Sets the ivld best price local service.
	 *
	 * @param ivldBestPriceLocalService the ivld best price local service
	 */
	public void setIvldBestPriceLocalService(
		com.stpl.app.service.IvldBestPriceLocalService ivldBestPriceLocalService) {
		this.ivldBestPriceLocalService = ivldBestPriceLocalService;
	}

	/**
	 * Returns the ivld best price persistence.
	 *
	 * @return the ivld best price persistence
	 */
	public IvldBestPricePersistence getIvldBestPricePersistence() {
		return ivldBestPricePersistence;
	}

	/**
	 * Sets the ivld best price persistence.
	 *
	 * @param ivldBestPricePersistence the ivld best price persistence
	 */
	public void setIvldBestPricePersistence(
		IvldBestPricePersistence ivldBestPricePersistence) {
		this.ivldBestPricePersistence = ivldBestPricePersistence;
	}

	/**
	 * Returns the ivld cpi local service.
	 *
	 * @return the ivld cpi local service
	 */
	public com.stpl.app.service.IvldCpiLocalService getIvldCpiLocalService() {
		return ivldCpiLocalService;
	}

	/**
	 * Sets the ivld cpi local service.
	 *
	 * @param ivldCpiLocalService the ivld cpi local service
	 */
	public void setIvldCpiLocalService(
		com.stpl.app.service.IvldCpiLocalService ivldCpiLocalService) {
		this.ivldCpiLocalService = ivldCpiLocalService;
	}

	/**
	 * Returns the ivld cpi persistence.
	 *
	 * @return the ivld cpi persistence
	 */
	public IvldCpiPersistence getIvldCpiPersistence() {
		return ivldCpiPersistence;
	}

	/**
	 * Sets the ivld cpi persistence.
	 *
	 * @param ivldCpiPersistence the ivld cpi persistence
	 */
	public void setIvldCpiPersistence(IvldCpiPersistence ivldCpiPersistence) {
		this.ivldCpiPersistence = ivldCpiPersistence;
	}

	/**
	 * Returns the ivld demand actual local service.
	 *
	 * @return the ivld demand actual local service
	 */
	public com.stpl.app.service.IvldDemandActualLocalService getIvldDemandActualLocalService() {
		return ivldDemandActualLocalService;
	}

	/**
	 * Sets the ivld demand actual local service.
	 *
	 * @param ivldDemandActualLocalService the ivld demand actual local service
	 */
	public void setIvldDemandActualLocalService(
		com.stpl.app.service.IvldDemandActualLocalService ivldDemandActualLocalService) {
		this.ivldDemandActualLocalService = ivldDemandActualLocalService;
	}

	/**
	 * Returns the ivld demand actual persistence.
	 *
	 * @return the ivld demand actual persistence
	 */
	public IvldDemandActualPersistence getIvldDemandActualPersistence() {
		return ivldDemandActualPersistence;
	}

	/**
	 * Sets the ivld demand actual persistence.
	 *
	 * @param ivldDemandActualPersistence the ivld demand actual persistence
	 */
	public void setIvldDemandActualPersistence(
		IvldDemandActualPersistence ivldDemandActualPersistence) {
		this.ivldDemandActualPersistence = ivldDemandActualPersistence;
	}

	/**
	 * Returns the ivld demand forecast local service.
	 *
	 * @return the ivld demand forecast local service
	 */
	public com.stpl.app.service.IvldDemandForecastLocalService getIvldDemandForecastLocalService() {
		return ivldDemandForecastLocalService;
	}

	/**
	 * Sets the ivld demand forecast local service.
	 *
	 * @param ivldDemandForecastLocalService the ivld demand forecast local service
	 */
	public void setIvldDemandForecastLocalService(
		com.stpl.app.service.IvldDemandForecastLocalService ivldDemandForecastLocalService) {
		this.ivldDemandForecastLocalService = ivldDemandForecastLocalService;
	}

	/**
	 * Returns the ivld demand forecast persistence.
	 *
	 * @return the ivld demand forecast persistence
	 */
	public IvldDemandForecastPersistence getIvldDemandForecastPersistence() {
		return ivldDemandForecastPersistence;
	}

	/**
	 * Sets the ivld demand forecast persistence.
	 *
	 * @param ivldDemandForecastPersistence the ivld demand forecast persistence
	 */
	public void setIvldDemandForecastPersistence(
		IvldDemandForecastPersistence ivldDemandForecastPersistence) {
		this.ivldDemandForecastPersistence = ivldDemandForecastPersistence;
	}

	/**
	 * Returns the ivld forecast sales local service.
	 *
	 * @return the ivld forecast sales local service
	 */
	public com.stpl.app.service.IvldForecastSalesLocalService getIvldForecastSalesLocalService() {
		return ivldForecastSalesLocalService;
	}

	/**
	 * Sets the ivld forecast sales local service.
	 *
	 * @param ivldForecastSalesLocalService the ivld forecast sales local service
	 */
	public void setIvldForecastSalesLocalService(
		com.stpl.app.service.IvldForecastSalesLocalService ivldForecastSalesLocalService) {
		this.ivldForecastSalesLocalService = ivldForecastSalesLocalService;
	}

	/**
	 * Returns the ivld forecast sales persistence.
	 *
	 * @return the ivld forecast sales persistence
	 */
	public IvldForecastSalesPersistence getIvldForecastSalesPersistence() {
		return ivldForecastSalesPersistence;
	}

	/**
	 * Sets the ivld forecast sales persistence.
	 *
	 * @param ivldForecastSalesPersistence the ivld forecast sales persistence
	 */
	public void setIvldForecastSalesPersistence(
		IvldForecastSalesPersistence ivldForecastSalesPersistence) {
		this.ivldForecastSalesPersistence = ivldForecastSalesPersistence;
	}

	/**
	 * Returns the ivld formula details local service.
	 *
	 * @return the ivld formula details local service
	 */
	public com.stpl.app.service.IvldFormulaDetailsLocalService getIvldFormulaDetailsLocalService() {
		return ivldFormulaDetailsLocalService;
	}

	/**
	 * Sets the ivld formula details local service.
	 *
	 * @param ivldFormulaDetailsLocalService the ivld formula details local service
	 */
	public void setIvldFormulaDetailsLocalService(
		com.stpl.app.service.IvldFormulaDetailsLocalService ivldFormulaDetailsLocalService) {
		this.ivldFormulaDetailsLocalService = ivldFormulaDetailsLocalService;
	}

	/**
	 * Returns the ivld formula details persistence.
	 *
	 * @return the ivld formula details persistence
	 */
	public IvldFormulaDetailsPersistence getIvldFormulaDetailsPersistence() {
		return ivldFormulaDetailsPersistence;
	}

	/**
	 * Sets the ivld formula details persistence.
	 *
	 * @param ivldFormulaDetailsPersistence the ivld formula details persistence
	 */
	public void setIvldFormulaDetailsPersistence(
		IvldFormulaDetailsPersistence ivldFormulaDetailsPersistence) {
		this.ivldFormulaDetailsPersistence = ivldFormulaDetailsPersistence;
	}

	/**
	 * Returns the ivld gl balance local service.
	 *
	 * @return the ivld gl balance local service
	 */
	public com.stpl.app.service.IvldGlBalanceLocalService getIvldGlBalanceLocalService() {
		return ivldGlBalanceLocalService;
	}

	/**
	 * Sets the ivld gl balance local service.
	 *
	 * @param ivldGlBalanceLocalService the ivld gl balance local service
	 */
	public void setIvldGlBalanceLocalService(
		com.stpl.app.service.IvldGlBalanceLocalService ivldGlBalanceLocalService) {
		this.ivldGlBalanceLocalService = ivldGlBalanceLocalService;
	}

	/**
	 * Returns the ivld gl balance persistence.
	 *
	 * @return the ivld gl balance persistence
	 */
	public IvldGlBalancePersistence getIvldGlBalancePersistence() {
		return ivldGlBalancePersistence;
	}

	/**
	 * Sets the ivld gl balance persistence.
	 *
	 * @param ivldGlBalancePersistence the ivld gl balance persistence
	 */
	public void setIvldGlBalancePersistence(
		IvldGlBalancePersistence ivldGlBalancePersistence) {
		this.ivldGlBalancePersistence = ivldGlBalancePersistence;
	}

	/**
	 * Returns the ivld gl cost center local service.
	 *
	 * @return the ivld gl cost center local service
	 */
	public com.stpl.app.service.IvldGlCostCenterLocalService getIvldGlCostCenterLocalService() {
		return ivldGlCostCenterLocalService;
	}

	/**
	 * Sets the ivld gl cost center local service.
	 *
	 * @param ivldGlCostCenterLocalService the ivld gl cost center local service
	 */
	public void setIvldGlCostCenterLocalService(
		com.stpl.app.service.IvldGlCostCenterLocalService ivldGlCostCenterLocalService) {
		this.ivldGlCostCenterLocalService = ivldGlCostCenterLocalService;
	}

	/**
	 * Returns the ivld gl cost center persistence.
	 *
	 * @return the ivld gl cost center persistence
	 */
	public IvldGlCostCenterPersistence getIvldGlCostCenterPersistence() {
		return ivldGlCostCenterPersistence;
	}

	/**
	 * Sets the ivld gl cost center persistence.
	 *
	 * @param ivldGlCostCenterPersistence the ivld gl cost center persistence
	 */
	public void setIvldGlCostCenterPersistence(
		IvldGlCostCenterPersistence ivldGlCostCenterPersistence) {
		this.ivldGlCostCenterPersistence = ivldGlCostCenterPersistence;
	}

	/**
	 * Returns the ivld inventory wd actual mas local service.
	 *
	 * @return the ivld inventory wd actual mas local service
	 */
	public com.stpl.app.service.IvldInventoryWdActualMasLocalService getIvldInventoryWdActualMasLocalService() {
		return ivldInventoryWdActualMasLocalService;
	}

	/**
	 * Sets the ivld inventory wd actual mas local service.
	 *
	 * @param ivldInventoryWdActualMasLocalService the ivld inventory wd actual mas local service
	 */
	public void setIvldInventoryWdActualMasLocalService(
		com.stpl.app.service.IvldInventoryWdActualMasLocalService ivldInventoryWdActualMasLocalService) {
		this.ivldInventoryWdActualMasLocalService = ivldInventoryWdActualMasLocalService;
	}

	/**
	 * Returns the ivld inventory wd actual mas persistence.
	 *
	 * @return the ivld inventory wd actual mas persistence
	 */
	public IvldInventoryWdActualMasPersistence getIvldInventoryWdActualMasPersistence() {
		return ivldInventoryWdActualMasPersistence;
	}

	/**
	 * Sets the ivld inventory wd actual mas persistence.
	 *
	 * @param ivldInventoryWdActualMasPersistence the ivld inventory wd actual mas persistence
	 */
	public void setIvldInventoryWdActualMasPersistence(
		IvldInventoryWdActualMasPersistence ivldInventoryWdActualMasPersistence) {
		this.ivldInventoryWdActualMasPersistence = ivldInventoryWdActualMasPersistence;
	}

	/**
	 * Returns the ivld inventory wd proj mas local service.
	 *
	 * @return the ivld inventory wd proj mas local service
	 */
	public com.stpl.app.service.IvldInventoryWdProjMasLocalService getIvldInventoryWdProjMasLocalService() {
		return ivldInventoryWdProjMasLocalService;
	}

	/**
	 * Sets the ivld inventory wd proj mas local service.
	 *
	 * @param ivldInventoryWdProjMasLocalService the ivld inventory wd proj mas local service
	 */
	public void setIvldInventoryWdProjMasLocalService(
		com.stpl.app.service.IvldInventoryWdProjMasLocalService ivldInventoryWdProjMasLocalService) {
		this.ivldInventoryWdProjMasLocalService = ivldInventoryWdProjMasLocalService;
	}

	/**
	 * Returns the ivld inventory wd proj mas persistence.
	 *
	 * @return the ivld inventory wd proj mas persistence
	 */
	public IvldInventoryWdProjMasPersistence getIvldInventoryWdProjMasPersistence() {
		return ivldInventoryWdProjMasPersistence;
	}

	/**
	 * Sets the ivld inventory wd proj mas persistence.
	 *
	 * @param ivldInventoryWdProjMasPersistence the ivld inventory wd proj mas persistence
	 */
	public void setIvldInventoryWdProjMasPersistence(
		IvldInventoryWdProjMasPersistence ivldInventoryWdProjMasPersistence) {
		this.ivldInventoryWdProjMasPersistence = ivldInventoryWdProjMasPersistence;
	}

	/**
	 * Returns the ivld item hierarchy local service.
	 *
	 * @return the ivld item hierarchy local service
	 */
	public com.stpl.app.service.IvldItemHierarchyLocalService getIvldItemHierarchyLocalService() {
		return ivldItemHierarchyLocalService;
	}

	/**
	 * Sets the ivld item hierarchy local service.
	 *
	 * @param ivldItemHierarchyLocalService the ivld item hierarchy local service
	 */
	public void setIvldItemHierarchyLocalService(
		com.stpl.app.service.IvldItemHierarchyLocalService ivldItemHierarchyLocalService) {
		this.ivldItemHierarchyLocalService = ivldItemHierarchyLocalService;
	}

	/**
	 * Returns the ivld item hierarchy persistence.
	 *
	 * @return the ivld item hierarchy persistence
	 */
	public IvldItemHierarchyPersistence getIvldItemHierarchyPersistence() {
		return ivldItemHierarchyPersistence;
	}

	/**
	 * Sets the ivld item hierarchy persistence.
	 *
	 * @param ivldItemHierarchyPersistence the ivld item hierarchy persistence
	 */
	public void setIvldItemHierarchyPersistence(
		IvldItemHierarchyPersistence ivldItemHierarchyPersistence) {
		this.ivldItemHierarchyPersistence = ivldItemHierarchyPersistence;
	}

	/**
	 * Returns the ivld item hierarchy definition local service.
	 *
	 * @return the ivld item hierarchy definition local service
	 */
	public com.stpl.app.service.IvldItemHierarchyDefinitionLocalService getIvldItemHierarchyDefinitionLocalService() {
		return ivldItemHierarchyDefinitionLocalService;
	}

	/**
	 * Sets the ivld item hierarchy definition local service.
	 *
	 * @param ivldItemHierarchyDefinitionLocalService the ivld item hierarchy definition local service
	 */
	public void setIvldItemHierarchyDefinitionLocalService(
		com.stpl.app.service.IvldItemHierarchyDefinitionLocalService ivldItemHierarchyDefinitionLocalService) {
		this.ivldItemHierarchyDefinitionLocalService = ivldItemHierarchyDefinitionLocalService;
	}

	/**
	 * Returns the ivld item hierarchy definition persistence.
	 *
	 * @return the ivld item hierarchy definition persistence
	 */
	public IvldItemHierarchyDefinitionPersistence getIvldItemHierarchyDefinitionPersistence() {
		return ivldItemHierarchyDefinitionPersistence;
	}

	/**
	 * Sets the ivld item hierarchy definition persistence.
	 *
	 * @param ivldItemHierarchyDefinitionPersistence the ivld item hierarchy definition persistence
	 */
	public void setIvldItemHierarchyDefinitionPersistence(
		IvldItemHierarchyDefinitionPersistence ivldItemHierarchyDefinitionPersistence) {
		this.ivldItemHierarchyDefinitionPersistence = ivldItemHierarchyDefinitionPersistence;
	}

	/**
	 * Returns the ivld lot master local service.
	 *
	 * @return the ivld lot master local service
	 */
	public com.stpl.app.service.IvldLotMasterLocalService getIvldLotMasterLocalService() {
		return ivldLotMasterLocalService;
	}

	/**
	 * Sets the ivld lot master local service.
	 *
	 * @param ivldLotMasterLocalService the ivld lot master local service
	 */
	public void setIvldLotMasterLocalService(
		com.stpl.app.service.IvldLotMasterLocalService ivldLotMasterLocalService) {
		this.ivldLotMasterLocalService = ivldLotMasterLocalService;
	}

	/**
	 * Returns the ivld lot master persistence.
	 *
	 * @return the ivld lot master persistence
	 */
	public IvldLotMasterPersistence getIvldLotMasterPersistence() {
		return ivldLotMasterPersistence;
	}

	/**
	 * Sets the ivld lot master persistence.
	 *
	 * @param ivldLotMasterPersistence the ivld lot master persistence
	 */
	public void setIvldLotMasterPersistence(
		IvldLotMasterPersistence ivldLotMasterPersistence) {
		this.ivldLotMasterPersistence = ivldLotMasterPersistence;
	}

	/**
	 * Returns the ivld master data attribute local service.
	 *
	 * @return the ivld master data attribute local service
	 */
	public com.stpl.app.service.IvldMasterDataAttributeLocalService getIvldMasterDataAttributeLocalService() {
		return ivldMasterDataAttributeLocalService;
	}

	/**
	 * Sets the ivld master data attribute local service.
	 *
	 * @param ivldMasterDataAttributeLocalService the ivld master data attribute local service
	 */
	public void setIvldMasterDataAttributeLocalService(
		com.stpl.app.service.IvldMasterDataAttributeLocalService ivldMasterDataAttributeLocalService) {
		this.ivldMasterDataAttributeLocalService = ivldMasterDataAttributeLocalService;
	}

	/**
	 * Returns the ivld master data attribute persistence.
	 *
	 * @return the ivld master data attribute persistence
	 */
	public IvldMasterDataAttributePersistence getIvldMasterDataAttributePersistence() {
		return ivldMasterDataAttributePersistence;
	}

	/**
	 * Sets the ivld master data attribute persistence.
	 *
	 * @param ivldMasterDataAttributePersistence the ivld master data attribute persistence
	 */
	public void setIvldMasterDataAttributePersistence(
		IvldMasterDataAttributePersistence ivldMasterDataAttributePersistence) {
		this.ivldMasterDataAttributePersistence = ivldMasterDataAttributePersistence;
	}

	/**
	 * Returns the ivld returns local service.
	 *
	 * @return the ivld returns local service
	 */
	public com.stpl.app.service.IvldReturnsLocalService getIvldReturnsLocalService() {
		return ivldReturnsLocalService;
	}

	/**
	 * Sets the ivld returns local service.
	 *
	 * @param ivldReturnsLocalService the ivld returns local service
	 */
	public void setIvldReturnsLocalService(
		com.stpl.app.service.IvldReturnsLocalService ivldReturnsLocalService) {
		this.ivldReturnsLocalService = ivldReturnsLocalService;
	}

	/**
	 * Returns the ivld returns persistence.
	 *
	 * @return the ivld returns persistence
	 */
	public IvldReturnsPersistence getIvldReturnsPersistence() {
		return ivldReturnsPersistence;
	}

	/**
	 * Sets the ivld returns persistence.
	 *
	 * @param ivldReturnsPersistence the ivld returns persistence
	 */
	public void setIvldReturnsPersistence(
		IvldReturnsPersistence ivldReturnsPersistence) {
		this.ivldReturnsPersistence = ivldReturnsPersistence;
	}

	/**
	 * Returns the ivld sales master local service.
	 *
	 * @return the ivld sales master local service
	 */
	public com.stpl.app.service.IvldSalesMasterLocalService getIvldSalesMasterLocalService() {
		return ivldSalesMasterLocalService;
	}

	/**
	 * Sets the ivld sales master local service.
	 *
	 * @param ivldSalesMasterLocalService the ivld sales master local service
	 */
	public void setIvldSalesMasterLocalService(
		com.stpl.app.service.IvldSalesMasterLocalService ivldSalesMasterLocalService) {
		this.ivldSalesMasterLocalService = ivldSalesMasterLocalService;
	}

	/**
	 * Returns the ivld sales master persistence.
	 *
	 * @return the ivld sales master persistence
	 */
	public IvldSalesMasterPersistence getIvldSalesMasterPersistence() {
		return ivldSalesMasterPersistence;
	}

	/**
	 * Sets the ivld sales master persistence.
	 *
	 * @param ivldSalesMasterPersistence the ivld sales master persistence
	 */
	public void setIvldSalesMasterPersistence(
		IvldSalesMasterPersistence ivldSalesMasterPersistence) {
		this.ivldSalesMasterPersistence = ivldSalesMasterPersistence;
	}

	/**
	 * Returns the lot master local service.
	 *
	 * @return the lot master local service
	 */
	public com.stpl.app.service.LotMasterLocalService getLotMasterLocalService() {
		return lotMasterLocalService;
	}

	/**
	 * Sets the lot master local service.
	 *
	 * @param lotMasterLocalService the lot master local service
	 */
	public void setLotMasterLocalService(
		com.stpl.app.service.LotMasterLocalService lotMasterLocalService) {
		this.lotMasterLocalService = lotMasterLocalService;
	}

	/**
	 * Returns the lot master persistence.
	 *
	 * @return the lot master persistence
	 */
	public LotMasterPersistence getLotMasterPersistence() {
		return lotMasterPersistence;
	}

	/**
	 * Sets the lot master persistence.
	 *
	 * @param lotMasterPersistence the lot master persistence
	 */
	public void setLotMasterPersistence(
		LotMasterPersistence lotMasterPersistence) {
		this.lotMasterPersistence = lotMasterPersistence;
	}

	/**
	 * Returns the mail notification master local service.
	 *
	 * @return the mail notification master local service
	 */
	public com.stpl.app.service.MailNotificationMasterLocalService getMailNotificationMasterLocalService() {
		return mailNotificationMasterLocalService;
	}

	/**
	 * Sets the mail notification master local service.
	 *
	 * @param mailNotificationMasterLocalService the mail notification master local service
	 */
	public void setMailNotificationMasterLocalService(
		com.stpl.app.service.MailNotificationMasterLocalService mailNotificationMasterLocalService) {
		this.mailNotificationMasterLocalService = mailNotificationMasterLocalService;
	}

	/**
	 * Returns the mail notification master persistence.
	 *
	 * @return the mail notification master persistence
	 */
	public MailNotificationMasterPersistence getMailNotificationMasterPersistence() {
		return mailNotificationMasterPersistence;
	}

	/**
	 * Sets the mail notification master persistence.
	 *
	 * @param mailNotificationMasterPersistence the mail notification master persistence
	 */
	public void setMailNotificationMasterPersistence(
		MailNotificationMasterPersistence mailNotificationMasterPersistence) {
		this.mailNotificationMasterPersistence = mailNotificationMasterPersistence;
	}

	/**
	 * Returns the m assumptions local service.
	 *
	 * @return the m assumptions local service
	 */
	public com.stpl.app.service.MAssumptionsLocalService getMAssumptionsLocalService() {
		return mAssumptionsLocalService;
	}

	/**
	 * Sets the m assumptions local service.
	 *
	 * @param mAssumptionsLocalService the m assumptions local service
	 */
	public void setMAssumptionsLocalService(
		com.stpl.app.service.MAssumptionsLocalService mAssumptionsLocalService) {
		this.mAssumptionsLocalService = mAssumptionsLocalService;
	}

	/**
	 * Returns the m assumptions persistence.
	 *
	 * @return the m assumptions persistence
	 */
	public MAssumptionsPersistence getMAssumptionsPersistence() {
		return mAssumptionsPersistence;
	}

	/**
	 * Sets the m assumptions persistence.
	 *
	 * @param mAssumptionsPersistence the m assumptions persistence
	 */
	public void setMAssumptionsPersistence(
		MAssumptionsPersistence mAssumptionsPersistence) {
		this.mAssumptionsPersistence = mAssumptionsPersistence;
	}

	/**
	 * Returns the master data attribute local service.
	 *
	 * @return the master data attribute local service
	 */
	public com.stpl.app.service.MasterDataAttributeLocalService getMasterDataAttributeLocalService() {
		return masterDataAttributeLocalService;
	}

	/**
	 * Sets the master data attribute local service.
	 *
	 * @param masterDataAttributeLocalService the master data attribute local service
	 */
	public void setMasterDataAttributeLocalService(
		com.stpl.app.service.MasterDataAttributeLocalService masterDataAttributeLocalService) {
		this.masterDataAttributeLocalService = masterDataAttributeLocalService;
	}

	/**
	 * Returns the master data attribute persistence.
	 *
	 * @return the master data attribute persistence
	 */
	public MasterDataAttributePersistence getMasterDataAttributePersistence() {
		return masterDataAttributePersistence;
	}

	/**
	 * Sets the master data attribute persistence.
	 *
	 * @param masterDataAttributePersistence the master data attribute persistence
	 */
	public void setMasterDataAttributePersistence(
		MasterDataAttributePersistence masterDataAttributePersistence) {
		this.masterDataAttributePersistence = masterDataAttributePersistence;
	}

	/**
	 * Returns the master data files local service.
	 *
	 * @return the master data files local service
	 */
	public com.stpl.app.service.MasterDataFilesLocalService getMasterDataFilesLocalService() {
		return masterDataFilesLocalService;
	}

	/**
	 * Sets the master data files local service.
	 *
	 * @param masterDataFilesLocalService the master data files local service
	 */
	public void setMasterDataFilesLocalService(
		com.stpl.app.service.MasterDataFilesLocalService masterDataFilesLocalService) {
		this.masterDataFilesLocalService = masterDataFilesLocalService;
	}

	/**
	 * Returns the master data files persistence.
	 *
	 * @return the master data files persistence
	 */
	public MasterDataFilesPersistence getMasterDataFilesPersistence() {
		return masterDataFilesPersistence;
	}

	/**
	 * Sets the master data files persistence.
	 *
	 * @param masterDataFilesPersistence the master data files persistence
	 */
	public void setMasterDataFilesPersistence(
		MasterDataFilesPersistence masterDataFilesPersistence) {
		this.masterDataFilesPersistence = masterDataFilesPersistence;
	}

	/**
	 * Returns the medicaid new ndc local service.
	 *
	 * @return the medicaid new ndc local service
	 */
	public com.stpl.app.service.MedicaidNewNdcLocalService getMedicaidNewNdcLocalService() {
		return medicaidNewNdcLocalService;
	}

	/**
	 * Sets the medicaid new ndc local service.
	 *
	 * @param medicaidNewNdcLocalService the medicaid new ndc local service
	 */
	public void setMedicaidNewNdcLocalService(
		com.stpl.app.service.MedicaidNewNdcLocalService medicaidNewNdcLocalService) {
		this.medicaidNewNdcLocalService = medicaidNewNdcLocalService;
	}

	/**
	 * Returns the medicaid new ndc persistence.
	 *
	 * @return the medicaid new ndc persistence
	 */
	public MedicaidNewNdcPersistence getMedicaidNewNdcPersistence() {
		return medicaidNewNdcPersistence;
	}

	/**
	 * Sets the medicaid new ndc persistence.
	 *
	 * @param medicaidNewNdcPersistence the medicaid new ndc persistence
	 */
	public void setMedicaidNewNdcPersistence(
		MedicaidNewNdcPersistence medicaidNewNdcPersistence) {
		this.medicaidNewNdcPersistence = medicaidNewNdcPersistence;
	}

	/**
	 * Returns the medicaid ura actuals local service.
	 *
	 * @return the medicaid ura actuals local service
	 */
	public com.stpl.app.service.MedicaidUraActualsLocalService getMedicaidUraActualsLocalService() {
		return medicaidUraActualsLocalService;
	}

	/**
	 * Sets the medicaid ura actuals local service.
	 *
	 * @param medicaidUraActualsLocalService the medicaid ura actuals local service
	 */
	public void setMedicaidUraActualsLocalService(
		com.stpl.app.service.MedicaidUraActualsLocalService medicaidUraActualsLocalService) {
		this.medicaidUraActualsLocalService = medicaidUraActualsLocalService;
	}

	/**
	 * Returns the medicaid ura actuals persistence.
	 *
	 * @return the medicaid ura actuals persistence
	 */
	public MedicaidUraActualsPersistence getMedicaidUraActualsPersistence() {
		return medicaidUraActualsPersistence;
	}

	/**
	 * Sets the medicaid ura actuals persistence.
	 *
	 * @param medicaidUraActualsPersistence the medicaid ura actuals persistence
	 */
	public void setMedicaidUraActualsPersistence(
		MedicaidUraActualsPersistence medicaidUraActualsPersistence) {
		this.medicaidUraActualsPersistence = medicaidUraActualsPersistence;
	}

	/**
	 * Returns the medicaid ura proj local service.
	 *
	 * @return the medicaid ura proj local service
	 */
	public com.stpl.app.service.MedicaidUraProjLocalService getMedicaidUraProjLocalService() {
		return medicaidUraProjLocalService;
	}

	/**
	 * Sets the medicaid ura proj local service.
	 *
	 * @param medicaidUraProjLocalService the medicaid ura proj local service
	 */
	public void setMedicaidUraProjLocalService(
		com.stpl.app.service.MedicaidUraProjLocalService medicaidUraProjLocalService) {
		this.medicaidUraProjLocalService = medicaidUraProjLocalService;
	}

	/**
	 * Returns the medicaid ura proj persistence.
	 *
	 * @return the medicaid ura proj persistence
	 */
	public MedicaidUraProjPersistence getMedicaidUraProjPersistence() {
		return medicaidUraProjPersistence;
	}

	/**
	 * Sets the medicaid ura proj persistence.
	 *
	 * @param medicaidUraProjPersistence the medicaid ura proj persistence
	 */
	public void setMedicaidUraProjPersistence(
		MedicaidUraProjPersistence medicaidUraProjPersistence) {
		this.medicaidUraProjPersistence = medicaidUraProjPersistence;
	}

	/**
	 * Returns the module properties local service.
	 *
	 * @return the module properties local service
	 */
	public com.stpl.app.service.ModulePropertiesLocalService getModulePropertiesLocalService() {
		return modulePropertiesLocalService;
	}

	/**
	 * Sets the module properties local service.
	 *
	 * @param modulePropertiesLocalService the module properties local service
	 */
	public void setModulePropertiesLocalService(
		com.stpl.app.service.ModulePropertiesLocalService modulePropertiesLocalService) {
		this.modulePropertiesLocalService = modulePropertiesLocalService;
	}

	/**
	 * Returns the module properties persistence.
	 *
	 * @return the module properties persistence
	 */
	public ModulePropertiesPersistence getModulePropertiesPersistence() {
		return modulePropertiesPersistence;
	}

	/**
	 * Sets the module properties persistence.
	 *
	 * @param modulePropertiesPersistence the module properties persistence
	 */
	public void setModulePropertiesPersistence(
		ModulePropertiesPersistence modulePropertiesPersistence) {
		this.modulePropertiesPersistence = modulePropertiesPersistence;
	}

	/**
	 * Returns the module submodule master local service.
	 *
	 * @return the module submodule master local service
	 */
	public com.stpl.app.service.ModuleSubmoduleMasterLocalService getModuleSubmoduleMasterLocalService() {
		return moduleSubmoduleMasterLocalService;
	}

	/**
	 * Sets the module submodule master local service.
	 *
	 * @param moduleSubmoduleMasterLocalService the module submodule master local service
	 */
	public void setModuleSubmoduleMasterLocalService(
		com.stpl.app.service.ModuleSubmoduleMasterLocalService moduleSubmoduleMasterLocalService) {
		this.moduleSubmoduleMasterLocalService = moduleSubmoduleMasterLocalService;
	}

	/**
	 * Returns the module submodule master persistence.
	 *
	 * @return the module submodule master persistence
	 */
	public ModuleSubmoduleMasterPersistence getModuleSubmoduleMasterPersistence() {
		return moduleSubmoduleMasterPersistence;
	}

	/**
	 * Sets the module submodule master persistence.
	 *
	 * @param moduleSubmoduleMasterPersistence the module submodule master persistence
	 */
	public void setModuleSubmoduleMasterPersistence(
		ModuleSubmoduleMasterPersistence moduleSubmoduleMasterPersistence) {
		this.moduleSubmoduleMasterPersistence = moduleSubmoduleMasterPersistence;
	}

	/**
	 * Returns the m parity lookup local service.
	 *
	 * @return the m parity lookup local service
	 */
	public com.stpl.app.service.MParityLookupLocalService getMParityLookupLocalService() {
		return mParityLookupLocalService;
	}

	/**
	 * Sets the m parity lookup local service.
	 *
	 * @param mParityLookupLocalService the m parity lookup local service
	 */
	public void setMParityLookupLocalService(
		com.stpl.app.service.MParityLookupLocalService mParityLookupLocalService) {
		this.mParityLookupLocalService = mParityLookupLocalService;
	}

	/**
	 * Returns the m parity lookup persistence.
	 *
	 * @return the m parity lookup persistence
	 */
	public MParityLookupPersistence getMParityLookupPersistence() {
		return mParityLookupPersistence;
	}

	/**
	 * Sets the m parity lookup persistence.
	 *
	 * @param mParityLookupPersistence the m parity lookup persistence
	 */
	public void setMParityLookupPersistence(
		MParityLookupPersistence mParityLookupPersistence) {
		this.mParityLookupPersistence = mParityLookupPersistence;
	}

	/**
	 * Returns the m projection selection local service.
	 *
	 * @return the m projection selection local service
	 */
	public com.stpl.app.service.MProjectionSelectionLocalService getMProjectionSelectionLocalService() {
		return mProjectionSelectionLocalService;
	}

	/**
	 * Sets the m projection selection local service.
	 *
	 * @param mProjectionSelectionLocalService the m projection selection local service
	 */
	public void setMProjectionSelectionLocalService(
		com.stpl.app.service.MProjectionSelectionLocalService mProjectionSelectionLocalService) {
		this.mProjectionSelectionLocalService = mProjectionSelectionLocalService;
	}

	/**
	 * Returns the m projection selection persistence.
	 *
	 * @return the m projection selection persistence
	 */
	public MProjectionSelectionPersistence getMProjectionSelectionPersistence() {
		return mProjectionSelectionPersistence;
	}

	/**
	 * Sets the m projection selection persistence.
	 *
	 * @param mProjectionSelectionPersistence the m projection selection persistence
	 */
	public void setMProjectionSelectionPersistence(
		MProjectionSelectionPersistence mProjectionSelectionPersistence) {
		this.mProjectionSelectionPersistence = mProjectionSelectionPersistence;
	}

	/**
	 * Returns the m sales projection master local service.
	 *
	 * @return the m sales projection master local service
	 */
	public com.stpl.app.service.MSalesProjectionMasterLocalService getMSalesProjectionMasterLocalService() {
		return mSalesProjectionMasterLocalService;
	}

	/**
	 * Sets the m sales projection master local service.
	 *
	 * @param mSalesProjectionMasterLocalService the m sales projection master local service
	 */
	public void setMSalesProjectionMasterLocalService(
		com.stpl.app.service.MSalesProjectionMasterLocalService mSalesProjectionMasterLocalService) {
		this.mSalesProjectionMasterLocalService = mSalesProjectionMasterLocalService;
	}

	/**
	 * Returns the m sales projection master persistence.
	 *
	 * @return the m sales projection master persistence
	 */
	public MSalesProjectionMasterPersistence getMSalesProjectionMasterPersistence() {
		return mSalesProjectionMasterPersistence;
	}

	/**
	 * Sets the m sales projection master persistence.
	 *
	 * @param mSalesProjectionMasterPersistence the m sales projection master persistence
	 */
	public void setMSalesProjectionMasterPersistence(
		MSalesProjectionMasterPersistence mSalesProjectionMasterPersistence) {
		this.mSalesProjectionMasterPersistence = mSalesProjectionMasterPersistence;
	}

	/**
	 * Returns the m supplemental disc actuals local service.
	 *
	 * @return the m supplemental disc actuals local service
	 */
	public com.stpl.app.service.MSupplementalDiscActualsLocalService getMSupplementalDiscActualsLocalService() {
		return mSupplementalDiscActualsLocalService;
	}

	/**
	 * Sets the m supplemental disc actuals local service.
	 *
	 * @param mSupplementalDiscActualsLocalService the m supplemental disc actuals local service
	 */
	public void setMSupplementalDiscActualsLocalService(
		com.stpl.app.service.MSupplementalDiscActualsLocalService mSupplementalDiscActualsLocalService) {
		this.mSupplementalDiscActualsLocalService = mSupplementalDiscActualsLocalService;
	}

	/**
	 * Returns the m supplemental disc actuals persistence.
	 *
	 * @return the m supplemental disc actuals persistence
	 */
	public MSupplementalDiscActualsPersistence getMSupplementalDiscActualsPersistence() {
		return mSupplementalDiscActualsPersistence;
	}

	/**
	 * Sets the m supplemental disc actuals persistence.
	 *
	 * @param mSupplementalDiscActualsPersistence the m supplemental disc actuals persistence
	 */
	public void setMSupplementalDiscActualsPersistence(
		MSupplementalDiscActualsPersistence mSupplementalDiscActualsPersistence) {
		this.mSupplementalDiscActualsPersistence = mSupplementalDiscActualsPersistence;
	}

	/**
	 * Returns the m supplemental disc master local service.
	 *
	 * @return the m supplemental disc master local service
	 */
	public com.stpl.app.service.MSupplementalDiscMasterLocalService getMSupplementalDiscMasterLocalService() {
		return mSupplementalDiscMasterLocalService;
	}

	/**
	 * Sets the m supplemental disc master local service.
	 *
	 * @param mSupplementalDiscMasterLocalService the m supplemental disc master local service
	 */
	public void setMSupplementalDiscMasterLocalService(
		com.stpl.app.service.MSupplementalDiscMasterLocalService mSupplementalDiscMasterLocalService) {
		this.mSupplementalDiscMasterLocalService = mSupplementalDiscMasterLocalService;
	}

	/**
	 * Returns the m supplemental disc master persistence.
	 *
	 * @return the m supplemental disc master persistence
	 */
	public MSupplementalDiscMasterPersistence getMSupplementalDiscMasterPersistence() {
		return mSupplementalDiscMasterPersistence;
	}

	/**
	 * Sets the m supplemental disc master persistence.
	 *
	 * @param mSupplementalDiscMasterPersistence the m supplemental disc master persistence
	 */
	public void setMSupplementalDiscMasterPersistence(
		MSupplementalDiscMasterPersistence mSupplementalDiscMasterPersistence) {
		this.mSupplementalDiscMasterPersistence = mSupplementalDiscMasterPersistence;
	}

	/**
	 * Returns the m supplemental disc proj local service.
	 *
	 * @return the m supplemental disc proj local service
	 */
	public com.stpl.app.service.MSupplementalDiscProjLocalService getMSupplementalDiscProjLocalService() {
		return mSupplementalDiscProjLocalService;
	}

	/**
	 * Sets the m supplemental disc proj local service.
	 *
	 * @param mSupplementalDiscProjLocalService the m supplemental disc proj local service
	 */
	public void setMSupplementalDiscProjLocalService(
		com.stpl.app.service.MSupplementalDiscProjLocalService mSupplementalDiscProjLocalService) {
		this.mSupplementalDiscProjLocalService = mSupplementalDiscProjLocalService;
	}

	/**
	 * Returns the m supplemental disc proj persistence.
	 *
	 * @return the m supplemental disc proj persistence
	 */
	public MSupplementalDiscProjPersistence getMSupplementalDiscProjPersistence() {
		return mSupplementalDiscProjPersistence;
	}

	/**
	 * Sets the m supplemental disc proj persistence.
	 *
	 * @param mSupplementalDiscProjPersistence the m supplemental disc proj persistence
	 */
	public void setMSupplementalDiscProjPersistence(
		MSupplementalDiscProjPersistence mSupplementalDiscProjPersistence) {
		this.mSupplementalDiscProjPersistence = mSupplementalDiscProjPersistence;
	}

	/**
	 * Returns the na proj details local service.
	 *
	 * @return the na proj details local service
	 */
	public com.stpl.app.service.NaProjDetailsLocalService getNaProjDetailsLocalService() {
		return naProjDetailsLocalService;
	}

	/**
	 * Sets the na proj details local service.
	 *
	 * @param naProjDetailsLocalService the na proj details local service
	 */
	public void setNaProjDetailsLocalService(
		com.stpl.app.service.NaProjDetailsLocalService naProjDetailsLocalService) {
		this.naProjDetailsLocalService = naProjDetailsLocalService;
	}

	/**
	 * Returns the na proj details persistence.
	 *
	 * @return the na proj details persistence
	 */
	public NaProjDetailsPersistence getNaProjDetailsPersistence() {
		return naProjDetailsPersistence;
	}

	/**
	 * Sets the na proj details persistence.
	 *
	 * @param naProjDetailsPersistence the na proj details persistence
	 */
	public void setNaProjDetailsPersistence(
		NaProjDetailsPersistence naProjDetailsPersistence) {
		this.naProjDetailsPersistence = naProjDetailsPersistence;
	}

	/**
	 * Returns the na projection selection local service.
	 *
	 * @return the na projection selection local service
	 */
	public com.stpl.app.service.NaProjectionSelectionLocalService getNaProjectionSelectionLocalService() {
		return naProjectionSelectionLocalService;
	}

	/**
	 * Sets the na projection selection local service.
	 *
	 * @param naProjectionSelectionLocalService the na projection selection local service
	 */
	public void setNaProjectionSelectionLocalService(
		com.stpl.app.service.NaProjectionSelectionLocalService naProjectionSelectionLocalService) {
		this.naProjectionSelectionLocalService = naProjectionSelectionLocalService;
	}

	/**
	 * Returns the na projection selection persistence.
	 *
	 * @return the na projection selection persistence
	 */
	public NaProjectionSelectionPersistence getNaProjectionSelectionPersistence() {
		return naProjectionSelectionPersistence;
	}

	/**
	 * Sets the na projection selection persistence.
	 *
	 * @param naProjectionSelectionPersistence the na projection selection persistence
	 */
	public void setNaProjectionSelectionPersistence(
		NaProjectionSelectionPersistence naProjectionSelectionPersistence) {
		this.naProjectionSelectionPersistence = naProjectionSelectionPersistence;
	}

	/**
	 * Returns the na proj master local service.
	 *
	 * @return the na proj master local service
	 */
	public com.stpl.app.service.NaProjMasterLocalService getNaProjMasterLocalService() {
		return naProjMasterLocalService;
	}

	/**
	 * Sets the na proj master local service.
	 *
	 * @param naProjMasterLocalService the na proj master local service
	 */
	public void setNaProjMasterLocalService(
		com.stpl.app.service.NaProjMasterLocalService naProjMasterLocalService) {
		this.naProjMasterLocalService = naProjMasterLocalService;
	}

	/**
	 * Returns the na proj master persistence.
	 *
	 * @return the na proj master persistence
	 */
	public NaProjMasterPersistence getNaProjMasterPersistence() {
		return naProjMasterPersistence;
	}

	/**
	 * Sets the na proj master persistence.
	 *
	 * @param naProjMasterPersistence the na proj master persistence
	 */
	public void setNaProjMasterPersistence(
		NaProjMasterPersistence naProjMasterPersistence) {
		this.naProjMasterPersistence = naProjMasterPersistence;
	}

	/**
	 * Returns the national assumptions local service.
	 *
	 * @return the national assumptions local service
	 */
	public com.stpl.app.service.NationalAssumptionsLocalService getNationalAssumptionsLocalService() {
		return nationalAssumptionsLocalService;
	}

	/**
	 * Sets the national assumptions local service.
	 *
	 * @param nationalAssumptionsLocalService the national assumptions local service
	 */
	public void setNationalAssumptionsLocalService(
		com.stpl.app.service.NationalAssumptionsLocalService nationalAssumptionsLocalService) {
		this.nationalAssumptionsLocalService = nationalAssumptionsLocalService;
	}

	/**
	 * Returns the national assumptions persistence.
	 *
	 * @return the national assumptions persistence
	 */
	public NationalAssumptionsPersistence getNationalAssumptionsPersistence() {
		return nationalAssumptionsPersistence;
	}

	/**
	 * Sets the national assumptions persistence.
	 *
	 * @param nationalAssumptionsPersistence the national assumptions persistence
	 */
	public void setNationalAssumptionsPersistence(
		NationalAssumptionsPersistence nationalAssumptionsPersistence) {
		this.nationalAssumptionsPersistence = nationalAssumptionsPersistence;
	}

	/**
	 * Returns the national assumptions actuals local service.
	 *
	 * @return the national assumptions actuals local service
	 */
	public com.stpl.app.service.NationalAssumptionsActualsLocalService getNationalAssumptionsActualsLocalService() {
		return nationalAssumptionsActualsLocalService;
	}

	/**
	 * Sets the national assumptions actuals local service.
	 *
	 * @param nationalAssumptionsActualsLocalService the national assumptions actuals local service
	 */
	public void setNationalAssumptionsActualsLocalService(
		com.stpl.app.service.NationalAssumptionsActualsLocalService nationalAssumptionsActualsLocalService) {
		this.nationalAssumptionsActualsLocalService = nationalAssumptionsActualsLocalService;
	}

	/**
	 * Returns the national assumptions actuals persistence.
	 *
	 * @return the national assumptions actuals persistence
	 */
	public NationalAssumptionsActualsPersistence getNationalAssumptionsActualsPersistence() {
		return nationalAssumptionsActualsPersistence;
	}

	/**
	 * Sets the national assumptions actuals persistence.
	 *
	 * @param nationalAssumptionsActualsPersistence the national assumptions actuals persistence
	 */
	public void setNationalAssumptionsActualsPersistence(
		NationalAssumptionsActualsPersistence nationalAssumptionsActualsPersistence) {
		this.nationalAssumptionsActualsPersistence = nationalAssumptionsActualsPersistence;
	}

	/**
	 * Returns the national assumptions proj local service.
	 *
	 * @return the national assumptions proj local service
	 */
	public com.stpl.app.service.NationalAssumptionsProjLocalService getNationalAssumptionsProjLocalService() {
		return nationalAssumptionsProjLocalService;
	}

	/**
	 * Sets the national assumptions proj local service.
	 *
	 * @param nationalAssumptionsProjLocalService the national assumptions proj local service
	 */
	public void setNationalAssumptionsProjLocalService(
		com.stpl.app.service.NationalAssumptionsProjLocalService nationalAssumptionsProjLocalService) {
		this.nationalAssumptionsProjLocalService = nationalAssumptionsProjLocalService;
	}

	/**
	 * Returns the national assumptions proj persistence.
	 *
	 * @return the national assumptions proj persistence
	 */
	public NationalAssumptionsProjPersistence getNationalAssumptionsProjPersistence() {
		return nationalAssumptionsProjPersistence;
	}

	/**
	 * Sets the national assumptions proj persistence.
	 *
	 * @param nationalAssumptionsProjPersistence the national assumptions proj persistence
	 */
	public void setNationalAssumptionsProjPersistence(
		NationalAssumptionsProjPersistence nationalAssumptionsProjPersistence) {
		this.nationalAssumptionsProjPersistence = nationalAssumptionsProjPersistence;
	}

	/**
	 * Returns the net sales formula master local service.
	 *
	 * @return the net sales formula master local service
	 */
	public com.stpl.app.service.NetSalesFormulaMasterLocalService getNetSalesFormulaMasterLocalService() {
		return netSalesFormulaMasterLocalService;
	}

	/**
	 * Sets the net sales formula master local service.
	 *
	 * @param netSalesFormulaMasterLocalService the net sales formula master local service
	 */
	public void setNetSalesFormulaMasterLocalService(
		com.stpl.app.service.NetSalesFormulaMasterLocalService netSalesFormulaMasterLocalService) {
		this.netSalesFormulaMasterLocalService = netSalesFormulaMasterLocalService;
	}

	/**
	 * Returns the net sales formula master persistence.
	 *
	 * @return the net sales formula master persistence
	 */
	public NetSalesFormulaMasterPersistence getNetSalesFormulaMasterPersistence() {
		return netSalesFormulaMasterPersistence;
	}

	/**
	 * Sets the net sales formula master persistence.
	 *
	 * @param netSalesFormulaMasterPersistence the net sales formula master persistence
	 */
	public void setNetSalesFormulaMasterPersistence(
		NetSalesFormulaMasterPersistence netSalesFormulaMasterPersistence) {
		this.netSalesFormulaMasterPersistence = netSalesFormulaMasterPersistence;
	}

	/**
	 * Returns the nm actual discount local service.
	 *
	 * @return the nm actual discount local service
	 */
	public com.stpl.app.service.NmActualDiscountLocalService getNmActualDiscountLocalService() {
		return nmActualDiscountLocalService;
	}

	/**
	 * Sets the nm actual discount local service.
	 *
	 * @param nmActualDiscountLocalService the nm actual discount local service
	 */
	public void setNmActualDiscountLocalService(
		com.stpl.app.service.NmActualDiscountLocalService nmActualDiscountLocalService) {
		this.nmActualDiscountLocalService = nmActualDiscountLocalService;
	}

	/**
	 * Returns the nm actual discount persistence.
	 *
	 * @return the nm actual discount persistence
	 */
	public NmActualDiscountPersistence getNmActualDiscountPersistence() {
		return nmActualDiscountPersistence;
	}

	/**
	 * Sets the nm actual discount persistence.
	 *
	 * @param nmActualDiscountPersistence the nm actual discount persistence
	 */
	public void setNmActualDiscountPersistence(
		NmActualDiscountPersistence nmActualDiscountPersistence) {
		this.nmActualDiscountPersistence = nmActualDiscountPersistence;
	}

	/**
	 * Returns the nm actual ppa local service.
	 *
	 * @return the nm actual ppa local service
	 */
	public com.stpl.app.service.NmActualPpaLocalService getNmActualPpaLocalService() {
		return nmActualPpaLocalService;
	}

	/**
	 * Sets the nm actual ppa local service.
	 *
	 * @param nmActualPpaLocalService the nm actual ppa local service
	 */
	public void setNmActualPpaLocalService(
		com.stpl.app.service.NmActualPpaLocalService nmActualPpaLocalService) {
		this.nmActualPpaLocalService = nmActualPpaLocalService;
	}

	/**
	 * Returns the nm actual ppa persistence.
	 *
	 * @return the nm actual ppa persistence
	 */
	public NmActualPpaPersistence getNmActualPpaPersistence() {
		return nmActualPpaPersistence;
	}

	/**
	 * Sets the nm actual ppa persistence.
	 *
	 * @param nmActualPpaPersistence the nm actual ppa persistence
	 */
	public void setNmActualPpaPersistence(
		NmActualPpaPersistence nmActualPpaPersistence) {
		this.nmActualPpaPersistence = nmActualPpaPersistence;
	}

	/**
	 * Returns the nm discount projection local service.
	 *
	 * @return the nm discount projection local service
	 */
	public com.stpl.app.service.NmDiscountProjectionLocalService getNmDiscountProjectionLocalService() {
		return nmDiscountProjectionLocalService;
	}

	/**
	 * Sets the nm discount projection local service.
	 *
	 * @param nmDiscountProjectionLocalService the nm discount projection local service
	 */
	public void setNmDiscountProjectionLocalService(
		com.stpl.app.service.NmDiscountProjectionLocalService nmDiscountProjectionLocalService) {
		this.nmDiscountProjectionLocalService = nmDiscountProjectionLocalService;
	}

	/**
	 * Returns the nm discount projection persistence.
	 *
	 * @return the nm discount projection persistence
	 */
	public NmDiscountProjectionPersistence getNmDiscountProjectionPersistence() {
		return nmDiscountProjectionPersistence;
	}

	/**
	 * Sets the nm discount projection persistence.
	 *
	 * @param nmDiscountProjectionPersistence the nm discount projection persistence
	 */
	public void setNmDiscountProjectionPersistence(
		NmDiscountProjectionPersistence nmDiscountProjectionPersistence) {
		this.nmDiscountProjectionPersistence = nmDiscountProjectionPersistence;
	}

	/**
	 * Returns the nm discount proj master local service.
	 *
	 * @return the nm discount proj master local service
	 */
	public com.stpl.app.service.NmDiscountProjMasterLocalService getNmDiscountProjMasterLocalService() {
		return nmDiscountProjMasterLocalService;
	}

	/**
	 * Sets the nm discount proj master local service.
	 *
	 * @param nmDiscountProjMasterLocalService the nm discount proj master local service
	 */
	public void setNmDiscountProjMasterLocalService(
		com.stpl.app.service.NmDiscountProjMasterLocalService nmDiscountProjMasterLocalService) {
		this.nmDiscountProjMasterLocalService = nmDiscountProjMasterLocalService;
	}

	/**
	 * Returns the nm discount proj master persistence.
	 *
	 * @return the nm discount proj master persistence
	 */
	public NmDiscountProjMasterPersistence getNmDiscountProjMasterPersistence() {
		return nmDiscountProjMasterPersistence;
	}

	/**
	 * Sets the nm discount proj master persistence.
	 *
	 * @param nmDiscountProjMasterPersistence the nm discount proj master persistence
	 */
	public void setNmDiscountProjMasterPersistence(
		NmDiscountProjMasterPersistence nmDiscountProjMasterPersistence) {
		this.nmDiscountProjMasterPersistence = nmDiscountProjMasterPersistence;
	}

	/**
	 * Returns the nm ppa projection local service.
	 *
	 * @return the nm ppa projection local service
	 */
	public com.stpl.app.service.NmPpaProjectionLocalService getNmPpaProjectionLocalService() {
		return nmPpaProjectionLocalService;
	}

	/**
	 * Sets the nm ppa projection local service.
	 *
	 * @param nmPpaProjectionLocalService the nm ppa projection local service
	 */
	public void setNmPpaProjectionLocalService(
		com.stpl.app.service.NmPpaProjectionLocalService nmPpaProjectionLocalService) {
		this.nmPpaProjectionLocalService = nmPpaProjectionLocalService;
	}

	/**
	 * Returns the nm ppa projection persistence.
	 *
	 * @return the nm ppa projection persistence
	 */
	public NmPpaProjectionPersistence getNmPpaProjectionPersistence() {
		return nmPpaProjectionPersistence;
	}

	/**
	 * Sets the nm ppa projection persistence.
	 *
	 * @param nmPpaProjectionPersistence the nm ppa projection persistence
	 */
	public void setNmPpaProjectionPersistence(
		NmPpaProjectionPersistence nmPpaProjectionPersistence) {
		this.nmPpaProjectionPersistence = nmPpaProjectionPersistence;
	}

	/**
	 * Returns the nm ppa projection master local service.
	 *
	 * @return the nm ppa projection master local service
	 */
	public com.stpl.app.service.NmPpaProjectionMasterLocalService getNmPpaProjectionMasterLocalService() {
		return nmPpaProjectionMasterLocalService;
	}

	/**
	 * Sets the nm ppa projection master local service.
	 *
	 * @param nmPpaProjectionMasterLocalService the nm ppa projection master local service
	 */
	public void setNmPpaProjectionMasterLocalService(
		com.stpl.app.service.NmPpaProjectionMasterLocalService nmPpaProjectionMasterLocalService) {
		this.nmPpaProjectionMasterLocalService = nmPpaProjectionMasterLocalService;
	}

	/**
	 * Returns the nm ppa projection master persistence.
	 *
	 * @return the nm ppa projection master persistence
	 */
	public NmPpaProjectionMasterPersistence getNmPpaProjectionMasterPersistence() {
		return nmPpaProjectionMasterPersistence;
	}

	/**
	 * Sets the nm ppa projection master persistence.
	 *
	 * @param nmPpaProjectionMasterPersistence the nm ppa projection master persistence
	 */
	public void setNmPpaProjectionMasterPersistence(
		NmPpaProjectionMasterPersistence nmPpaProjectionMasterPersistence) {
		this.nmPpaProjectionMasterPersistence = nmPpaProjectionMasterPersistence;
	}

	/**
	 * Returns the nm projection selection local service.
	 *
	 * @return the nm projection selection local service
	 */
	public com.stpl.app.service.NmProjectionSelectionLocalService getNmProjectionSelectionLocalService() {
		return nmProjectionSelectionLocalService;
	}

	/**
	 * Sets the nm projection selection local service.
	 *
	 * @param nmProjectionSelectionLocalService the nm projection selection local service
	 */
	public void setNmProjectionSelectionLocalService(
		com.stpl.app.service.NmProjectionSelectionLocalService nmProjectionSelectionLocalService) {
		this.nmProjectionSelectionLocalService = nmProjectionSelectionLocalService;
	}

	/**
	 * Returns the nm projection selection persistence.
	 *
	 * @return the nm projection selection persistence
	 */
	public NmProjectionSelectionPersistence getNmProjectionSelectionPersistence() {
		return nmProjectionSelectionPersistence;
	}

	/**
	 * Sets the nm projection selection persistence.
	 *
	 * @param nmProjectionSelectionPersistence the nm projection selection persistence
	 */
	public void setNmProjectionSelectionPersistence(
		NmProjectionSelectionPersistence nmProjectionSelectionPersistence) {
		this.nmProjectionSelectionPersistence = nmProjectionSelectionPersistence;
	}

	/**
	 * Returns the nm sales projection local service.
	 *
	 * @return the nm sales projection local service
	 */
	public com.stpl.app.service.NmSalesProjectionLocalService getNmSalesProjectionLocalService() {
		return nmSalesProjectionLocalService;
	}

	/**
	 * Sets the nm sales projection local service.
	 *
	 * @param nmSalesProjectionLocalService the nm sales projection local service
	 */
	public void setNmSalesProjectionLocalService(
		com.stpl.app.service.NmSalesProjectionLocalService nmSalesProjectionLocalService) {
		this.nmSalesProjectionLocalService = nmSalesProjectionLocalService;
	}

	/**
	 * Returns the nm sales projection persistence.
	 *
	 * @return the nm sales projection persistence
	 */
	public NmSalesProjectionPersistence getNmSalesProjectionPersistence() {
		return nmSalesProjectionPersistence;
	}

	/**
	 * Sets the nm sales projection persistence.
	 *
	 * @param nmSalesProjectionPersistence the nm sales projection persistence
	 */
	public void setNmSalesProjectionPersistence(
		NmSalesProjectionPersistence nmSalesProjectionPersistence) {
		this.nmSalesProjectionPersistence = nmSalesProjectionPersistence;
	}

	/**
	 * Returns the nm sales projection master local service.
	 *
	 * @return the nm sales projection master local service
	 */
	public com.stpl.app.service.NmSalesProjectionMasterLocalService getNmSalesProjectionMasterLocalService() {
		return nmSalesProjectionMasterLocalService;
	}

	/**
	 * Sets the nm sales projection master local service.
	 *
	 * @param nmSalesProjectionMasterLocalService the nm sales projection master local service
	 */
	public void setNmSalesProjectionMasterLocalService(
		com.stpl.app.service.NmSalesProjectionMasterLocalService nmSalesProjectionMasterLocalService) {
		this.nmSalesProjectionMasterLocalService = nmSalesProjectionMasterLocalService;
	}

	/**
	 * Returns the nm sales projection master persistence.
	 *
	 * @return the nm sales projection master persistence
	 */
	public NmSalesProjectionMasterPersistence getNmSalesProjectionMasterPersistence() {
		return nmSalesProjectionMasterPersistence;
	}

	/**
	 * Sets the nm sales projection master persistence.
	 *
	 * @param nmSalesProjectionMasterPersistence the nm sales projection master persistence
	 */
	public void setNmSalesProjectionMasterPersistence(
		NmSalesProjectionMasterPersistence nmSalesProjectionMasterPersistence) {
		this.nmSalesProjectionMasterPersistence = nmSalesProjectionMasterPersistence;
	}

	/**
	 * Returns the period local service.
	 *
	 * @return the period local service
	 */
	public com.stpl.app.service.PeriodLocalService getPeriodLocalService() {
		return periodLocalService;
	}

	/**
	 * Sets the period local service.
	 *
	 * @param periodLocalService the period local service
	 */
	public void setPeriodLocalService(
		com.stpl.app.service.PeriodLocalService periodLocalService) {
		this.periodLocalService = periodLocalService;
	}

	/**
	 * Returns the period persistence.
	 *
	 * @return the period persistence
	 */
	public PeriodPersistence getPeriodPersistence() {
		return periodPersistence;
	}

	/**
	 * Sets the period persistence.
	 *
	 * @param periodPersistence the period persistence
	 */
	public void setPeriodPersistence(PeriodPersistence periodPersistence) {
		this.periodPersistence = periodPersistence;
	}

	/**
	 * Returns the phs actuals local service.
	 *
	 * @return the phs actuals local service
	 */
	public com.stpl.app.service.PhsActualsLocalService getPhsActualsLocalService() {
		return phsActualsLocalService;
	}

	/**
	 * Sets the phs actuals local service.
	 *
	 * @param phsActualsLocalService the phs actuals local service
	 */
	public void setPhsActualsLocalService(
		com.stpl.app.service.PhsActualsLocalService phsActualsLocalService) {
		this.phsActualsLocalService = phsActualsLocalService;
	}

	/**
	 * Returns the phs actuals persistence.
	 *
	 * @return the phs actuals persistence
	 */
	public PhsActualsPersistence getPhsActualsPersistence() {
		return phsActualsPersistence;
	}

	/**
	 * Sets the phs actuals persistence.
	 *
	 * @param phsActualsPersistence the phs actuals persistence
	 */
	public void setPhsActualsPersistence(
		PhsActualsPersistence phsActualsPersistence) {
		this.phsActualsPersistence = phsActualsPersistence;
	}

	/**
	 * Returns the phs proj local service.
	 *
	 * @return the phs proj local service
	 */
	public com.stpl.app.service.PhsProjLocalService getPhsProjLocalService() {
		return phsProjLocalService;
	}

	/**
	 * Sets the phs proj local service.
	 *
	 * @param phsProjLocalService the phs proj local service
	 */
	public void setPhsProjLocalService(
		com.stpl.app.service.PhsProjLocalService phsProjLocalService) {
		this.phsProjLocalService = phsProjLocalService;
	}

	/**
	 * Returns the phs proj persistence.
	 *
	 * @return the phs proj persistence
	 */
	public PhsProjPersistence getPhsProjPersistence() {
		return phsProjPersistence;
	}

	/**
	 * Sets the phs proj persistence.
	 *
	 * @param phsProjPersistence the phs proj persistence
	 */
	public void setPhsProjPersistence(PhsProjPersistence phsProjPersistence) {
		this.phsProjPersistence = phsProjPersistence;
	}

	/**
	 * Returns the price schedule details local service.
	 *
	 * @return the price schedule details local service
	 */
	public com.stpl.app.service.PriceScheduleDetailsLocalService getPriceScheduleDetailsLocalService() {
		return priceScheduleDetailsLocalService;
	}

	/**
	 * Sets the price schedule details local service.
	 *
	 * @param priceScheduleDetailsLocalService the price schedule details local service
	 */
	public void setPriceScheduleDetailsLocalService(
		com.stpl.app.service.PriceScheduleDetailsLocalService priceScheduleDetailsLocalService) {
		this.priceScheduleDetailsLocalService = priceScheduleDetailsLocalService;
	}

	/**
	 * Returns the price schedule details persistence.
	 *
	 * @return the price schedule details persistence
	 */
	public PriceScheduleDetailsPersistence getPriceScheduleDetailsPersistence() {
		return priceScheduleDetailsPersistence;
	}

	/**
	 * Sets the price schedule details persistence.
	 *
	 * @param priceScheduleDetailsPersistence the price schedule details persistence
	 */
	public void setPriceScheduleDetailsPersistence(
		PriceScheduleDetailsPersistence priceScheduleDetailsPersistence) {
		this.priceScheduleDetailsPersistence = priceScheduleDetailsPersistence;
	}

	/**
	 * Returns the price schedule master local service.
	 *
	 * @return the price schedule master local service
	 */
	public com.stpl.app.service.PriceScheduleMasterLocalService getPriceScheduleMasterLocalService() {
		return priceScheduleMasterLocalService;
	}

	/**
	 * Sets the price schedule master local service.
	 *
	 * @param priceScheduleMasterLocalService the price schedule master local service
	 */
	public void setPriceScheduleMasterLocalService(
		com.stpl.app.service.PriceScheduleMasterLocalService priceScheduleMasterLocalService) {
		this.priceScheduleMasterLocalService = priceScheduleMasterLocalService;
	}

	/**
	 * Returns the price schedule master persistence.
	 *
	 * @return the price schedule master persistence
	 */
	public PriceScheduleMasterPersistence getPriceScheduleMasterPersistence() {
		return priceScheduleMasterPersistence;
	}

	/**
	 * Sets the price schedule master persistence.
	 *
	 * @param priceScheduleMasterPersistence the price schedule master persistence
	 */
	public void setPriceScheduleMasterPersistence(
		PriceScheduleMasterPersistence priceScheduleMasterPersistence) {
		this.priceScheduleMasterPersistence = priceScheduleMasterPersistence;
	}

	/**
	 * Returns the projection cust details local service.
	 *
	 * @return the projection cust details local service
	 */
	public com.stpl.app.service.ProjectionCustDetailsLocalService getProjectionCustDetailsLocalService() {
		return projectionCustDetailsLocalService;
	}

	/**
	 * Sets the projection cust details local service.
	 *
	 * @param projectionCustDetailsLocalService the projection cust details local service
	 */
	public void setProjectionCustDetailsLocalService(
		com.stpl.app.service.ProjectionCustDetailsLocalService projectionCustDetailsLocalService) {
		this.projectionCustDetailsLocalService = projectionCustDetailsLocalService;
	}

	/**
	 * Returns the projection cust details persistence.
	 *
	 * @return the projection cust details persistence
	 */
	public ProjectionCustDetailsPersistence getProjectionCustDetailsPersistence() {
		return projectionCustDetailsPersistence;
	}

	/**
	 * Sets the projection cust details persistence.
	 *
	 * @param projectionCustDetailsPersistence the projection cust details persistence
	 */
	public void setProjectionCustDetailsPersistence(
		ProjectionCustDetailsPersistence projectionCustDetailsPersistence) {
		this.projectionCustDetailsPersistence = projectionCustDetailsPersistence;
	}

	/**
	 * Returns the projection cust hierarchy local service.
	 *
	 * @return the projection cust hierarchy local service
	 */
	public com.stpl.app.service.ProjectionCustHierarchyLocalService getProjectionCustHierarchyLocalService() {
		return projectionCustHierarchyLocalService;
	}

	/**
	 * Sets the projection cust hierarchy local service.
	 *
	 * @param projectionCustHierarchyLocalService the projection cust hierarchy local service
	 */
	public void setProjectionCustHierarchyLocalService(
		com.stpl.app.service.ProjectionCustHierarchyLocalService projectionCustHierarchyLocalService) {
		this.projectionCustHierarchyLocalService = projectionCustHierarchyLocalService;
	}

	/**
	 * Returns the projection cust hierarchy persistence.
	 *
	 * @return the projection cust hierarchy persistence
	 */
	public ProjectionCustHierarchyPersistence getProjectionCustHierarchyPersistence() {
		return projectionCustHierarchyPersistence;
	}

	/**
	 * Sets the projection cust hierarchy persistence.
	 *
	 * @param projectionCustHierarchyPersistence the projection cust hierarchy persistence
	 */
	public void setProjectionCustHierarchyPersistence(
		ProjectionCustHierarchyPersistence projectionCustHierarchyPersistence) {
		this.projectionCustHierarchyPersistence = projectionCustHierarchyPersistence;
	}

	/**
	 * Returns the projection details local service.
	 *
	 * @return the projection details local service
	 */
	public com.stpl.app.service.ProjectionDetailsLocalService getProjectionDetailsLocalService() {
		return projectionDetailsLocalService;
	}

	/**
	 * Sets the projection details local service.
	 *
	 * @param projectionDetailsLocalService the projection details local service
	 */
	public void setProjectionDetailsLocalService(
		com.stpl.app.service.ProjectionDetailsLocalService projectionDetailsLocalService) {
		this.projectionDetailsLocalService = projectionDetailsLocalService;
	}

	/**
	 * Returns the projection details persistence.
	 *
	 * @return the projection details persistence
	 */
	public ProjectionDetailsPersistence getProjectionDetailsPersistence() {
		return projectionDetailsPersistence;
	}

	/**
	 * Sets the projection details persistence.
	 *
	 * @param projectionDetailsPersistence the projection details persistence
	 */
	public void setProjectionDetailsPersistence(
		ProjectionDetailsPersistence projectionDetailsPersistence) {
		this.projectionDetailsPersistence = projectionDetailsPersistence;
	}

	/**
	 * Returns the projection master local service.
	 *
	 * @return the projection master local service
	 */
	public com.stpl.app.service.ProjectionMasterLocalService getProjectionMasterLocalService() {
		return projectionMasterLocalService;
	}

	/**
	 * Sets the projection master local service.
	 *
	 * @param projectionMasterLocalService the projection master local service
	 */
	public void setProjectionMasterLocalService(
		com.stpl.app.service.ProjectionMasterLocalService projectionMasterLocalService) {
		this.projectionMasterLocalService = projectionMasterLocalService;
	}

	/**
	 * Returns the projection master persistence.
	 *
	 * @return the projection master persistence
	 */
	public ProjectionMasterPersistence getProjectionMasterPersistence() {
		return projectionMasterPersistence;
	}

	/**
	 * Sets the projection master persistence.
	 *
	 * @param projectionMasterPersistence the projection master persistence
	 */
	public void setProjectionMasterPersistence(
		ProjectionMasterPersistence projectionMasterPersistence) {
		this.projectionMasterPersistence = projectionMasterPersistence;
	}

	/**
	 * Returns the projection name config local service.
	 *
	 * @return the projection name config local service
	 */
	public com.stpl.app.service.ProjectionNameConfigLocalService getProjectionNameConfigLocalService() {
		return projectionNameConfigLocalService;
	}

	/**
	 * Sets the projection name config local service.
	 *
	 * @param projectionNameConfigLocalService the projection name config local service
	 */
	public void setProjectionNameConfigLocalService(
		com.stpl.app.service.ProjectionNameConfigLocalService projectionNameConfigLocalService) {
		this.projectionNameConfigLocalService = projectionNameConfigLocalService;
	}

	/**
	 * Returns the projection name config persistence.
	 *
	 * @return the projection name config persistence
	 */
	public ProjectionNameConfigPersistence getProjectionNameConfigPersistence() {
		return projectionNameConfigPersistence;
	}

	/**
	 * Sets the projection name config persistence.
	 *
	 * @param projectionNameConfigPersistence the projection name config persistence
	 */
	public void setProjectionNameConfigPersistence(
		ProjectionNameConfigPersistence projectionNameConfigPersistence) {
		this.projectionNameConfigPersistence = projectionNameConfigPersistence;
	}

	/**
	 * Returns the projection prod details local service.
	 *
	 * @return the projection prod details local service
	 */
	public com.stpl.app.service.ProjectionProdDetailsLocalService getProjectionProdDetailsLocalService() {
		return projectionProdDetailsLocalService;
	}

	/**
	 * Sets the projection prod details local service.
	 *
	 * @param projectionProdDetailsLocalService the projection prod details local service
	 */
	public void setProjectionProdDetailsLocalService(
		com.stpl.app.service.ProjectionProdDetailsLocalService projectionProdDetailsLocalService) {
		this.projectionProdDetailsLocalService = projectionProdDetailsLocalService;
	}

	/**
	 * Returns the projection prod details persistence.
	 *
	 * @return the projection prod details persistence
	 */
	public ProjectionProdDetailsPersistence getProjectionProdDetailsPersistence() {
		return projectionProdDetailsPersistence;
	}

	/**
	 * Sets the projection prod details persistence.
	 *
	 * @param projectionProdDetailsPersistence the projection prod details persistence
	 */
	public void setProjectionProdDetailsPersistence(
		ProjectionProdDetailsPersistence projectionProdDetailsPersistence) {
		this.projectionProdDetailsPersistence = projectionProdDetailsPersistence;
	}

	/**
	 * Returns the projection prod hierarchy local service.
	 *
	 * @return the projection prod hierarchy local service
	 */
	public com.stpl.app.service.ProjectionProdHierarchyLocalService getProjectionProdHierarchyLocalService() {
		return projectionProdHierarchyLocalService;
	}

	/**
	 * Sets the projection prod hierarchy local service.
	 *
	 * @param projectionProdHierarchyLocalService the projection prod hierarchy local service
	 */
	public void setProjectionProdHierarchyLocalService(
		com.stpl.app.service.ProjectionProdHierarchyLocalService projectionProdHierarchyLocalService) {
		this.projectionProdHierarchyLocalService = projectionProdHierarchyLocalService;
	}

	/**
	 * Returns the projection prod hierarchy persistence.
	 *
	 * @return the projection prod hierarchy persistence
	 */
	public ProjectionProdHierarchyPersistence getProjectionProdHierarchyPersistence() {
		return projectionProdHierarchyPersistence;
	}

	/**
	 * Sets the projection prod hierarchy persistence.
	 *
	 * @param projectionProdHierarchyPersistence the projection prod hierarchy persistence
	 */
	public void setProjectionProdHierarchyPersistence(
		ProjectionProdHierarchyPersistence projectionProdHierarchyPersistence) {
		this.projectionProdHierarchyPersistence = projectionProdHierarchyPersistence;
	}

	/**
	 * Returns the ps contract local service.
	 *
	 * @return the ps contract local service
	 */
	public com.stpl.app.service.PsContractLocalService getPsContractLocalService() {
		return psContractLocalService;
	}

	/**
	 * Sets the ps contract local service.
	 *
	 * @param psContractLocalService the ps contract local service
	 */
	public void setPsContractLocalService(
		com.stpl.app.service.PsContractLocalService psContractLocalService) {
		this.psContractLocalService = psContractLocalService;
	}

	/**
	 * Returns the ps contract persistence.
	 *
	 * @return the ps contract persistence
	 */
	public PsContractPersistence getPsContractPersistence() {
		return psContractPersistence;
	}

	/**
	 * Sets the ps contract persistence.
	 *
	 * @param psContractPersistence the ps contract persistence
	 */
	public void setPsContractPersistence(
		PsContractPersistence psContractPersistence) {
		this.psContractPersistence = psContractPersistence;
	}

	/**
	 * Returns the ps contract details local service.
	 *
	 * @return the ps contract details local service
	 */
	public com.stpl.app.service.PsContractDetailsLocalService getPsContractDetailsLocalService() {
		return psContractDetailsLocalService;
	}

	/**
	 * Sets the ps contract details local service.
	 *
	 * @param psContractDetailsLocalService the ps contract details local service
	 */
	public void setPsContractDetailsLocalService(
		com.stpl.app.service.PsContractDetailsLocalService psContractDetailsLocalService) {
		this.psContractDetailsLocalService = psContractDetailsLocalService;
	}

	/**
	 * Returns the ps contract details persistence.
	 *
	 * @return the ps contract details persistence
	 */
	public PsContractDetailsPersistence getPsContractDetailsPersistence() {
		return psContractDetailsPersistence;
	}

	/**
	 * Sets the ps contract details persistence.
	 *
	 * @param psContractDetailsPersistence the ps contract details persistence
	 */
	public void setPsContractDetailsPersistence(
		PsContractDetailsPersistence psContractDetailsPersistence) {
		this.psContractDetailsPersistence = psContractDetailsPersistence;
	}

	/**
	 * Returns the ps details local service.
	 *
	 * @return the ps details local service
	 */
	public com.stpl.app.service.PsDetailsLocalService getPsDetailsLocalService() {
		return psDetailsLocalService;
	}

	/**
	 * Sets the ps details local service.
	 *
	 * @param psDetailsLocalService the ps details local service
	 */
	public void setPsDetailsLocalService(
		com.stpl.app.service.PsDetailsLocalService psDetailsLocalService) {
		this.psDetailsLocalService = psDetailsLocalService;
	}

	/**
	 * Returns the ps details persistence.
	 *
	 * @return the ps details persistence
	 */
	public PsDetailsPersistence getPsDetailsPersistence() {
		return psDetailsPersistence;
	}

	/**
	 * Sets the ps details persistence.
	 *
	 * @param psDetailsPersistence the ps details persistence
	 */
	public void setPsDetailsPersistence(
		PsDetailsPersistence psDetailsPersistence) {
		this.psDetailsPersistence = psDetailsPersistence;
	}

	/**
	 * Returns the ps model local service.
	 *
	 * @return the ps model local service
	 */
	public com.stpl.app.service.PsModelLocalService getPsModelLocalService() {
		return psModelLocalService;
	}

	/**
	 * Sets the ps model local service.
	 *
	 * @param psModelLocalService the ps model local service
	 */
	public void setPsModelLocalService(
		com.stpl.app.service.PsModelLocalService psModelLocalService) {
		this.psModelLocalService = psModelLocalService;
	}

	/**
	 * Returns the ps model persistence.
	 *
	 * @return the ps model persistence
	 */
	public PsModelPersistence getPsModelPersistence() {
		return psModelPersistence;
	}

	/**
	 * Sets the ps model persistence.
	 *
	 * @param psModelPersistence the ps model persistence
	 */
	public void setPsModelPersistence(PsModelPersistence psModelPersistence) {
		this.psModelPersistence = psModelPersistence;
	}

	/**
	 * Returns the rebate plan master local service.
	 *
	 * @return the rebate plan master local service
	 */
	public com.stpl.app.service.RebatePlanMasterLocalService getRebatePlanMasterLocalService() {
		return rebatePlanMasterLocalService;
	}

	/**
	 * Sets the rebate plan master local service.
	 *
	 * @param rebatePlanMasterLocalService the rebate plan master local service
	 */
	public void setRebatePlanMasterLocalService(
		com.stpl.app.service.RebatePlanMasterLocalService rebatePlanMasterLocalService) {
		this.rebatePlanMasterLocalService = rebatePlanMasterLocalService;
	}

	/**
	 * Returns the rebate plan master persistence.
	 *
	 * @return the rebate plan master persistence
	 */
	public RebatePlanMasterPersistence getRebatePlanMasterPersistence() {
		return rebatePlanMasterPersistence;
	}

	/**
	 * Sets the rebate plan master persistence.
	 *
	 * @param rebatePlanMasterPersistence the rebate plan master persistence
	 */
	public void setRebatePlanMasterPersistence(
		RebatePlanMasterPersistence rebatePlanMasterPersistence) {
		this.rebatePlanMasterPersistence = rebatePlanMasterPersistence;
	}

	/**
	 * Returns the rebate plan tier local service.
	 *
	 * @return the rebate plan tier local service
	 */
	public com.stpl.app.service.RebatePlanTierLocalService getRebatePlanTierLocalService() {
		return rebatePlanTierLocalService;
	}

	/**
	 * Sets the rebate plan tier local service.
	 *
	 * @param rebatePlanTierLocalService the rebate plan tier local service
	 */
	public void setRebatePlanTierLocalService(
		com.stpl.app.service.RebatePlanTierLocalService rebatePlanTierLocalService) {
		this.rebatePlanTierLocalService = rebatePlanTierLocalService;
	}

	/**
	 * Returns the rebate plan tier persistence.
	 *
	 * @return the rebate plan tier persistence
	 */
	public RebatePlanTierPersistence getRebatePlanTierPersistence() {
		return rebatePlanTierPersistence;
	}

	/**
	 * Sets the rebate plan tier persistence.
	 *
	 * @param rebatePlanTierPersistence the rebate plan tier persistence
	 */
	public void setRebatePlanTierPersistence(
		RebatePlanTierPersistence rebatePlanTierPersistence) {
		this.rebatePlanTierPersistence = rebatePlanTierPersistence;
	}

	/**
	 * Returns the rebate tier formula local service.
	 *
	 * @return the rebate tier formula local service
	 */
	public com.stpl.app.service.RebateTierFormulaLocalService getRebateTierFormulaLocalService() {
		return rebateTierFormulaLocalService;
	}

	/**
	 * Sets the rebate tier formula local service.
	 *
	 * @param rebateTierFormulaLocalService the rebate tier formula local service
	 */
	public void setRebateTierFormulaLocalService(
		com.stpl.app.service.RebateTierFormulaLocalService rebateTierFormulaLocalService) {
		this.rebateTierFormulaLocalService = rebateTierFormulaLocalService;
	}

	/**
	 * Returns the rebate tier formula persistence.
	 *
	 * @return the rebate tier formula persistence
	 */
	public RebateTierFormulaPersistence getRebateTierFormulaPersistence() {
		return rebateTierFormulaPersistence;
	}

	/**
	 * Sets the rebate tier formula persistence.
	 *
	 * @param rebateTierFormulaPersistence the rebate tier formula persistence
	 */
	public void setRebateTierFormulaPersistence(
		RebateTierFormulaPersistence rebateTierFormulaPersistence) {
		this.rebateTierFormulaPersistence = rebateTierFormulaPersistence;
	}

	/**
	 * Returns the relationship builder local service.
	 *
	 * @return the relationship builder local service
	 */
	public com.stpl.app.service.RelationshipBuilderLocalService getRelationshipBuilderLocalService() {
		return relationshipBuilderLocalService;
	}

	/**
	 * Sets the relationship builder local service.
	 *
	 * @param relationshipBuilderLocalService the relationship builder local service
	 */
	public void setRelationshipBuilderLocalService(
		com.stpl.app.service.RelationshipBuilderLocalService relationshipBuilderLocalService) {
		this.relationshipBuilderLocalService = relationshipBuilderLocalService;
	}

	/**
	 * Returns the relationship builder persistence.
	 *
	 * @return the relationship builder persistence
	 */
	public RelationshipBuilderPersistence getRelationshipBuilderPersistence() {
		return relationshipBuilderPersistence;
	}

	/**
	 * Sets the relationship builder persistence.
	 *
	 * @param relationshipBuilderPersistence the relationship builder persistence
	 */
	public void setRelationshipBuilderPersistence(
		RelationshipBuilderPersistence relationshipBuilderPersistence) {
		this.relationshipBuilderPersistence = relationshipBuilderPersistence;
	}

	/**
	 * Returns the relationship level definition local service.
	 *
	 * @return the relationship level definition local service
	 */
	public com.stpl.app.service.RelationshipLevelDefinitionLocalService getRelationshipLevelDefinitionLocalService() {
		return relationshipLevelDefinitionLocalService;
	}

	/**
	 * Sets the relationship level definition local service.
	 *
	 * @param relationshipLevelDefinitionLocalService the relationship level definition local service
	 */
	public void setRelationshipLevelDefinitionLocalService(
		com.stpl.app.service.RelationshipLevelDefinitionLocalService relationshipLevelDefinitionLocalService) {
		this.relationshipLevelDefinitionLocalService = relationshipLevelDefinitionLocalService;
	}

	/**
	 * Returns the relationship level definition persistence.
	 *
	 * @return the relationship level definition persistence
	 */
	public RelationshipLevelDefinitionPersistence getRelationshipLevelDefinitionPersistence() {
		return relationshipLevelDefinitionPersistence;
	}

	/**
	 * Sets the relationship level definition persistence.
	 *
	 * @param relationshipLevelDefinitionPersistence the relationship level definition persistence
	 */
	public void setRelationshipLevelDefinitionPersistence(
		RelationshipLevelDefinitionPersistence relationshipLevelDefinitionPersistence) {
		this.relationshipLevelDefinitionPersistence = relationshipLevelDefinitionPersistence;
	}

	/**
	 * Returns the returns master local service.
	 *
	 * @return the returns master local service
	 */
	public com.stpl.app.service.ReturnsMasterLocalService getReturnsMasterLocalService() {
		return returnsMasterLocalService;
	}

	/**
	 * Sets the returns master local service.
	 *
	 * @param returnsMasterLocalService the returns master local service
	 */
	public void setReturnsMasterLocalService(
		com.stpl.app.service.ReturnsMasterLocalService returnsMasterLocalService) {
		this.returnsMasterLocalService = returnsMasterLocalService;
	}

	/**
	 * Returns the returns master persistence.
	 *
	 * @return the returns master persistence
	 */
	public ReturnsMasterPersistence getReturnsMasterPersistence() {
		return returnsMasterPersistence;
	}

	/**
	 * Sets the returns master persistence.
	 *
	 * @param returnsMasterPersistence the returns master persistence
	 */
	public void setReturnsMasterPersistence(
		ReturnsMasterPersistence returnsMasterPersistence) {
		this.returnsMasterPersistence = returnsMasterPersistence;
	}

	/**
	 * Returns the rs contract local service.
	 *
	 * @return the rs contract local service
	 */
	public com.stpl.app.service.RsContractLocalService getRsContractLocalService() {
		return rsContractLocalService;
	}

	/**
	 * Sets the rs contract local service.
	 *
	 * @param rsContractLocalService the rs contract local service
	 */
	public void setRsContractLocalService(
		com.stpl.app.service.RsContractLocalService rsContractLocalService) {
		this.rsContractLocalService = rsContractLocalService;
	}

	/**
	 * Returns the rs contract persistence.
	 *
	 * @return the rs contract persistence
	 */
	public RsContractPersistence getRsContractPersistence() {
		return rsContractPersistence;
	}

	/**
	 * Sets the rs contract persistence.
	 *
	 * @param rsContractPersistence the rs contract persistence
	 */
	public void setRsContractPersistence(
		RsContractPersistence rsContractPersistence) {
		this.rsContractPersistence = rsContractPersistence;
	}

	/**
	 * Returns the rs contract details local service.
	 *
	 * @return the rs contract details local service
	 */
	public com.stpl.app.service.RsContractDetailsLocalService getRsContractDetailsLocalService() {
		return rsContractDetailsLocalService;
	}

	/**
	 * Sets the rs contract details local service.
	 *
	 * @param rsContractDetailsLocalService the rs contract details local service
	 */
	public void setRsContractDetailsLocalService(
		com.stpl.app.service.RsContractDetailsLocalService rsContractDetailsLocalService) {
		this.rsContractDetailsLocalService = rsContractDetailsLocalService;
	}

	/**
	 * Returns the rs contract details persistence.
	 *
	 * @return the rs contract details persistence
	 */
	public RsContractDetailsPersistence getRsContractDetailsPersistence() {
		return rsContractDetailsPersistence;
	}

	/**
	 * Sets the rs contract details persistence.
	 *
	 * @param rsContractDetailsPersistence the rs contract details persistence
	 */
	public void setRsContractDetailsPersistence(
		RsContractDetailsPersistence rsContractDetailsPersistence) {
		this.rsContractDetailsPersistence = rsContractDetailsPersistence;
	}

	/**
	 * Returns the rs contract details fr local service.
	 *
	 * @return the rs contract details fr local service
	 */
	public com.stpl.app.service.RsContractDetailsFrLocalService getRsContractDetailsFrLocalService() {
		return rsContractDetailsFrLocalService;
	}

	/**
	 * Sets the rs contract details fr local service.
	 *
	 * @param rsContractDetailsFrLocalService the rs contract details fr local service
	 */
	public void setRsContractDetailsFrLocalService(
		com.stpl.app.service.RsContractDetailsFrLocalService rsContractDetailsFrLocalService) {
		this.rsContractDetailsFrLocalService = rsContractDetailsFrLocalService;
	}

	/**
	 * Returns the rs contract details fr persistence.
	 *
	 * @return the rs contract details fr persistence
	 */
	public RsContractDetailsFrPersistence getRsContractDetailsFrPersistence() {
		return rsContractDetailsFrPersistence;
	}

	/**
	 * Sets the rs contract details fr persistence.
	 *
	 * @param rsContractDetailsFrPersistence the rs contract details fr persistence
	 */
	public void setRsContractDetailsFrPersistence(
		RsContractDetailsFrPersistence rsContractDetailsFrPersistence) {
		this.rsContractDetailsFrPersistence = rsContractDetailsFrPersistence;
	}

	/**
	 * Returns the rs details local service.
	 *
	 * @return the rs details local service
	 */
	public com.stpl.app.service.RsDetailsLocalService getRsDetailsLocalService() {
		return rsDetailsLocalService;
	}

	/**
	 * Sets the rs details local service.
	 *
	 * @param rsDetailsLocalService the rs details local service
	 */
	public void setRsDetailsLocalService(
		com.stpl.app.service.RsDetailsLocalService rsDetailsLocalService) {
		this.rsDetailsLocalService = rsDetailsLocalService;
	}

	/**
	 * Returns the rs details persistence.
	 *
	 * @return the rs details persistence
	 */
	public RsDetailsPersistence getRsDetailsPersistence() {
		return rsDetailsPersistence;
	}

	/**
	 * Sets the rs details persistence.
	 *
	 * @param rsDetailsPersistence the rs details persistence
	 */
	public void setRsDetailsPersistence(
		RsDetailsPersistence rsDetailsPersistence) {
		this.rsDetailsPersistence = rsDetailsPersistence;
	}

	/**
	 * Returns the rs details fr local service.
	 *
	 * @return the rs details fr local service
	 */
	public com.stpl.app.service.RsDetailsFrLocalService getRsDetailsFrLocalService() {
		return rsDetailsFrLocalService;
	}

	/**
	 * Sets the rs details fr local service.
	 *
	 * @param rsDetailsFrLocalService the rs details fr local service
	 */
	public void setRsDetailsFrLocalService(
		com.stpl.app.service.RsDetailsFrLocalService rsDetailsFrLocalService) {
		this.rsDetailsFrLocalService = rsDetailsFrLocalService;
	}

	/**
	 * Returns the rs details fr persistence.
	 *
	 * @return the rs details fr persistence
	 */
	public RsDetailsFrPersistence getRsDetailsFrPersistence() {
		return rsDetailsFrPersistence;
	}

	/**
	 * Sets the rs details fr persistence.
	 *
	 * @param rsDetailsFrPersistence the rs details fr persistence
	 */
	public void setRsDetailsFrPersistence(
		RsDetailsFrPersistence rsDetailsFrPersistence) {
		this.rsDetailsFrPersistence = rsDetailsFrPersistence;
	}

	/**
	 * Returns the rs model local service.
	 *
	 * @return the rs model local service
	 */
	public com.stpl.app.service.RsModelLocalService getRsModelLocalService() {
		return rsModelLocalService;
	}

	/**
	 * Sets the rs model local service.
	 *
	 * @param rsModelLocalService the rs model local service
	 */
	public void setRsModelLocalService(
		com.stpl.app.service.RsModelLocalService rsModelLocalService) {
		this.rsModelLocalService = rsModelLocalService;
	}

	/**
	 * Returns the rs model persistence.
	 *
	 * @return the rs model persistence
	 */
	public RsModelPersistence getRsModelPersistence() {
		return rsModelPersistence;
	}

	/**
	 * Sets the rs model persistence.
	 *
	 * @param rsModelPersistence the rs model persistence
	 */
	public void setRsModelPersistence(RsModelPersistence rsModelPersistence) {
		this.rsModelPersistence = rsModelPersistence;
	}

	/**
	 * Returns the sales basis details local service.
	 *
	 * @return the sales basis details local service
	 */
	public com.stpl.app.service.SalesBasisDetailsLocalService getSalesBasisDetailsLocalService() {
		return salesBasisDetailsLocalService;
	}

	/**
	 * Sets the sales basis details local service.
	 *
	 * @param salesBasisDetailsLocalService the sales basis details local service
	 */
	public void setSalesBasisDetailsLocalService(
		com.stpl.app.service.SalesBasisDetailsLocalService salesBasisDetailsLocalService) {
		this.salesBasisDetailsLocalService = salesBasisDetailsLocalService;
	}

	/**
	 * Returns the sales basis details persistence.
	 *
	 * @return the sales basis details persistence
	 */
	public SalesBasisDetailsPersistence getSalesBasisDetailsPersistence() {
		return salesBasisDetailsPersistence;
	}

	/**
	 * Sets the sales basis details persistence.
	 *
	 * @param salesBasisDetailsPersistence the sales basis details persistence
	 */
	public void setSalesBasisDetailsPersistence(
		SalesBasisDetailsPersistence salesBasisDetailsPersistence) {
		this.salesBasisDetailsPersistence = salesBasisDetailsPersistence;
	}

	/**
	 * Returns the sales master local service.
	 *
	 * @return the sales master local service
	 */
	public com.stpl.app.service.SalesMasterLocalService getSalesMasterLocalService() {
		return salesMasterLocalService;
	}

	/**
	 * Sets the sales master local service.
	 *
	 * @param salesMasterLocalService the sales master local service
	 */
	public void setSalesMasterLocalService(
		com.stpl.app.service.SalesMasterLocalService salesMasterLocalService) {
		this.salesMasterLocalService = salesMasterLocalService;
	}

	/**
	 * Returns the sales master persistence.
	 *
	 * @return the sales master persistence
	 */
	public SalesMasterPersistence getSalesMasterPersistence() {
		return salesMasterPersistence;
	}

	/**
	 * Sets the sales master persistence.
	 *
	 * @param salesMasterPersistence the sales master persistence
	 */
	public void setSalesMasterPersistence(
		SalesMasterPersistence salesMasterPersistence) {
		this.salesMasterPersistence = salesMasterPersistence;
	}

	/**
	 * Returns the st ch actual discount local service.
	 *
	 * @return the st ch actual discount local service
	 */
	public com.stpl.app.service.StChActualDiscountLocalService getStChActualDiscountLocalService() {
		return stChActualDiscountLocalService;
	}

	/**
	 * Sets the st ch actual discount local service.
	 *
	 * @param stChActualDiscountLocalService the st ch actual discount local service
	 */
	public void setStChActualDiscountLocalService(
		com.stpl.app.service.StChActualDiscountLocalService stChActualDiscountLocalService) {
		this.stChActualDiscountLocalService = stChActualDiscountLocalService;
	}

	/**
	 * Returns the st ch actual discount persistence.
	 *
	 * @return the st ch actual discount persistence
	 */
	public StChActualDiscountPersistence getStChActualDiscountPersistence() {
		return stChActualDiscountPersistence;
	}

	/**
	 * Sets the st ch actual discount persistence.
	 *
	 * @param stChActualDiscountPersistence the st ch actual discount persistence
	 */
	public void setStChActualDiscountPersistence(
		StChActualDiscountPersistence stChActualDiscountPersistence) {
		this.stChActualDiscountPersistence = stChActualDiscountPersistence;
	}

	/**
	 * Returns the st ch assumptions local service.
	 *
	 * @return the st ch assumptions local service
	 */
	public com.stpl.app.service.StChAssumptionsLocalService getStChAssumptionsLocalService() {
		return stChAssumptionsLocalService;
	}

	/**
	 * Sets the st ch assumptions local service.
	 *
	 * @param stChAssumptionsLocalService the st ch assumptions local service
	 */
	public void setStChAssumptionsLocalService(
		com.stpl.app.service.StChAssumptionsLocalService stChAssumptionsLocalService) {
		this.stChAssumptionsLocalService = stChAssumptionsLocalService;
	}

	/**
	 * Returns the st ch assumptions persistence.
	 *
	 * @return the st ch assumptions persistence
	 */
	public StChAssumptionsPersistence getStChAssumptionsPersistence() {
		return stChAssumptionsPersistence;
	}

	/**
	 * Sets the st ch assumptions persistence.
	 *
	 * @param stChAssumptionsPersistence the st ch assumptions persistence
	 */
	public void setStChAssumptionsPersistence(
		StChAssumptionsPersistence stChAssumptionsPersistence) {
		this.stChAssumptionsPersistence = stChAssumptionsPersistence;
	}

	/**
	 * Returns the st ch discount proj master local service.
	 *
	 * @return the st ch discount proj master local service
	 */
	public com.stpl.app.service.StChDiscountProjMasterLocalService getStChDiscountProjMasterLocalService() {
		return stChDiscountProjMasterLocalService;
	}

	/**
	 * Sets the st ch discount proj master local service.
	 *
	 * @param stChDiscountProjMasterLocalService the st ch discount proj master local service
	 */
	public void setStChDiscountProjMasterLocalService(
		com.stpl.app.service.StChDiscountProjMasterLocalService stChDiscountProjMasterLocalService) {
		this.stChDiscountProjMasterLocalService = stChDiscountProjMasterLocalService;
	}

	/**
	 * Returns the st ch discount proj master persistence.
	 *
	 * @return the st ch discount proj master persistence
	 */
	public StChDiscountProjMasterPersistence getStChDiscountProjMasterPersistence() {
		return stChDiscountProjMasterPersistence;
	}

	/**
	 * Sets the st ch discount proj master persistence.
	 *
	 * @param stChDiscountProjMasterPersistence the st ch discount proj master persistence
	 */
	public void setStChDiscountProjMasterPersistence(
		StChDiscountProjMasterPersistence stChDiscountProjMasterPersistence) {
		this.stChDiscountProjMasterPersistence = stChDiscountProjMasterPersistence;
	}

	/**
	 * Returns the st ch projection discount local service.
	 *
	 * @return the st ch projection discount local service
	 */
	public com.stpl.app.service.StChProjectionDiscountLocalService getStChProjectionDiscountLocalService() {
		return stChProjectionDiscountLocalService;
	}

	/**
	 * Sets the st ch projection discount local service.
	 *
	 * @param stChProjectionDiscountLocalService the st ch projection discount local service
	 */
	public void setStChProjectionDiscountLocalService(
		com.stpl.app.service.StChProjectionDiscountLocalService stChProjectionDiscountLocalService) {
		this.stChProjectionDiscountLocalService = stChProjectionDiscountLocalService;
	}

	/**
	 * Returns the st ch projection discount persistence.
	 *
	 * @return the st ch projection discount persistence
	 */
	public StChProjectionDiscountPersistence getStChProjectionDiscountPersistence() {
		return stChProjectionDiscountPersistence;
	}

	/**
	 * Sets the st ch projection discount persistence.
	 *
	 * @param stChProjectionDiscountPersistence the st ch projection discount persistence
	 */
	public void setStChProjectionDiscountPersistence(
		StChProjectionDiscountPersistence stChProjectionDiscountPersistence) {
		this.stChProjectionDiscountPersistence = stChProjectionDiscountPersistence;
	}

	/**
	 * Returns the st ch sales projection master local service.
	 *
	 * @return the st ch sales projection master local service
	 */
	public com.stpl.app.service.StChSalesProjectionMasterLocalService getStChSalesProjectionMasterLocalService() {
		return stChSalesProjectionMasterLocalService;
	}

	/**
	 * Sets the st ch sales projection master local service.
	 *
	 * @param stChSalesProjectionMasterLocalService the st ch sales projection master local service
	 */
	public void setStChSalesProjectionMasterLocalService(
		com.stpl.app.service.StChSalesProjectionMasterLocalService stChSalesProjectionMasterLocalService) {
		this.stChSalesProjectionMasterLocalService = stChSalesProjectionMasterLocalService;
	}

	/**
	 * Returns the st ch sales projection master persistence.
	 *
	 * @return the st ch sales projection master persistence
	 */
	public StChSalesProjectionMasterPersistence getStChSalesProjectionMasterPersistence() {
		return stChSalesProjectionMasterPersistence;
	}

	/**
	 * Sets the st ch sales projection master persistence.
	 *
	 * @param stChSalesProjectionMasterPersistence the st ch sales projection master persistence
	 */
	public void setStChSalesProjectionMasterPersistence(
		StChSalesProjectionMasterPersistence stChSalesProjectionMasterPersistence) {
		this.stChSalesProjectionMasterPersistence = stChSalesProjectionMasterPersistence;
	}

	/**
	 * Returns the st deduction calendar details local service.
	 *
	 * @return the st deduction calendar details local service
	 */
	public com.stpl.app.service.StDeductionCalendarDetailsLocalService getStDeductionCalendarDetailsLocalService() {
		return stDeductionCalendarDetailsLocalService;
	}

	/**
	 * Sets the st deduction calendar details local service.
	 *
	 * @param stDeductionCalendarDetailsLocalService the st deduction calendar details local service
	 */
	public void setStDeductionCalendarDetailsLocalService(
		com.stpl.app.service.StDeductionCalendarDetailsLocalService stDeductionCalendarDetailsLocalService) {
		this.stDeductionCalendarDetailsLocalService = stDeductionCalendarDetailsLocalService;
	}

	/**
	 * Returns the st deduction calendar details persistence.
	 *
	 * @return the st deduction calendar details persistence
	 */
	public StDeductionCalendarDetailsPersistence getStDeductionCalendarDetailsPersistence() {
		return stDeductionCalendarDetailsPersistence;
	}

	/**
	 * Sets the st deduction calendar details persistence.
	 *
	 * @param stDeductionCalendarDetailsPersistence the st deduction calendar details persistence
	 */
	public void setStDeductionCalendarDetailsPersistence(
		StDeductionCalendarDetailsPersistence stDeductionCalendarDetailsPersistence) {
		this.stDeductionCalendarDetailsPersistence = stDeductionCalendarDetailsPersistence;
	}

	/**
	 * Returns the st federal new ndc local service.
	 *
	 * @return the st federal new ndc local service
	 */
	public com.stpl.app.service.StFederalNewNdcLocalService getStFederalNewNdcLocalService() {
		return stFederalNewNdcLocalService;
	}

	/**
	 * Sets the st federal new ndc local service.
	 *
	 * @param stFederalNewNdcLocalService the st federal new ndc local service
	 */
	public void setStFederalNewNdcLocalService(
		com.stpl.app.service.StFederalNewNdcLocalService stFederalNewNdcLocalService) {
		this.stFederalNewNdcLocalService = stFederalNewNdcLocalService;
	}

	/**
	 * Returns the st federal new ndc persistence.
	 *
	 * @return the st federal new ndc persistence
	 */
	public StFederalNewNdcPersistence getStFederalNewNdcPersistence() {
		return stFederalNewNdcPersistence;
	}

	/**
	 * Sets the st federal new ndc persistence.
	 *
	 * @param stFederalNewNdcPersistence the st federal new ndc persistence
	 */
	public void setStFederalNewNdcPersistence(
		StFederalNewNdcPersistence stFederalNewNdcPersistence) {
		this.stFederalNewNdcPersistence = stFederalNewNdcPersistence;
	}

	/**
	 * Returns the st m assumptions local service.
	 *
	 * @return the st m assumptions local service
	 */
	public com.stpl.app.service.StMAssumptionsLocalService getStMAssumptionsLocalService() {
		return stMAssumptionsLocalService;
	}

	/**
	 * Sets the st m assumptions local service.
	 *
	 * @param stMAssumptionsLocalService the st m assumptions local service
	 */
	public void setStMAssumptionsLocalService(
		com.stpl.app.service.StMAssumptionsLocalService stMAssumptionsLocalService) {
		this.stMAssumptionsLocalService = stMAssumptionsLocalService;
	}

	/**
	 * Returns the st m assumptions persistence.
	 *
	 * @return the st m assumptions persistence
	 */
	public StMAssumptionsPersistence getStMAssumptionsPersistence() {
		return stMAssumptionsPersistence;
	}

	/**
	 * Sets the st m assumptions persistence.
	 *
	 * @param stMAssumptionsPersistence the st m assumptions persistence
	 */
	public void setStMAssumptionsPersistence(
		StMAssumptionsPersistence stMAssumptionsPersistence) {
		this.stMAssumptionsPersistence = stMAssumptionsPersistence;
	}

	/**
	 * Returns the st medicaid new ndc local service.
	 *
	 * @return the st medicaid new ndc local service
	 */
	public com.stpl.app.service.StMedicaidNewNdcLocalService getStMedicaidNewNdcLocalService() {
		return stMedicaidNewNdcLocalService;
	}

	/**
	 * Sets the st medicaid new ndc local service.
	 *
	 * @param stMedicaidNewNdcLocalService the st medicaid new ndc local service
	 */
	public void setStMedicaidNewNdcLocalService(
		com.stpl.app.service.StMedicaidNewNdcLocalService stMedicaidNewNdcLocalService) {
		this.stMedicaidNewNdcLocalService = stMedicaidNewNdcLocalService;
	}

	/**
	 * Returns the st medicaid new ndc persistence.
	 *
	 * @return the st medicaid new ndc persistence
	 */
	public StMedicaidNewNdcPersistence getStMedicaidNewNdcPersistence() {
		return stMedicaidNewNdcPersistence;
	}

	/**
	 * Sets the st medicaid new ndc persistence.
	 *
	 * @param stMedicaidNewNdcPersistence the st medicaid new ndc persistence
	 */
	public void setStMedicaidNewNdcPersistence(
		StMedicaidNewNdcPersistence stMedicaidNewNdcPersistence) {
		this.stMedicaidNewNdcPersistence = stMedicaidNewNdcPersistence;
	}

	/**
	 * Returns the st m supplemental disc actuals local service.
	 *
	 * @return the st m supplemental disc actuals local service
	 */
	public com.stpl.app.service.StMSupplementalDiscActualsLocalService getStMSupplementalDiscActualsLocalService() {
		return stMSupplementalDiscActualsLocalService;
	}

	/**
	 * Sets the st m supplemental disc actuals local service.
	 *
	 * @param stMSupplementalDiscActualsLocalService the st m supplemental disc actuals local service
	 */
	public void setStMSupplementalDiscActualsLocalService(
		com.stpl.app.service.StMSupplementalDiscActualsLocalService stMSupplementalDiscActualsLocalService) {
		this.stMSupplementalDiscActualsLocalService = stMSupplementalDiscActualsLocalService;
	}

	/**
	 * Returns the st m supplemental disc actuals persistence.
	 *
	 * @return the st m supplemental disc actuals persistence
	 */
	public StMSupplementalDiscActualsPersistence getStMSupplementalDiscActualsPersistence() {
		return stMSupplementalDiscActualsPersistence;
	}

	/**
	 * Sets the st m supplemental disc actuals persistence.
	 *
	 * @param stMSupplementalDiscActualsPersistence the st m supplemental disc actuals persistence
	 */
	public void setStMSupplementalDiscActualsPersistence(
		StMSupplementalDiscActualsPersistence stMSupplementalDiscActualsPersistence) {
		this.stMSupplementalDiscActualsPersistence = stMSupplementalDiscActualsPersistence;
	}

	/**
	 * Returns the st m supplemental disc master local service.
	 *
	 * @return the st m supplemental disc master local service
	 */
	public com.stpl.app.service.StMSupplementalDiscMasterLocalService getStMSupplementalDiscMasterLocalService() {
		return stMSupplementalDiscMasterLocalService;
	}

	/**
	 * Sets the st m supplemental disc master local service.
	 *
	 * @param stMSupplementalDiscMasterLocalService the st m supplemental disc master local service
	 */
	public void setStMSupplementalDiscMasterLocalService(
		com.stpl.app.service.StMSupplementalDiscMasterLocalService stMSupplementalDiscMasterLocalService) {
		this.stMSupplementalDiscMasterLocalService = stMSupplementalDiscMasterLocalService;
	}

	/**
	 * Returns the st m supplemental disc master persistence.
	 *
	 * @return the st m supplemental disc master persistence
	 */
	public StMSupplementalDiscMasterPersistence getStMSupplementalDiscMasterPersistence() {
		return stMSupplementalDiscMasterPersistence;
	}

	/**
	 * Sets the st m supplemental disc master persistence.
	 *
	 * @param stMSupplementalDiscMasterPersistence the st m supplemental disc master persistence
	 */
	public void setStMSupplementalDiscMasterPersistence(
		StMSupplementalDiscMasterPersistence stMSupplementalDiscMasterPersistence) {
		this.stMSupplementalDiscMasterPersistence = stMSupplementalDiscMasterPersistence;
	}

	/**
	 * Returns the st m supplemental disc proj local service.
	 *
	 * @return the st m supplemental disc proj local service
	 */
	public com.stpl.app.service.StMSupplementalDiscProjLocalService getStMSupplementalDiscProjLocalService() {
		return stMSupplementalDiscProjLocalService;
	}

	/**
	 * Sets the st m supplemental disc proj local service.
	 *
	 * @param stMSupplementalDiscProjLocalService the st m supplemental disc proj local service
	 */
	public void setStMSupplementalDiscProjLocalService(
		com.stpl.app.service.StMSupplementalDiscProjLocalService stMSupplementalDiscProjLocalService) {
		this.stMSupplementalDiscProjLocalService = stMSupplementalDiscProjLocalService;
	}

	/**
	 * Returns the st m supplemental disc proj persistence.
	 *
	 * @return the st m supplemental disc proj persistence
	 */
	public StMSupplementalDiscProjPersistence getStMSupplementalDiscProjPersistence() {
		return stMSupplementalDiscProjPersistence;
	}

	/**
	 * Sets the st m supplemental disc proj persistence.
	 *
	 * @param stMSupplementalDiscProjPersistence the st m supplemental disc proj persistence
	 */
	public void setStMSupplementalDiscProjPersistence(
		StMSupplementalDiscProjPersistence stMSupplementalDiscProjPersistence) {
		this.stMSupplementalDiscProjPersistence = stMSupplementalDiscProjPersistence;
	}

	/**
	 * Returns the st national assumptions local service.
	 *
	 * @return the st national assumptions local service
	 */
	public com.stpl.app.service.StNationalAssumptionsLocalService getStNationalAssumptionsLocalService() {
		return stNationalAssumptionsLocalService;
	}

	/**
	 * Sets the st national assumptions local service.
	 *
	 * @param stNationalAssumptionsLocalService the st national assumptions local service
	 */
	public void setStNationalAssumptionsLocalService(
		com.stpl.app.service.StNationalAssumptionsLocalService stNationalAssumptionsLocalService) {
		this.stNationalAssumptionsLocalService = stNationalAssumptionsLocalService;
	}

	/**
	 * Returns the st national assumptions persistence.
	 *
	 * @return the st national assumptions persistence
	 */
	public StNationalAssumptionsPersistence getStNationalAssumptionsPersistence() {
		return stNationalAssumptionsPersistence;
	}

	/**
	 * Sets the st national assumptions persistence.
	 *
	 * @param stNationalAssumptionsPersistence the st national assumptions persistence
	 */
	public void setStNationalAssumptionsPersistence(
		StNationalAssumptionsPersistence stNationalAssumptionsPersistence) {
		this.stNationalAssumptionsPersistence = stNationalAssumptionsPersistence;
	}

	/**
	 * Returns the st new ndc local service.
	 *
	 * @return the st new ndc local service
	 */
	public com.stpl.app.service.StNewNdcLocalService getStNewNdcLocalService() {
		return stNewNdcLocalService;
	}

	/**
	 * Sets the st new ndc local service.
	 *
	 * @param stNewNdcLocalService the st new ndc local service
	 */
	public void setStNewNdcLocalService(
		com.stpl.app.service.StNewNdcLocalService stNewNdcLocalService) {
		this.stNewNdcLocalService = stNewNdcLocalService;
	}

	/**
	 * Returns the st new ndc persistence.
	 *
	 * @return the st new ndc persistence
	 */
	public StNewNdcPersistence getStNewNdcPersistence() {
		return stNewNdcPersistence;
	}

	/**
	 * Sets the st new ndc persistence.
	 *
	 * @param stNewNdcPersistence the st new ndc persistence
	 */
	public void setStNewNdcPersistence(StNewNdcPersistence stNewNdcPersistence) {
		this.stNewNdcPersistence = stNewNdcPersistence;
	}

	/**
	 * Returns the st nm actual discount local service.
	 *
	 * @return the st nm actual discount local service
	 */
	public com.stpl.app.service.StNmActualDiscountLocalService getStNmActualDiscountLocalService() {
		return stNmActualDiscountLocalService;
	}

	/**
	 * Sets the st nm actual discount local service.
	 *
	 * @param stNmActualDiscountLocalService the st nm actual discount local service
	 */
	public void setStNmActualDiscountLocalService(
		com.stpl.app.service.StNmActualDiscountLocalService stNmActualDiscountLocalService) {
		this.stNmActualDiscountLocalService = stNmActualDiscountLocalService;
	}

	/**
	 * Returns the st nm actual discount persistence.
	 *
	 * @return the st nm actual discount persistence
	 */
	public StNmActualDiscountPersistence getStNmActualDiscountPersistence() {
		return stNmActualDiscountPersistence;
	}

	/**
	 * Sets the st nm actual discount persistence.
	 *
	 * @param stNmActualDiscountPersistence the st nm actual discount persistence
	 */
	public void setStNmActualDiscountPersistence(
		StNmActualDiscountPersistence stNmActualDiscountPersistence) {
		this.stNmActualDiscountPersistence = stNmActualDiscountPersistence;
	}

	/**
	 * Returns the st nm actual ppa local service.
	 *
	 * @return the st nm actual ppa local service
	 */
	public com.stpl.app.service.StNmActualPpaLocalService getStNmActualPpaLocalService() {
		return stNmActualPpaLocalService;
	}

	/**
	 * Sets the st nm actual ppa local service.
	 *
	 * @param stNmActualPpaLocalService the st nm actual ppa local service
	 */
	public void setStNmActualPpaLocalService(
		com.stpl.app.service.StNmActualPpaLocalService stNmActualPpaLocalService) {
		this.stNmActualPpaLocalService = stNmActualPpaLocalService;
	}

	/**
	 * Returns the st nm actual ppa persistence.
	 *
	 * @return the st nm actual ppa persistence
	 */
	public StNmActualPpaPersistence getStNmActualPpaPersistence() {
		return stNmActualPpaPersistence;
	}

	/**
	 * Sets the st nm actual ppa persistence.
	 *
	 * @param stNmActualPpaPersistence the st nm actual ppa persistence
	 */
	public void setStNmActualPpaPersistence(
		StNmActualPpaPersistence stNmActualPpaPersistence) {
		this.stNmActualPpaPersistence = stNmActualPpaPersistence;
	}

	/**
	 * Returns the st nm assumptions local service.
	 *
	 * @return the st nm assumptions local service
	 */
	public com.stpl.app.service.StNmAssumptionsLocalService getStNmAssumptionsLocalService() {
		return stNmAssumptionsLocalService;
	}

	/**
	 * Sets the st nm assumptions local service.
	 *
	 * @param stNmAssumptionsLocalService the st nm assumptions local service
	 */
	public void setStNmAssumptionsLocalService(
		com.stpl.app.service.StNmAssumptionsLocalService stNmAssumptionsLocalService) {
		this.stNmAssumptionsLocalService = stNmAssumptionsLocalService;
	}

	/**
	 * Returns the st nm assumptions persistence.
	 *
	 * @return the st nm assumptions persistence
	 */
	public StNmAssumptionsPersistence getStNmAssumptionsPersistence() {
		return stNmAssumptionsPersistence;
	}

	/**
	 * Sets the st nm assumptions persistence.
	 *
	 * @param stNmAssumptionsPersistence the st nm assumptions persistence
	 */
	public void setStNmAssumptionsPersistence(
		StNmAssumptionsPersistence stNmAssumptionsPersistence) {
		this.stNmAssumptionsPersistence = stNmAssumptionsPersistence;
	}

	/**
	 * Returns the st nm discount projection local service.
	 *
	 * @return the st nm discount projection local service
	 */
	public com.stpl.app.service.StNmDiscountProjectionLocalService getStNmDiscountProjectionLocalService() {
		return stNmDiscountProjectionLocalService;
	}

	/**
	 * Sets the st nm discount projection local service.
	 *
	 * @param stNmDiscountProjectionLocalService the st nm discount projection local service
	 */
	public void setStNmDiscountProjectionLocalService(
		com.stpl.app.service.StNmDiscountProjectionLocalService stNmDiscountProjectionLocalService) {
		this.stNmDiscountProjectionLocalService = stNmDiscountProjectionLocalService;
	}

	/**
	 * Returns the st nm discount projection persistence.
	 *
	 * @return the st nm discount projection persistence
	 */
	public StNmDiscountProjectionPersistence getStNmDiscountProjectionPersistence() {
		return stNmDiscountProjectionPersistence;
	}

	/**
	 * Sets the st nm discount projection persistence.
	 *
	 * @param stNmDiscountProjectionPersistence the st nm discount projection persistence
	 */
	public void setStNmDiscountProjectionPersistence(
		StNmDiscountProjectionPersistence stNmDiscountProjectionPersistence) {
		this.stNmDiscountProjectionPersistence = stNmDiscountProjectionPersistence;
	}

	/**
	 * Returns the st nm discount proj master local service.
	 *
	 * @return the st nm discount proj master local service
	 */
	public com.stpl.app.service.StNmDiscountProjMasterLocalService getStNmDiscountProjMasterLocalService() {
		return stNmDiscountProjMasterLocalService;
	}

	/**
	 * Sets the st nm discount proj master local service.
	 *
	 * @param stNmDiscountProjMasterLocalService the st nm discount proj master local service
	 */
	public void setStNmDiscountProjMasterLocalService(
		com.stpl.app.service.StNmDiscountProjMasterLocalService stNmDiscountProjMasterLocalService) {
		this.stNmDiscountProjMasterLocalService = stNmDiscountProjMasterLocalService;
	}

	/**
	 * Returns the st nm discount proj master persistence.
	 *
	 * @return the st nm discount proj master persistence
	 */
	public StNmDiscountProjMasterPersistence getStNmDiscountProjMasterPersistence() {
		return stNmDiscountProjMasterPersistence;
	}

	/**
	 * Sets the st nm discount proj master persistence.
	 *
	 * @param stNmDiscountProjMasterPersistence the st nm discount proj master persistence
	 */
	public void setStNmDiscountProjMasterPersistence(
		StNmDiscountProjMasterPersistence stNmDiscountProjMasterPersistence) {
		this.stNmDiscountProjMasterPersistence = stNmDiscountProjMasterPersistence;
	}

	/**
	 * Returns the st nm ppa projection local service.
	 *
	 * @return the st nm ppa projection local service
	 */
	public com.stpl.app.service.StNmPpaProjectionLocalService getStNmPpaProjectionLocalService() {
		return stNmPpaProjectionLocalService;
	}

	/**
	 * Sets the st nm ppa projection local service.
	 *
	 * @param stNmPpaProjectionLocalService the st nm ppa projection local service
	 */
	public void setStNmPpaProjectionLocalService(
		com.stpl.app.service.StNmPpaProjectionLocalService stNmPpaProjectionLocalService) {
		this.stNmPpaProjectionLocalService = stNmPpaProjectionLocalService;
	}

	/**
	 * Returns the st nm ppa projection persistence.
	 *
	 * @return the st nm ppa projection persistence
	 */
	public StNmPpaProjectionPersistence getStNmPpaProjectionPersistence() {
		return stNmPpaProjectionPersistence;
	}

	/**
	 * Sets the st nm ppa projection persistence.
	 *
	 * @param stNmPpaProjectionPersistence the st nm ppa projection persistence
	 */
	public void setStNmPpaProjectionPersistence(
		StNmPpaProjectionPersistence stNmPpaProjectionPersistence) {
		this.stNmPpaProjectionPersistence = stNmPpaProjectionPersistence;
	}

	/**
	 * Returns the st nm ppa projection master local service.
	 *
	 * @return the st nm ppa projection master local service
	 */
	public com.stpl.app.service.StNmPpaProjectionMasterLocalService getStNmPpaProjectionMasterLocalService() {
		return stNmPpaProjectionMasterLocalService;
	}

	/**
	 * Sets the st nm ppa projection master local service.
	 *
	 * @param stNmPpaProjectionMasterLocalService the st nm ppa projection master local service
	 */
	public void setStNmPpaProjectionMasterLocalService(
		com.stpl.app.service.StNmPpaProjectionMasterLocalService stNmPpaProjectionMasterLocalService) {
		this.stNmPpaProjectionMasterLocalService = stNmPpaProjectionMasterLocalService;
	}

	/**
	 * Returns the st nm ppa projection master persistence.
	 *
	 * @return the st nm ppa projection master persistence
	 */
	public StNmPpaProjectionMasterPersistence getStNmPpaProjectionMasterPersistence() {
		return stNmPpaProjectionMasterPersistence;
	}

	/**
	 * Sets the st nm ppa projection master persistence.
	 *
	 * @param stNmPpaProjectionMasterPersistence the st nm ppa projection master persistence
	 */
	public void setStNmPpaProjectionMasterPersistence(
		StNmPpaProjectionMasterPersistence stNmPpaProjectionMasterPersistence) {
		this.stNmPpaProjectionMasterPersistence = stNmPpaProjectionMasterPersistence;
	}

	/**
	 * Returns the st selection table local service.
	 *
	 * @return the st selection table local service
	 */
	public com.stpl.app.service.StSelectionTableLocalService getStSelectionTableLocalService() {
		return stSelectionTableLocalService;
	}

	/**
	 * Sets the st selection table local service.
	 *
	 * @param stSelectionTableLocalService the st selection table local service
	 */
	public void setStSelectionTableLocalService(
		com.stpl.app.service.StSelectionTableLocalService stSelectionTableLocalService) {
		this.stSelectionTableLocalService = stSelectionTableLocalService;
	}

	/**
	 * Returns the st selection table persistence.
	 *
	 * @return the st selection table persistence
	 */
	public StSelectionTablePersistence getStSelectionTablePersistence() {
		return stSelectionTablePersistence;
	}

	/**
	 * Sets the st selection table persistence.
	 *
	 * @param stSelectionTablePersistence the st selection table persistence
	 */
	public void setStSelectionTablePersistence(
		StSelectionTablePersistence stSelectionTablePersistence) {
		this.stSelectionTablePersistence = stSelectionTablePersistence;
	}

	/**
	 * Returns the transaction module details local service.
	 *
	 * @return the transaction module details local service
	 */
	public com.stpl.app.service.TransactionModuleDetailsLocalService getTransactionModuleDetailsLocalService() {
		return transactionModuleDetailsLocalService;
	}

	/**
	 * Sets the transaction module details local service.
	 *
	 * @param transactionModuleDetailsLocalService the transaction module details local service
	 */
	public void setTransactionModuleDetailsLocalService(
		com.stpl.app.service.TransactionModuleDetailsLocalService transactionModuleDetailsLocalService) {
		this.transactionModuleDetailsLocalService = transactionModuleDetailsLocalService;
	}

	/**
	 * Returns the transaction module details persistence.
	 *
	 * @return the transaction module details persistence
	 */
	public TransactionModuleDetailsPersistence getTransactionModuleDetailsPersistence() {
		return transactionModuleDetailsPersistence;
	}

	/**
	 * Sets the transaction module details persistence.
	 *
	 * @param transactionModuleDetailsPersistence the transaction module details persistence
	 */
	public void setTransactionModuleDetailsPersistence(
		TransactionModuleDetailsPersistence transactionModuleDetailsPersistence) {
		this.transactionModuleDetailsPersistence = transactionModuleDetailsPersistence;
	}

	/**
	 * Returns the transaction module master local service.
	 *
	 * @return the transaction module master local service
	 */
	public com.stpl.app.service.TransactionModuleMasterLocalService getTransactionModuleMasterLocalService() {
		return transactionModuleMasterLocalService;
	}

	/**
	 * Sets the transaction module master local service.
	 *
	 * @param transactionModuleMasterLocalService the transaction module master local service
	 */
	public void setTransactionModuleMasterLocalService(
		com.stpl.app.service.TransactionModuleMasterLocalService transactionModuleMasterLocalService) {
		this.transactionModuleMasterLocalService = transactionModuleMasterLocalService;
	}

	/**
	 * Returns the transaction module master persistence.
	 *
	 * @return the transaction module master persistence
	 */
	public TransactionModuleMasterPersistence getTransactionModuleMasterPersistence() {
		return transactionModuleMasterPersistence;
	}

	/**
	 * Sets the transaction module master persistence.
	 *
	 * @param transactionModuleMasterPersistence the transaction module master persistence
	 */
	public void setTransactionModuleMasterPersistence(
		TransactionModuleMasterPersistence transactionModuleMasterPersistence) {
		this.transactionModuleMasterPersistence = transactionModuleMasterPersistence;
	}

	/**
	 * Returns the udcs local service.
	 *
	 * @return the udcs local service
	 */
	public com.stpl.app.service.UdcsLocalService getUdcsLocalService() {
		return udcsLocalService;
	}

	/**
	 * Sets the udcs local service.
	 *
	 * @param udcsLocalService the udcs local service
	 */
	public void setUdcsLocalService(
		com.stpl.app.service.UdcsLocalService udcsLocalService) {
		this.udcsLocalService = udcsLocalService;
	}

	/**
	 * Returns the udcs persistence.
	 *
	 * @return the udcs persistence
	 */
	public UdcsPersistence getUdcsPersistence() {
		return udcsPersistence;
	}

	/**
	 * Sets the udcs persistence.
	 *
	 * @param udcsPersistence the udcs persistence
	 */
	public void setUdcsPersistence(UdcsPersistence udcsPersistence) {
		this.udcsPersistence = udcsPersistence;
	}

	/**
	 * Returns the usergroup businessrole local service.
	 *
	 * @return the usergroup businessrole local service
	 */
	public com.stpl.app.service.UsergroupBusinessroleLocalService getUsergroupBusinessroleLocalService() {
		return usergroupBusinessroleLocalService;
	}

	/**
	 * Sets the usergroup businessrole local service.
	 *
	 * @param usergroupBusinessroleLocalService the usergroup businessrole local service
	 */
	public void setUsergroupBusinessroleLocalService(
		com.stpl.app.service.UsergroupBusinessroleLocalService usergroupBusinessroleLocalService) {
		this.usergroupBusinessroleLocalService = usergroupBusinessroleLocalService;
	}

	/**
	 * Returns the usergroup businessrole persistence.
	 *
	 * @return the usergroup businessrole persistence
	 */
	public UsergroupBusinessrolePersistence getUsergroupBusinessrolePersistence() {
		return usergroupBusinessrolePersistence;
	}

	/**
	 * Sets the usergroup businessrole persistence.
	 *
	 * @param usergroupBusinessrolePersistence the usergroup businessrole persistence
	 */
	public void setUsergroupBusinessrolePersistence(
		UsergroupBusinessrolePersistence usergroupBusinessrolePersistence) {
		this.usergroupBusinessrolePersistence = usergroupBusinessrolePersistence;
	}

	/**
	 * Returns the usergroup domain master local service.
	 *
	 * @return the usergroup domain master local service
	 */
	public com.stpl.app.service.UsergroupDomainMasterLocalService getUsergroupDomainMasterLocalService() {
		return usergroupDomainMasterLocalService;
	}

	/**
	 * Sets the usergroup domain master local service.
	 *
	 * @param usergroupDomainMasterLocalService the usergroup domain master local service
	 */
	public void setUsergroupDomainMasterLocalService(
		com.stpl.app.service.UsergroupDomainMasterLocalService usergroupDomainMasterLocalService) {
		this.usergroupDomainMasterLocalService = usergroupDomainMasterLocalService;
	}

	/**
	 * Returns the usergroup domain master persistence.
	 *
	 * @return the usergroup domain master persistence
	 */
	public UsergroupDomainMasterPersistence getUsergroupDomainMasterPersistence() {
		return usergroupDomainMasterPersistence;
	}

	/**
	 * Sets the usergroup domain master persistence.
	 *
	 * @param usergroupDomainMasterPersistence the usergroup domain master persistence
	 */
	public void setUsergroupDomainMasterPersistence(
		UsergroupDomainMasterPersistence usergroupDomainMasterPersistence) {
		this.usergroupDomainMasterPersistence = usergroupDomainMasterPersistence;
	}

	/**
	 * Returns the vw accrual master local service.
	 *
	 * @return the vw accrual master local service
	 */
	public com.stpl.app.service.VwAccrualMasterLocalService getVwAccrualMasterLocalService() {
		return vwAccrualMasterLocalService;
	}

	/**
	 * Sets the vw accrual master local service.
	 *
	 * @param vwAccrualMasterLocalService the vw accrual master local service
	 */
	public void setVwAccrualMasterLocalService(
		com.stpl.app.service.VwAccrualMasterLocalService vwAccrualMasterLocalService) {
		this.vwAccrualMasterLocalService = vwAccrualMasterLocalService;
	}

	/**
	 * Returns the vw accrual master persistence.
	 *
	 * @return the vw accrual master persistence
	 */
	public VwAccrualMasterPersistence getVwAccrualMasterPersistence() {
		return vwAccrualMasterPersistence;
	}

	/**
	 * Sets the vw accrual master persistence.
	 *
	 * @param vwAccrualMasterPersistence the vw accrual master persistence
	 */
	public void setVwAccrualMasterPersistence(
		VwAccrualMasterPersistence vwAccrualMasterPersistence) {
		this.vwAccrualMasterPersistence = vwAccrualMasterPersistence;
	}

	/**
	 * Returns the vw demand forecast actual local service.
	 *
	 * @return the vw demand forecast actual local service
	 */
	public com.stpl.app.service.VwDemandForecastActualLocalService getVwDemandForecastActualLocalService() {
		return vwDemandForecastActualLocalService;
	}

	/**
	 * Sets the vw demand forecast actual local service.
	 *
	 * @param vwDemandForecastActualLocalService the vw demand forecast actual local service
	 */
	public void setVwDemandForecastActualLocalService(
		com.stpl.app.service.VwDemandForecastActualLocalService vwDemandForecastActualLocalService) {
		this.vwDemandForecastActualLocalService = vwDemandForecastActualLocalService;
	}

	/**
	 * Returns the vw demand forecast actual persistence.
	 *
	 * @return the vw demand forecast actual persistence
	 */
	public VwDemandForecastActualPersistence getVwDemandForecastActualPersistence() {
		return vwDemandForecastActualPersistence;
	}

	/**
	 * Sets the vw demand forecast actual persistence.
	 *
	 * @param vwDemandForecastActualPersistence the vw demand forecast actual persistence
	 */
	public void setVwDemandForecastActualPersistence(
		VwDemandForecastActualPersistence vwDemandForecastActualPersistence) {
		this.vwDemandForecastActualPersistence = vwDemandForecastActualPersistence;
	}

	/**
	 * Returns the vw inventory wd actual proj mas local service.
	 *
	 * @return the vw inventory wd actual proj mas local service
	 */
	public com.stpl.app.service.VwInventoryWdActualProjMasLocalService getVwInventoryWdActualProjMasLocalService() {
		return vwInventoryWdActualProjMasLocalService;
	}

	/**
	 * Sets the vw inventory wd actual proj mas local service.
	 *
	 * @param vwInventoryWdActualProjMasLocalService the vw inventory wd actual proj mas local service
	 */
	public void setVwInventoryWdActualProjMasLocalService(
		com.stpl.app.service.VwInventoryWdActualProjMasLocalService vwInventoryWdActualProjMasLocalService) {
		this.vwInventoryWdActualProjMasLocalService = vwInventoryWdActualProjMasLocalService;
	}

	/**
	 * Returns the vw inventory wd actual proj mas persistence.
	 *
	 * @return the vw inventory wd actual proj mas persistence
	 */
	public VwInventoryWdActualProjMasPersistence getVwInventoryWdActualProjMasPersistence() {
		return vwInventoryWdActualProjMasPersistence;
	}

	/**
	 * Sets the vw inventory wd actual proj mas persistence.
	 *
	 * @param vwInventoryWdActualProjMasPersistence the vw inventory wd actual proj mas persistence
	 */
	public void setVwInventoryWdActualProjMasPersistence(
		VwInventoryWdActualProjMasPersistence vwInventoryWdActualProjMasPersistence) {
		this.vwInventoryWdActualProjMasPersistence = vwInventoryWdActualProjMasPersistence;
	}

	/**
	 * Returns the vw ivld demand forecast actual local service.
	 *
	 * @return the vw ivld demand forecast actual local service
	 */
	public com.stpl.app.service.VwIvldDemandForecastActualLocalService getVwIvldDemandForecastActualLocalService() {
		return vwIvldDemandForecastActualLocalService;
	}

	/**
	 * Sets the vw ivld demand forecast actual local service.
	 *
	 * @param vwIvldDemandForecastActualLocalService the vw ivld demand forecast actual local service
	 */
	public void setVwIvldDemandForecastActualLocalService(
		com.stpl.app.service.VwIvldDemandForecastActualLocalService vwIvldDemandForecastActualLocalService) {
		this.vwIvldDemandForecastActualLocalService = vwIvldDemandForecastActualLocalService;
	}

	/**
	 * Returns the vw ivld demand forecast actual persistence.
	 *
	 * @return the vw ivld demand forecast actual persistence
	 */
	public VwIvldDemandForecastActualPersistence getVwIvldDemandForecastActualPersistence() {
		return vwIvldDemandForecastActualPersistence;
	}

	/**
	 * Sets the vw ivld demand forecast actual persistence.
	 *
	 * @param vwIvldDemandForecastActualPersistence the vw ivld demand forecast actual persistence
	 */
	public void setVwIvldDemandForecastActualPersistence(
		VwIvldDemandForecastActualPersistence vwIvldDemandForecastActualPersistence) {
		this.vwIvldDemandForecastActualPersistence = vwIvldDemandForecastActualPersistence;
	}

	/**
	 * Returns the vw ivld inventory wd actual proj mas local service.
	 *
	 * @return the vw ivld inventory wd actual proj mas local service
	 */
	public com.stpl.app.service.VwIvldInventoryWdActualProjMasLocalService getVwIvldInventoryWdActualProjMasLocalService() {
		return vwIvldInventoryWdActualProjMasLocalService;
	}

	/**
	 * Sets the vw ivld inventory wd actual proj mas local service.
	 *
	 * @param vwIvldInventoryWdActualProjMasLocalService the vw ivld inventory wd actual proj mas local service
	 */
	public void setVwIvldInventoryWdActualProjMasLocalService(
		com.stpl.app.service.VwIvldInventoryWdActualProjMasLocalService vwIvldInventoryWdActualProjMasLocalService) {
		this.vwIvldInventoryWdActualProjMasLocalService = vwIvldInventoryWdActualProjMasLocalService;
	}

	/**
	 * Returns the vw ivld inventory wd actual proj mas persistence.
	 *
	 * @return the vw ivld inventory wd actual proj mas persistence
	 */
	public VwIvldInventoryWdActualProjMasPersistence getVwIvldInventoryWdActualProjMasPersistence() {
		return vwIvldInventoryWdActualProjMasPersistence;
	}

	/**
	 * Sets the vw ivld inventory wd actual proj mas persistence.
	 *
	 * @param vwIvldInventoryWdActualProjMasPersistence the vw ivld inventory wd actual proj mas persistence
	 */
	public void setVwIvldInventoryWdActualProjMasPersistence(
		VwIvldInventoryWdActualProjMasPersistence vwIvldInventoryWdActualProjMasPersistence) {
		this.vwIvldInventoryWdActualProjMasPersistence = vwIvldInventoryWdActualProjMasPersistence;
	}

	/**
	 * Returns the vw user tables local service.
	 *
	 * @return the vw user tables local service
	 */
	public com.stpl.app.service.VwUserTablesLocalService getVwUserTablesLocalService() {
		return vwUserTablesLocalService;
	}

	/**
	 * Sets the vw user tables local service.
	 *
	 * @param vwUserTablesLocalService the vw user tables local service
	 */
	public void setVwUserTablesLocalService(
		com.stpl.app.service.VwUserTablesLocalService vwUserTablesLocalService) {
		this.vwUserTablesLocalService = vwUserTablesLocalService;
	}

	/**
	 * Returns the vw user tables persistence.
	 *
	 * @return the vw user tables persistence
	 */
	public VwUserTablesPersistence getVwUserTablesPersistence() {
		return vwUserTablesPersistence;
	}

	/**
	 * Sets the vw user tables persistence.
	 *
	 * @param vwUserTablesPersistence the vw user tables persistence
	 */
	public void setVwUserTablesPersistence(
		VwUserTablesPersistence vwUserTablesPersistence) {
		this.vwUserTablesPersistence = vwUserTablesPersistence;
	}

	/**
	 * Returns the wf mail config local service.
	 *
	 * @return the wf mail config local service
	 */
	public com.stpl.app.service.WfMailConfigLocalService getWfMailConfigLocalService() {
		return wfMailConfigLocalService;
	}

	/**
	 * Sets the wf mail config local service.
	 *
	 * @param wfMailConfigLocalService the wf mail config local service
	 */
	public void setWfMailConfigLocalService(
		com.stpl.app.service.WfMailConfigLocalService wfMailConfigLocalService) {
		this.wfMailConfigLocalService = wfMailConfigLocalService;
	}

	/**
	 * Returns the wf mail config persistence.
	 *
	 * @return the wf mail config persistence
	 */
	public WfMailConfigPersistence getWfMailConfigPersistence() {
		return wfMailConfigPersistence;
	}

	/**
	 * Sets the wf mail config persistence.
	 *
	 * @param wfMailConfigPersistence the wf mail config persistence
	 */
	public void setWfMailConfigPersistence(
		WfMailConfigPersistence wfMailConfigPersistence) {
		this.wfMailConfigPersistence = wfMailConfigPersistence;
	}

	/**
	 * Returns the workflow master local service.
	 *
	 * @return the workflow master local service
	 */
	public com.stpl.app.service.WorkflowMasterLocalService getWorkflowMasterLocalService() {
		return workflowMasterLocalService;
	}

	/**
	 * Sets the workflow master local service.
	 *
	 * @param workflowMasterLocalService the workflow master local service
	 */
	public void setWorkflowMasterLocalService(
		com.stpl.app.service.WorkflowMasterLocalService workflowMasterLocalService) {
		this.workflowMasterLocalService = workflowMasterLocalService;
	}

	/**
	 * Returns the workflow master persistence.
	 *
	 * @return the workflow master persistence
	 */
	public WorkflowMasterPersistence getWorkflowMasterPersistence() {
		return workflowMasterPersistence;
	}

	/**
	 * Sets the workflow master persistence.
	 *
	 * @param workflowMasterPersistence the workflow master persistence
	 */
	public void setWorkflowMasterPersistence(
		WorkflowMasterPersistence workflowMasterPersistence) {
		this.workflowMasterPersistence = workflowMasterPersistence;
	}

	/**
	 * Returns the workflow process info local service.
	 *
	 * @return the workflow process info local service
	 */
	public com.stpl.app.service.WorkflowProcessInfoLocalService getWorkflowProcessInfoLocalService() {
		return workflowProcessInfoLocalService;
	}

	/**
	 * Sets the workflow process info local service.
	 *
	 * @param workflowProcessInfoLocalService the workflow process info local service
	 */
	public void setWorkflowProcessInfoLocalService(
		com.stpl.app.service.WorkflowProcessInfoLocalService workflowProcessInfoLocalService) {
		this.workflowProcessInfoLocalService = workflowProcessInfoLocalService;
	}

	/**
	 * Returns the workflow process info persistence.
	 *
	 * @return the workflow process info persistence
	 */
	public WorkflowProcessInfoPersistence getWorkflowProcessInfoPersistence() {
		return workflowProcessInfoPersistence;
	}

	/**
	 * Sets the workflow process info persistence.
	 *
	 * @param workflowProcessInfoPersistence the workflow process info persistence
	 */
	public void setWorkflowProcessInfoPersistence(
		WorkflowProcessInfoPersistence workflowProcessInfoPersistence) {
		this.workflowProcessInfoPersistence = workflowProcessInfoPersistence;
	}

	/**
	 * Returns the workflow profile local service.
	 *
	 * @return the workflow profile local service
	 */
	public com.stpl.app.service.WorkflowProfileLocalService getWorkflowProfileLocalService() {
		return workflowProfileLocalService;
	}

	/**
	 * Sets the workflow profile local service.
	 *
	 * @param workflowProfileLocalService the workflow profile local service
	 */
	public void setWorkflowProfileLocalService(
		com.stpl.app.service.WorkflowProfileLocalService workflowProfileLocalService) {
		this.workflowProfileLocalService = workflowProfileLocalService;
	}

	/**
	 * Returns the workflow profile persistence.
	 *
	 * @return the workflow profile persistence
	 */
	public WorkflowProfilePersistence getWorkflowProfilePersistence() {
		return workflowProfilePersistence;
	}

	/**
	 * Sets the workflow profile persistence.
	 *
	 * @param workflowProfilePersistence the workflow profile persistence
	 */
	public void setWorkflowProfilePersistence(
		WorkflowProfilePersistence workflowProfilePersistence) {
		this.workflowProfilePersistence = workflowProfilePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.stpl.app.model.IfpContract",
			ifpContractLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.stpl.app.model.IfpContract");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return IfpContractLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return IfpContract.class;
	}

	protected String getModelClassName() {
		return IfpContract.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ifpContractPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.stpl.app.service.AccrualDetailsLocalService.class)
	protected com.stpl.app.service.AccrualDetailsLocalService accrualDetailsLocalService;
	@BeanReference(type = AccrualDetailsPersistence.class)
	protected AccrualDetailsPersistence accrualDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.AccrualMasterLocalService.class)
	protected com.stpl.app.service.AccrualMasterLocalService accrualMasterLocalService;
	@BeanReference(type = AccrualMasterPersistence.class)
	protected AccrualMasterPersistence accrualMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ActualsMasterLocalService.class)
	protected com.stpl.app.service.ActualsMasterLocalService actualsMasterLocalService;
	@BeanReference(type = ActualsMasterPersistence.class)
	protected ActualsMasterPersistence actualsMasterPersistence;
	@BeanReference(type = com.stpl.app.service.AdditionalNotesLocalService.class)
	protected com.stpl.app.service.AdditionalNotesLocalService additionalNotesLocalService;
	@BeanReference(type = AdditionalNotesPersistence.class)
	protected AdditionalNotesPersistence additionalNotesPersistence;
	@BeanReference(type = com.stpl.app.service.AuditMasterInboundLocalService.class)
	protected com.stpl.app.service.AuditMasterInboundLocalService auditMasterInboundLocalService;
	@BeanReference(type = AuditMasterInboundPersistence.class)
	protected AuditMasterInboundPersistence auditMasterInboundPersistence;
	@BeanReference(type = com.stpl.app.service.AverageShelfLifeMasterLocalService.class)
	protected com.stpl.app.service.AverageShelfLifeMasterLocalService averageShelfLifeMasterLocalService;
	@BeanReference(type = AverageShelfLifeMasterPersistence.class)
	protected AverageShelfLifeMasterPersistence averageShelfLifeMasterPersistence;
	@BeanReference(type = com.stpl.app.service.BestPriceMasterLocalService.class)
	protected com.stpl.app.service.BestPriceMasterLocalService bestPriceMasterLocalService;
	@BeanReference(type = BestPriceMasterPersistence.class)
	protected BestPriceMasterPersistence bestPriceMasterPersistence;
	@BeanReference(type = com.stpl.app.service.BrandMasterLocalService.class)
	protected com.stpl.app.service.BrandMasterLocalService brandMasterLocalService;
	@BeanReference(type = BrandMasterPersistence.class)
	protected BrandMasterPersistence brandMasterPersistence;
	@BeanReference(type = com.stpl.app.service.BusinessroleMasterLocalService.class)
	protected com.stpl.app.service.BusinessroleMasterLocalService businessroleMasterLocalService;
	@BeanReference(type = BusinessroleMasterPersistence.class)
	protected BusinessroleMasterPersistence businessroleMasterPersistence;
	@BeanReference(type = com.stpl.app.service.BusinessroleModuleLocalService.class)
	protected com.stpl.app.service.BusinessroleModuleLocalService businessroleModuleLocalService;
	@BeanReference(type = BusinessroleModulePersistence.class)
	protected BusinessroleModulePersistence businessroleModulePersistence;
	@BeanReference(type = com.stpl.app.service.CcpDetailsLocalService.class)
	protected com.stpl.app.service.CcpDetailsLocalService ccpDetailsLocalService;
	@BeanReference(type = CcpDetailsPersistence.class)
	protected CcpDetailsPersistence ccpDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.CcpMapLocalService.class)
	protected com.stpl.app.service.CcpMapLocalService ccpMapLocalService;
	@BeanReference(type = CcpMapPersistence.class)
	protected CcpMapPersistence ccpMapPersistence;
	@BeanReference(type = com.stpl.app.service.CdrDetailsLocalService.class)
	protected com.stpl.app.service.CdrDetailsLocalService cdrDetailsLocalService;
	@BeanReference(type = CdrDetailsPersistence.class)
	protected CdrDetailsPersistence cdrDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.CdrModelLocalService.class)
	protected com.stpl.app.service.CdrModelLocalService cdrModelLocalService;
	@BeanReference(type = CdrModelPersistence.class)
	protected CdrModelPersistence cdrModelPersistence;
	@BeanReference(type = com.stpl.app.service.CfpContractLocalService.class)
	protected com.stpl.app.service.CfpContractLocalService cfpContractLocalService;
	@BeanReference(type = CfpContractPersistence.class)
	protected CfpContractPersistence cfpContractPersistence;
	@BeanReference(type = com.stpl.app.service.CfpContractDetailsLocalService.class)
	protected com.stpl.app.service.CfpContractDetailsLocalService cfpContractDetailsLocalService;
	@BeanReference(type = CfpContractDetailsPersistence.class)
	protected CfpContractDetailsPersistence cfpContractDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.CfpDetailsLocalService.class)
	protected com.stpl.app.service.CfpDetailsLocalService cfpDetailsLocalService;
	@BeanReference(type = CfpDetailsPersistence.class)
	protected CfpDetailsPersistence cfpDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.CfpModelLocalService.class)
	protected com.stpl.app.service.CfpModelLocalService cfpModelLocalService;
	@BeanReference(type = CfpModelPersistence.class)
	protected CfpModelPersistence cfpModelPersistence;
	@BeanReference(type = com.stpl.app.service.ChActualDiscountLocalService.class)
	protected com.stpl.app.service.ChActualDiscountLocalService chActualDiscountLocalService;
	@BeanReference(type = ChActualDiscountPersistence.class)
	protected ChActualDiscountPersistence chActualDiscountPersistence;
	@BeanReference(type = com.stpl.app.service.ChActualSalesLocalService.class)
	protected com.stpl.app.service.ChActualSalesLocalService chActualSalesLocalService;
	@BeanReference(type = ChActualSalesPersistence.class)
	protected ChActualSalesPersistence chActualSalesPersistence;
	@BeanReference(type = com.stpl.app.service.ChAssumptionsLocalService.class)
	protected com.stpl.app.service.ChAssumptionsLocalService chAssumptionsLocalService;
	@BeanReference(type = ChAssumptionsPersistence.class)
	protected ChAssumptionsPersistence chAssumptionsPersistence;
	@BeanReference(type = com.stpl.app.service.ChDiscountProjMasterLocalService.class)
	protected com.stpl.app.service.ChDiscountProjMasterLocalService chDiscountProjMasterLocalService;
	@BeanReference(type = ChDiscountProjMasterPersistence.class)
	protected ChDiscountProjMasterPersistence chDiscountProjMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ChProjectionDiscountLocalService.class)
	protected com.stpl.app.service.ChProjectionDiscountLocalService chProjectionDiscountLocalService;
	@BeanReference(type = ChProjectionDiscountPersistence.class)
	protected ChProjectionDiscountPersistence chProjectionDiscountPersistence;
	@BeanReference(type = com.stpl.app.service.ChProjectionSelectionLocalService.class)
	protected com.stpl.app.service.ChProjectionSelectionLocalService chProjectionSelectionLocalService;
	@BeanReference(type = ChProjectionSelectionPersistence.class)
	protected ChProjectionSelectionPersistence chProjectionSelectionPersistence;
	@BeanReference(type = com.stpl.app.service.ChSalesProjectionLocalService.class)
	protected com.stpl.app.service.ChSalesProjectionLocalService chSalesProjectionLocalService;
	@BeanReference(type = ChSalesProjectionPersistence.class)
	protected ChSalesProjectionPersistence chSalesProjectionPersistence;
	@BeanReference(type = com.stpl.app.service.ChSalesProjectionMasterLocalService.class)
	protected com.stpl.app.service.ChSalesProjectionMasterLocalService chSalesProjectionMasterLocalService;
	@BeanReference(type = ChSalesProjectionMasterPersistence.class)
	protected ChSalesProjectionMasterPersistence chSalesProjectionMasterPersistence;
	@BeanReference(type = com.stpl.app.service.CompanyGroupLocalService.class)
	protected com.stpl.app.service.CompanyGroupLocalService companyGroupLocalService;
	@BeanReference(type = CompanyGroupPersistence.class)
	protected CompanyGroupPersistence companyGroupPersistence;
	@BeanReference(type = com.stpl.app.service.CompanyGroupDetailsLocalService.class)
	protected com.stpl.app.service.CompanyGroupDetailsLocalService companyGroupDetailsLocalService;
	@BeanReference(type = CompanyGroupDetailsPersistence.class)
	protected CompanyGroupDetailsPersistence companyGroupDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.CompanyIdentifierLocalService.class)
	protected com.stpl.app.service.CompanyIdentifierLocalService companyIdentifierLocalService;
	@BeanReference(type = CompanyIdentifierPersistence.class)
	protected CompanyIdentifierPersistence companyIdentifierPersistence;
	@BeanReference(type = com.stpl.app.service.CompanyMasterLocalService.class)
	protected com.stpl.app.service.CompanyMasterLocalService companyMasterLocalService;
	@BeanReference(type = CompanyMasterPersistence.class)
	protected CompanyMasterPersistence companyMasterPersistence;
	@BeanReference(type = com.stpl.app.service.CompanyParentDetailsLocalService.class)
	protected com.stpl.app.service.CompanyParentDetailsLocalService companyParentDetailsLocalService;
	@BeanReference(type = CompanyParentDetailsPersistence.class)
	protected CompanyParentDetailsPersistence companyParentDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.CompanyQualifierLocalService.class)
	protected com.stpl.app.service.CompanyQualifierLocalService companyQualifierLocalService;
	@BeanReference(type = CompanyQualifierPersistence.class)
	protected CompanyQualifierPersistence companyQualifierPersistence;
	@BeanReference(type = com.stpl.app.service.CompanyTradeClassLocalService.class)
	protected com.stpl.app.service.CompanyTradeClassLocalService companyTradeClassLocalService;
	@BeanReference(type = CompanyTradeClassPersistence.class)
	protected CompanyTradeClassPersistence companyTradeClassPersistence;
	@BeanReference(type = com.stpl.app.service.ContractAliasMasterLocalService.class)
	protected com.stpl.app.service.ContractAliasMasterLocalService contractAliasMasterLocalService;
	@BeanReference(type = ContractAliasMasterPersistence.class)
	protected ContractAliasMasterPersistence contractAliasMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ContractMasterLocalService.class)
	protected com.stpl.app.service.ContractMasterLocalService contractMasterLocalService;
	@BeanReference(type = ContractMasterPersistence.class)
	protected ContractMasterPersistence contractMasterPersistence;
	@BeanReference(type = com.stpl.app.service.CpiIndexMasterLocalService.class)
	protected com.stpl.app.service.CpiIndexMasterLocalService cpiIndexMasterLocalService;
	@BeanReference(type = CpiIndexMasterPersistence.class)
	protected CpiIndexMasterPersistence cpiIndexMasterPersistence;
	@BeanReference(type = com.stpl.app.service.CustomViewDetailsLocalService.class)
	protected com.stpl.app.service.CustomViewDetailsLocalService customViewDetailsLocalService;
	@BeanReference(type = CustomViewDetailsPersistence.class)
	protected CustomViewDetailsPersistence customViewDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.CustomViewMasterLocalService.class)
	protected com.stpl.app.service.CustomViewMasterLocalService customViewMasterLocalService;
	@BeanReference(type = CustomViewMasterPersistence.class)
	protected CustomViewMasterPersistence customViewMasterPersistence;
	@BeanReference(type = com.stpl.app.service.DeductionCalendarDetailsLocalService.class)
	protected com.stpl.app.service.DeductionCalendarDetailsLocalService deductionCalendarDetailsLocalService;
	@BeanReference(type = DeductionCalendarDetailsPersistence.class)
	protected DeductionCalendarDetailsPersistence deductionCalendarDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.DeductionCalendarMasterLocalService.class)
	protected com.stpl.app.service.DeductionCalendarMasterLocalService deductionCalendarMasterLocalService;
	@BeanReference(type = DeductionCalendarMasterPersistence.class)
	protected DeductionCalendarMasterPersistence deductionCalendarMasterPersistence;
	@BeanReference(type = com.stpl.app.service.DeductionDetailsLocalService.class)
	protected com.stpl.app.service.DeductionDetailsLocalService deductionDetailsLocalService;
	@BeanReference(type = DeductionDetailsPersistence.class)
	protected DeductionDetailsPersistence deductionDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.DeductionGroupLocalService.class)
	protected com.stpl.app.service.DeductionGroupLocalService deductionGroupLocalService;
	@BeanReference(type = DeductionGroupPersistence.class)
	protected DeductionGroupPersistence deductionGroupPersistence;
	@BeanReference(type = com.stpl.app.service.DeductionGroupDetailsLocalService.class)
	protected com.stpl.app.service.DeductionGroupDetailsLocalService deductionGroupDetailsLocalService;
	@BeanReference(type = DeductionGroupDetailsPersistence.class)
	protected DeductionGroupDetailsPersistence deductionGroupDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.DemandForecastLocalService.class)
	protected com.stpl.app.service.DemandForecastLocalService demandForecastLocalService;
	@BeanReference(type = DemandForecastPersistence.class)
	protected DemandForecastPersistence demandForecastPersistence;
	@BeanReference(type = com.stpl.app.service.DocDetailsLocalService.class)
	protected com.stpl.app.service.DocDetailsLocalService docDetailsLocalService;
	@BeanReference(type = DocDetailsPersistence.class)
	protected DocDetailsPersistence docDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.FcpActualsLocalService.class)
	protected com.stpl.app.service.FcpActualsLocalService fcpActualsLocalService;
	@BeanReference(type = FcpActualsPersistence.class)
	protected FcpActualsPersistence fcpActualsPersistence;
	@BeanReference(type = com.stpl.app.service.FcpProjLocalService.class)
	protected com.stpl.app.service.FcpProjLocalService fcpProjLocalService;
	@BeanReference(type = FcpProjPersistence.class)
	protected FcpProjPersistence fcpProjPersistence;
	@BeanReference(type = com.stpl.app.service.FederalNewNdcLocalService.class)
	protected com.stpl.app.service.FederalNewNdcLocalService federalNewNdcLocalService;
	@BeanReference(type = FederalNewNdcPersistence.class)
	protected FederalNewNdcPersistence federalNewNdcPersistence;
	@BeanReference(type = com.stpl.app.service.FileManagementLocalService.class)
	protected com.stpl.app.service.FileManagementLocalService fileManagementLocalService;
	@BeanReference(type = FileManagementPersistence.class)
	protected FileManagementPersistence fileManagementPersistence;
	@BeanReference(type = com.stpl.app.service.ForecastConfigLocalService.class)
	protected com.stpl.app.service.ForecastConfigLocalService forecastConfigLocalService;
	@BeanReference(type = ForecastConfigPersistence.class)
	protected ForecastConfigPersistence forecastConfigPersistence;
	@BeanReference(type = com.stpl.app.service.ForecastingFormulaLocalService.class)
	protected com.stpl.app.service.ForecastingFormulaLocalService forecastingFormulaLocalService;
	@BeanReference(type = ForecastingFormulaPersistence.class)
	protected ForecastingFormulaPersistence forecastingFormulaPersistence;
	@BeanReference(type = com.stpl.app.service.ForecastingMasterLocalService.class)
	protected com.stpl.app.service.ForecastingMasterLocalService forecastingMasterLocalService;
	@BeanReference(type = ForecastingMasterPersistence.class)
	protected ForecastingMasterPersistence forecastingMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ForecastingViewMasterLocalService.class)
	protected com.stpl.app.service.ForecastingViewMasterLocalService forecastingViewMasterLocalService;
	@BeanReference(type = ForecastingViewMasterPersistence.class)
	protected ForecastingViewMasterPersistence forecastingViewMasterPersistence;
	@BeanReference(type = com.stpl.app.service.FormulaDetailsMasterLocalService.class)
	protected com.stpl.app.service.FormulaDetailsMasterLocalService formulaDetailsMasterLocalService;
	@BeanReference(type = FormulaDetailsMasterPersistence.class)
	protected FormulaDetailsMasterPersistence formulaDetailsMasterPersistence;
	@BeanReference(type = com.stpl.app.service.GcmCompanyDetailsLocalService.class)
	protected com.stpl.app.service.GcmCompanyDetailsLocalService gcmCompanyDetailsLocalService;
	@BeanReference(type = GcmCompanyDetailsPersistence.class)
	protected GcmCompanyDetailsPersistence gcmCompanyDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.GcmCompanyLinkLocalService.class)
	protected com.stpl.app.service.GcmCompanyLinkLocalService gcmCompanyLinkLocalService;
	@BeanReference(type = GcmCompanyLinkPersistence.class)
	protected GcmCompanyLinkPersistence gcmCompanyLinkPersistence;
	@BeanReference(type = com.stpl.app.service.GcmContractDetailsLocalService.class)
	protected com.stpl.app.service.GcmContractDetailsLocalService gcmContractDetailsLocalService;
	@BeanReference(type = GcmContractDetailsPersistence.class)
	protected GcmContractDetailsPersistence gcmContractDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.GcmGlobalDetailsLocalService.class)
	protected com.stpl.app.service.GcmGlobalDetailsLocalService gcmGlobalDetailsLocalService;
	@BeanReference(type = GcmGlobalDetailsPersistence.class)
	protected GcmGlobalDetailsPersistence gcmGlobalDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.GcmItemDetailsLocalService.class)
	protected com.stpl.app.service.GcmItemDetailsLocalService gcmItemDetailsLocalService;
	@BeanReference(type = GcmItemDetailsPersistence.class)
	protected GcmItemDetailsPersistence gcmItemDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.GlBalanceMasterLocalService.class)
	protected com.stpl.app.service.GlBalanceMasterLocalService glBalanceMasterLocalService;
	@BeanReference(type = GlBalanceMasterPersistence.class)
	protected GlBalanceMasterPersistence glBalanceMasterPersistence;
	@BeanReference(type = com.stpl.app.service.GlCostCenterMasterLocalService.class)
	protected com.stpl.app.service.GlCostCenterMasterLocalService glCostCenterMasterLocalService;
	@BeanReference(type = GlCostCenterMasterPersistence.class)
	protected GlCostCenterMasterPersistence glCostCenterMasterPersistence;
	@BeanReference(type = com.stpl.app.service.HelperTableLocalService.class)
	protected com.stpl.app.service.HelperTableLocalService helperTableLocalService;
	@BeanReference(type = HelperTablePersistence.class)
	protected HelperTablePersistence helperTablePersistence;
	@BeanReference(type = HelperTableFinder.class)
	protected HelperTableFinder helperTableFinder;
	@BeanReference(type = com.stpl.app.service.HierarchyDefinitionLocalService.class)
	protected com.stpl.app.service.HierarchyDefinitionLocalService hierarchyDefinitionLocalService;
	@BeanReference(type = HierarchyDefinitionPersistence.class)
	protected HierarchyDefinitionPersistence hierarchyDefinitionPersistence;
	@BeanReference(type = com.stpl.app.service.HierarchyLevelDefinitionLocalService.class)
	protected com.stpl.app.service.HierarchyLevelDefinitionLocalService hierarchyLevelDefinitionLocalService;
	@BeanReference(type = HierarchyLevelDefinitionPersistence.class)
	protected HierarchyLevelDefinitionPersistence hierarchyLevelDefinitionPersistence;
	@BeanReference(type = com.stpl.app.service.HierarchyLevelValuesLocalService.class)
	protected com.stpl.app.service.HierarchyLevelValuesLocalService hierarchyLevelValuesLocalService;
	@BeanReference(type = HierarchyLevelValuesPersistence.class)
	protected HierarchyLevelValuesPersistence hierarchyLevelValuesPersistence;
	@BeanReference(type = com.stpl.app.service.HistCompanyGroupLocalService.class)
	protected com.stpl.app.service.HistCompanyGroupLocalService histCompanyGroupLocalService;
	@BeanReference(type = HistCompanyGroupPersistence.class)
	protected HistCompanyGroupPersistence histCompanyGroupPersistence;
	@BeanReference(type = com.stpl.app.service.HistCompanyGroupDetailsLocalService.class)
	protected com.stpl.app.service.HistCompanyGroupDetailsLocalService histCompanyGroupDetailsLocalService;
	@BeanReference(type = HistCompanyGroupDetailsPersistence.class)
	protected HistCompanyGroupDetailsPersistence histCompanyGroupDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.HistHierarchyDefinitionLocalService.class)
	protected com.stpl.app.service.HistHierarchyDefinitionLocalService histHierarchyDefinitionLocalService;
	@BeanReference(type = HistHierarchyDefinitionPersistence.class)
	protected HistHierarchyDefinitionPersistence histHierarchyDefinitionPersistence;
	@BeanReference(type = com.stpl.app.service.HistHierarchyLevelDefnLocalService.class)
	protected com.stpl.app.service.HistHierarchyLevelDefnLocalService histHierarchyLevelDefnLocalService;
	@BeanReference(type = HistHierarchyLevelDefnPersistence.class)
	protected HistHierarchyLevelDefnPersistence histHierarchyLevelDefnPersistence;
	@BeanReference(type = com.stpl.app.service.HistHierarchyLevelValuesLocalService.class)
	protected com.stpl.app.service.HistHierarchyLevelValuesLocalService histHierarchyLevelValuesLocalService;
	@BeanReference(type = HistHierarchyLevelValuesPersistence.class)
	protected HistHierarchyLevelValuesPersistence histHierarchyLevelValuesPersistence;
	@BeanReference(type = com.stpl.app.service.HistItemGroupLocalService.class)
	protected com.stpl.app.service.HistItemGroupLocalService histItemGroupLocalService;
	@BeanReference(type = HistItemGroupPersistence.class)
	protected HistItemGroupPersistence histItemGroupPersistence;
	@BeanReference(type = com.stpl.app.service.HistItemGroupDetailsLocalService.class)
	protected com.stpl.app.service.HistItemGroupDetailsLocalService histItemGroupDetailsLocalService;
	@BeanReference(type = HistItemGroupDetailsPersistence.class)
	protected HistItemGroupDetailsPersistence histItemGroupDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.HistRelationshipBuilderLocalService.class)
	protected com.stpl.app.service.HistRelationshipBuilderLocalService histRelationshipBuilderLocalService;
	@BeanReference(type = HistRelationshipBuilderPersistence.class)
	protected HistRelationshipBuilderPersistence histRelationshipBuilderPersistence;
	@BeanReference(type = com.stpl.app.service.HistRelationshipLevelDefnLocalService.class)
	protected com.stpl.app.service.HistRelationshipLevelDefnLocalService histRelationshipLevelDefnLocalService;
	@BeanReference(type = HistRelationshipLevelDefnPersistence.class)
	protected HistRelationshipLevelDefnPersistence histRelationshipLevelDefnPersistence;
	@BeanReference(type = com.stpl.app.service.HistWorkflowMasterLocalService.class)
	protected com.stpl.app.service.HistWorkflowMasterLocalService histWorkflowMasterLocalService;
	@BeanReference(type = HistWorkflowMasterPersistence.class)
	protected HistWorkflowMasterPersistence histWorkflowMasterPersistence;
	@BeanReference(type = IfpContractLocalService.class)
	protected IfpContractLocalService ifpContractLocalService;
	@BeanReference(type = IfpContractPersistence.class)
	protected IfpContractPersistence ifpContractPersistence;
	@BeanReference(type = com.stpl.app.service.IfpContractDetailsLocalService.class)
	protected com.stpl.app.service.IfpContractDetailsLocalService ifpContractDetailsLocalService;
	@BeanReference(type = IfpContractDetailsPersistence.class)
	protected IfpContractDetailsPersistence ifpContractDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.IfpDetailsLocalService.class)
	protected com.stpl.app.service.IfpDetailsLocalService ifpDetailsLocalService;
	@BeanReference(type = IfpDetailsPersistence.class)
	protected IfpDetailsPersistence ifpDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.IfpModelLocalService.class)
	protected com.stpl.app.service.IfpModelLocalService ifpModelLocalService;
	@BeanReference(type = IfpModelPersistence.class)
	protected IfpModelPersistence ifpModelPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdCfpDetailsLocalService.class)
	protected com.stpl.app.service.ImtdCfpDetailsLocalService imtdCfpDetailsLocalService;
	@BeanReference(type = ImtdCfpDetailsPersistence.class)
	protected ImtdCfpDetailsPersistence imtdCfpDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdDeductionDetailsLocalService.class)
	protected com.stpl.app.service.ImtdDeductionDetailsLocalService imtdDeductionDetailsLocalService;
	@BeanReference(type = ImtdDeductionDetailsPersistence.class)
	protected ImtdDeductionDetailsPersistence imtdDeductionDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdIfpDetailsLocalService.class)
	protected com.stpl.app.service.ImtdIfpDetailsLocalService imtdIfpDetailsLocalService;
	@BeanReference(type = ImtdIfpDetailsPersistence.class)
	protected ImtdIfpDetailsPersistence imtdIfpDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdItemPriceRebateDetailsLocalService.class)
	protected com.stpl.app.service.ImtdItemPriceRebateDetailsLocalService imtdItemPriceRebateDetailsLocalService;
	@BeanReference(type = ImtdItemPriceRebateDetailsPersistence.class)
	protected ImtdItemPriceRebateDetailsPersistence imtdItemPriceRebateDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdLevelValuesLocalService.class)
	protected com.stpl.app.service.ImtdLevelValuesLocalService imtdLevelValuesLocalService;
	@BeanReference(type = ImtdLevelValuesPersistence.class)
	protected ImtdLevelValuesPersistence imtdLevelValuesPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdPsDetailsLocalService.class)
	protected com.stpl.app.service.ImtdPsDetailsLocalService imtdPsDetailsLocalService;
	@BeanReference(type = ImtdPsDetailsPersistence.class)
	protected ImtdPsDetailsPersistence imtdPsDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdRsContractDetailsFrLocalService.class)
	protected com.stpl.app.service.ImtdRsContractDetailsFrLocalService imtdRsContractDetailsFrLocalService;
	@BeanReference(type = ImtdRsContractDetailsFrPersistence.class)
	protected ImtdRsContractDetailsFrPersistence imtdRsContractDetailsFrPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdRsDetailsLocalService.class)
	protected com.stpl.app.service.ImtdRsDetailsLocalService imtdRsDetailsLocalService;
	@BeanReference(type = ImtdRsDetailsPersistence.class)
	protected ImtdRsDetailsPersistence imtdRsDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdRsDetailsFrLocalService.class)
	protected com.stpl.app.service.ImtdRsDetailsFrLocalService imtdRsDetailsFrLocalService;
	@BeanReference(type = ImtdRsDetailsFrPersistence.class)
	protected ImtdRsDetailsFrPersistence imtdRsDetailsFrPersistence;
	@BeanReference(type = com.stpl.app.service.ImtdSalesBasisDetailsLocalService.class)
	protected com.stpl.app.service.ImtdSalesBasisDetailsLocalService imtdSalesBasisDetailsLocalService;
	@BeanReference(type = ImtdSalesBasisDetailsPersistence.class)
	protected ImtdSalesBasisDetailsPersistence imtdSalesBasisDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.InventoryWdActualMasLocalService.class)
	protected com.stpl.app.service.InventoryWdActualMasLocalService inventoryWdActualMasLocalService;
	@BeanReference(type = InventoryWdActualMasPersistence.class)
	protected InventoryWdActualMasPersistence inventoryWdActualMasPersistence;
	@BeanReference(type = com.stpl.app.service.InventoryWdProjMasLocalService.class)
	protected com.stpl.app.service.InventoryWdProjMasLocalService inventoryWdProjMasLocalService;
	@BeanReference(type = InventoryWdProjMasPersistence.class)
	protected InventoryWdProjMasPersistence inventoryWdProjMasPersistence;
	@BeanReference(type = com.stpl.app.service.ItemGroupLocalService.class)
	protected com.stpl.app.service.ItemGroupLocalService itemGroupLocalService;
	@BeanReference(type = ItemGroupPersistence.class)
	protected ItemGroupPersistence itemGroupPersistence;
	@BeanReference(type = com.stpl.app.service.ItemGroupDetailsLocalService.class)
	protected com.stpl.app.service.ItemGroupDetailsLocalService itemGroupDetailsLocalService;
	@BeanReference(type = ItemGroupDetailsPersistence.class)
	protected ItemGroupDetailsPersistence itemGroupDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ItemHierarchyDefMasterLocalService.class)
	protected com.stpl.app.service.ItemHierarchyDefMasterLocalService itemHierarchyDefMasterLocalService;
	@BeanReference(type = ItemHierarchyDefMasterPersistence.class)
	protected ItemHierarchyDefMasterPersistence itemHierarchyDefMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ItemHierarchyMasterLocalService.class)
	protected com.stpl.app.service.ItemHierarchyMasterLocalService itemHierarchyMasterLocalService;
	@BeanReference(type = ItemHierarchyMasterPersistence.class)
	protected ItemHierarchyMasterPersistence itemHierarchyMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ItemIdentifierLocalService.class)
	protected com.stpl.app.service.ItemIdentifierLocalService itemIdentifierLocalService;
	@BeanReference(type = ItemIdentifierPersistence.class)
	protected ItemIdentifierPersistence itemIdentifierPersistence;
	@BeanReference(type = com.stpl.app.service.ItemMasterLocalService.class)
	protected com.stpl.app.service.ItemMasterLocalService itemMasterLocalService;
	@BeanReference(type = ItemMasterPersistence.class)
	protected ItemMasterPersistence itemMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ItemPricingLocalService.class)
	protected com.stpl.app.service.ItemPricingLocalService itemPricingLocalService;
	@BeanReference(type = ItemPricingPersistence.class)
	protected ItemPricingPersistence itemPricingPersistence;
	@BeanReference(type = com.stpl.app.service.ItemPricingQualifierLocalService.class)
	protected com.stpl.app.service.ItemPricingQualifierLocalService itemPricingQualifierLocalService;
	@BeanReference(type = ItemPricingQualifierPersistence.class)
	protected ItemPricingQualifierPersistence itemPricingQualifierPersistence;
	@BeanReference(type = com.stpl.app.service.ItemQualifierLocalService.class)
	protected com.stpl.app.service.ItemQualifierLocalService itemQualifierLocalService;
	@BeanReference(type = ItemQualifierPersistence.class)
	protected ItemQualifierPersistence itemQualifierPersistence;
	@BeanReference(type = com.stpl.app.service.IvldActualMasterLocalService.class)
	protected com.stpl.app.service.IvldActualMasterLocalService ivldActualMasterLocalService;
	@BeanReference(type = IvldActualMasterPersistence.class)
	protected IvldActualMasterPersistence ivldActualMasterPersistence;
	@BeanReference(type = com.stpl.app.service.IvldAverageShelfLifeLocalService.class)
	protected com.stpl.app.service.IvldAverageShelfLifeLocalService ivldAverageShelfLifeLocalService;
	@BeanReference(type = IvldAverageShelfLifePersistence.class)
	protected IvldAverageShelfLifePersistence ivldAverageShelfLifePersistence;
	@BeanReference(type = com.stpl.app.service.IvldBestPriceLocalService.class)
	protected com.stpl.app.service.IvldBestPriceLocalService ivldBestPriceLocalService;
	@BeanReference(type = IvldBestPricePersistence.class)
	protected IvldBestPricePersistence ivldBestPricePersistence;
	@BeanReference(type = com.stpl.app.service.IvldCpiLocalService.class)
	protected com.stpl.app.service.IvldCpiLocalService ivldCpiLocalService;
	@BeanReference(type = IvldCpiPersistence.class)
	protected IvldCpiPersistence ivldCpiPersistence;
	@BeanReference(type = com.stpl.app.service.IvldDemandActualLocalService.class)
	protected com.stpl.app.service.IvldDemandActualLocalService ivldDemandActualLocalService;
	@BeanReference(type = IvldDemandActualPersistence.class)
	protected IvldDemandActualPersistence ivldDemandActualPersistence;
	@BeanReference(type = com.stpl.app.service.IvldDemandForecastLocalService.class)
	protected com.stpl.app.service.IvldDemandForecastLocalService ivldDemandForecastLocalService;
	@BeanReference(type = IvldDemandForecastPersistence.class)
	protected IvldDemandForecastPersistence ivldDemandForecastPersistence;
	@BeanReference(type = com.stpl.app.service.IvldForecastSalesLocalService.class)
	protected com.stpl.app.service.IvldForecastSalesLocalService ivldForecastSalesLocalService;
	@BeanReference(type = IvldForecastSalesPersistence.class)
	protected IvldForecastSalesPersistence ivldForecastSalesPersistence;
	@BeanReference(type = com.stpl.app.service.IvldFormulaDetailsLocalService.class)
	protected com.stpl.app.service.IvldFormulaDetailsLocalService ivldFormulaDetailsLocalService;
	@BeanReference(type = IvldFormulaDetailsPersistence.class)
	protected IvldFormulaDetailsPersistence ivldFormulaDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.IvldGlBalanceLocalService.class)
	protected com.stpl.app.service.IvldGlBalanceLocalService ivldGlBalanceLocalService;
	@BeanReference(type = IvldGlBalancePersistence.class)
	protected IvldGlBalancePersistence ivldGlBalancePersistence;
	@BeanReference(type = com.stpl.app.service.IvldGlCostCenterLocalService.class)
	protected com.stpl.app.service.IvldGlCostCenterLocalService ivldGlCostCenterLocalService;
	@BeanReference(type = IvldGlCostCenterPersistence.class)
	protected IvldGlCostCenterPersistence ivldGlCostCenterPersistence;
	@BeanReference(type = com.stpl.app.service.IvldInventoryWdActualMasLocalService.class)
	protected com.stpl.app.service.IvldInventoryWdActualMasLocalService ivldInventoryWdActualMasLocalService;
	@BeanReference(type = IvldInventoryWdActualMasPersistence.class)
	protected IvldInventoryWdActualMasPersistence ivldInventoryWdActualMasPersistence;
	@BeanReference(type = com.stpl.app.service.IvldInventoryWdProjMasLocalService.class)
	protected com.stpl.app.service.IvldInventoryWdProjMasLocalService ivldInventoryWdProjMasLocalService;
	@BeanReference(type = IvldInventoryWdProjMasPersistence.class)
	protected IvldInventoryWdProjMasPersistence ivldInventoryWdProjMasPersistence;
	@BeanReference(type = com.stpl.app.service.IvldItemHierarchyLocalService.class)
	protected com.stpl.app.service.IvldItemHierarchyLocalService ivldItemHierarchyLocalService;
	@BeanReference(type = IvldItemHierarchyPersistence.class)
	protected IvldItemHierarchyPersistence ivldItemHierarchyPersistence;
	@BeanReference(type = com.stpl.app.service.IvldItemHierarchyDefinitionLocalService.class)
	protected com.stpl.app.service.IvldItemHierarchyDefinitionLocalService ivldItemHierarchyDefinitionLocalService;
	@BeanReference(type = IvldItemHierarchyDefinitionPersistence.class)
	protected IvldItemHierarchyDefinitionPersistence ivldItemHierarchyDefinitionPersistence;
	@BeanReference(type = com.stpl.app.service.IvldLotMasterLocalService.class)
	protected com.stpl.app.service.IvldLotMasterLocalService ivldLotMasterLocalService;
	@BeanReference(type = IvldLotMasterPersistence.class)
	protected IvldLotMasterPersistence ivldLotMasterPersistence;
	@BeanReference(type = com.stpl.app.service.IvldMasterDataAttributeLocalService.class)
	protected com.stpl.app.service.IvldMasterDataAttributeLocalService ivldMasterDataAttributeLocalService;
	@BeanReference(type = IvldMasterDataAttributePersistence.class)
	protected IvldMasterDataAttributePersistence ivldMasterDataAttributePersistence;
	@BeanReference(type = com.stpl.app.service.IvldReturnsLocalService.class)
	protected com.stpl.app.service.IvldReturnsLocalService ivldReturnsLocalService;
	@BeanReference(type = IvldReturnsPersistence.class)
	protected IvldReturnsPersistence ivldReturnsPersistence;
	@BeanReference(type = com.stpl.app.service.IvldSalesMasterLocalService.class)
	protected com.stpl.app.service.IvldSalesMasterLocalService ivldSalesMasterLocalService;
	@BeanReference(type = IvldSalesMasterPersistence.class)
	protected IvldSalesMasterPersistence ivldSalesMasterPersistence;
	@BeanReference(type = com.stpl.app.service.LotMasterLocalService.class)
	protected com.stpl.app.service.LotMasterLocalService lotMasterLocalService;
	@BeanReference(type = LotMasterPersistence.class)
	protected LotMasterPersistence lotMasterPersistence;
	@BeanReference(type = com.stpl.app.service.MailNotificationMasterLocalService.class)
	protected com.stpl.app.service.MailNotificationMasterLocalService mailNotificationMasterLocalService;
	@BeanReference(type = MailNotificationMasterPersistence.class)
	protected MailNotificationMasterPersistence mailNotificationMasterPersistence;
	@BeanReference(type = com.stpl.app.service.MAssumptionsLocalService.class)
	protected com.stpl.app.service.MAssumptionsLocalService mAssumptionsLocalService;
	@BeanReference(type = MAssumptionsPersistence.class)
	protected MAssumptionsPersistence mAssumptionsPersistence;
	@BeanReference(type = com.stpl.app.service.MasterDataAttributeLocalService.class)
	protected com.stpl.app.service.MasterDataAttributeLocalService masterDataAttributeLocalService;
	@BeanReference(type = MasterDataAttributePersistence.class)
	protected MasterDataAttributePersistence masterDataAttributePersistence;
	@BeanReference(type = com.stpl.app.service.MasterDataFilesLocalService.class)
	protected com.stpl.app.service.MasterDataFilesLocalService masterDataFilesLocalService;
	@BeanReference(type = MasterDataFilesPersistence.class)
	protected MasterDataFilesPersistence masterDataFilesPersistence;
	@BeanReference(type = com.stpl.app.service.MedicaidNewNdcLocalService.class)
	protected com.stpl.app.service.MedicaidNewNdcLocalService medicaidNewNdcLocalService;
	@BeanReference(type = MedicaidNewNdcPersistence.class)
	protected MedicaidNewNdcPersistence medicaidNewNdcPersistence;
	@BeanReference(type = com.stpl.app.service.MedicaidUraActualsLocalService.class)
	protected com.stpl.app.service.MedicaidUraActualsLocalService medicaidUraActualsLocalService;
	@BeanReference(type = MedicaidUraActualsPersistence.class)
	protected MedicaidUraActualsPersistence medicaidUraActualsPersistence;
	@BeanReference(type = com.stpl.app.service.MedicaidUraProjLocalService.class)
	protected com.stpl.app.service.MedicaidUraProjLocalService medicaidUraProjLocalService;
	@BeanReference(type = MedicaidUraProjPersistence.class)
	protected MedicaidUraProjPersistence medicaidUraProjPersistence;
	@BeanReference(type = com.stpl.app.service.ModulePropertiesLocalService.class)
	protected com.stpl.app.service.ModulePropertiesLocalService modulePropertiesLocalService;
	@BeanReference(type = ModulePropertiesPersistence.class)
	protected ModulePropertiesPersistence modulePropertiesPersistence;
	@BeanReference(type = com.stpl.app.service.ModuleSubmoduleMasterLocalService.class)
	protected com.stpl.app.service.ModuleSubmoduleMasterLocalService moduleSubmoduleMasterLocalService;
	@BeanReference(type = ModuleSubmoduleMasterPersistence.class)
	protected ModuleSubmoduleMasterPersistence moduleSubmoduleMasterPersistence;
	@BeanReference(type = com.stpl.app.service.MParityLookupLocalService.class)
	protected com.stpl.app.service.MParityLookupLocalService mParityLookupLocalService;
	@BeanReference(type = MParityLookupPersistence.class)
	protected MParityLookupPersistence mParityLookupPersistence;
	@BeanReference(type = com.stpl.app.service.MProjectionSelectionLocalService.class)
	protected com.stpl.app.service.MProjectionSelectionLocalService mProjectionSelectionLocalService;
	@BeanReference(type = MProjectionSelectionPersistence.class)
	protected MProjectionSelectionPersistence mProjectionSelectionPersistence;
	@BeanReference(type = com.stpl.app.service.MSalesProjectionMasterLocalService.class)
	protected com.stpl.app.service.MSalesProjectionMasterLocalService mSalesProjectionMasterLocalService;
	@BeanReference(type = MSalesProjectionMasterPersistence.class)
	protected MSalesProjectionMasterPersistence mSalesProjectionMasterPersistence;
	@BeanReference(type = com.stpl.app.service.MSupplementalDiscActualsLocalService.class)
	protected com.stpl.app.service.MSupplementalDiscActualsLocalService mSupplementalDiscActualsLocalService;
	@BeanReference(type = MSupplementalDiscActualsPersistence.class)
	protected MSupplementalDiscActualsPersistence mSupplementalDiscActualsPersistence;
	@BeanReference(type = com.stpl.app.service.MSupplementalDiscMasterLocalService.class)
	protected com.stpl.app.service.MSupplementalDiscMasterLocalService mSupplementalDiscMasterLocalService;
	@BeanReference(type = MSupplementalDiscMasterPersistence.class)
	protected MSupplementalDiscMasterPersistence mSupplementalDiscMasterPersistence;
	@BeanReference(type = com.stpl.app.service.MSupplementalDiscProjLocalService.class)
	protected com.stpl.app.service.MSupplementalDiscProjLocalService mSupplementalDiscProjLocalService;
	@BeanReference(type = MSupplementalDiscProjPersistence.class)
	protected MSupplementalDiscProjPersistence mSupplementalDiscProjPersistence;
	@BeanReference(type = com.stpl.app.service.NaProjDetailsLocalService.class)
	protected com.stpl.app.service.NaProjDetailsLocalService naProjDetailsLocalService;
	@BeanReference(type = NaProjDetailsPersistence.class)
	protected NaProjDetailsPersistence naProjDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.NaProjectionSelectionLocalService.class)
	protected com.stpl.app.service.NaProjectionSelectionLocalService naProjectionSelectionLocalService;
	@BeanReference(type = NaProjectionSelectionPersistence.class)
	protected NaProjectionSelectionPersistence naProjectionSelectionPersistence;
	@BeanReference(type = com.stpl.app.service.NaProjMasterLocalService.class)
	protected com.stpl.app.service.NaProjMasterLocalService naProjMasterLocalService;
	@BeanReference(type = NaProjMasterPersistence.class)
	protected NaProjMasterPersistence naProjMasterPersistence;
	@BeanReference(type = com.stpl.app.service.NationalAssumptionsLocalService.class)
	protected com.stpl.app.service.NationalAssumptionsLocalService nationalAssumptionsLocalService;
	@BeanReference(type = NationalAssumptionsPersistence.class)
	protected NationalAssumptionsPersistence nationalAssumptionsPersistence;
	@BeanReference(type = com.stpl.app.service.NationalAssumptionsActualsLocalService.class)
	protected com.stpl.app.service.NationalAssumptionsActualsLocalService nationalAssumptionsActualsLocalService;
	@BeanReference(type = NationalAssumptionsActualsPersistence.class)
	protected NationalAssumptionsActualsPersistence nationalAssumptionsActualsPersistence;
	@BeanReference(type = com.stpl.app.service.NationalAssumptionsProjLocalService.class)
	protected com.stpl.app.service.NationalAssumptionsProjLocalService nationalAssumptionsProjLocalService;
	@BeanReference(type = NationalAssumptionsProjPersistence.class)
	protected NationalAssumptionsProjPersistence nationalAssumptionsProjPersistence;
	@BeanReference(type = com.stpl.app.service.NetSalesFormulaMasterLocalService.class)
	protected com.stpl.app.service.NetSalesFormulaMasterLocalService netSalesFormulaMasterLocalService;
	@BeanReference(type = NetSalesFormulaMasterPersistence.class)
	protected NetSalesFormulaMasterPersistence netSalesFormulaMasterPersistence;
	@BeanReference(type = com.stpl.app.service.NmActualDiscountLocalService.class)
	protected com.stpl.app.service.NmActualDiscountLocalService nmActualDiscountLocalService;
	@BeanReference(type = NmActualDiscountPersistence.class)
	protected NmActualDiscountPersistence nmActualDiscountPersistence;
	@BeanReference(type = com.stpl.app.service.NmActualPpaLocalService.class)
	protected com.stpl.app.service.NmActualPpaLocalService nmActualPpaLocalService;
	@BeanReference(type = NmActualPpaPersistence.class)
	protected NmActualPpaPersistence nmActualPpaPersistence;
	@BeanReference(type = com.stpl.app.service.NmDiscountProjectionLocalService.class)
	protected com.stpl.app.service.NmDiscountProjectionLocalService nmDiscountProjectionLocalService;
	@BeanReference(type = NmDiscountProjectionPersistence.class)
	protected NmDiscountProjectionPersistence nmDiscountProjectionPersistence;
	@BeanReference(type = com.stpl.app.service.NmDiscountProjMasterLocalService.class)
	protected com.stpl.app.service.NmDiscountProjMasterLocalService nmDiscountProjMasterLocalService;
	@BeanReference(type = NmDiscountProjMasterPersistence.class)
	protected NmDiscountProjMasterPersistence nmDiscountProjMasterPersistence;
	@BeanReference(type = com.stpl.app.service.NmPpaProjectionLocalService.class)
	protected com.stpl.app.service.NmPpaProjectionLocalService nmPpaProjectionLocalService;
	@BeanReference(type = NmPpaProjectionPersistence.class)
	protected NmPpaProjectionPersistence nmPpaProjectionPersistence;
	@BeanReference(type = com.stpl.app.service.NmPpaProjectionMasterLocalService.class)
	protected com.stpl.app.service.NmPpaProjectionMasterLocalService nmPpaProjectionMasterLocalService;
	@BeanReference(type = NmPpaProjectionMasterPersistence.class)
	protected NmPpaProjectionMasterPersistence nmPpaProjectionMasterPersistence;
	@BeanReference(type = com.stpl.app.service.NmProjectionSelectionLocalService.class)
	protected com.stpl.app.service.NmProjectionSelectionLocalService nmProjectionSelectionLocalService;
	@BeanReference(type = NmProjectionSelectionPersistence.class)
	protected NmProjectionSelectionPersistence nmProjectionSelectionPersistence;
	@BeanReference(type = com.stpl.app.service.NmSalesProjectionLocalService.class)
	protected com.stpl.app.service.NmSalesProjectionLocalService nmSalesProjectionLocalService;
	@BeanReference(type = NmSalesProjectionPersistence.class)
	protected NmSalesProjectionPersistence nmSalesProjectionPersistence;
	@BeanReference(type = com.stpl.app.service.NmSalesProjectionMasterLocalService.class)
	protected com.stpl.app.service.NmSalesProjectionMasterLocalService nmSalesProjectionMasterLocalService;
	@BeanReference(type = NmSalesProjectionMasterPersistence.class)
	protected NmSalesProjectionMasterPersistence nmSalesProjectionMasterPersistence;
	@BeanReference(type = com.stpl.app.service.PeriodLocalService.class)
	protected com.stpl.app.service.PeriodLocalService periodLocalService;
	@BeanReference(type = PeriodPersistence.class)
	protected PeriodPersistence periodPersistence;
	@BeanReference(type = com.stpl.app.service.PhsActualsLocalService.class)
	protected com.stpl.app.service.PhsActualsLocalService phsActualsLocalService;
	@BeanReference(type = PhsActualsPersistence.class)
	protected PhsActualsPersistence phsActualsPersistence;
	@BeanReference(type = com.stpl.app.service.PhsProjLocalService.class)
	protected com.stpl.app.service.PhsProjLocalService phsProjLocalService;
	@BeanReference(type = PhsProjPersistence.class)
	protected PhsProjPersistence phsProjPersistence;
	@BeanReference(type = com.stpl.app.service.PriceScheduleDetailsLocalService.class)
	protected com.stpl.app.service.PriceScheduleDetailsLocalService priceScheduleDetailsLocalService;
	@BeanReference(type = PriceScheduleDetailsPersistence.class)
	protected PriceScheduleDetailsPersistence priceScheduleDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.PriceScheduleMasterLocalService.class)
	protected com.stpl.app.service.PriceScheduleMasterLocalService priceScheduleMasterLocalService;
	@BeanReference(type = PriceScheduleMasterPersistence.class)
	protected PriceScheduleMasterPersistence priceScheduleMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ProjectionCustDetailsLocalService.class)
	protected com.stpl.app.service.ProjectionCustDetailsLocalService projectionCustDetailsLocalService;
	@BeanReference(type = ProjectionCustDetailsPersistence.class)
	protected ProjectionCustDetailsPersistence projectionCustDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ProjectionCustHierarchyLocalService.class)
	protected com.stpl.app.service.ProjectionCustHierarchyLocalService projectionCustHierarchyLocalService;
	@BeanReference(type = ProjectionCustHierarchyPersistence.class)
	protected ProjectionCustHierarchyPersistence projectionCustHierarchyPersistence;
	@BeanReference(type = com.stpl.app.service.ProjectionDetailsLocalService.class)
	protected com.stpl.app.service.ProjectionDetailsLocalService projectionDetailsLocalService;
	@BeanReference(type = ProjectionDetailsPersistence.class)
	protected ProjectionDetailsPersistence projectionDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ProjectionMasterLocalService.class)
	protected com.stpl.app.service.ProjectionMasterLocalService projectionMasterLocalService;
	@BeanReference(type = ProjectionMasterPersistence.class)
	protected ProjectionMasterPersistence projectionMasterPersistence;
	@BeanReference(type = com.stpl.app.service.ProjectionNameConfigLocalService.class)
	protected com.stpl.app.service.ProjectionNameConfigLocalService projectionNameConfigLocalService;
	@BeanReference(type = ProjectionNameConfigPersistence.class)
	protected ProjectionNameConfigPersistence projectionNameConfigPersistence;
	@BeanReference(type = com.stpl.app.service.ProjectionProdDetailsLocalService.class)
	protected com.stpl.app.service.ProjectionProdDetailsLocalService projectionProdDetailsLocalService;
	@BeanReference(type = ProjectionProdDetailsPersistence.class)
	protected ProjectionProdDetailsPersistence projectionProdDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.ProjectionProdHierarchyLocalService.class)
	protected com.stpl.app.service.ProjectionProdHierarchyLocalService projectionProdHierarchyLocalService;
	@BeanReference(type = ProjectionProdHierarchyPersistence.class)
	protected ProjectionProdHierarchyPersistence projectionProdHierarchyPersistence;
	@BeanReference(type = com.stpl.app.service.PsContractLocalService.class)
	protected com.stpl.app.service.PsContractLocalService psContractLocalService;
	@BeanReference(type = PsContractPersistence.class)
	protected PsContractPersistence psContractPersistence;
	@BeanReference(type = com.stpl.app.service.PsContractDetailsLocalService.class)
	protected com.stpl.app.service.PsContractDetailsLocalService psContractDetailsLocalService;
	@BeanReference(type = PsContractDetailsPersistence.class)
	protected PsContractDetailsPersistence psContractDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.PsDetailsLocalService.class)
	protected com.stpl.app.service.PsDetailsLocalService psDetailsLocalService;
	@BeanReference(type = PsDetailsPersistence.class)
	protected PsDetailsPersistence psDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.PsModelLocalService.class)
	protected com.stpl.app.service.PsModelLocalService psModelLocalService;
	@BeanReference(type = PsModelPersistence.class)
	protected PsModelPersistence psModelPersistence;
	@BeanReference(type = com.stpl.app.service.RebatePlanMasterLocalService.class)
	protected com.stpl.app.service.RebatePlanMasterLocalService rebatePlanMasterLocalService;
	@BeanReference(type = RebatePlanMasterPersistence.class)
	protected RebatePlanMasterPersistence rebatePlanMasterPersistence;
	@BeanReference(type = com.stpl.app.service.RebatePlanTierLocalService.class)
	protected com.stpl.app.service.RebatePlanTierLocalService rebatePlanTierLocalService;
	@BeanReference(type = RebatePlanTierPersistence.class)
	protected RebatePlanTierPersistence rebatePlanTierPersistence;
	@BeanReference(type = com.stpl.app.service.RebateTierFormulaLocalService.class)
	protected com.stpl.app.service.RebateTierFormulaLocalService rebateTierFormulaLocalService;
	@BeanReference(type = RebateTierFormulaPersistence.class)
	protected RebateTierFormulaPersistence rebateTierFormulaPersistence;
	@BeanReference(type = com.stpl.app.service.RelationshipBuilderLocalService.class)
	protected com.stpl.app.service.RelationshipBuilderLocalService relationshipBuilderLocalService;
	@BeanReference(type = RelationshipBuilderPersistence.class)
	protected RelationshipBuilderPersistence relationshipBuilderPersistence;
	@BeanReference(type = com.stpl.app.service.RelationshipLevelDefinitionLocalService.class)
	protected com.stpl.app.service.RelationshipLevelDefinitionLocalService relationshipLevelDefinitionLocalService;
	@BeanReference(type = RelationshipLevelDefinitionPersistence.class)
	protected RelationshipLevelDefinitionPersistence relationshipLevelDefinitionPersistence;
	@BeanReference(type = com.stpl.app.service.ReturnsMasterLocalService.class)
	protected com.stpl.app.service.ReturnsMasterLocalService returnsMasterLocalService;
	@BeanReference(type = ReturnsMasterPersistence.class)
	protected ReturnsMasterPersistence returnsMasterPersistence;
	@BeanReference(type = com.stpl.app.service.RsContractLocalService.class)
	protected com.stpl.app.service.RsContractLocalService rsContractLocalService;
	@BeanReference(type = RsContractPersistence.class)
	protected RsContractPersistence rsContractPersistence;
	@BeanReference(type = com.stpl.app.service.RsContractDetailsLocalService.class)
	protected com.stpl.app.service.RsContractDetailsLocalService rsContractDetailsLocalService;
	@BeanReference(type = RsContractDetailsPersistence.class)
	protected RsContractDetailsPersistence rsContractDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.RsContractDetailsFrLocalService.class)
	protected com.stpl.app.service.RsContractDetailsFrLocalService rsContractDetailsFrLocalService;
	@BeanReference(type = RsContractDetailsFrPersistence.class)
	protected RsContractDetailsFrPersistence rsContractDetailsFrPersistence;
	@BeanReference(type = com.stpl.app.service.RsDetailsLocalService.class)
	protected com.stpl.app.service.RsDetailsLocalService rsDetailsLocalService;
	@BeanReference(type = RsDetailsPersistence.class)
	protected RsDetailsPersistence rsDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.RsDetailsFrLocalService.class)
	protected com.stpl.app.service.RsDetailsFrLocalService rsDetailsFrLocalService;
	@BeanReference(type = RsDetailsFrPersistence.class)
	protected RsDetailsFrPersistence rsDetailsFrPersistence;
	@BeanReference(type = com.stpl.app.service.RsModelLocalService.class)
	protected com.stpl.app.service.RsModelLocalService rsModelLocalService;
	@BeanReference(type = RsModelPersistence.class)
	protected RsModelPersistence rsModelPersistence;
	@BeanReference(type = com.stpl.app.service.SalesBasisDetailsLocalService.class)
	protected com.stpl.app.service.SalesBasisDetailsLocalService salesBasisDetailsLocalService;
	@BeanReference(type = SalesBasisDetailsPersistence.class)
	protected SalesBasisDetailsPersistence salesBasisDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.SalesMasterLocalService.class)
	protected com.stpl.app.service.SalesMasterLocalService salesMasterLocalService;
	@BeanReference(type = SalesMasterPersistence.class)
	protected SalesMasterPersistence salesMasterPersistence;
	@BeanReference(type = com.stpl.app.service.StChActualDiscountLocalService.class)
	protected com.stpl.app.service.StChActualDiscountLocalService stChActualDiscountLocalService;
	@BeanReference(type = StChActualDiscountPersistence.class)
	protected StChActualDiscountPersistence stChActualDiscountPersistence;
	@BeanReference(type = com.stpl.app.service.StChAssumptionsLocalService.class)
	protected com.stpl.app.service.StChAssumptionsLocalService stChAssumptionsLocalService;
	@BeanReference(type = StChAssumptionsPersistence.class)
	protected StChAssumptionsPersistence stChAssumptionsPersistence;
	@BeanReference(type = com.stpl.app.service.StChDiscountProjMasterLocalService.class)
	protected com.stpl.app.service.StChDiscountProjMasterLocalService stChDiscountProjMasterLocalService;
	@BeanReference(type = StChDiscountProjMasterPersistence.class)
	protected StChDiscountProjMasterPersistence stChDiscountProjMasterPersistence;
	@BeanReference(type = com.stpl.app.service.StChProjectionDiscountLocalService.class)
	protected com.stpl.app.service.StChProjectionDiscountLocalService stChProjectionDiscountLocalService;
	@BeanReference(type = StChProjectionDiscountPersistence.class)
	protected StChProjectionDiscountPersistence stChProjectionDiscountPersistence;
	@BeanReference(type = com.stpl.app.service.StChSalesProjectionMasterLocalService.class)
	protected com.stpl.app.service.StChSalesProjectionMasterLocalService stChSalesProjectionMasterLocalService;
	@BeanReference(type = StChSalesProjectionMasterPersistence.class)
	protected StChSalesProjectionMasterPersistence stChSalesProjectionMasterPersistence;
	@BeanReference(type = com.stpl.app.service.StDeductionCalendarDetailsLocalService.class)
	protected com.stpl.app.service.StDeductionCalendarDetailsLocalService stDeductionCalendarDetailsLocalService;
	@BeanReference(type = StDeductionCalendarDetailsPersistence.class)
	protected StDeductionCalendarDetailsPersistence stDeductionCalendarDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.StFederalNewNdcLocalService.class)
	protected com.stpl.app.service.StFederalNewNdcLocalService stFederalNewNdcLocalService;
	@BeanReference(type = StFederalNewNdcPersistence.class)
	protected StFederalNewNdcPersistence stFederalNewNdcPersistence;
	@BeanReference(type = com.stpl.app.service.StMAssumptionsLocalService.class)
	protected com.stpl.app.service.StMAssumptionsLocalService stMAssumptionsLocalService;
	@BeanReference(type = StMAssumptionsPersistence.class)
	protected StMAssumptionsPersistence stMAssumptionsPersistence;
	@BeanReference(type = com.stpl.app.service.StMedicaidNewNdcLocalService.class)
	protected com.stpl.app.service.StMedicaidNewNdcLocalService stMedicaidNewNdcLocalService;
	@BeanReference(type = StMedicaidNewNdcPersistence.class)
	protected StMedicaidNewNdcPersistence stMedicaidNewNdcPersistence;
	@BeanReference(type = com.stpl.app.service.StMSupplementalDiscActualsLocalService.class)
	protected com.stpl.app.service.StMSupplementalDiscActualsLocalService stMSupplementalDiscActualsLocalService;
	@BeanReference(type = StMSupplementalDiscActualsPersistence.class)
	protected StMSupplementalDiscActualsPersistence stMSupplementalDiscActualsPersistence;
	@BeanReference(type = com.stpl.app.service.StMSupplementalDiscMasterLocalService.class)
	protected com.stpl.app.service.StMSupplementalDiscMasterLocalService stMSupplementalDiscMasterLocalService;
	@BeanReference(type = StMSupplementalDiscMasterPersistence.class)
	protected StMSupplementalDiscMasterPersistence stMSupplementalDiscMasterPersistence;
	@BeanReference(type = com.stpl.app.service.StMSupplementalDiscProjLocalService.class)
	protected com.stpl.app.service.StMSupplementalDiscProjLocalService stMSupplementalDiscProjLocalService;
	@BeanReference(type = StMSupplementalDiscProjPersistence.class)
	protected StMSupplementalDiscProjPersistence stMSupplementalDiscProjPersistence;
	@BeanReference(type = com.stpl.app.service.StNationalAssumptionsLocalService.class)
	protected com.stpl.app.service.StNationalAssumptionsLocalService stNationalAssumptionsLocalService;
	@BeanReference(type = StNationalAssumptionsPersistence.class)
	protected StNationalAssumptionsPersistence stNationalAssumptionsPersistence;
	@BeanReference(type = com.stpl.app.service.StNewNdcLocalService.class)
	protected com.stpl.app.service.StNewNdcLocalService stNewNdcLocalService;
	@BeanReference(type = StNewNdcPersistence.class)
	protected StNewNdcPersistence stNewNdcPersistence;
	@BeanReference(type = com.stpl.app.service.StNmActualDiscountLocalService.class)
	protected com.stpl.app.service.StNmActualDiscountLocalService stNmActualDiscountLocalService;
	@BeanReference(type = StNmActualDiscountPersistence.class)
	protected StNmActualDiscountPersistence stNmActualDiscountPersistence;
	@BeanReference(type = com.stpl.app.service.StNmActualPpaLocalService.class)
	protected com.stpl.app.service.StNmActualPpaLocalService stNmActualPpaLocalService;
	@BeanReference(type = StNmActualPpaPersistence.class)
	protected StNmActualPpaPersistence stNmActualPpaPersistence;
	@BeanReference(type = com.stpl.app.service.StNmAssumptionsLocalService.class)
	protected com.stpl.app.service.StNmAssumptionsLocalService stNmAssumptionsLocalService;
	@BeanReference(type = StNmAssumptionsPersistence.class)
	protected StNmAssumptionsPersistence stNmAssumptionsPersistence;
	@BeanReference(type = com.stpl.app.service.StNmDiscountProjectionLocalService.class)
	protected com.stpl.app.service.StNmDiscountProjectionLocalService stNmDiscountProjectionLocalService;
	@BeanReference(type = StNmDiscountProjectionPersistence.class)
	protected StNmDiscountProjectionPersistence stNmDiscountProjectionPersistence;
	@BeanReference(type = com.stpl.app.service.StNmDiscountProjMasterLocalService.class)
	protected com.stpl.app.service.StNmDiscountProjMasterLocalService stNmDiscountProjMasterLocalService;
	@BeanReference(type = StNmDiscountProjMasterPersistence.class)
	protected StNmDiscountProjMasterPersistence stNmDiscountProjMasterPersistence;
	@BeanReference(type = com.stpl.app.service.StNmPpaProjectionLocalService.class)
	protected com.stpl.app.service.StNmPpaProjectionLocalService stNmPpaProjectionLocalService;
	@BeanReference(type = StNmPpaProjectionPersistence.class)
	protected StNmPpaProjectionPersistence stNmPpaProjectionPersistence;
	@BeanReference(type = com.stpl.app.service.StNmPpaProjectionMasterLocalService.class)
	protected com.stpl.app.service.StNmPpaProjectionMasterLocalService stNmPpaProjectionMasterLocalService;
	@BeanReference(type = StNmPpaProjectionMasterPersistence.class)
	protected StNmPpaProjectionMasterPersistence stNmPpaProjectionMasterPersistence;
	@BeanReference(type = com.stpl.app.service.StSelectionTableLocalService.class)
	protected com.stpl.app.service.StSelectionTableLocalService stSelectionTableLocalService;
	@BeanReference(type = StSelectionTablePersistence.class)
	protected StSelectionTablePersistence stSelectionTablePersistence;
	@BeanReference(type = com.stpl.app.service.TransactionModuleDetailsLocalService.class)
	protected com.stpl.app.service.TransactionModuleDetailsLocalService transactionModuleDetailsLocalService;
	@BeanReference(type = TransactionModuleDetailsPersistence.class)
	protected TransactionModuleDetailsPersistence transactionModuleDetailsPersistence;
	@BeanReference(type = com.stpl.app.service.TransactionModuleMasterLocalService.class)
	protected com.stpl.app.service.TransactionModuleMasterLocalService transactionModuleMasterLocalService;
	@BeanReference(type = TransactionModuleMasterPersistence.class)
	protected TransactionModuleMasterPersistence transactionModuleMasterPersistence;
	@BeanReference(type = com.stpl.app.service.UdcsLocalService.class)
	protected com.stpl.app.service.UdcsLocalService udcsLocalService;
	@BeanReference(type = UdcsPersistence.class)
	protected UdcsPersistence udcsPersistence;
	@BeanReference(type = com.stpl.app.service.UsergroupBusinessroleLocalService.class)
	protected com.stpl.app.service.UsergroupBusinessroleLocalService usergroupBusinessroleLocalService;
	@BeanReference(type = UsergroupBusinessrolePersistence.class)
	protected UsergroupBusinessrolePersistence usergroupBusinessrolePersistence;
	@BeanReference(type = com.stpl.app.service.UsergroupDomainMasterLocalService.class)
	protected com.stpl.app.service.UsergroupDomainMasterLocalService usergroupDomainMasterLocalService;
	@BeanReference(type = UsergroupDomainMasterPersistence.class)
	protected UsergroupDomainMasterPersistence usergroupDomainMasterPersistence;
	@BeanReference(type = com.stpl.app.service.VwAccrualMasterLocalService.class)
	protected com.stpl.app.service.VwAccrualMasterLocalService vwAccrualMasterLocalService;
	@BeanReference(type = VwAccrualMasterPersistence.class)
	protected VwAccrualMasterPersistence vwAccrualMasterPersistence;
	@BeanReference(type = com.stpl.app.service.VwDemandForecastActualLocalService.class)
	protected com.stpl.app.service.VwDemandForecastActualLocalService vwDemandForecastActualLocalService;
	@BeanReference(type = VwDemandForecastActualPersistence.class)
	protected VwDemandForecastActualPersistence vwDemandForecastActualPersistence;
	@BeanReference(type = com.stpl.app.service.VwInventoryWdActualProjMasLocalService.class)
	protected com.stpl.app.service.VwInventoryWdActualProjMasLocalService vwInventoryWdActualProjMasLocalService;
	@BeanReference(type = VwInventoryWdActualProjMasPersistence.class)
	protected VwInventoryWdActualProjMasPersistence vwInventoryWdActualProjMasPersistence;
	@BeanReference(type = com.stpl.app.service.VwIvldDemandForecastActualLocalService.class)
	protected com.stpl.app.service.VwIvldDemandForecastActualLocalService vwIvldDemandForecastActualLocalService;
	@BeanReference(type = VwIvldDemandForecastActualPersistence.class)
	protected VwIvldDemandForecastActualPersistence vwIvldDemandForecastActualPersistence;
	@BeanReference(type = com.stpl.app.service.VwIvldInventoryWdActualProjMasLocalService.class)
	protected com.stpl.app.service.VwIvldInventoryWdActualProjMasLocalService vwIvldInventoryWdActualProjMasLocalService;
	@BeanReference(type = VwIvldInventoryWdActualProjMasPersistence.class)
	protected VwIvldInventoryWdActualProjMasPersistence vwIvldInventoryWdActualProjMasPersistence;
	@BeanReference(type = com.stpl.app.service.VwUserTablesLocalService.class)
	protected com.stpl.app.service.VwUserTablesLocalService vwUserTablesLocalService;
	@BeanReference(type = VwUserTablesPersistence.class)
	protected VwUserTablesPersistence vwUserTablesPersistence;
	@BeanReference(type = com.stpl.app.service.WfMailConfigLocalService.class)
	protected com.stpl.app.service.WfMailConfigLocalService wfMailConfigLocalService;
	@BeanReference(type = WfMailConfigPersistence.class)
	protected WfMailConfigPersistence wfMailConfigPersistence;
	@BeanReference(type = com.stpl.app.service.WorkflowMasterLocalService.class)
	protected com.stpl.app.service.WorkflowMasterLocalService workflowMasterLocalService;
	@BeanReference(type = WorkflowMasterPersistence.class)
	protected WorkflowMasterPersistence workflowMasterPersistence;
	@BeanReference(type = com.stpl.app.service.WorkflowProcessInfoLocalService.class)
	protected com.stpl.app.service.WorkflowProcessInfoLocalService workflowProcessInfoLocalService;
	@BeanReference(type = WorkflowProcessInfoPersistence.class)
	protected WorkflowProcessInfoPersistence workflowProcessInfoPersistence;
	@BeanReference(type = com.stpl.app.service.WorkflowProfileLocalService.class)
	protected com.stpl.app.service.WorkflowProfileLocalService workflowProfileLocalService;
	@BeanReference(type = WorkflowProfilePersistence.class)
	protected WorkflowProfilePersistence workflowProfilePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}