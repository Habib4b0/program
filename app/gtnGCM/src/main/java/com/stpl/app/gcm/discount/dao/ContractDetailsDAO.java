/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dao;

import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsContractDetails;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.RsModel;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author santanukumar
 */
public interface ContractDetailsDAO {

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List cfpDetailsDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Adds&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;cfpDetailsAttached&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param cfpDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    CfpContractDetails addCfpDetailsAttached(
            CfpContractDetails cfpDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Deletes&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;cfpDetailsAttached&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param cfpDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    CfpContractDetails deleteCfpDetailsAttached(
            CfpContractDetails cfpDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List cfpMasterDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Adds&nbsp;the&nbsp;cfp&nbsp;master&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;cfpMasterAttached&nbsp;the&nbsp;cfp&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param cfpMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    CfpContract addCfpMasterAttached(CfpContract cfpMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Deletes&nbsp;the&nbsp;cfp&nbsp;master&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;cfpMasterAttached&nbsp;the&nbsp;cfp&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param cfpMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    CfpContract deleteCfpMasterAttached(CfpContract cfpMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List companyFamilyplanDetailsDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List companyFamilyplanMasterDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Returns&nbsp;the&nbsp;company&nbsp;family&nbsp;plan&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;company&nbsp;familyplan&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param systemId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    CfpModel getCompanyFamilyplanMaster(int systemId) throws SystemException,
            PortalException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List ifpDetailsDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Deletes&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;ifpDetailsAttached&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param ifpDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    IfpContractDetails deleteIfpDetailsAttached(
            IfpContractDetails ifpDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Adds&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;ifpDetailsAttached&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param ifpDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    IfpContractDetails addIfpDetailsAttached(
            IfpContractDetails ifpDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List ifpMasterDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Deletes&nbsp;the&nbsp;ifp&nbsp;master&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;ifpMasterAttached&nbsp;the&nbsp;ifp&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param ifpMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    IfpContract deleteIfpMasterAttached(IfpContract ifpMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Adds&nbsp;the&nbsp;ifp&nbsp;master&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;ifpMasterAttached&nbsp;the&nbsp;ifp&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param ifpMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    IfpContract addIfpMasterAttached(IfpContract ifpMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List itemFamilyPlanDetailsDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List itemFamilyPlanMasterDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Returns&nbsp;the&nbsp;item&nbsp;family&nbsp;plan&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;item&nbsp;family&nbsp;plan&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param systemId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    IfpModel getItemFamilyPlanMaster(int systemId) throws SystemException,
            PortalException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List priceScheduleDetailsDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List priceScheduleMasterDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Returns&nbsp;the&nbsp;price&nbsp;schedule&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;price&nbsp;schedule&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param systemId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    PsModel getPriceScheduleMaster(int systemId) throws SystemException,
            PortalException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List psDetailsDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Deletes&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;psDetailsAttached&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param psDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    PsContractDetails deletePsDetailsAttached(
            PsContractDetails psDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Adds&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;psDetailsAttached&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param psDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    PsContractDetails addPsDetailsAttached(PsContractDetails psDetailsAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List psMasterDynamicQuery(DynamicQuery dynamicQuery) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Deletes&nbsp;the&nbsp;ps&nbsp;master&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;psMasterAttached&nbsp;the&nbsp;ps&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param psMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    PsContract deletePsMasterAttached(PsContract psMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Adds&nbsp;the&nbsp;ps&nbsp;master&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;psMasterAttached&nbsp;the&nbsp;ps&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param psMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    PsContract addPsMasterAttached(PsContract psMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List rebateScheduleDetailsDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List rebateScheduleMasterDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Returns&nbsp;the&nbsp;rebate&nbsp;schedule&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;rebate&nbsp;schedule&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param systemId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    RsModel getRebateScheduleMaster(int systemId) throws SystemException,
            PortalException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List rsDetailsDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Deletes&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;rsDetailsAttached&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param rsDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    RsContractDetails deleteRsDetailsAttached(
            RsContractDetails rsDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Adds&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;rsDetailsAttached&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param rsDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    RsContractDetails addRsDetailsAttached(RsContractDetails rsDetailsAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List rsMasterDynamicQuery(DynamicQuery dynamicQuery) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Deletes&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached&nbsp;from&nbsp;the&nbsp;database.<br><br>@param&nbsp;rsMasterAttached&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param rsMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    RsContract deleteRsMasterAttached(RsContract rsMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Adds&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached&nbsp;to&nbsp;the&nbsp;database.<br><br>@param&nbsp;rsMasterAttached&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param rsMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    RsContract addRsMasterAttached(RsContract rsMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;list<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    List contractMasterDynamicQuery(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Returns&nbsp;the&nbsp;contract&nbsp;master&nbsp;with&nbsp;the&nbsp;primary&nbsp;key.<br><br>@param&nbsp;systemId&nbsp;the&nbsp;system&nbsp;id<br>@return&nbsp;the&nbsp;contract&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception<br>@throws&nbsp;PortalException&nbsp;the&nbsp;portal&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param systemId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    ContractMaster getContractMaster(int systemId) throws SystemException,
            PortalException;

    /**
     * <!-- begin-UML-doc -->
     * Updates&nbsp;the&nbsp;contract&nbsp;master&nbsp;in&nbsp;the&nbsp;database&nbsp;or&nbsp;adds&nbsp;it&nbsp;if&nbsp;it&nbsp;does&nbsp;not&nbsp;yet<br>exist.<br><br>@param&nbsp;contractMaster&nbsp;the&nbsp;contract&nbsp;master<br>@return&nbsp;the&nbsp;contract&nbsp;master<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param contractMaster
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    ContractMaster updateContractMaster(ContractMaster contractMaster)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Returns&nbsp;the&nbsp;number&nbsp;of&nbsp;rows&nbsp;that&nbsp;match&nbsp;the&nbsp;dynamic&nbsp;query.<br><br>@param&nbsp;dynamicQuery&nbsp;the&nbsp;dynamic&nbsp;query<br>@return&nbsp;the&nbsp;long<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    long contractMasterDynamicQueryCount(DynamicQuery dynamicQuery)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Update&nbsp;cfp&nbsp;master&nbsp;attached.<br><br>@param&nbsp;cfpMasterAttached&nbsp;the&nbsp;cfp&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param cfpMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    CfpContract updateCfpMasterAttached(CfpContract cfpMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Update&nbsp;cfp&nbsp;details&nbsp;attached.<br><br>@param&nbsp;cfpDetailsAttached&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;cfp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param cfpDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    CfpContractDetails updateCfpDetailsAttached(
            CfpContractDetails cfpDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Update&nbsp;ifp&nbsp;master&nbsp;attached.<br><br>@param&nbsp;ifpMasterAttached&nbsp;the&nbsp;ifp&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param ifpMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    IfpContract updateIfpMasterAttached(IfpContract ifpMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Update&nbsp;ifp&nbsp;details&nbsp;attached.<br><br>@param&nbsp;ifpDetailsAttached&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ifp&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param ifpDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    IfpContractDetails updateIfpDetailsAttached(
            IfpContractDetails ifpDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Update&nbsp;ps&nbsp;master&nbsp;attached.<br><br>@param&nbsp;psMasterAttached&nbsp;the&nbsp;ps&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param psMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    PsContract updatePsMasterAttached(PsContract psMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Update&nbsp;ps&nbsp;details&nbsp;attached.<br><br>@param&nbsp;psDetailsAttached&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;ps&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param psDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    PsContractDetails updatePsDetailsAttached(
            PsContractDetails psDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Update&nbsp;rs&nbsp;master&nbsp;attached.<br><br>@param&nbsp;rsMasterAttached&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;master&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param rsMasterAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    RsContract updateRsMasterAttached(RsContract rsMasterAttached)
            throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * Update&nbsp;rs&nbsp;details&nbsp;attached.<br><br>@param&nbsp;rsDetailsAttached&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@return&nbsp;the&nbsp;rs&nbsp;details&nbsp;attached<br>@throws&nbsp;SystemException&nbsp;the&nbsp;system&nbsp;exception
     * <!-- end-UML-doc -->
     *
     * @param rsDetailsAttached
     * @return
     * @throws SystemException
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    RsContractDetails updateRsDetailsAttached(
            RsContractDetails rsDetailsAttached) throws SystemException;

    /**
     * <!-- begin-UML-doc -->
     * <!-- end-UML-doc --> @return @throws SystemException
     *
     * @generated "UML to JPA
     * (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    public int getContractMasterCount() throws SystemException;

}
