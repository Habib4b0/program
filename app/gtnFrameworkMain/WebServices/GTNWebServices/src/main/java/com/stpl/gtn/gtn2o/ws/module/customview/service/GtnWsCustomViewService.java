/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.customview.service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustViewDetails;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustViewMaster;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipLevelDefinition;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
@Service
@Scope(value = "singleton")
public class GtnWsCustomViewService {

    private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCustomViewService.class);

    @Autowired
    private GtnWsSqlService gtnWsSqlService;

    @Autowired
    private GtnFrameworkQueryGeneratorService queryGeneratorService;

    @Autowired
    private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;

    @Autowired
    private org.hibernate.SessionFactory sessionFactory;

    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

    public org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void checkCustomViewSave(GtnWsCustomViewRequest cvRequest,
            GtnWsCustomViewResponse cvResponse) throws GtnFrameworkGeneralException {
        try {
            cvResponse.setSuccess(true);
            if (cvRequest.getCvSysId() == 0 && checkDuplicateCustomViewName(cvRequest)) {
                cvResponse.setSuccess(false);
                cvResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
                cvResponse.setMessage("Entered Custom View Name already exists.");
                return;
            }
        } catch (Exception e) {
            cvResponse.setSuccess(false);
            throw new GtnFrameworkGeneralException("Exception in checkSaveRelationship", e);
        }

    }

    @SuppressWarnings("rawtypes")
    private boolean checkDuplicateCustomViewName(GtnWsCustomViewRequest cvRequest)
            throws GtnFrameworkGeneralException {
        int relationCount = 0;
        try {
            List<String> inputlist = new ArrayList<>();
            inputlist.add(cvRequest.getCustomViewName());
            List result = executeQuery(getQuery("getCustomViewNameDuplicateCheck"), inputlist);
            if (result != null && !result.isEmpty()) {
                relationCount = Integer.parseInt(String.valueOf(result.get(0)));
            }
        } catch (Exception e) {
            throw new GtnFrameworkGeneralException("Exception in checkDuplicateRelationshipName", e);
        }
        return relationCount != 0;
    }

    @SuppressWarnings("rawtypes")
    public boolean saveCustomView(GtnWsCustomViewRequest cvRequest)
            throws GtnFrameworkGeneralException {
        int customViewMasterSid = saveCustViewMaster(cvRequest);
        saveCustomViewDetails(customViewMasterSid, cvRequest);
        customViewSaveLogicCCPDetails(customViewMasterSid, cvRequest);
        return true;
    }

    private int saveCustViewMaster(GtnWsCustomViewRequest cvRequest) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int customViewMasterSid = 0;
        try {
            if (cvRequest.getCvSysId() == 0) {
                CustViewMaster master = new CustViewMaster();
                master.setCustViewName(cvRequest.getCustomViewName());
                master.setCustViewDescription(cvRequest.getCustomViewDescription());
                master.setCustViewType(cvRequest.getCustomViewType());
                master.setCustomerRelationshipSid(cvRequest.getCustomerRelationshipSid());
                master.setProductRelationshipSid(cvRequest.getProductRelationshipSid());
                master.setCreatedBy(Integer.parseInt(cvRequest.getCreatedBy()));
                master.setCreatedDate(cvRequest.getCreatedDate());
                master.setModifiedBy(Integer.parseInt(cvRequest.getModifiedBy()));
                master.setModifiedDate(cvRequest.getModifiedDate());
                master.setScreenName(cvRequest.getCustomViewType());
                customViewMasterSid = (int) session.save(master);
            } else {
                CustViewMaster master = session.get(CustViewMaster.class, cvRequest.getCvSysId());
                master.setCustViewName(cvRequest.getCustomViewName());
                master.setCustViewDescription(cvRequest.getCustomViewDescription());
                master.setCustViewType(cvRequest.getCustomViewType());
                master.setCustomerRelationshipSid(cvRequest.getCustomerRelationshipSid());
                master.setProductRelationshipSid(cvRequest.getProductRelationshipSid());
                master.setModifiedBy(Integer.parseInt(cvRequest.getModifiedBy()));
                master.setModifiedDate(cvRequest.getModifiedDate());
                master.setScreenName(cvRequest.getCustomViewType());
                session.update(master);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return customViewMasterSid;
    }

    @SuppressWarnings("rawtypes")
    public List executeQuery(String sqlQuery, List paramList) throws GtnFrameworkGeneralException {
        return gtnSqlQueryEngine.executeSelectQuery(sqlQuery, paramList);
    }

    public String getQuery(String sqlId) {
        return gtnWsSqlService.getQuery(sqlId);
    }

    private void saveCustomViewDetails(int customViewMasterSid, GtnWsCustomViewRequest cvRequest) {
        List<GtnWsRecordBean> cvTreeNodeList=cvRequest.getCvTreeNodeList();
        int masterSid=cvRequest.getCvSysId();
        Session session = getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            if (masterSid == 0) {
                saveCustViewDetailsRecords(cvTreeNodeList, customViewMasterSid, session);
            } else {
                String hql = "delete from CustViewDetails where customViewMasterSid= :classId";
                session.createQuery(hql).setString("classId", String.valueOf(masterSid)).executeUpdate();
                saveCustViewDetailsRecords(cvTreeNodeList, cvRequest.getCvSysId(), session);
            }
            if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
                tx.commit();
            }
        } catch (Exception e) {
            tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    private void saveCustViewDetailsRecords(List<GtnWsRecordBean> cvTreeNodeList, int customViewMasterSid, Session session) {
        CustViewDetails details;
        int i = 1;
        for (Object ob : cvTreeNodeList) {
            GtnWsRecordBean dto = (GtnWsRecordBean) ob;
            details = new CustViewDetails();
            details.setCustomViewMasterSid(customViewMasterSid);
            details.setHierarchyId((Integer) dto.getAdditionalPropertyByIndex(3));
            String indicator = String.valueOf(dto.getAdditionalPropertyByIndex(2));
            details.setHierarchyIndicator(indicator.charAt(0));
            details.setLevelNo(i);
            i++;
            session.save(details);
        }
    }

    private void customViewSaveLogicCCPDetails(int customViewMasterSid, GtnWsCustomViewRequest cvRequest) {
         try {
        GtnForecastHierarchyInputBean inputBean =new GtnForecastHierarchyInputBean();
        inputBean.setSelectedCustomerRelationShipBuilderSid(cvRequest.getCustomerRelationshipSid());
        inputBean.setSelectedProductRelationShipBuilderSid(cvRequest.getProductRelationshipSid());
        
        RelationshipBuilder customerRb = getSessionFactory().openSession().get(RelationshipBuilder.class, inputBean.getSelectedCustomerRelationShipBuilderSid());
        inputBean.setSelectedCustomerRelationShipBuilderVersionNo(customerRb.getVersionNo());
        inputBean.setSelectedCustomerHierarcySid(customerRb.getHierarchyDefinition().getHierarchyDefinitionSid());
        inputBean.setSelectedCustomerHierarchyVersionNo(customerRb.getHierarchyVersion());
        
        RelationshipBuilder productRb = getSessionFactory().openSession().get(RelationshipBuilder.class, inputBean.getSelectedProductRelationShipBuilderSid());
        inputBean.setSelectedProductRelationShipBuilderVersionNo(productRb.getVersionNo());
        inputBean.setSelectedProductHierarcySid(productRb.getHierarchyDefinition().getHierarchyDefinitionSid());
        inputBean.setSelectedProductHierarchyVersionNo(productRb.getHierarchyVersion());
        int masterSid=cvRequest.getCvSysId();
        customViewMasterSid = (masterSid == 0) ? cvRequest.getCvSysId() : masterSid;
        insertToCCPTable(inputBean,customViewMasterSid);
        } catch (GtnFrameworkGeneralException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void insertToCCPTable(GtnForecastHierarchyInputBean inputBean,int customViewMasterSid) throws GtnFrameworkGeneralException {
        try {
            List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList = relationUpdateService
                    .getHierarchyBuilder(inputBean.getSelectedCustomerHierarcySid(),
                            inputBean.getSelectedCustomerHierarchyVersionNo());
            List<HierarchyLevelDefinitionBean> productHierarchyLevelDefinitionList = relationUpdateService
                    .getHierarchyBuilder(inputBean.getSelectedProductHierarcySid(),
                            inputBean.getSelectedProductHierarchyVersionNo());
            String customerHierarchyQuery = getCustomerAndContractHierarchyQuery(
                    customerHierarchyLevelDefinitionList,inputBean.getSelectedCustomerRelationShipBuilderVersionNo(),inputBean.getSelectedCustomerRelationShipBuilderSid(), false);
            String productHierarchyQuery = getCustomerAndContractHierarchyQuery(
                    productHierarchyLevelDefinitionList,inputBean.getSelectedProductRelationShipBuilderVersionNo(),inputBean.getSelectedProductRelationShipBuilderSid(), true);
            List<String> input = new ArrayList<>();
            input.add(customerHierarchyQuery);
            input.add(productHierarchyQuery);
            input.add(String.valueOf(inputBean.getSelectedCustomerRelationShipBuilderSid()));
            input.add(String.valueOf(inputBean.getSelectedProductRelationShipBuilderSid()));
            input.add(String.valueOf(customViewMasterSid));
            input.add(String.valueOf(inputBean.getSelectedCustomerRelationShipBuilderVersionNo()));
            input.add(String.valueOf(inputBean.getSelectedProductRelationShipBuilderVersionNo()));
            String withTableNameQuery = gtnWsSqlService.getQuery(input, "ccpInsertQueryCustom");
            gtnSqlQueryEngine.executeInsertOrUpdateQuery(withTableNameQuery);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private String getCustomerAndContractHierarchyQuery(
            List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
            int relationVersionNo,int relationSid, boolean isProduct) {

        List<Object> input = new ArrayList<>();

        HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
                .getLastLinkedLevel(hierarchyLevelDefinitionList);
        input.add(relationVersionNo);
        input.add(lastLevelDto.getLevelNo());
        input.add(relationSid);

        String beanName = isProduct ? "CCP_INSERT_PRODUCT" : "CCP_INSERT_CUSTOMER";
        GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getQuerybySituationNameAndLevel(lastLevelDto,
                beanName, hierarchyLevelDefinitionList);

        return gtnWsSqlService.getReplacedQuery(input, queryBean.generateQuery());
    }
    
    public List<GtnWsRecordBean> getSavedTreeData(GtnWsCustomViewRequest cvRequest) {
        Session session = getSessionFactory().openSession();
        Criteria selectCriteria = session.createCriteria(CustViewDetails.class);
        selectCriteria.add(Restrictions.eq("customViewMasterSid", cvRequest.getCvSysId()));
        List<CustViewDetails> gtnListOfData = selectCriteria.list();
        
        List<GtnWsRecordBean> recordTreeData = new ArrayList<>();
        GtnWsRecordBean gtnWsRecordBean;
        for (CustViewDetails detailsData : gtnListOfData) {
            gtnWsRecordBean = new GtnWsRecordBean();
            gtnWsRecordBean.addAdditionalProperty(detailsData.getLevelNo());
            gtnWsRecordBean.addAdditionalProperty(detailsData.getLevelNo());
            gtnWsRecordBean.addAdditionalProperty(detailsData.getHierarchyIndicator());
            gtnWsRecordBean.addAdditionalProperty(detailsData.getHierarchyId());
            Criteria relationCriteria = session.createCriteria(RelationshipLevelDefinition.class);
            relationCriteria.add(Restrictions.eq("hierarchyLevelDefinition.hierarchyLevelDefinitionSid", detailsData.getHierarchyId()));
            List<RelationshipLevelDefinition> relationData = relationCriteria.list();
            gtnWsRecordBean.addProperties(relationData.get(0).getLevelName());
            gtnWsRecordBean.setRecordHeader(Arrays.asList("levelName"));
            
            recordTreeData.add(gtnWsRecordBean);
        }
        return recordTreeData;
    }

}
